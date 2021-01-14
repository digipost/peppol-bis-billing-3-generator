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
import peppol.bis.invoice3.domain.BaseAmount;
import peppol.bis.invoice3.domain.BaseQuantity;
import peppol.bis.invoice3.domain.Contact;
import peppol.bis.invoice3.domain.PriceAllowanceCharge;
import peppol.bis.invoice3.domain.PriceAmount;

import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class ContactTest {

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact();
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) contact.node();
        assertElementNameIs(element, "Contact", CAC_NS);

        assertUnsetOptionalElement(element, "Name");
        assertUnsetOptionalElement(element, "Telephone");
        assertUnsetOptionalElement(element, "ElectronicMail");
    }

    @Test
    void to_xml_optional_elements() {
        contact
            .withName("xyz123")
            .withTelephone("887 654 321")
            .withelectronicMail("test.name@foo.bar");

        final Element element = (Element) contact.node();

        assertRequiredElement(element, "Name", equalTo("xyz123"));
        assertRequiredElement(element, "Telephone", equalTo("887 654 321"));
        assertRequiredElement(element, "ElectronicMail", equalTo("test.name@foo.bar"));
    }

}
