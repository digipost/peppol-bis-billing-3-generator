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
import peppol.bis.invoice3.domain.EmbeddedDocumentBinaryObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class EmbeddedDocumentBinaryObjectTest {

    private EmbeddedDocumentBinaryObject embeddedDocumentBinaryObject;

    @BeforeEach
    void setUp() {
        embeddedDocumentBinaryObject = new EmbeddedDocumentBinaryObject(
            "text/csv", "Hours-spent.csv", "aHR0cHM6Ly90ZXN0LXZlZmEuZGlmaS5uby9wZXBwb2xiaXMvcG9hY2MvYmlsbGluZy8zLjAvYmlzLw=="
        );
    }

    @Test
    void to_xml_required_elements() {
        final Element element = (Element) embeddedDocumentBinaryObject.node();
        assertElementNameIs(element, "EmbeddedDocumentBinaryObject", CBC_NS);

        assertThat(element.text(), equalTo("aHR0cHM6Ly90ZXN0LXZlZmEuZGlmaS5uby9wZXBwb2xiaXMvcG9hY2MvYmlsbGluZy8zLjAvYmlzLw=="));
        assertThat(element.attrs().get("mimeCode"), equalTo("text/csv"));
        assertThat(element.attrs().get("filename"), equalTo("Hours-spent.csv"));
    }

}
