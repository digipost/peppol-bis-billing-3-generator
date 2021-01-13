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
import peppol.bis.invoice3.domain.CompanyID;
import peppol.bis.invoice3.domain.PartyIdentification;
import peppol.bis.invoice3.domain.PartyName;
import peppol.bis.invoice3.domain.PayeeParty;
import peppol.bis.invoice3.domain.PayeePartyPartyLegalEntity;

import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class PayeePartyTest {

    private PayeeParty payeeParty;

    @BeforeEach
    void setUp() {
        payeeParty = new PayeeParty(
            new PartyName("")
        );
    }

    @Test
    void to_xml_required_elements() {
        final Element element = (Element) payeeParty.node();
        assertElementNameIs(element, "PayeeParty", CAC_NS);

        assertRequiredElement(element, "PartyName");

        assertUnsetOptionalElement(element, "PartyLegalEntity");
        assertUnsetOptionalElement(element, "PartyIdentification");
    }

    @Test
    void to_xml_optional_elements() {
        payeeParty
            .withPartyLegalEntity(new PayeePartyPartyLegalEntity(new CompanyID("")))
            .withPartyIdentification(new PartyIdentification(""));

        final Element element = (Element) payeeParty.node();

        assertRequiredElement(element, "PartyLegalEntity");
        assertRequiredElement(element, "PartyIdentification");
    }
}
