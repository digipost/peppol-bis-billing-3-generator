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

public class AllowanceCharge {
    private boolean chargeIndicator;
    private String allowanceChargeReasonCode;
    private String allowanceChargeReason;
    private String multiplierFactorNumeric;
    private Amount amount;
    private Amount baseAmount;
    private TaxCategory taxCategory;

    public AllowanceCharge(boolean chargeIndicator, Amount amount) {
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

}

