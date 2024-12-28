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
package work.rustam.common.services.ui.drivers;

import com.codeborne.selenide.Configuration;

public class DriverManager {

    public static void initBrowser(String browser){
        switch (browser){
            case "Chrome":
                initChrome();
                break;
            case "HtmlUnit":
                initHtmlUnit();
                break;
            default:
                initHeadlessChrome();
                break;
        }
        Configuration.timeout = 20000;
    }

    private static void initHeadlessChrome(){
        Configuration.browser = "work.rustam.common.services.ui.drivers.HeadlessChromeDriver";
    }

    private static void initHtmlUnit(){
        Configuration.browser = "work.rustam.common.services.ui.drivers.HtmlUnitDriver";
    }

    private static void initChrome(){
        Configuration.browser = "work.rustam.common.services.ui.drivers.ChromeDriver";
    }
}
