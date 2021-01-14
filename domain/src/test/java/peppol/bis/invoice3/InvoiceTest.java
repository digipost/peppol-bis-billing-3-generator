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
import peppol.bis.invoice3.domain.AdditionalDocumentReference;
import peppol.bis.invoice3.domain.Amount;
import peppol.bis.invoice3.domain.BillingReference;
import peppol.bis.invoice3.domain.ClassifiedTaxCategory;
import peppol.bis.invoice3.domain.ContractDocumentReference;
import peppol.bis.invoice3.domain.Country;
import peppol.bis.invoice3.domain.Delivery;
import peppol.bis.invoice3.domain.DespatchDocumentReference;
import peppol.bis.invoice3.domain.EndpointID;
import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.domain.InvoiceAllowanceCharge;
import peppol.bis.invoice3.domain.InvoiceDocumentReference;
import peppol.bis.invoice3.domain.InvoiceLine;
import peppol.bis.invoice3.domain.InvoicePeriod;
import peppol.bis.invoice3.domain.InvoicedQuantity;
import peppol.bis.invoice3.domain.Item;
import peppol.bis.invoice3.domain.LegalMonetaryTotal;
import peppol.bis.invoice3.domain.LineExtensionAmount;
import peppol.bis.invoice3.domain.OrderReference;
import peppol.bis.invoice3.domain.OriginatorDocumentReference;
import peppol.bis.invoice3.domain.Party;
import peppol.bis.invoice3.domain.PartyLegalEntity;
import peppol.bis.invoice3.domain.PartyName;
import peppol.bis.invoice3.domain.PartyTaxScheme;
import peppol.bis.invoice3.domain.PayableAmount;
import peppol.bis.invoice3.domain.PayeeParty;
import peppol.bis.invoice3.domain.PaymentMeans;
import peppol.bis.invoice3.domain.PaymentMeansCode;
import peppol.bis.invoice3.domain.PaymentTerms;
import peppol.bis.invoice3.domain.PostalAddress;
import peppol.bis.invoice3.domain.Price;
import peppol.bis.invoice3.domain.PriceAmount;
import peppol.bis.invoice3.domain.ProjectReference;
import peppol.bis.invoice3.domain.ReceiptDocumentReference;
import peppol.bis.invoice3.domain.TaxAmount;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxExclusiveAmount;
import peppol.bis.invoice3.domain.TaxInclusiveAmount;
import peppol.bis.invoice3.domain.TaxRepresentativeParty;
import peppol.bis.invoice3.domain.TaxScheme;
import peppol.bis.invoice3.domain.TaxTotal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;

public class InvoiceTest  {

    private Invoice invoice;

    @BeforeEach
    void setUp() {

        final LegalMonetaryTotal legalMonetaryTotal = new LegalMonetaryTotal(
            new LineExtensionAmount("1273", "EUR")
            , new TaxExclusiveAmount("1273", "EUR")
            , new TaxInclusiveAmount("1273", "EUR")
            , new PayableAmount("1273", "EUR")
        );

        final TaxTotal taxTotal = new TaxTotal(
            new TaxAmount("1233", "EUR")
        );

        final InvoiceLine invoiceLine = new InvoiceLine(
            "1"
            , new InvoicedQuantity("STK", "1")
            , new LineExtensionAmount("1272", "EUR")
            , new Item("Laptop computer", new ClassifiedTaxCategory("S", new TaxScheme("VAT")))
            , new Price(new PriceAmount("1233", "EUR"))
        );

        invoice = new Invoice(
            "33445566"
            , "2017-11-01"
            , "EUR"
            , new AccountingSupplierParty(new Party(
                new EndpointID("")
                , new PostalAddress(new Country("NO"))
                , new PartyLegalEntity("")
            ))
            , new AccountingCustomerParty(new Party(
                new EndpointID("")
                , new PostalAddress(new Country("NO"))
                , new PartyLegalEntity("")
            ))
            , taxTotal
            , legalMonetaryTotal
            , invoiceLine
        );
    }

    @Test
    void invoice_to_xml_for_basic_elements() {
        final Element element = invoice.xmlRoot();

        assertRequiredElement(element, "CustomizationID", equalTo("urn:cen.eu:en16931:2017#compliant#urn:fdc:peppol.eu:2017:poacc:billing:3.0"));
        assertRequiredElement(element, "ProfileID", equalTo("urn:fdc:peppol.eu:2017:poacc:billing:01:1.0"));
        assertRequiredElement(element, "ID", equalTo("33445566"));
        assertRequiredElement(element, "IssueDate", equalTo("2017-11-01"));
        assertRequiredElement(element, "InvoiceTypeCode", equalTo("380"));
        assertRequiredElement(element, "DocumentCurrencyCode", equalTo("EUR"));

        assertRequiredElement(element, "LegalMonetaryTotal");
        assertRequiredElement(element, "AccountingCustomerParty");
        assertRequiredElement(element, "AccountingSupplierParty");

        assertThat(element.find("TaxTotal").check().size(), equalTo(1));
        assertThat(element.find("InvoiceLine").check().size(), equalTo(1));

        /*
          All these are 0..1 or 0..n cardinality, and we assert here for their non-precence
          so that other tests do not need to.
         */

        assertUnsetOptionalElement(element, "DueDate");
        assertUnsetOptionalElement(element, "Note");
        assertUnsetOptionalElement(element, "TaxPointDate");
        assertUnsetOptionalElement(element, "TaxCurrencyCode");
        assertUnsetOptionalElement(element, "AccountingCost");
        assertUnsetOptionalElement(element, "BuyerReference");
        assertUnsetOptionalElement(element, "InvoicePeriod");
        assertUnsetOptionalElement(element, "OrderReference");
        assertUnsetOptionalElement(element, "BillingReference");
        assertUnsetOptionalElement(element, "DespatchDocumentReference");
        assertUnsetOptionalElement(element, "ReceiptDocumentReference");
        assertUnsetOptionalElement(element, "OriginatorDocumentReference");
        assertUnsetOptionalElement(element, "ContractDocumentReference");
        assertUnsetOptionalElement(element, "AdditionalDocumentReference");
        assertUnsetOptionalElement(element, "ProjectReference");
        assertUnsetOptionalElement(element, "PayeeParty");
        assertUnsetOptionalElement(element, "TaxRepresentativeParty");
        assertUnsetOptionalElement(element, "Delivery");
        assertUnsetOptionalElement(element, "PaymentMeans");
        assertUnsetOptionalElement(element, "PaymentTerms");
        assertUnsetOptionalElement(element, "AllowanceCharge");
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

        final Element element = invoice.xmlRoot();

        assertRequiredElement(element, "DueDate", equalTo("2020-02-01"));
        assertRequiredElement(element, "Note", equalTo("Paganini no. 5"));
        assertRequiredElement(element, "TaxPointDate", equalTo("2020-02-02"));
        assertRequiredElement(element, "TaxCurrencyCode", equalTo("NOK"));
        assertRequiredElement(element, "AccountingCost", equalTo("Project cost code 123"));
        assertRequiredElement(element, "BuyerReference", equalTo("abs1234"));
    }

    @Test
    void invoice_to_xml_for_overwritten_defaults() {
        final Invoice invoice = this.invoice
            .withProcessNumber(13)
            .withInvoiceTypeCode(780);

        final Element element = invoice.xmlRoot();

        assertRequiredElement(element, "ProfileID", equalTo("urn:fdc:peppol.eu:2017:poacc:billing:13:1.0"));
        assertRequiredElement(element, "InvoiceTypeCode", equalTo("780"));
    }

    @Test
    void invoice_to_xml_for_added_tax_total() {
        final Invoice invoice = this.invoice
            .withTaxTotal(new TaxTotal(new TaxAmount("322", "EUR")));

        final Element element = invoice.xmlRoot();

        assertThat(element.find("TaxTotal").check().size(), equalTo(2));

        //Max 2 elements
        assertThrows(IllegalArgumentException.class, () -> this.invoice
            .withTaxTotal(new TaxTotal(new TaxAmount("322", "EUR"))));
    }

    @Test
    void invoice_to_xml_for_added_simple_elements() {
        final Invoice invoice = this.invoice
            .withPaymentTerms(new PaymentTerms("10 days"))
            .withProjectReference(new ProjectReference("PID33"))
            .withOriginatorDocumentReference(new OriginatorDocumentReference("PPID-123"))
            .withReceiptDocumentReference(new ReceiptDocumentReference("rec98"))
            .withContractDocumentReference(new ContractDocumentReference("123Contractref"))
            .withDespatchDocumentReference(new DespatchDocumentReference("desp98"))
            .withInvoicePeriod(new InvoicePeriod("2020-11-11", "2020-12-12"))
            .withOrderReference(new OrderReference("98776"))
            .withBillingReference(new BillingReference(new InvoiceDocumentReference("inv123")))
            .withDelivery(new Delivery())
            .withTaxRepresentativeParty(new TaxRepresentativeParty(
                new PartyName("Tax Representative Name AS")
                , new PostalAddress(new Country("NO"))
                , new PartyTaxScheme("FR932874294", new TaxScheme("VAT"))
            ))
            .withAdditionalDocumentReferences(new AdditionalDocumentReference("AB23456"))
            .withPaymentMeans(new PaymentMeans(new PaymentMeansCode("30")))
            .withPayeeParty(new PayeeParty(new PartyName("")))
            ;

        final Element element = invoice.xmlRoot();

        assertRequiredElement(element, "PaymentTerms");
        assertRequiredElement(element, "ProjectReference");
        assertRequiredElement(element, "OriginatorDocumentReference");
        assertRequiredElement(element, "ReceiptDocumentReference");
        assertRequiredElement(element, "ContractDocumentReference");
        assertRequiredElement(element, "DespatchDocumentReference");
        assertRequiredElement(element, "InvoicePeriod");
        assertRequiredElement(element, "OrderReference");
        assertRequiredElement(element, "BillingReference");
        assertRequiredElement(element, "Delivery");
        assertRequiredElement(element, "TaxRepresentativeParty");
        assertRequiredElement(element, "AdditionalDocumentReference");
        assertRequiredElement(element, "PaymentMeans");
        assertRequiredElement(element, "PayeeParty");
    }

    @Test
    void invoice_to_xml_for_added_invoice_lines() {
        final InvoiceLine invoiceLine = new InvoiceLine(
            "2"
            , new InvoicedQuantity("STK", "1")
            , new LineExtensionAmount("1272", "EUR")
            , new Item("Power cord", new ClassifiedTaxCategory("S", new TaxScheme("VAT")))
            , new Price(new PriceAmount("1233", "EUR"))
        );
        final Invoice invoice = this.invoice
            .withInvoiceLine(
                invoiceLine
            );

        final Element element = invoice.xmlRoot();

        assertThat(element.find("InvoiceLine").check().size(), equalTo(2));
    }


    @Test
    void InvoiceLine_to_xml_allowance_charge() {
        invoice
            .withAllowanceCharge(new InvoiceAllowanceCharge(true, new Amount("211", "EUR"), new TaxCategory("G", new TaxScheme("VAT"))))
            .withAllowanceCharge(new InvoiceAllowanceCharge(true, new Amount("212", "EUR"), new TaxCategory("G", new TaxScheme("VAT"))))
            .withAllowanceCharge(new InvoiceAllowanceCharge(true, new Amount("213", "EUR"), new TaxCategory("G", new TaxScheme("VAT"))))
        ;

        final Element element = (Element) invoice.node();

        assertThat(element.find("AllowanceCharge").size(), equalTo(3));
    }


    @Test
    void ProcessID_should_leftpad_0() {
        assertThat(invoice.getProfileID(), is(equalTo("urn:fdc:peppol.eu:2017:poacc:billing:01:1.0")));
        assertThat(invoice.withProcessNumber(11).getProfileID(), is(equalTo("urn:fdc:peppol.eu:2017:poacc:billing:11:1.0")));

    }
}
