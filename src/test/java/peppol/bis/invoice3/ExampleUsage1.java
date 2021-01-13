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

import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.AccountingCustomerParty;
import peppol.bis.invoice3.domain.AccountingSupplierParty;
import peppol.bis.invoice3.domain.Address;
import peppol.bis.invoice3.domain.Amount;
import peppol.bis.invoice3.domain.ClassifiedTaxCategory;
import peppol.bis.invoice3.domain.CompanyID;
import peppol.bis.invoice3.domain.Contact;
import peppol.bis.invoice3.domain.Country;
import peppol.bis.invoice3.domain.Delivery;
import peppol.bis.invoice3.domain.DeliveryLocation;
import peppol.bis.invoice3.domain.DeliveryParty;
import peppol.bis.invoice3.domain.EndpointID;
import peppol.bis.invoice3.domain.FinancialInstitutionBranch;
import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.domain.InvoiceLine;
import peppol.bis.invoice3.domain.Item;
import peppol.bis.invoice3.domain.LegalMonetaryTotal;
import peppol.bis.invoice3.domain.LineExtensionAmount;
import peppol.bis.invoice3.domain.Party;
import peppol.bis.invoice3.domain.PartyIdentification;
import peppol.bis.invoice3.domain.PartyLegalEntity;
import peppol.bis.invoice3.domain.PartyName;
import peppol.bis.invoice3.domain.PartyTaxScheme;
import peppol.bis.invoice3.domain.PayableAmount;
import peppol.bis.invoice3.domain.PayeeFinancialAccount;
import peppol.bis.invoice3.domain.PaymentMeans;
import peppol.bis.invoice3.domain.PaymentMeansCode;
import peppol.bis.invoice3.domain.PaymentTerms;
import peppol.bis.invoice3.domain.PostalAddress;
import peppol.bis.invoice3.domain.Price;
import peppol.bis.invoice3.domain.InvoicedQuantity;
import peppol.bis.invoice3.domain.PriceAmount;
import peppol.bis.invoice3.domain.SellersItemIdentification;
import peppol.bis.invoice3.domain.TaxAmount;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxExclusiveAmount;
import peppol.bis.invoice3.domain.TaxInclusiveAmount;
import peppol.bis.invoice3.domain.TaxScheme;
import peppol.bis.invoice3.domain.TaxSubtotal;
import peppol.bis.invoice3.domain.TaxTotal;
import peppol.bis.invoice3.domain.TaxableAmount;

public class ExampleUsage1 {

    @Test
    void caseStudy1_NO() {

        final AccountingSupplierParty accountingSupplierParty = new AccountingSupplierParty(
            new Party(
                new EndpointID("998765432").withSchemeID("0192")
                , new PostalAddress(new Country("NO")).withStreetName("Oslogate 1").withCityName("Oslo").withPostalZone("0342")
                , new PartyLegalEntity("Acme Cargo AS").withCompanyID(new CompanyID("998765432").withSchemeID("0192"))
            ).withPartyIdentification(new PartyIdentification("998765432"))
                .withPartyName(new PartyName("PartyName"))
                .withPartyTaxScheme(new PartyTaxScheme("NO998765432MVA", new TaxScheme("VAT")))
                .withPartyTaxScheme(new PartyTaxScheme("Foretaksregisteret", new TaxScheme("TAX")))
                .withContact(new Contact().withTelephone("04321 (abroad +47 12341234)"))
        );

        final AccountingCustomerParty accountingCustomerParty = new AccountingCustomerParty(
            new Party(
                new EndpointID("987654320").withSchemeID("0192")
                , new PostalAddress(new Country("NO")).withStreetName("Sandnesgate 3").withCityName("Sandnes").withPostalZone("4313")
                , new PartyLegalEntity("Acme As").withCompanyID(new CompanyID("987654320").withSchemeID("0192"))
            ).withPartyIdentification(new PartyIdentification("10030177835"))
                .withPartyName(new PartyName("Acme As"))
                .withPartyTaxScheme(new PartyTaxScheme("NO987654320MVA", new TaxScheme("VAT")))
        );

        final TaxTotal taxTotal = new TaxTotal(
            new TaxAmount("0.00", "NOK")
        ).withTaxSubtotal(new TaxSubtotal(
            new TaxableAmount("2860.00", "NOK")
            , new TaxAmount("0.00", "NOK")
            , new TaxCategory("G", new TaxScheme("VAT"))
            .withPercent("0.000")
            .withTaxExemptionReason("Utførsel av varer og tjenester, 0 %")
        ));

        final LegalMonetaryTotal legalMonetaryTotal = new LegalMonetaryTotal(
            new LineExtensionAmount("2860.00", "NOK")
            , new TaxExclusiveAmount("2860.00", "NOK")
            , new TaxInclusiveAmount("2860.00", "NOK")
            , new PayableAmount("2860.00", "NOK")
        );

        final InvoiceLine invoiceLine = new InvoiceLine(
            "1", new InvoicedQuantity("STK", "1")
            , new LineExtensionAmount("2860.00", "NOK")
            , new Item(
            "Frakt", new ClassifiedTaxCategory("G", new TaxScheme("VAT")).withPercent("0.000")
        ).withSellersItemIdentification(new SellersItemIdentification("101366"))
            , new Price(new PriceAmount("2860.00", "NOK"))
        );

        final Delivery delivery = new Delivery().withDeliveryLocation(
            new DeliveryLocation().withAddress(
                new Address(new Country("NO")).withAdditionalStreetName("Sandnesgate 3")
                    .withCityName("Sandnes")
                    .withPostalZone("4313")
            )
        ).withDeliveryParty(new DeliveryParty(new PartyName("Acme As")));


        final PaymentMeans paymentMeans1 = new PaymentMeans(
            new PaymentMeansCode("58").withName("SEPA credit transfer")
        ).withPaymentID("123456789101")
            .withPayeeFinancialAccount(
                new PayeeFinancialAccount("NO6960650514745")
                    .withFinancialInstitutionBranch(new FinancialInstitutionBranch("NDEANOKK"))
            );

        final PaymentMeans paymentMeans2 = new PaymentMeans(
            new PaymentMeansCode("30").withName("Credit transfer")
        ).withPaymentID("123456789101")
            .withPayeeFinancialAccount(
                new PayeeFinancialAccount("60650514745")
                    .withFinancialInstitutionBranch(new FinancialInstitutionBranch("NDEANOKK"))
            );

        final Invoice invoice = new Invoice(
            "12345678910"
            , "2020-11-19"
            , "NOK"
            , accountingSupplierParty
            , accountingCustomerParty
            , taxTotal
            , legalMonetaryTotal
            , invoiceLine
        ).withBuyerReference("n/a")
            .withDueDate("2020-11-30")
            .withPaymentTerms(new PaymentTerms("10 dager"))
            .withDelivery(delivery)
            .withPaymentMeans(paymentMeans1)
            .withPaymentMeans(paymentMeans2);

        //InvoiceApi.from(invoice).process().log();
    }
}
