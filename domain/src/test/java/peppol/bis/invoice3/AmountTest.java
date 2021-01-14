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
import peppol.bis.invoice3.domain.AllowanceTotalAmount;
import peppol.bis.invoice3.domain.Amount;
import peppol.bis.invoice3.domain.BaseAmount;
import peppol.bis.invoice3.domain.ChargeTotalAmount;
import peppol.bis.invoice3.domain.LineExtensionAmount;
import peppol.bis.invoice3.domain.PayableAmount;
import peppol.bis.invoice3.domain.PayableRoundingAmount;
import peppol.bis.invoice3.domain.PrepaidAmount;
import peppol.bis.invoice3.domain.PriceAmount;
import peppol.bis.invoice3.domain.TaxExclusiveAmount;
import peppol.bis.invoice3.domain.TaxInclusiveAmount;
import peppol.bis.invoice3.domain.TaxableAmount;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


class AmountTest {

    @Test
    void Amount_to_xml() {
        final Element element = (Element) new Amount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("Amount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void LineExtensionAmount_to_xml() {
        final Element element = (Element) new LineExtensionAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("LineExtensionAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void TaxExclusiveAmount_to_xml() {
        final Element element = (Element) new TaxExclusiveAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("TaxExclusiveAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void TaxInclusiveAmount_to_xml() {
        final Element element = (Element) new TaxInclusiveAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("TaxInclusiveAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void PayableAmount_to_xml() {
        final Element element = (Element) new PayableAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("PayableAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void AllowanceTotalAmount_to_xml() {
        final Element element = (Element) new AllowanceTotalAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("AllowanceTotalAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void ChargeTotalAmount_to_xml() {
        final Element element = (Element) new ChargeTotalAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("ChargeTotalAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void PrepaidAmount_to_xml() {
        final Element element = (Element) new PrepaidAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("PrepaidAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void PayableRoundingAmount_to_xml() {
        final Element element = (Element) new PayableRoundingAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("PayableRoundingAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void PriceAmount_to_xml() {
        final Element element = (Element) new PriceAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("PriceAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void TaxableAmount_to_xml() {
        final Element element = (Element) new TaxableAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("TaxableAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }

    @Test
    void BaseAmount_to_xml() {
        final Element element = (Element) new BaseAmount("1273", "EUR").node();
        assertThat(element.getName().getName(), equalTo("BaseAmount"));
        assertThat(element.text(), equalTo("1273"));
        assertThat(element.attrs().get("currencyID"), equalTo("EUR"));
    }
}
