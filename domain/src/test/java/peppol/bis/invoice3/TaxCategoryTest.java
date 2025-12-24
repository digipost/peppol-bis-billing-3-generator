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
import peppol.bis.invoice3.domain.codes.TaxCategoryIdentifier;
import peppol.bis.invoice3.domain.codes.TaxExemptionReasonCode;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class TaxCategoryTest  {

    private TaxCategory taxCategory;

    @BeforeEach
    void setUp() {
        taxCategory = new TaxCategory(
            TaxCategoryIdentifier.S, new TaxScheme("VAT")
        );
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) taxCategory.node();

        assertElementNameIs(element, "TaxCategory", CAC_NS);
        assertRequiredElement(element, "ID");
        assertRequiredElement(element, "TaxScheme");

        assertUnsetOptionalElement(element, "Percent");
        assertUnsetOptionalElement(element, "TaxExemptionReason");
        assertUnsetOptionalElement(element, "TaxExemptionReasonCode");

        assertRequiredElement(element, "ID", equalTo("S"));
    }

    @Test
    void to_xml_optional_elements() {
        taxCategory
            .withPercent("25")
            .withTaxExemptionReason("Exempt New Means of Transport")
            .withTaxExemptionReasonCode(TaxExemptionReasonCode.VATEX_EU_G);

        final Element element = (Element) taxCategory.node();

        assertRequiredElement(element, "Percent", equalTo("25"));
        assertRequiredElement(element, "TaxExemptionReason", equalTo("Exempt New Means of Transport"));
        assertRequiredElement(element, "TaxExemptionReasonCode", equalTo("VATEX-EU-G"));
    }

    @Test
    void withTaxExemptionReasonCodeAsStringValid() {
        taxCategory
            .withPercent("25")
            .withTaxExemptionReason("Exempt New Means of Transport")
            .withTaxExemptionReasonCode("VATEX-EU-G");

        final Element element = (Element) taxCategory.node();

        assertRequiredElement(element, "Percent", equalTo("25"));
        assertRequiredElement(element, "TaxExemptionReason", equalTo("Exempt New Means of Transport"));
        assertRequiredElement(element, "TaxExemptionReasonCode", equalTo("VATEX-EU-G"));
    }

    @Test
    void withTaxExemptionReasonCodeAsStringInalid() {
        assertThrows(IllegalArgumentException.class, ()-> taxCategory.withTaxExemptionReasonCode("DUMMY_CODE"));
    }

}
