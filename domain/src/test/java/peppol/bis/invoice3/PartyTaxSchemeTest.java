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
import peppol.bis.invoice3.domain.PartyTaxScheme;
import peppol.bis.invoice3.domain.TaxAmount;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxScheme;
import peppol.bis.invoice3.domain.TaxSubtotal;
import peppol.bis.invoice3.domain.TaxableAmount;

import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class PartyTaxSchemeTest {

    private PartyTaxScheme partyTaxScheme;

    @BeforeEach
    void setUp() {
        partyTaxScheme = new PartyTaxScheme(
            "FR932874294", new TaxScheme("VAT")
        );
    }

    @Test
    void to_xml_required_elements() {
        final Element element = (Element) partyTaxScheme.node();
        assertElementNameIs(element, "PartyTaxScheme", CAC_NS);

        assertRequiredElement(element, "CompanyID", equalTo("FR932874294"));
        assertRequiredElement(element, "TaxScheme");
    }

}
