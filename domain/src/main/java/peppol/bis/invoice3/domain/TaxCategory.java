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
import peppol.bis.invoice3.domain.codes.TaxCategoryIdentifier;
import peppol.bis.invoice3.domain.codes.TaxExemptionReasonCode;

import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class TaxCategory implements XmlElement {
    private final TaxCategoryIdentifier id;
    private final TaxScheme taxScheme;
    private String percent;
    private TaxExemptionReasonCode taxExemptionReasonCode;
    private String taxExemptionReason;

    /**
     * @deprecated
     */
    public TaxCategory(String id, TaxScheme taxScheme) {
        try {
            this.id = TaxCategoryIdentifier.valueOf(id);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                    "Invalid TaxCategoryIdentifier (UNCL5305): " + id, ex
            );
        }
        this.taxScheme = taxScheme;
    }

    public TaxCategory(TaxCategoryIdentifier id, TaxScheme taxScheme) {
        this.id = id;
        this.taxScheme = taxScheme;
    }

    public TaxCategory withPercent(String percent) {
        this.percent = percent;
        return this;
    }

    /**
     * @deprecated
     */
    public TaxCategory withTaxExemptionReasonCode(String taxExemptionReasonCode) {
        try {
            this.taxExemptionReasonCode = TaxExemptionReasonCode.valueOf(taxExemptionReasonCode);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                    "Invalid TaxExemptionReasonCode (UNECERec20): " + taxExemptionReasonCode, ex
            );
        }
        return this;
    }

    public TaxCategory withTaxExemptionReasonCode(TaxExemptionReasonCode taxExemptionReasonCode) {
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

        required(this.id.name(), "ID", elm, CBC_NS);
        optional(this.percent, "Percent", elm, CBC_NS);
        optional(this.taxExemptionReasonCode != null ? this.taxExemptionReasonCode.getCode() : null, "TaxExemptionReasonCode", elm, CBC_NS);
        optional(this.taxExemptionReason, "TaxExemptionReason", elm, CBC_NS);
        required(this.taxScheme, elm);

        return elm;
    }
}
