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
import peppol.bis.invoice3.domain.ID;
import peppol.bis.invoice3.domain.StandardItemIdentification;

import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

class StandardItemIdentificationTest {

    private StandardItemIdentification standardItemIdentification;

    @BeforeEach
    void setUp() {
        standardItemIdentification = new StandardItemIdentification(
            new ID("foo12")
        );
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) standardItemIdentification.node();
        assertElementNameIs(element, "StandardItemIdentification", CAC_NS);

        assertRequiredElement(element, "ID", equalTo("foo12"));
    }
}
