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
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.BaseQuantity;
import peppol.bis.invoice3.domain.InvoicedQuantity;
import peppol.bis.invoice3.domain.PaymentMeansCode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


class PaymentMeansCodeTest {

    @Test
    void PaymentMeansCode_to_xml() {
        final Element element = (Element) new PaymentMeansCode("30").node();
        assertThat(element.getName().getName(), equalTo("PaymentMeansCode"));
        assertThat(element.text(), equalTo("30"));
        assertThat(element.attrs().get("name"), equalTo(null));
    }

    @Test
    void PaymentMeansCode_to_xml_with_name() {
        final Element element = (Element) new PaymentMeansCode("30").withName("Credit transfer").node();
        assertThat(element.getName().getName(), equalTo("PaymentMeansCode"));
        assertThat(element.text(), equalTo("30"));
        assertThat(element.attrs().get("name"), equalTo("Credit transfer"));
    }


}
