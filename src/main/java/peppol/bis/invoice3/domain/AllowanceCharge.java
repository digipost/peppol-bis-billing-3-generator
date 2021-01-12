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

public class AllowanceCharge implements XmlElement {
    private boolean chargeIndicator;
    private String allowanceChargeReasonCode;
    private String allowanceChargeReason;
    private String multiplierFactorNumeric;
    private Amount amount;
    private Amount baseAmount;
    private TaxCategory taxCategory;

    public AllowanceCharge(boolean chargeIndicator, BaseAmount amount) {
        this.chargeIndicator = chargeIndicator;
        this.amount = amount;
    }

    public AllowanceCharge withAllowanceChargeReasonCode(String allowanceChargeReasonCode) {
        this.allowanceChargeReasonCode = allowanceChargeReasonCode;
        return this;
    }

    public AllowanceCharge withAllowanceChargeReason(String allowanceChargeReason) {
        this.allowanceChargeReason = allowanceChargeReason;
        return this;
    }

    public AllowanceCharge withMultiplierFactorNumeric(String multiplierFactorNumeric) {
        this.multiplierFactorNumeric = multiplierFactorNumeric;
        return this;
    }

    public AllowanceCharge withBaseAmount(Amount baseAmount) {
        this.baseAmount = baseAmount;
        return this;
    }

    public AllowanceCharge withTaxCategory(TaxCategory taxCategory) {
        this.taxCategory = taxCategory;
        return this;
    }

    @Override
    public Node node() {
        final Element elm = Xml.el(new QualifiedName(CAC_NS, name()));

        return elm;
    }

}

