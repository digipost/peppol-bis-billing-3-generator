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
import org.eaxy.Node;
import org.eaxy.QualifiedName;
import org.eaxy.Xml;

import java.util.ArrayList;
import java.util.List;

import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class InvoiceLine implements XmlElement {

    private String id;
    private String note;
    private InvoicedQuantity invoicedQuantity;
    private Amount lineExtensionAmount;
    private String accountingCost;
    private InvoicePeriod invoicePeriod;
    private OrderLineReference orderLineReference;
    private DocumentReference documentReference;
    private final List<XmlElement> allowanceCharges = new ArrayList<>();
    private Item item;
    private Price price;

    public InvoiceLine(String id, InvoicedQuantity invoicedQuantity, LineExtensionAmount lineExtensionAmount, Item item, Price price) {
        this.id = id;
        this.invoicedQuantity = invoicedQuantity;
        this.lineExtensionAmount = lineExtensionAmount;
        this.item = item;
        this.price = price;
    }

    public InvoiceLine withNote(String note) {
        this.note = note;
        return this;
    }

    public InvoiceLine withAccountingCost(String accountingCost) {
        this.accountingCost = accountingCost;
        return this;
    }

    public InvoiceLine withInvoicePeriod(InvoicePeriod invoicePeriod) {
        this.invoicePeriod = invoicePeriod;
        return this;
    }

    public InvoiceLine withOrderLineReference(OrderLineReference orderLineReference) {
        this.orderLineReference = orderLineReference;
        return this;
    }

    public InvoiceLine withDocumentReference(DocumentReference documentReference) {
        this.documentReference = documentReference;
        return this;
    }

    public InvoiceLine withAllowanceCharge(AllowanceCharge allowanceCharge) {
        this.allowanceCharges.add(allowanceCharge);
        return this;
    }

    @Override
    public Node node() {
        final Element elm = Xml.el(new QualifiedName(CAC_NS, name()));

        required(this.id, "ID", elm, CBC_NS);

        required(this.invoicedQuantity, elm);
        required(this.lineExtensionAmount, elm);
        required(this.item, elm);
        required(this.price, elm);

        optional(this.note, "Note", elm, CBC_NS);
        optional(this.accountingCost, "AccountingCost", elm, CBC_NS);

        optional(this.invoicePeriod, elm);
        optional(this.orderLineReference, elm);
        optional(this.documentReference, elm);

        list(allowanceCharges, elm);

        return elm;
    }
}
