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
import peppol.bis.invoice3.domain.AccountingCustomerParty;
import peppol.bis.invoice3.domain.AccountingSupplierParty;
import peppol.bis.invoice3.domain.Country;
import peppol.bis.invoice3.domain.EndpointID;
import peppol.bis.invoice3.domain.Party;
import peppol.bis.invoice3.domain.PartyLegalEntity;
import peppol.bis.invoice3.domain.PostalAddress;

import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

class AccountingCustomerPartyTest {

    private AccountingCustomerParty accountingSupplierParty;

    @BeforeEach
    void setUp() {
        accountingSupplierParty = new AccountingCustomerParty(
            new Party(
                new EndpointID("")
                , new PostalAddress(new Country("NO"))
                , new PartyLegalEntity("")
            )
        );
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) accountingSupplierParty.node();
        assertElementNameIs(element, "AccountingCustomerParty", CAC_NS);

        assertRequiredElement(element, "Party");
    }
}
