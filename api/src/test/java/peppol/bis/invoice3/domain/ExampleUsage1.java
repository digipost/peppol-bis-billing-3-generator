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

    public static Invoice norwegianExample(){

        final AccountingSupplierParty accountingSupplierParty = new AccountingSupplierParty(
            new Party(
                new EndpointID("123456785").withSchemeID("0192")
                , new PostalAddress(new Country("NO"))
                .withStreetName("Main street 34")
                .withAdditionalStreetName("Suite 123")
                .withCityName("Big city")
                .withPostalZone("54321")
                .withCountrySubentity("RegionA")
                , new PartyLegalEntity("The Sellercompany ASA").withCompanyID(new CompanyID("123456785").withSchemeID("0192"))
            ).withPartyIdentification(new PartyIdentification(new ID("1238764941386").withSchemeID("0088")))
                .withPartyName(new PartyName("Salescompany ltd."))
                .withPartyTaxScheme(new PartyTaxScheme("NO123456785MVA", new TaxScheme("VAT")))
                .withPartyTaxScheme(new PartyTaxScheme("Foretaksregisteret", new TaxScheme("TAX")))
                .withContact(
                    new Contact()
                        .withName("Antonio Salesmacher")
                        .withTelephone("46211230")
                        .withelectronicMail("antonio@salescompany.no")
                )
        );

        final AccountingCustomerParty accountingCustomerParty = new AccountingCustomerParty(
            new Party(
                new EndpointID("987654325").withSchemeID("0192")
                , new PostalAddress(new Country("NO"))
                .withStreetName("Anystreet 8")
                .withAdditionalStreetName("Back door")
                .withCityName("Anytown")
                .withPostalZone("101")
                .withCountrySubentity("RegionB")
                , new PartyLegalEntity("Buyercompany ASA").withCompanyID(new CompanyID("987654325").withSchemeID("0192"))
            ).withPartyIdentification(new PartyIdentification(new ID("3456789012098").withSchemeID("0088")))
                .withPartyName(new PartyName("The Buyercompany"))
                .withPartyTaxScheme(new PartyTaxScheme("NO987654325MVA", new TaxScheme("VAT")))
                .withContact(
                    new Contact()
                        .withName("John Doe")
                        .withTelephone("5121230")
                        .withelectronicMail("john@buyercompany.no")
                )
        );

        final TaxTotal taxTotal = new TaxTotal(
            new TaxAmount("365.28", "NOK")
        ).withTaxSubtotal(
            new TaxSubtotal(
                new TaxableAmount("1460.5", "NOK")
                , new TaxAmount("365.13", "NOK")
                , new TaxCategory("S", new TaxScheme("VAT")).withPercent("25")
            )
        ).withTaxSubtotal(
            new TaxSubtotal(
                new TaxableAmount("1", "NOK")
                , new TaxAmount("0.15", "NOK")
                , new TaxCategory("S", new TaxScheme("VAT")).withPercent("15")
            )
        ).withTaxSubtotal(
            new TaxSubtotal(
                new TaxableAmount("-25", "NOK")
                , new TaxAmount("0", "NOK")
                , new TaxCategory("E", new TaxScheme("VAT")).withPercent("0").withTaxExemptionReason("Exempt New Means of Transport")
            )
        );

        final LegalMonetaryTotal legalMonetaryTotal = new LegalMonetaryTotal(
            new LineExtensionAmount("1436.5", "NOK")
            , new TaxExclusiveAmount("1436.5", "NOK")
            , new TaxInclusiveAmount("1801.78", "NOK")
            , new PayableAmount("802.00", "NOK")
        ).withAllowanceTotalAmount(new AllowanceTotalAmount("100", "NOK"))
            .withChargeTotalAmount(new ChargeTotalAmount("100", "NOK"))
            .withPayableRoundingAmount(new PayableRoundingAmount("0.22", "NOK"))
            .withPrepaidAmount(new PrepaidAmount("1000", "NOK"));

        final InvoiceLine invoiceLine1 = new InvoiceLine(
            "1", new InvoicedQuantity("1", "NAR"), new LineExtensionAmount("1273", "NOK")
            , new Item(
            "Laptop computer", new ClassifiedTaxCategory("S", new TaxScheme("VAT")).withPercent("25")
        ).withDescription("Processor: Intel Core 2 Duo SU9400 LV (1.4GHz). RAM: 3MB. Screen 1440x900")
            .withSellersItemIdentification(new SellersItemIdentification("JB007"))
            .withStandardItemIdentification(new StandardItemIdentification(new ID("1234567890124").withSchemeID("0088")))
            .withOriginCountry(new OriginCountry("DE"))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("12344321", "MP")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("65434568", "STI")))
            .withAdditionalItemProperty(new AdditionalItemProperty("Color", "Black"))
            , new Price(new PriceAmount("1273", "NOK"))
            .withBaseQuantity(new BaseQuantity("1"))
            .withAllowanceCharge(new PriceAllowanceCharge(false, new Amount("227", "NOK")).withBaseAmount(new BaseAmount("1500", "NOK")))
        ).withNote("Scratch on box")
            .withAccountingCost("BookingCode001")
            .withInvoicePeriod(new InvoicePeriod("2013-06-01", "2013-06-30"))
            .withOrderLineReference(new OrderLineReference("1"))
            .withAllowanceCharge(new InvoiceLineAllowanceCharge(false, new Amount("12", "NOK")).withAllowanceChargeReason("Damage"))
            .withAllowanceCharge(new InvoiceLineAllowanceCharge(true, new Amount("12", "NOK")).withAllowanceChargeReason("Testing"));


        final InvoiceLine invoiceLine2 = new InvoiceLine(
            "2", new InvoicedQuantity("-1", "NAR"), new LineExtensionAmount("-3.96", "NOK")
            , new Item(
            "Returned \"Advanced computing\" book", new ClassifiedTaxCategory("S", new TaxScheme("VAT")).withPercent("15")
        ).withSellersItemIdentification(new SellersItemIdentification("JB008"))
            .withStandardItemIdentification(new StandardItemIdentification(new ID("1234567890125").withSchemeID("0088")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("32344324", "MP")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("65434567", "STI")))
            , new Price(new PriceAmount("3.96", "NOK"))
            .withBaseQuantity(new BaseQuantity("1"))
        ).withNote("Cover is slightly damaged.")
            .withAccountingCost("BookingCode002")
            .withOrderLineReference(new OrderLineReference("5"));


        final InvoiceLine invoiceLine3 = new InvoiceLine(
            "3", new InvoicedQuantity("2", "NAR"), new LineExtensionAmount("4.96", "NOK")
            , new Item(
            "\"Computing for dummies\" book", new ClassifiedTaxCategory("S", new TaxScheme("VAT")).withPercent("15")
        )
            .withSellersItemIdentification(new SellersItemIdentification("JB009"))
            .withStandardItemIdentification(new StandardItemIdentification(new ID("1234567890126").withSchemeID("0088")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("32344324", "MP")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("65434566", "STI")))
            , new Price(new PriceAmount("2.48", "NOK"))
            .withBaseQuantity(new BaseQuantity("1"))
            .withAllowanceCharge(new PriceAllowanceCharge(false, new Amount("0.27", "NOK")).withBaseAmount(new BaseAmount("2.75", "NOK")))
        ).withAccountingCost("BookingCode003")
            .withOrderLineReference(new OrderLineReference("3"));


        final InvoiceLine invoiceLine4 = new InvoiceLine(
            "4", new InvoicedQuantity("-1", "NAR"), new LineExtensionAmount("-25", "NOK")
            , new Item(
            "Returned IBM 5150 desktop", new ClassifiedTaxCategory("E", new TaxScheme("VAT")).withPercent("0")
        )
            .withSellersItemIdentification(new SellersItemIdentification("JB010"))
            .withStandardItemIdentification(new StandardItemIdentification(new ID("1234567890127").withSchemeID("0088")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("12344322", "MP")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("65434565", "STI")))
            , new Price(new PriceAmount("25", "NOK"))
            .withBaseQuantity(new BaseQuantity("1"))
        ).withAccountingCost("BookingCode004")
            .withOrderLineReference(new OrderLineReference("2"));


        final InvoiceLine invoiceLine5 = new InvoiceLine(
            "5", new InvoicedQuantity("250", "MTR"), new LineExtensionAmount("187.5", "NOK")
            , new Item(
            "Network cable", new ClassifiedTaxCategory("S", new TaxScheme("VAT")).withPercent("25")
        )
            .withSellersItemIdentification(new SellersItemIdentification("JB011"))
            .withStandardItemIdentification(new StandardItemIdentification(new ID("1234567890128").withSchemeID("0088")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("12344325", "MP")))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("65434564", "STI")))
            .withAdditionalItemProperty(new AdditionalItemProperty("Type", "Cat5"))
            , new Price(new PriceAmount("0.75", "NOK"))
            .withBaseQuantity(new BaseQuantity("1"))
        ).withAccountingCost("BookingCode005")
            .withOrderLineReference(new OrderLineReference("4"));


        final Invoice norwegianExample = new Invoice(
            "TOSL108"
            , "2013-06-30"
            , "NOK"
            , accountingSupplierParty
            , accountingCustomerParty
            , taxTotal
            , legalMonetaryTotal
        ).withDueDate("2013-07-20")
            .withInvoiceTypeCode(380)
            .withNote("Ordered in our booth at the convention.")
            .withTaxPointDate("2013-06-30")
            .withAccountingCost("Project cost code 123")
            .withBuyerReference("3150bdn")
            .withInvoicePeriod(
                new InvoicePeriod("2013-06-01", "2013-06-30")
            ).withOrderReference(new OrderReference("123"))
            .withContractDocumentReference(new ContractDocumentReference("Contract321"))
            .withAdditionalDocumentReferences(
                new AdditionalDocumentReference("Doc1")
                .withDocumentDescription("Timesheet")
                .withAttachment(
                    new Attachment()
                    .withExternalReference(new ExternalReference("http://www.suppliersite.eu/sheet001.html"))
                )
            )
            .withAdditionalDocumentReferences(
                new AdditionalDocumentReference("Doc2")
                .withDocumentDescription("EHF specification")
                .withAttachment(new Attachment().withEmbeddedDocumentBinaryObject(new EmbeddedDocumentBinaryObject(
                    "application/pdf", "Hours-spent.csv", "VGVzdCBiYXNlIDY0IGVuY29kaW5n"
                )))
            ).withPayeeParty(
                new PayeeParty(new PartyName("Ebeneser Scrooge AS"))
                .withPartyIdentification(new PartyIdentification(new ID("2298740918237").withSchemeID("0088")))
                .withPartyLegalEntity(new PayeePartyPartyLegalEntity(new CompanyID("999999999").withSchemeID("0192")))
            ).withTaxRepresentativeParty(
                new TaxRepresentativeParty(
                    new PartyName("Tax handling company AS")
                    , new PostalAddress(new Country("NO"))
                        .withStreetName("Regent street")
                        .withAdditionalStreetName("Front door")
                        .withCityName("Newtown")
                        .withPostalZone("101")
                        .withCountrySubentity("RegionC")
                    , new PartyTaxScheme("NO999999999MVA", new TaxScheme("VAT"))
                )
            ).withDelivery(
                new Delivery()
                .withActualDeliveryDate("2013-06-15")
                .withDeliveryLocation(new DeliveryLocation().withId(new ID("6754238987643").withSchemeID("0088")))
            ).withPaymentMeans(
                new PaymentMeans(new PaymentMeansCode("31"))
                .withPaymentID("0003434323213231")
                .withPayeeFinancialAccount(
                    new PayeeFinancialAccount("NO9386011117947")
                    .withFinancialInstitutionBranch(
                        new FinancialInstitutionBranch("DNBANOKK")
                    )

                )
            ).withPaymentTerms(new PaymentTerms("2 % discount if paid within 2 days. Penalty percentage 10% from due date"))
            .withAllowanceCharge(
                new InvoiceAllowanceCharge(
                    true
                    , new Amount("100", "NOK")
                    , new TaxCategory("S", new TaxScheme("VAT")).withPercent("25")
                ).withAllowanceChargeReasonCode("FC").withAllowanceChargeReason("Freight")
            )
            .withAllowanceCharge(
                new InvoiceAllowanceCharge(
                    false
                    , new Amount("100", "NOK")
                    , new TaxCategory("S", new TaxScheme("VAT")).withPercent("25")
                ).withAllowanceChargeReasonCode("95").withAllowanceChargeReason("Promotion discount")
            ).withInvoiceLine(invoiceLine1)
            .withInvoiceLine(invoiceLine2)
            .withInvoiceLine(invoiceLine3)
            .withInvoiceLine(invoiceLine4)
            .withInvoiceLine(invoiceLine5)
            ;
        return norwegianExample;
    }

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
            "1", new InvoicedQuantity("1", "STK")
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

        final Invoice invoice = new Invoice(
            "12345678910"
            , "2020-11-19"
            , "NOK"
            , accountingSupplierParty
            , accountingCustomerParty
            , taxTotal
            , legalMonetaryTotal
        ).withBuyerReference("n/a")
            .withInvoiceLine(invoiceLine)
            .withDueDate("2020-11-30")
            .withPaymentTerms(new PaymentTerms("10 dager"))
            .withDelivery(delivery)
            .withPaymentMeans(paymentMeans1);

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
