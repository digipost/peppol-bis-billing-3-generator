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

public class CreditNote extends BillingCommon<CreditNote> implements XmlRootElement, XmlElement {

    private static final int UNCL1001_Commercial_invoice = 380;

    private String profileID;
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
    private String paymentDueDate;
    private PaymentTerms paymentTerms;
    private final List<XmlElement> allowanceCharges = new ArrayList<>();
    private final List<XmlElement> taxTotals = new ArrayList<>();
    private final LegalMonetaryTotal legalMonetaryTotal;
    private final List<XmlElement> creditNoteLines = new ArrayList<>();

    public CreditNote(String id, String issueDate, String documentCurrencyCode, AccountingSupplierParty accountingSupplierParty, AccountingCustomerParty accountingCustomerParty, TaxTotal taxTotal, LegalMonetaryTotal legalMonetaryTotal, CreditNoteLine creditNoteLine) {
        super(id, issueDate);
        this.accountingSupplierParty = accountingSupplierParty;
        this.accountingCustomerParty = accountingCustomerParty;
        this.legalMonetaryTotal = legalMonetaryTotal;
        this.withInvoiceTypeCode(UNCL1001_Commercial_invoice);
        this.documentCurrencyCode = documentCurrencyCode;
        this.taxTotals.add(taxTotal);
        this.creditNoteLines.add(creditNoteLine);
    }

    public CreditNote withProcessNumber(int processNumber) {
        this.profileID = "urn:fdc:peppol.eu:2017:poacc:billing:NN:1.0".replace("NN", format("%02d", processNumber));
        return this;
    }

    public CreditNote withInvoiceTypeCode(int invoiceTypeCode) {
        this.invoiceTypeCode = String.valueOf(invoiceTypeCode);
        return this;
    }

    public CreditNote withBuyerReference(String buyerReference) {
        this.buyerReference = buyerReference;
        return this;
    }

    public CreditNote withPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
        return this;
    }

    public CreditNote withNote(String note) {
        this.note = note;
        return this;
    }

    public CreditNote withTaxCurrencyCode(String taxCurrencyCode) {
        this.taxCurrencyCode = taxCurrencyCode;
        return this;
    }

    public CreditNote withAccountingCost(String accountingCost) {
        this.accountingCost = accountingCost;
        return this;
    }

    public CreditNote withInvoicePeriod(InvoicePeriod invoicePeriod) {
        this.invoicePeriod = invoicePeriod;
        return this;
    }

    public CreditNote withOrderReference(OrderReference orderReference) {
        this.orderReference = orderReference;
        return this;
    }

    public CreditNote withBillingReference(BillingReference billingReference) {
        this.billingReference = billingReference;
        return this;
    }

    public CreditNote withDespatchDocumentReference(DespatchDocumentReference despatchDocumentReference) {
        this.despatchDocumentReference = despatchDocumentReference;
        return this;
    }

    public CreditNote withReceiptDocumentReference(ReceiptDocumentReference receiptDocumentReference) {
        this.receiptDocumentReference = receiptDocumentReference;
        return this;
    }

    public CreditNote withOriginatorDocumentReference(OriginatorDocumentReference originatorDocumentReference) {
        this.originatorDocumentReference = originatorDocumentReference;
        return this;
    }

    public CreditNote withContractDocumentReference(ContractDocumentReference contractDocumentReference) {
        this.contractDocumentReference = contractDocumentReference;
        return this;
    }

    public CreditNote withAdditionalDocumentReferences(AdditionalDocumentReference additionalDocumentReference) {
        this.additionalDocumentReferences.add(additionalDocumentReference);
        return this;
    }

    public CreditNote withProjectReference(ProjectReference projectReference) {
        this.projectReference = projectReference;
        return this;
    }

    public CreditNote withTaxRepresentativeParty(TaxRepresentativeParty taxRepresentativeParty) {
        this.taxRepresentativeParty = taxRepresentativeParty;
        return this;
    }

    public CreditNote withDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public CreditNote withPayeeParty(PayeeParty payeeParty) {
        this.payeeParty = payeeParty;
        return this;
    }
    public CreditNote withPaymentMeans(PaymentMeans paymentMeans) {
        this.paymentMeans.add(paymentMeans);
        return this;
    }

    public CreditNote withPaymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
        return this;
    }

    public CreditNote withAllowanceCharge(InvoiceAllowanceCharge allowanceCharge) {
        this.allowanceCharges.add(allowanceCharge);
        return this;
    }

    public CreditNote withTaxTotal(TaxTotal taxTotal) {
        if (this.taxTotals.size() >= 2) throw new IllegalArgumentException("Too many TaxTotal");
        this.taxTotals.add(taxTotal);
        return this;
    }

    public CreditNote withInvoiceLine(InvoiceLine invoiceLine) {
        this.creditNoteLines.add(invoiceLine);
        return this;
    }

    @Override
    public Element xmlRoot() {
        return node();
    }

    @Override
    protected Namespace ROOT_NS() {
        return new Namespace("urn:oasis:names:specification:ubl:schema:xsd:CreditNote-2");
    }

    @Override
    public Element node() {
        final Element elm = super.node();

        optional(super.taxPointDate, "TaxPointDate", elm, CBC_NS);

        required(this.invoiceTypeCode, "CreditNoteTypeCode", elm, CBC_NS);
        optional(this.note, "Note", elm, CBC_NS);
        required(this.documentCurrencyCode, "DocumentCurrencyCode", elm, CBC_NS);
        optional(this.taxCurrencyCode, "TaxCurrencyCode", elm, CBC_NS);
        optional(this.accountingCost, "AccountingCost", elm, CBC_NS);
        optional(this.buyerReference, "BuyerReference", elm, CBC_NS);
        optional(this.invoicePeriod, elm);
        optional(this.orderReference, elm);
        optional(this.billingReference, elm);
        optional(this.despatchDocumentReference, elm);
        optional(this.receiptDocumentReference, elm);
        optional(this.contractDocumentReference, elm);
        list(this.additionalDocumentReferences, elm);
        optional(this.originatorDocumentReference, elm);
        required(this.accountingSupplierParty, elm);
        required(this.accountingCustomerParty, elm);
        optional(this.payeeParty, elm);
        optional(this.taxRepresentativeParty, elm);
        optional(this.delivery, elm);
        list(this.paymentMeans, elm);
        optional(this.paymentDueDate, "PaymentDueDate", elm, CBC_NS);
        optional(this.paymentTerms, elm);
        list(this.allowanceCharges, elm);
        list(this.taxTotals, elm);
        required(this.legalMonetaryTotal, elm);
        list(this.creditNoteLines, elm);

        return elm;
    }
}
