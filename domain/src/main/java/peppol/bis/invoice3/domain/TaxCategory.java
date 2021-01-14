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

public class TaxCategory implements XmlElement {
    private String id;
    private TaxScheme taxScheme;
    private String percent;
    private String taxExemptionReasonCode;
    private String taxExemptionReason;

    public TaxCategory(String id, TaxScheme taxScheme) {
        this.id = id;
        this.taxScheme = taxScheme;
    }

    public TaxCategory withPercent(String percent) {
        this.percent = percent;
        return this;
    }

    public TaxCategory withTaxExemptionReasonCode(String taxExemptionReasonCode) {
        this.taxExemptionReasonCode = taxExemptionReasonCode;
        return this;
    }

    public TaxCategory withTaxExemptionReason(String taxExemptionReason) {
        this.taxExemptionReason = taxExemptionReason;
        return this;
    }

    @Override
    public Node node() {
        final Element elm = Xml.el(new QualifiedName(CAC_NS, name()));

        required(this.id, "ID", elm, CBC_NS);
        required(this.taxScheme, elm);

        optional(this.percent, "Percent", elm, CBC_NS);
        optional(this.taxExemptionReasonCode, "TaxExemptionReasonCode", elm, CBC_NS);
        optional(this.taxExemptionReason, "TaxExemptionReason", elm, CBC_NS);

        return elm;
    }
}
