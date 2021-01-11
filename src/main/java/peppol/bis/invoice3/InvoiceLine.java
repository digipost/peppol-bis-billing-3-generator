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

import java.util.ArrayList;
import java.util.List;

public class InvoiceLine {

    private String id;
    private String note;
    private Quantity invoicedQuantity;
    private Amount lineExtensionAmount;
    private String accountingCost;
    private InvoicePeriod invoicePeriod;
    private OrderLineReference orderLineReference;
    private DocumentReference documentReference;
    private List<AllowanceCharge> allowanceCharges = new ArrayList<>();
    private Item item;
    private Price price;

    public InvoiceLine(String id, Quantity invoicedQuantity, Amount lineExtensionAmount, Item item, Price price) {
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

}
