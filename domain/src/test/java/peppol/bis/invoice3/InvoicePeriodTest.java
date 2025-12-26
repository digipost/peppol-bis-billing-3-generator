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
import peppol.bis.invoice3.domain.BaseQuantity;
import peppol.bis.invoice3.domain.InvoicePeriod;
import peppol.bis.invoice3.domain.codes.VatDateCode;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

class InvoicePeriodTest {

    private InvoicePeriod invoicePeriod;

    @BeforeEach
    void setUp() {
        invoicePeriod = new InvoicePeriod("2020-11-11", "2020-12-12");
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) invoicePeriod.node();
        assertElementNameIs(element, "InvoicePeriod", CAC_NS);

        assertRequiredElement(element, "StartDate", equalTo("2020-11-11"));
        assertRequiredElement(element, "EndDate", equalTo("2020-12-12"));

        assertUnsetOptionalElement(element, "DescriptionCode");
    }

    @Test
    void to_xml_optional_elements() {
        invoicePeriod.withDescriptionCode(VatDateCode.VDC_35);
        final Element element = (Element) invoicePeriod.node();

        assertRequiredElement(element, "DescriptionCode", equalTo("35"));
    }

    @Test
    void to_xml_optional_elementsDate_code_as_string() {
        invoicePeriod.withDescriptionCode("35");
        final Element element = (Element) invoicePeriod.node();

        assertRequiredElement(element, "DescriptionCode", equalTo("35"));
        assertThrows(IllegalArgumentException.class, () -> invoicePeriod.withDescriptionCode("DUMMY"));
    }

}
