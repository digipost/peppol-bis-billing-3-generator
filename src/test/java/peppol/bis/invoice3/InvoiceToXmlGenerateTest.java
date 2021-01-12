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
import org.eaxy.NonMatchingPathException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.Amount;
import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.domain.LegalMonetaryTotal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvoiceToXmlGenerateTest {

    private Invoice invoice;

    @BeforeEach
    void setUp() {
        invoice = new Invoice(
            "33445566"
            , "2017-11-01"
            , "EUR"
            , null
            , null
            , null
            , new LegalMonetaryTotal(new Amount("1273", "EUR"), null, null, null)
            , null
        );
    }

    @Test
    void invoice_to_xml_for_basic_elements() {
        final Element xml = InvoiceApi.from(invoice).process().log().xml();

        assertThat(xml.find("CustomizationID").first().text(), equalTo("urn:cen.eu:en16931:2017#compliant#urn:fdc:peppol.eu:2017:poacc:billing:3.0"));
        assertThat(xml.find("ProfileID").first().text(), equalTo("urn:fdc:peppol.eu:2017:poacc:billing:01:1.0"));
        assertThat(xml.find("ID").first().text(), equalTo("33445566"));
        assertThat(xml.find("IssueDate").first().text(), equalTo("2017-11-01"));
        assertThat(xml.find("InvoiceTypeCode").first().text(), equalTo("380"));
        assertThat(xml.find("DocumentCurrencyCode").first().text(), equalTo("EUR"));

        /*
          All these are 0..1 or 0..n cardinality, and we assert here for their non-precence
          so that other tests do not need to.
         */
        assertThrows(NonMatchingPathException.class, () -> xml.find("DueDate").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("Note").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("TaxPointDate").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("TaxCurrencyCode").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("AccountingCost").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("BuyerReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("InvoicePeriod").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("OrderReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("BillingReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("DespatchDocumentReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("ReceiptDocumentReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("OriginatorDocumentReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("ContractDocumentReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("AdditionalDocumentReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("ProjectReference").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("PayeeParty").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("TaxRepresentativeParty").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("Delivery").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("PaymentMeans").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("PaymentTerms").check());
        assertThrows(NonMatchingPathException.class, () -> xml.find("AllowanceCharge").check());
    }

    @Test
    void invoice_to_xml_for_nullable_basic_elements() {
        final Invoice invoice = this.invoice
            .withDueDate("2020-02-01")
            .withNote("Paganini no. 5")
            .withTaxPointDate("2020-02-02")
            .withTaxCurrencyCode("NOK")
            .withAccountingCost("Project cost code 123")
            .withBuyerReference("abs1234");

        final Element xml = InvoiceApi.from(invoice).process().log().xml();

        assertThat(xml.find("DueDate").first().text(), equalTo("2020-02-01"));
        assertThat(xml.find("Note").first().text(), equalTo("Paganini no. 5"));
        assertThat(xml.find("TaxPointDate").first().text(), equalTo("2020-02-02"));
        assertThat(xml.find("TaxCurrencyCode").first().text(), equalTo("NOK"));
        assertThat(xml.find("AccountingCost").first().text(), equalTo("Project cost code 123"));
        assertThat(xml.find("BuyerReference").first().text(), equalTo("abs1234"));
    }

    @Test
    void invoice_to_xml_for_overwritten_defaults() {
        final Invoice invoice = this.invoice
            .withProcessNumber(13)
            .withInvoiceTypeCode(780);

        final Element xml = InvoiceApi.from(invoice).process().log().xml();

        assertThat(xml.find("ProfileID").first().text(), equalTo("urn:fdc:peppol.eu:2017:poacc:billing:13:1.0"));
        assertThat(xml.find("InvoiceTypeCode").first().text(), equalTo("780"));
    }
}
