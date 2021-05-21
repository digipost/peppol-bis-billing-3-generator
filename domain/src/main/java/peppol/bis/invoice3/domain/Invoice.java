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

import org.eaxy.Element;
import org.eaxy.Namespace;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class Invoice extends BillingCommon<Invoice>{

    private static final int UNCL1001_Commercial_invoice = 380;
    public static final String INVOICE_NAMESPACE = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";

    private String dueDate;
    private String invoiceTypeCode;
    private String note;

    private String taxCurrencyCode;
    private final String documentCurrencyCode;
    private String accountingCost;
    private String buyerReference;
    private InvoicePeriod invoicePeriod;
    private OrderReference orderReference;
    private BillingReference billingReference;
    private DespatchDocumentReference despatchDocumentReference;
    private ReceiptDocumentReference receiptDocumentReference;
    private OriginatorDocumentReference originatorDocumentReference;
    private ContractDocumentReference contractDocumentReference;
    private final List<XmlElement> additionalDocumentReferences = new ArrayList<>();
    private ProjectReference projectReference;
    private AccountingSupplierParty accountingSupplierParty;
    private AccountingCustomerParty accountingCustomerParty;
    private PayeeParty payeeParty;
    private TaxRepresentativeParty taxRepresentativeParty;
    private Delivery delivery;
    private final List<XmlElement> paymentMeans = new ArrayList<>();
    private PaymentTerms paymentTerms;
    private final List<XmlElement> allowanceCharges = new ArrayList<>();
    private final List<XmlElement> taxTotals = new ArrayList<>();
    private final LegalMonetaryTotal legalMonetaryTotal;
    private final List<XmlElement> invoiceLines = new ArrayList<>();

    public Invoice(String id, String issueDate, String documentCurrencyCode, AccountingSupplierParty accountingSupplierParty, AccountingCustomerParty accountingCustomerParty, TaxTotal taxTotal, LegalMonetaryTotal legalMonetaryTotal, InvoiceLine invoiceLine) {
        super(id, issueDate);
        this.accountingSupplierParty = accountingSupplierParty;
        this.accountingCustomerParty = accountingCustomerParty;
        this.legalMonetaryTotal = legalMonetaryTotal;
        this.withInvoiceTypeCode(UNCL1001_Commercial_invoice);
        this.documentCurrencyCode = documentCurrencyCode;
        this.taxTotals.add(taxTotal);
        this.invoiceLines.add(invoiceLine);
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

    public Invoice withTaxCurrencyCode(String taxCurrencyCode) {
        this.taxCurrencyCode = taxCurrencyCode;
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

    public Invoice withPayeeParty(PayeeParty payeeParty) {
        this.payeeParty = payeeParty;
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

    public Invoice withAllowanceCharge(InvoiceAllowanceCharge allowanceCharge) {
        this.allowanceCharges.add(allowanceCharge);
        return this;
    }

    public Invoice withTaxTotal(TaxTotal taxTotal) {
        if (this.taxTotals.size() >= 2) throw new IllegalArgumentException("Too many TaxTotal");
        this.taxTotals.add(taxTotal);
        return this;
    }

    public Invoice withInvoiceLine(InvoiceLine invoiceLine) {
        this.invoiceLines.add(invoiceLine);
        return this;
    }

    @Override
    public Element xmlRoot() {
        return node();
    }


    @Override
    protected Namespace ROOT_NS() {
        return new Namespace(INVOICE_NAMESPACE);
    }

    @Override
    public Element node() {
        final Element elm = super.node();


        optional(this.dueDate, "DueDate", elm, CBC_NS);
        required(this.invoiceTypeCode, "InvoiceTypeCode", elm, CBC_NS);
        optional(this.note, "Note", elm, CBC_NS);
        optional(super.taxPointDate, "TaxPointDate", elm, CBC_NS);
        required(this.documentCurrencyCode, "DocumentCurrencyCode", elm, CBC_NS);
        optional(this.taxCurrencyCode, "TaxCurrencyCode", elm, CBC_NS);
        optional(this.accountingCost, "AccountingCost", elm, CBC_NS);
        optional(this.buyerReference, "BuyerReference", elm, CBC_NS);
        optional(this.invoicePeriod, elm);
        optional(this.orderReference, elm);
        optional(this.billingReference, elm);
        optional(this.despatchDocumentReference, elm);
        optional(this.receiptDocumentReference, elm);
        optional(this.originatorDocumentReference, elm);
        optional(this.contractDocumentReference, elm);
        list(this.additionalDocumentReferences, elm);
        optional(this.projectReference, elm);
        required(this.accountingSupplierParty, elm);
        required(this.accountingCustomerParty, elm);
        optional(this.payeeParty, elm);
        optional(this.taxRepresentativeParty, elm);
        optional(this.delivery, elm);
        list(this.paymentMeans, elm);
        optional(this.paymentTerms, elm);
        list(this.allowanceCharges, elm);
        list(this.taxTotals, elm);
        required(this.legalMonetaryTotal, elm);
        list(this.invoiceLines, elm);

        return elm;
    }
}
