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
package peppol.bis.invoice3.domain;

import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.api.PeppolBillingApi;
import peppol.bis.invoice3.validation.ValidationResult;

public class ExampleUsage1 {


    public static Invoice example1(){

        final AccountingSupplierParty accountingSupplierParty = new AccountingSupplierParty(
            new Party(
                new EndpointID("916725280").withSchemeID("0192")
                , new PostalAddress(new Country("NO")).withStreetName("Oslogate 1").withCityName("Oslo").withPostalZone("0342")
                , new PartyLegalEntity("Acme Cargo AS").withCompanyID(new CompanyID("916725280").withSchemeID("0192"))
            ).withPartyIdentification(new PartyIdentification(new ID("916725280")))
                .withPartyName(new PartyName("PartyName"))
                .withPartyTaxScheme(new PartyTaxScheme("NO" + "916725280" +  "MVA", new TaxScheme("VAT")))
                .withPartyTaxScheme(new PartyTaxScheme("Foretaksregisteret", new TaxScheme("TAX")))
                .withContact(new Contact().withTelephone("04321 (abroad +47 12341234)"))
        );

        final AccountingCustomerParty accountingCustomerParty = new AccountingCustomerParty(
            new Party(
                new EndpointID("916725280").withSchemeID("0192")
                , new PostalAddress(new Country("NO")).withStreetName("Sandnesgate 3").withCityName("Sandnes").withPostalZone("4313")
                , new PartyLegalEntity("Acme As").withCompanyID(new CompanyID("916725280").withSchemeID("0192"))
            ).withPartyIdentification(new PartyIdentification(new ID("10030177835")))
                .withPartyName(new PartyName("Acme As"))
                .withPartyTaxScheme(new PartyTaxScheme("NO916725280MVA", new TaxScheme("VAT")))
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
            new PaymentMeansCode("30")
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

        final PeppolBillingApi<Invoice> api = PeppolBillingApi.create(invoice);

        final ValidationResult validationResult = api.validate();

        if(validationResult.isValid()){
            // You have a valid peppol billing file and can either print it to file from string or
            // send it to Digipost through the ApiClient
            api.prettyPrint();
            api.inputStream();
        } else {
            // You have validation errors and you need to check them an correct them
            validationResult.errors().forEach(System.out::println);
            validationResult.warns().forEach(System.out::println);
        }


        return invoice;
    }

    @Test
    void caseStudy1_NO() {
        example1().xmlRoot();
    }
}
