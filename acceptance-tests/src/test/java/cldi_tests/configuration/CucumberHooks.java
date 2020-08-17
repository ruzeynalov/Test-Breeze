/*
 * Copyright 2002 - 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cldi_tests.configuration;

import com.cerner.pophealth.cldi.qa.common.utils.TestStatusText;
import com.cerner.pophealth.cldi.qa.common.utils.session.Key;
import com.cerner.pophealth.cldi.qa.common.utils.session.Session;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Scenario;
import cucumber.api.TestCase;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class CucumberHooks {
    int currentStepDefIndex;
    private static final TestStatusText testStatusText = new TestStatusText();
    private static int failedTests = 0;
    private static int passedTests = 0;
    private static int count = 0;

    
    @BeforeStep
     public void logCurrentStepInfo(Scenario scenario) throws Exception {

         Field f = scenario.getClass().getDeclaredField("testCase");
         f.setAccessible(true);
         TestCase r = (TestCase) f.get(scenario);

         List<PickleStepTestStep> stepDefs = r.getTestSteps()
                 .stream()
                 .filter(x -> x instanceof PickleStepTestStep)
                 .map(x -> (PickleStepTestStep) x)
                 .collect(Collectors.toList());

         PickleStepTestStep currentStepDef = stepDefs
                 .get(currentStepDefIndex);
         log.info("Current step is " + currentStepDef.getStepText());

     }

     @AfterStep
     public void incrementStep(Scenario scenario) {
         currentStepDefIndex += 1;
     }

    @Before
    public void setScenarioInfoIntoLog(Scenario scenario) {
        log.info("Scenario " + scenario.getName());
        log.info(testStatusText.getTEXT_FOR_START());
        setIdIntoLog(scenario);
        setScenarioName(scenario);
    }

    @After
    public void logCountOfTest(Scenario scenario) {
        count++;
        if (scenario.isFailed()) {
            failedTests++;
        } else {
            passedTests++;
        }
        log.info("There are {} tests completed.", count);
        Session.getCurrentSession().put(Key.Keys.COUNT, String.valueOf(count));
        log.info("Status of last test is {}", scenario.getStatus());
        log.info("Passed tests: {}, Failed test: {}", passedTests, failedTests);
        Session.getCurrentSession().put(Key.Keys.PASSED_TESTS, String.valueOf(passedTests));
        Session.getCurrentSession().put(Key.Keys.FAILED_TESTS, String.valueOf(failedTests));
        if (scenario.isFailed()) {
            log.info(testStatusText.getTEXT_FOR_FAILED_TEST());
        } else {
            log.info(testStatusText.getTEXT_FOR_PASSED_TEST());
        }
    }

    public void setScenarioName(Scenario scenario) {
        MDC.put("scenarioName", scenario.getName());
    }

    public void setIdIntoLog(Scenario scenario) {
        final Pattern testCaseIdPattern = Pattern.compile("@TestCaseId\\(\"+?([^\"]+)\"+?\\)");
        for (String tag : scenario.getSourceTagNames()) {
            Matcher matcher = testCaseIdPattern.matcher(tag);
            if (matcher.matches()) {
                final String testCaseId = matcher.group(1);
                MDC.put("testCaseId", testCaseId);
            }
        }
    }
}
