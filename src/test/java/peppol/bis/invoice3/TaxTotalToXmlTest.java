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
import peppol.bis.invoice3.domain.TaxAmount;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxScheme;
import peppol.bis.invoice3.domain.TaxSubtotal;
import peppol.bis.invoice3.domain.TaxTotal;
import peppol.bis.invoice3.domain.TaxableAmount;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxTotalToXmlTest {

    private TaxTotal taxTotal;

    @BeforeEach
    void setUp() {

        taxTotal = new TaxTotal(
            new TaxAmount("1233", "EUR")
        );

    }

    @Test
    void TaxTotal_to_xml_basic_elements() {

        final Element element = (Element) taxTotal.node();

        assertThat(element.getName().getName(), equalTo("TaxTotal"));
        element.find("TaxAmount").check();

        assertThrows(NonMatchingPathException.class, () -> element.find("TaxSubtotal").check());
    }

    @Test
    void TaxTotal_to_xml_optional_elements() {
        taxTotal.withTaxSubtotal(new TaxSubtotal(new TaxableAmount("123", "EUR"), new TaxAmount("124", "EUR"), new TaxCategory("g", new TaxScheme("VAT"))));

        final Element element = (Element) taxTotal.node();

        element.find("TaxSubtotal").check();
    }
}
