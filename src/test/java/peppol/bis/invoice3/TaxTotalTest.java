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
import peppol.bis.invoice3.domain.TaxAmount;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxScheme;
import peppol.bis.invoice3.domain.TaxSubtotal;
import peppol.bis.invoice3.domain.TaxTotal;
import peppol.bis.invoice3.domain.TaxableAmount;

import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class TaxTotalTest  {

    private TaxTotal taxTotal;

    @BeforeEach
    void setUp() {
        taxTotal = new TaxTotal(
            new TaxAmount("1233", "EUR")
        );
    }

    @Test
    void to_xml_required_elements() {
        final Element element = (Element) taxTotal.node();
        assertElementNameIs(element, "TaxTotal", CAC_NS);

        assertRequiredElement(element, "TaxAmount");

        assertUnsetOptionalElement(element, "TaxSubtotal");
    }

    @Test
    void to_xml_optional_elements() {
        taxTotal
            .withTaxSubtotal(new TaxSubtotal(new TaxableAmount("123", "EUR"), new TaxAmount("124", "EUR"), new TaxCategory("g", new TaxScheme("VAT"))));

        final Element element = (Element) taxTotal.node();

        assertRequiredElement(element, "TaxSubtotal");
    }
}
