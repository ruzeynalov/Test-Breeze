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
package work.rustam.common.services.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import org.springframework.stereotype.Service;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

@Service
public class GoogleResultsPage{
	public ElementsCollection results() {
		return $$("#search  h3");
	}

	public GoogleResultsPage search(String query)
		{
		$(By.name("q")).setValue(query).pressEnter();
		return page(GoogleResultsPage.class);
	}

	public ElementsCollection navigationLabels() {
		return $$(By.xpath("/descendant::h3[1]"));
	}
}

