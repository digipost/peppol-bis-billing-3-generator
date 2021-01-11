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

public class PaymentMeans {
    private PaymentMeansCode paymentMeansCode;
    private String paymentID;
    private CardAccount cardAccount;
    private PayeeFinancialAccount payeeFinancialAccount;
    private PaymentMandate paymentMandate;

    public PaymentMeans(PaymentMeansCode paymentMeansCode) {
        this.paymentMeansCode = paymentMeansCode;
    }

    public PaymentMeans withPaymentID(String paymentID) {
        this.paymentID = paymentID;
        return this;
    }

    public PaymentMeans withCardAccount(CardAccount cardAccount) {
        this.cardAccount = cardAccount;
        return this;
    }

    public PaymentMeans withPayeeFinancialAccount(PayeeFinancialAccount payeeFinancialAccount) {
        this.payeeFinancialAccount = payeeFinancialAccount;
        return this;
    }

    public PaymentMeans withPaymentMandate(PaymentMandate paymentMandate) {
        this.paymentMandate = paymentMandate;
        return this;
    }

}
