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

public class LegalMonetaryTotal {
    private Amount lineExtensionAmount;
    private Amount taxExclusiveAmount;
    private Amount taxInclusiveAmount;
    private Amount allowanceTotalAmount;
    private Amount chargeTotalAmount;
    private Amount prepaidAmount;
    private Amount payableRoundingAmount;
    private Amount payableAmount;

    public LegalMonetaryTotal(Amount lineExtensionAmount, Amount taxExclusiveAmount, Amount taxInclusiveAmount, Amount payableAmount) {
        this.lineExtensionAmount = lineExtensionAmount;
        this.taxExclusiveAmount = taxExclusiveAmount;
        this.taxInclusiveAmount = taxInclusiveAmount;
        this.payableAmount = payableAmount;
    }

    public LegalMonetaryTotal withAllowanceTotalAmount(Amount allowanceTotalAmount) {
        this.allowanceTotalAmount = allowanceTotalAmount;
        return this;
    }

    public LegalMonetaryTotal withChargeTotalAmount(Amount chargeTotalAmount) {
        this.chargeTotalAmount = chargeTotalAmount;
        return this;
    }

    public LegalMonetaryTotal withPrepaidAmount(Amount prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
        return this;
    }

    public LegalMonetaryTotal withPayableRoundingAmount(Amount payableRoundingAmount) {
        this.payableRoundingAmount = payableRoundingAmount;
        return this;
    }
}
