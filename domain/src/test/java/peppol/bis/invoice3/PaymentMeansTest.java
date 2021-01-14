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

import org.eaxy.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.CardAccount;
import peppol.bis.invoice3.domain.PayeeFinancialAccount;
import peppol.bis.invoice3.domain.PaymentMandate;
import peppol.bis.invoice3.domain.PaymentMeans;
import peppol.bis.invoice3.domain.PaymentMeansCode;

import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class PaymentMeansTest {

    private PaymentMeans taxCategory;

    @BeforeEach
    void setUp() {
        taxCategory = new PaymentMeans(
            new PaymentMeansCode("30").withName("Credit Transfer")
        );
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) taxCategory.node();
        assertElementNameIs(element, "PaymentMeans", CAC_NS);

        assertRequiredElement(element, "PaymentMeansCode", equalTo("30"));

        assertUnsetOptionalElement(element, "PaymentID");
        assertUnsetOptionalElement(element, "CardAccount");
        assertUnsetOptionalElement(element, "PayeeFinancialAccount");
        assertUnsetOptionalElement(element, "PaymentMandate");
    }

    @Test
    void to_xml_optional_elements() {
        taxCategory
            .withPaymentID("432948234234234")
            .withCardAccount(new CardAccount("", ""))
            .withPayeeFinancialAccount(new PayeeFinancialAccount(""))
            .withPaymentMandate(new PaymentMandate())
        ;

        final Element element = (Element) taxCategory.node();

        assertRequiredElement(element, "PaymentID", equalTo("432948234234234"));
        assertRequiredElement(element, "CardAccount");
        assertRequiredElement(element, "PayeeFinancialAccount");
        assertRequiredElement(element, "PaymentMandate");
    }

}
