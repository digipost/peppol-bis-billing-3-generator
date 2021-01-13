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
import peppol.bis.invoice3.domain.Contact;
import peppol.bis.invoice3.domain.Country;
import peppol.bis.invoice3.domain.EndpointID;
import peppol.bis.invoice3.domain.Party;
import peppol.bis.invoice3.domain.PartyIdentification;
import peppol.bis.invoice3.domain.PartyLegalEntity;
import peppol.bis.invoice3.domain.PartyName;
import peppol.bis.invoice3.domain.PartyTaxScheme;
import peppol.bis.invoice3.domain.PostalAddress;
import peppol.bis.invoice3.domain.TaxScheme;

import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class PartyTest {

    private Party party;

    @BeforeEach
    void setUp() {
        party = new Party(
            new EndpointID("")
            , new PostalAddress(new Country("NO"))
            , new PartyLegalEntity("")
        );
    }

    @Test
    void to_xml_required_elements() {
        final Element element = (Element) party.node();
        assertElementNameIs(element, "Party", CAC_NS);

        assertRequiredElement(element, "EndpointID");
        assertRequiredElement(element, "PostalAddress");
        assertRequiredElement(element, "PartyLegalEntity");

        assertUnsetOptionalElement(element, "Contact");
        assertUnsetOptionalElement(element, "PartyTaxScheme");
        assertUnsetOptionalElement(element, "PartyName");
        assertUnsetOptionalElement(element, "PartyIdentification");
    }

    @Test
    void to_xml_optional_elements() {
        party
            .withContact(new Contact())
            .withPartyName(new PartyName(""))
            .withPartyTaxScheme(new PartyTaxScheme("", new TaxScheme("")))
            .withPartyIdentification(new PartyIdentification(""))
            ;

        final Element element = (Element) party.node();

        assertRequiredElement(element, "Contact");
        assertRequiredElement(element, "PartyName");
        assertRequiredElement(element, "PartyTaxScheme");
        assertRequiredElement(element, "PartyIdentification");
    }
}
