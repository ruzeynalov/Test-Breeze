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
package skeleton_tests;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.isIE;

@CucumberOptions(
		glue = {"skeleton_tests.stepsDef", "skeleton_tests.configuration"},
		plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm","json:target/cucumber-report.json"})
public class RunAcceptanceTests extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@AfterClass
	public void ieRelax() {
		if (isIE()) {
			closeWebDriver();
			sleep(500);
		}
	}

	@AfterSuite
	public void teardown() {
		closeWebDriver();
		System.out.println("\n\n\n\n\n\n ***************************************Test Suite Completed!!!!******************************************");
	}
}
