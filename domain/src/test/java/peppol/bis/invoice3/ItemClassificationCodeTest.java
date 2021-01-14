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
import peppol.bis.invoice3.domain.ItemClassificationCode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class ItemClassificationCodeTest {

    private ItemClassificationCode itemClassificationCode;

    @BeforeEach
    void setUp() {
        itemClassificationCode = new ItemClassificationCode(
        "9873242", "STK"
        );
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) itemClassificationCode.node();
        assertElementNameIs(element, "ItemClassificationCode", CBC_NS);

        assertThat(element.text(), equalTo("9873242"));
        assertThat(element.attrs().get("listID"), equalTo("STK"));
        assertThat(element.attrs().size(), equalTo(1));
    }

    @Test
    void to_xml_optional_attrs() {
        itemClassificationCode.withlistVersionID("19.0501");

        final Element element = (Element) itemClassificationCode.node();
        assertThat(element.attrs().get("listVersionID"), equalTo("19.0501"));
    }
}
