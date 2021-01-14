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
import peppol.bis.invoice3.domain.Attachment;
import peppol.bis.invoice3.domain.DeliveryLocation;
import peppol.bis.invoice3.domain.DeliveryParty;
import peppol.bis.invoice3.domain.EmbeddedDocumentBinaryObject;
import peppol.bis.invoice3.domain.ExternalReference;
import peppol.bis.invoice3.domain.PartyName;

import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

public class AttachmentTest {

    private Attachment attachment;

    @BeforeEach
    void setUp() {
        attachment = new Attachment();
    }

    @Test
    void to_xml_required_elements() {
        final Element element = (Element) attachment.node();
        assertElementNameIs(element, "Attachment", CAC_NS);

        assertUnsetOptionalElement(element, "EmbeddedDocumentBinaryObject");
        assertUnsetOptionalElement(element, "ExternalReference");
    }

    @Test
    void to_xml_optional_elements() {
        attachment
            .withExternalReference(new ExternalReference("uri"))
            .withEmbeddedDocumentBinaryObject(new EmbeddedDocumentBinaryObject("", "", ""));


        final Element element = (Element) attachment.node();

        assertRequiredElement(element, "EmbeddedDocumentBinaryObject");
        assertRequiredElement(element, "ExternalReference");
    }
}
