[![Maven Central](https://maven-badges.herokuapp.com/maven-central/no.digipost/peppol-bis-billing-3-generator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/no.digipost/peppol-bis-billing-3-generator)
![](https://github.com/digipost/peppol-bis-billing-3-generator/workflows/Build%20and%20deploy/badge.svg)
[![License](https://img.shields.io/badge/license-Apache%202-blue)](https://github.com/digipost/peppol-bis-billing-3-generator/blob/master/LICENCE)

# Peppol bis billing 3 generator

This is a Pojo implementation of https://docs.peppol.eu/poacc/billing/3.0/. For now
only Invoice is created.

The principle is that the domain classes in plain java encode naming and cardinality
and they can produce xml from itself. 

We use `eaxy` as an XML generator because of
it ease of use and no dependency nature. 

We use `phive-api` for optional validation.


# Installation

If you just want to create the xml, you only need the api:
```
<dependency>
    <groupId>no.digipost</groupId>
    <artifactId>peppol-bis-billing-3-generator-api</artifactId>
    <version>x.y.z</version>
</dependency>
```

You then start from `Invoice` and create add the content yourself. You need to know what to add
and how to build the different domain constructs. Ie. you need to know what Peppol Billing is
and what is expected to be present. Luckily the documentation for the standard is very good.
Take a look at https://docs.peppol.eu/poacc/billing/3.0/syntax/ubl-invoice/tree/ for 
comprehensive documentation on all the fields. 

* We will be adding more country spesific object-builders on top of the basic API as need emerges.

Check out the example below.

# Validation

If you want, you can validate the constructed Peppol Billing xml right here without utilizing 
web pages where you upload an xml file and you get the response. You can put this validation 
into you Unit-testing of your code!

You need the validation-artifact:
```
<dependency>
    <groupId>no.digipost</groupId>
    <artifactId>peppol-bis-billing-3-generator-validation</artifactId>
    <version>x.y.z</version>
</dependency>
```

In api there is a `Validate` class that takes an `Invoice` as parameter. With the above depencency on classpath 
the validation kicks in and returns a `ValidationResult` stating whether the xml is valid Peppol Billing 3 or not
and with failures and/or error-information.

Eksample. error (From `InvoiceApiTest`): 
```
final Invoice invoice = ExampleUsage1.example1();
final ValidationResult result = new Validate(invoice).result();

//[fatal_error] [UBL-SR-47] in /:Invoice[1] [UBL-SR-47]-When there are more than one payment means code, they shall be equal Test=count(//cbc:PaymentMeansCode[not(preceding::cbc:PaymentMeansCode/. = .)])
```

Beware that default validation is done with the a given specification. We use `phive-rules-peppol` for 
validation. You can utilize this to change the rules that apply for your specific needs by
specifying the VESID that is in use. You can do this by setting a static field in `DefaultPeppolBilling3Validation`.
We do this in a test called `PeppolBillingpiTest`:

```
// This is the latest as of this writing and is 3.0.9
DefaultPeppolBilling3Validation.setVesid(PeppolValidation3_11_1.VID_OPENPEPPOL_INVOICE_V3);

You can use for instance (version 3.0.5+hotfix):
DefaultPeppolBilling3Validation.setVesid(PeppolValidation391.VID_OPENPEPPOL_INVOICE_V3);
```

Your accesspoint will probably do some kind of validation accoring to a given peppol version billing. You should
update your unit-tests accordingly to meet the correct setup requirements.

# Example of Invoice creation

```
// Create an Invoice with all your data. This is a low level non complete example.

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


// Perform validation (if you add peppol-bis-billing-3-generator-validation on classpath

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

```
