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
import peppol.bis.invoice3.domain.Amount;
import peppol.bis.invoice3.domain.BaseAmount;
import peppol.bis.invoice3.domain.InvoiceAllowanceCharge;
import peppol.bis.invoice3.domain.InvoiceLineAllowanceCharge;
import peppol.bis.invoice3.domain.PriceAllowanceCharge;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxScheme;

import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

class AllowanceChargeTest {

    private PriceAllowanceCharge priceAllowanceCharge;
    private InvoiceAllowanceCharge invoiceAllowanceCharge;
    private InvoiceLineAllowanceCharge invoiceLineAllowanceCharge;

    @BeforeEach
    void setUp() {
        priceAllowanceCharge = new PriceAllowanceCharge(
            true, new Amount("123", "EUR")
        );
        invoiceAllowanceCharge = new InvoiceAllowanceCharge(
            true, new Amount("123", "EUR"), new TaxCategory("G", new TaxScheme("VAT"))
        );
        invoiceLineAllowanceCharge = new InvoiceLineAllowanceCharge(
            true, new Amount("123", "EUR")
        );
    }

    @Test
    void PriceAllowanceCharge_to_xml_basic_elements() {
        final Element element = (Element) priceAllowanceCharge.node();
        assertElementNameIs(element, "AllowanceCharge", CAC_NS);

        assertRequiredElement(element, "ChargeIndicator", equalTo("true"));
        assertRequiredElement(element, "Amount");

        assertUnsetOptionalElement(element, "BaseAmount");
    }

    @Test
    void PriceAllowanceCharge_to_xml_optional_elements() {
        priceAllowanceCharge.withBaseAmount(new BaseAmount("344", "EUR"));

        final Element element = (Element) priceAllowanceCharge.node();

        assertRequiredElement(element, "BaseAmount");
    }

    @Test
    void InvoiceAllowanceCharge_to_xml_basic_elements() {
        final Element element = (Element) invoiceAllowanceCharge.node();
        assertElementNameIs(element, "AllowanceCharge", CAC_NS);

        assertRequiredElement(element, "ChargeIndicator", equalTo("true"));
        assertRequiredElement(element, "Amount");
        assertRequiredElement(element, "TaxCategory");

        assertUnsetOptionalElement(element, "BaseAmount");
    }

    @Test
    void InvoiceAllowanceCharge_to_xml_optional_elements() {
        invoiceAllowanceCharge
            .withBaseAmount(new BaseAmount("344", "EUR"))
            .withAllowanceChargeReasonCode("95")
            .withAllowanceChargeReason("Discount")
            .withMultiplierFactorNumeric("20")
        ;

        final Element element = (Element) invoiceAllowanceCharge.node();

        assertRequiredElement(element, "BaseAmount");
        assertRequiredElement(element, "AllowanceChargeReasonCode", equalTo("95"));
        assertRequiredElement(element, "AllowanceChargeReason", equalTo("Discount"));
        assertRequiredElement(element, "MultiplierFactorNumeric", equalTo("20"));
    }

    @Test
    void InvoiceLineAllowanceCharge_to_xml_basic_elements() {
        final Element element = (Element) invoiceLineAllowanceCharge.node();
        assertElementNameIs(element, "AllowanceCharge", CAC_NS);

        assertRequiredElement(element, "ChargeIndicator", equalTo("true"));
        assertRequiredElement(element, "Amount");

        assertUnsetOptionalElement(element, "BaseAmount");
    }

    @Test
    void InvoiceLineAllowanceCharge_to_xml_optional_elements() {
        invoiceLineAllowanceCharge
            .withBaseAmount(new BaseAmount("344", "EUR"))
            .withAllowanceChargeReasonCode("95")
            .withAllowanceChargeReason("Discount")
            .withMultiplierFactorNumeric("20")
        ;

        final Element element = (Element) invoiceLineAllowanceCharge.node();


        assertRequiredElement(element, "BaseAmount");
        assertRequiredElement(element, "AllowanceChargeReasonCode", equalTo("95"));
        assertRequiredElement(element, "AllowanceChargeReason", equalTo("Discount"));
        assertRequiredElement(element, "MultiplierFactorNumeric", equalTo("20"));
    }

}
