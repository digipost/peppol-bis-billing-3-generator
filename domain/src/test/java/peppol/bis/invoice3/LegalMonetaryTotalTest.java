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
import peppol.bis.invoice3.domain.AllowanceTotalAmount;
import peppol.bis.invoice3.domain.ChargeTotalAmount;
import peppol.bis.invoice3.domain.LegalMonetaryTotal;
import peppol.bis.invoice3.domain.LineExtensionAmount;
import peppol.bis.invoice3.domain.PayableAmount;
import peppol.bis.invoice3.domain.PayableRoundingAmount;
import peppol.bis.invoice3.domain.PrepaidAmount;
import peppol.bis.invoice3.domain.TaxExclusiveAmount;
import peppol.bis.invoice3.domain.TaxInclusiveAmount;
import peppol.bis.invoice3.domain.codes.CurrencyIdCode;

import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class LegalMonetaryTotalTest {

    private LegalMonetaryTotal legalMonetaryTotal;

    @BeforeEach
    void setUp() {
        legalMonetaryTotal = new LegalMonetaryTotal(
            new LineExtensionAmount("1273", CurrencyIdCode.EUR)
            , new TaxExclusiveAmount("1273", CurrencyIdCode.EUR)
            , new TaxInclusiveAmount("1273", CurrencyIdCode.EUR)
            , new PayableAmount("1273", CurrencyIdCode.EUR)
        );
    }

    @Test
    void to_xml_for_basic_elements() {

        final Element element = (Element) legalMonetaryTotal.node();
        assertElementNameIs(element, "LegalMonetaryTotal", CAC_NS);

        assertRequiredElement(element, "LineExtensionAmount");
        assertRequiredElement(element, "TaxExclusiveAmount");
        assertRequiredElement(element, "TaxInclusiveAmount");
        assertRequiredElement(element, "PayableAmount");

        assertUnsetOptionalElement(element, "AllowanceTotalAmount");
        assertUnsetOptionalElement(element, "ChargeTotalAmount");
        assertUnsetOptionalElement(element, "PrepaidAmount");
        assertUnsetOptionalElement(element, "PayableRoundingAmount");
    }

    @Test
    void to_xml_for_optional_elements() {
        legalMonetaryTotal
            .withAllowanceTotalAmount(new AllowanceTotalAmount("1273", CurrencyIdCode.EUR))
            .withChargeTotalAmount(new ChargeTotalAmount("1273", CurrencyIdCode.EUR))
            .withPayableRoundingAmount(new PayableRoundingAmount("1273", CurrencyIdCode.EUR))
            .withPrepaidAmount(new PrepaidAmount("1273", CurrencyIdCode.EUR));

        final Element element = (Element) legalMonetaryTotal.node();

        assertRequiredElement(element, "AllowanceTotalAmount");
        assertRequiredElement(element, "ChargeTotalAmount");
        assertRequiredElement(element, "PrepaidAmount");
        assertRequiredElement(element, "PayableRoundingAmount");
    }
}
