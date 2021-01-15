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
import peppol.bis.invoice3.domain.ID;
import peppol.bis.invoice3.domain.InvoiceLineAllowanceCharge;
import peppol.bis.invoice3.domain.Amount;
import peppol.bis.invoice3.domain.ClassifiedTaxCategory;
import peppol.bis.invoice3.domain.DocumentReference;
import peppol.bis.invoice3.domain.InvoiceLine;
import peppol.bis.invoice3.domain.InvoicePeriod;
import peppol.bis.invoice3.domain.InvoicedQuantity;
import peppol.bis.invoice3.domain.Item;
import peppol.bis.invoice3.domain.LineExtensionAmount;
import peppol.bis.invoice3.domain.OrderLineReference;
import peppol.bis.invoice3.domain.Price;
import peppol.bis.invoice3.domain.PriceAmount;
import peppol.bis.invoice3.domain.TaxScheme;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class InvoiceLineTest {

    private InvoiceLine invoiceLine;

    @BeforeEach
    void setUp() {
        invoiceLine = new InvoiceLine(
            "2"
            , new InvoicedQuantity("1", "STK")
            , new LineExtensionAmount("1272", "EUR")
            , new Item("Power cord", new ClassifiedTaxCategory("S", new TaxScheme("VAT")))
            , new Price(new PriceAmount("1233", "EUR"))
        );
    }

    @Test
    void InvoiceLine_to_xml_basic_elements() {
        final Element element = (Element) invoiceLine.node();
        assertElementNameIs(element, "InvoiceLine", CAC_NS);
        assertRequiredElement(element, "ID", equalTo("2"));

        assertRequiredElement(element, "InvoicedQuantity");
        assertRequiredElement(element, "LineExtensionAmount");
        assertRequiredElement(element, "Item");
        assertRequiredElement(element, "Price");

        assertUnsetOptionalElement(element, "Note");
        assertUnsetOptionalElement(element, "AccountingCost");
        assertUnsetOptionalElement(element, "InvoicePeriod");
        assertUnsetOptionalElement(element, "OrderLineReference");
        assertUnsetOptionalElement(element, "AllowanceCharge");
    }

    @Test
    void InvoiceLine_to_xml_optional_elements() {
        invoiceLine
            .withNote("Note goes here")
            .withAccountingCost("1287:65464")
            .withInvoicePeriod(new InvoicePeriod("2020-11-11", "2020-12-12"))
            .withOrderLineReference(new OrderLineReference("id12"))
            .withDocumentReference(new DocumentReference(new ID("doc12"), "130"))
            ;

        final Element element = (Element) invoiceLine.node();
        assertRequiredElement(element, "Note", equalTo("Note goes here"));
        assertRequiredElement(element, "AccountingCost", equalTo("1287:65464"));

        assertRequiredElement(element, "InvoicePeriod");
        assertRequiredElement(element, "OrderLineReference");
        assertRequiredElement(element, "DocumentReference");
    }

    @Test
    void InvoiceLine_to_xml_allowance_charge() {
        invoiceLine
            .withAllowanceCharge(new InvoiceLineAllowanceCharge(true, new Amount("211", "EUR")))
            .withAllowanceCharge(new InvoiceLineAllowanceCharge(true, new Amount("212", "EUR")))
            .withAllowanceCharge(new InvoiceLineAllowanceCharge(true, new Amount("213", "EUR")))
        ;

        final Element element = (Element) invoiceLine.node();

        assertThat(element.find("AllowanceCharge").size(), equalTo(3));
    }

}
