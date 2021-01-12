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
import peppol.bis.invoice3.domain.TaxAmount;
import peppol.bis.invoice3.domain.TaxCategory;
import peppol.bis.invoice3.domain.TaxScheme;
import peppol.bis.invoice3.domain.TaxSubtotal;
import peppol.bis.invoice3.domain.TaxableAmount;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TaxSubtotalToXmlTest {

    private TaxSubtotal taxSubtotal;

    @BeforeEach
    void setUp() {

        taxSubtotal = new TaxSubtotal(
            new TaxableAmount("1233", "EUR"),
            new TaxAmount("1233", "EUR"),
            new TaxCategory("S", new TaxScheme("VAT"))
        );

    }

    @Test
    void TaxSubtotal_to_xml_basic_elements() {

        final Element element = (Element) taxSubtotal.node();

        assertThat(element.getName().getName(), equalTo("TaxSubtotal"));
        element.find("TaxableAmount").check();
        element.find("TaxAmount").check();
        element.find("TaxCategory").check();
    }

}
