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
import org.eaxy.NonMatchingPathException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.AllowanceTotalAmount;
import peppol.bis.invoice3.domain.ChargeTotalAmount;
import peppol.bis.invoice3.domain.LegalMonetaryTotal;
import peppol.bis.invoice3.domain.LineExtensionAmount;
import peppol.bis.invoice3.domain.PayableAmount;
import peppol.bis.invoice3.domain.PayableRoundingAmount;
import peppol.bis.invoice3.domain.PrepaidAmount;
import peppol.bis.invoice3.domain.TaxExclusiveAmount;
import peppol.bis.invoice3.domain.TaxInclusiveAmount;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LegalMonetaryTotalToXmlTest {

    private LegalMonetaryTotal legalMonetaryTotal;

    @BeforeEach
    void setUp() {

        legalMonetaryTotal = new LegalMonetaryTotal(
            new LineExtensionAmount("1273", "EUR")
            , new TaxExclusiveAmount("1273", "EUR")
            , new TaxInclusiveAmount("1273", "EUR")
            , new PayableAmount("1273", "EUR")
        );

    }

    @Test
    void LegalMonetaryTotal_to_xml_for_basic_elements() {

        final Element element = (Element) legalMonetaryTotal.node();

        assertThat(element.getName().getName(), equalTo("LegalMonetaryTotal"));
        element.find("LineExtensionAmount").check();
        element.find("TaxExclusiveAmount").check();
        element.find("TaxInclusiveAmount").check();
        element.find("PayableAmount").check();

        assertThrows(NonMatchingPathException.class, () -> element.find("AllowanceTotalAmount").check());
        assertThrows(NonMatchingPathException.class, () -> element.find("ChargeTotalAmount").check());
        assertThrows(NonMatchingPathException.class, () -> element.find("PrepaidAmount").check());
        assertThrows(NonMatchingPathException.class, () -> element.find("PayableRoundingAmount").check());

    }

    @Test
    void LegalMonetaryTotal_to_xml_for_optional_elements() {
        legalMonetaryTotal
            .withAllowanceTotalAmount(new AllowanceTotalAmount("1273", "EUR"))
            .withChargeTotalAmount(new ChargeTotalAmount("1273", "EUR"))
            .withPayableRoundingAmount(new PayableRoundingAmount("1273", "EUR"))
            .withPrepaidAmount(new PrepaidAmount("1273", "EUR"));

        final Element element = (Element) legalMonetaryTotal.node();

        element.find("AllowanceTotalAmount").check();
        element.find("ChargeTotalAmount").check();
        element.find("PrepaidAmount").check();
        element.find("PayableRoundingAmount").check();
    }
}
