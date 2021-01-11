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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class Invoice {

    private static final int UNCL1001_Commercial_invoice = 380;

    private final String customizationID = "urn:cen.eu:en16931:2017#compliant#urn:fdc:peppol.eu:2017:poacc:billing:3.0";
    private String profileID;
    private String id;
    private String issueDate;
    private String dueDate;
    private String invoiceTypeCode;
    private String note;
    private String taxPointDate;
    private String documentCurrencyCode;
    private String accountingCost;
    private String buyerReference;
    private InvoicePeriod invoicePeriod;
    private OrderReference orderReference;
    private BillingReference billingReference;
    private DespatchDocumentReference despatchDocumentReference;
    private ReceiptDocumentReference receiptDocumentReference;
    private OriginatorDocumentReference originatorDocumentReference;
    private ContractDocumentReference contractDocumentReference;
    private List<AdditionalDocumentReference> additionalDocumentReferences = new ArrayList<>();
    private ProjectReference projectReference;
    private AccountingSupplierParty accountingSupplierParty;
    private AccountingCustomerParty accountingCustomerParty;
    private TaxRepresentativeParty taxRepresentativeParty;
    private Delivery delivery;
    private List<PaymentMeans> paymentMeans = new ArrayList<>();
    private PaymentTerms paymentTerms;
    private List<AllowanceCharge> allowanceCharges = new ArrayList<>();
    private List<TaxTotal> taxTotals = new ArrayList<>();
    private LegalMonetaryTotal legalMonetaryTotal;
    private List<InvoiceLine> invoiceLines = new ArrayList<>();

    public Invoice(String id, String issueDate, String documentCurrencyCode, AccountingSupplierParty accountingSupplierParty, AccountingCustomerParty accountingCustomerParty, TaxTotal taxTotal, LegalMonetaryTotal legalMonetaryTotal, InvoiceLine invoiceLine) {
        this.accountingSupplierParty = accountingSupplierParty;
        this.accountingCustomerParty = accountingCustomerParty;
        this.legalMonetaryTotal = legalMonetaryTotal;
        this.withProcessNumber(1);
        this.id = id;
        this.issueDate = issueDate;
        this.withInvoiceTypeCode(UNCL1001_Commercial_invoice);
        this.documentCurrencyCode = documentCurrencyCode;
        this.taxTotals.add(taxTotal);
        this.invoiceLines.add(invoiceLine);
    }

    public Invoice withProcessNumber(int processNumber) {
        this.profileID = "urn:fdc:peppol.eu:2017:poacc:billing:NN:1.0".replace("NN", format("%02d", processNumber));
        return this;
    }

    public Invoice withInvoiceTypeCode(int invoiceTypeCode) {
        this.invoiceTypeCode = String.valueOf(invoiceTypeCode);
        return this;
    }

    public Invoice withBuyerReference(String buyerReference) {
        this.buyerReference = buyerReference;
        return this;
    }

    public Invoice withDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Invoice withNote(String note) {
        this.note = note;
        return this;
    }

    public Invoice withTaxPointDate(String taxPointDate) {
        this.taxPointDate = taxPointDate;
        return this;
    }

    public Invoice withAccountingCost(String accountingCost) {
        this.accountingCost = accountingCost;
        return this;
    }

    public Invoice withInvoicePeriod(InvoicePeriod invoicePeriod) {
        this.invoicePeriod = invoicePeriod;
        return this;
    }

    public Invoice withOrderReference(OrderReference orderReference) {
        this.orderReference = orderReference;
        return this;
    }

    public Invoice withBillingReference(BillingReference billingReference) {
        this.billingReference = billingReference;
        return this;
    }

    public Invoice withDespatchDocumentReference(DespatchDocumentReference despatchDocumentReference) {
        this.despatchDocumentReference = despatchDocumentReference;
        return this;
    }

    public Invoice withReceiptDocumentReference(ReceiptDocumentReference receiptDocumentReference) {
        this.receiptDocumentReference = receiptDocumentReference;
        return this;
    }

    public Invoice withOriginatorDocumentReference(OriginatorDocumentReference originatorDocumentReference) {
        this.originatorDocumentReference = originatorDocumentReference;
        return this;
    }

    public Invoice withContractDocumentReference(ContractDocumentReference contractDocumentReference) {
        this.contractDocumentReference = contractDocumentReference;
        return this;
    }

    public Invoice withAdditionalDocumentReferences(AdditionalDocumentReference additionalDocumentReference) {
        this.additionalDocumentReferences.add(additionalDocumentReference);
        return this;
    }

    public Invoice withProjectReference(ProjectReference projectReference) {
        this.projectReference = projectReference;
        return this;
    }

    public Invoice withTaxRepresentativeParty(TaxRepresentativeParty taxRepresentativeParty) {
        this.taxRepresentativeParty = taxRepresentativeParty;
        return this;
    }

    public Invoice withDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public Invoice withPaymentMeans(PaymentMeans paymentMeans) {
        this.paymentMeans.add(paymentMeans);
        return this;
    }

    public Invoice withPaymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
        return this;
    }

    public Invoice withAllowanceCharges(AllowanceCharge allowanceCharge) {
        this.allowanceCharges.add(allowanceCharge);
        return this;
    }

    public Invoice withTaxTotal(TaxTotal taxTotal) {
        if (this.taxTotals.size() >= 2) throw new IllegalArgumentException("Too many TaxTotal");
        this.taxTotals.add(taxTotal);
        return this;
    }

    public String getProfileID() {
        return profileID;
    }

    public String getCustomizationID() {
        return customizationID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getId() {
        return id;
    }

    public String getInvoiceTypeCode() {
        return invoiceTypeCode;
    }

    public Optional<String> getDueDate() {
        return Optional.ofNullable(dueDate);
    }

    public String getDocumentCurrencyCode() {
        return documentCurrencyCode;
    }
}
