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

import java.util.Optional;

import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class LegalMonetaryTotal implements XmlElement {
    private final Amount lineExtensionAmount;
    private final Amount taxExclusiveAmount;
    private final Amount taxInclusiveAmount;
    private final Amount payableAmount;

    private Amount allowanceTotalAmount;
    private Amount chargeTotalAmount;
    private Amount prepaidAmount;
    private Amount payableRoundingAmount;

    public LegalMonetaryTotal(LineExtensionAmount lineExtensionAmount, TaxExclusiveAmount taxExclusiveAmount, TaxInclusiveAmount taxInclusiveAmount, PayableAmount payableAmount) {
        this.lineExtensionAmount = lineExtensionAmount;
        this.taxExclusiveAmount = taxExclusiveAmount;
        this.taxInclusiveAmount = taxInclusiveAmount;
        this.payableAmount = payableAmount;
    }

    public LegalMonetaryTotal withAllowanceTotalAmount(AllowanceTotalAmount allowanceTotalAmount) {
        this.allowanceTotalAmount = allowanceTotalAmount;
        return this;
    }

    public LegalMonetaryTotal withChargeTotalAmount(ChargeTotalAmount chargeTotalAmount) {
        this.chargeTotalAmount = chargeTotalAmount;
        return this;
    }

    public LegalMonetaryTotal withPrepaidAmount(PrepaidAmount prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
        return this;
    }

    public LegalMonetaryTotal withPayableRoundingAmount(PayableRoundingAmount payableRoundingAmount) {
        this.payableRoundingAmount = payableRoundingAmount;
        return this;
    }

    @Override
    public Node node() {
        final Element elm = Xml.el(new QualifiedName(CAC_NS, name()));

        elm.add(lineExtensionAmount.node());
        elm.add(taxExclusiveAmount.node());
        elm.add(taxInclusiveAmount.node());
        Optional.ofNullable(allowanceTotalAmount).ifPresent(amount -> elm.add(amount.node()));
        Optional.ofNullable(chargeTotalAmount).ifPresent(amount -> elm.add(amount.node()));
        Optional.ofNullable(prepaidAmount).ifPresent(amount -> elm.add(amount.node()));
        Optional.ofNullable(payableRoundingAmount).ifPresent(amount -> elm.add(amount.node()));
        elm.add(payableAmount.node());

        return elm;
    }
}
