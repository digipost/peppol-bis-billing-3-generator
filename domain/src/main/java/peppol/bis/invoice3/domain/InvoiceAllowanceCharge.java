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

import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class InvoiceAllowanceCharge implements XmlElement {
    private final boolean chargeIndicator;
    private String allowanceChargeReasonCode;
    private String allowanceChargeReason;
    private String multiplierFactorNumeric;
    private final Amount amount;
    private BaseAmount baseAmount;
    private final TaxCategory taxCategory;

    public InvoiceAllowanceCharge(boolean chargeIndicator, Amount amount, TaxCategory taxCategory) {
        this.chargeIndicator = chargeIndicator;
        this.amount = amount;
        this.taxCategory = taxCategory;
    }

    public InvoiceAllowanceCharge withAllowanceChargeReasonCode(String allowanceChargeReasonCode) {
        this.allowanceChargeReasonCode = allowanceChargeReasonCode;
        return this;
    }

    public InvoiceAllowanceCharge withAllowanceChargeReason(String allowanceChargeReason) {
        this.allowanceChargeReason = allowanceChargeReason;
        return this;
    }

    public InvoiceAllowanceCharge withMultiplierFactorNumeric(String multiplierFactorNumeric) {
        this.multiplierFactorNumeric = multiplierFactorNumeric;
        return this;
    }

    public InvoiceAllowanceCharge withBaseAmount(BaseAmount baseAmount) {
        this.baseAmount = baseAmount;
        return this;
    }

    @Override
    public Node node() {
        final Element elm = Xml.el(new QualifiedName(CAC_NS, name()));

        required(String.valueOf(this.chargeIndicator), "ChargeIndicator", elm, CBC_NS);
        required(this.amount, elm);

        optional(this.baseAmount, elm);
        optional(this.allowanceChargeReasonCode, "AllowanceChargeReasonCode", elm, CBC_NS);
        optional(this.allowanceChargeReason, "AllowanceChargeReason", elm, CBC_NS);
        optional(this.multiplierFactorNumeric, "MultiplierFactorNumeric", elm, CBC_NS);

        required(this.taxCategory, elm);

        return elm;
    }

    @Override
    public String name() {
        return "AllowanceCharge";
    }

}

