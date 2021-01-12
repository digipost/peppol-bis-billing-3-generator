/**
 * Copyright (C) Posten Norge AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package peppol.bis.invoice3;

import org.eaxy.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxScheme;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TaxCategoryToXmlTest {

    private TaxCategory taxCategory;

    @BeforeEach
    void setUp() {

        taxCategory = new TaxCategory(
            "S", new TaxScheme("VAT")
        );

    }

    @Test
    void TaxCategory_to_xml_basic_elements() {

        final Element element = (Element) taxCategory.node();

        assertThat(element.getName().getName(), equalTo("TaxCategory"));

        assertThat(element.find("ID").first().text(), equalTo("S"));
        assertThat(element.find("TaxScheme").first().text(), equalTo("VAT"));
    }

    @Test
    void TaxCategory_to_xml_optional_elements() {
        taxCategory
            .withPercent("25")
            .withTaxExemptionReason("Exempt New Means of Transport")
            .withTaxExemptionReasonCode("VATEX-EU-G");

        final Element element = (Element) taxCategory.node();

        assertThat(element.find("Percent").first().text(), equalTo("25"));
        assertThat(element.find("TaxExemptionReason").first().text(), equalTo("Exempt New Means of Transport"));
        assertThat(element.find("TaxExemptionReasonCode").first().text(), equalTo("VATEX-EU-G"));
    }

}
