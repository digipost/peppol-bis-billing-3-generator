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
package peppol.bis.invoice3.api;

import org.w3c.dom.Document;
import peppol.bis.invoice3.xml.Xml;
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.ExampleUsage1;
import peppol.bis.invoice3.validation.ValidationResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PeppolBillingApiTest {

    @Test
    public void test() throws IOException {
        try (InputStream inputStream = PeppolBillingApiTest.class.getResourceAsStream("/norwegian-example.xml")) {
            assertNotNull(inputStream);
            Document document = Xml.parse(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
            PeppolBillingApi<Document> peppolBillingApi = PeppolBillingApi.create(document);
            assertEquals("NO", peppolBillingApi.getCustomerCountryIdentifier());
            assertEquals("NO", peppolBillingApi.getSupplierCountryIdentifier());
            assertEquals("0192:123456785", peppolBillingApi.getSupplierEndpointID());
        }
    }

    @Test
    void generatedXmlRoundTripsThroughStandardDom() {
        PeppolBillingApi<?> generatedApi = PeppolBillingApi.create(ExampleUsage1.norwegianExample());
        String generated = generatedApi.prettyPrint();

        Document document = Xml.parse(generated);
        PeppolBillingApi<Document> parsed = PeppolBillingApi.create(document);
        ValidationResult validation = generatedApi.validate();

        assertTrue(parsed.isInvoice());
        assertEquals("NO", parsed.getCustomerCountryIdentifier());
        assertEquals("NO", parsed.getSupplierCountryIdentifier());
        assertEquals("0192:123456785", parsed.getSupplierEndpointID());
        assertTrue(validation.isValid(), () -> String.join("; ", validation.errors()));
    }

    @Test
    void parserRejectsDoctypeDeclarations() {
        String xml = "<!DOCTYPE Invoice [<!ENTITY xxe SYSTEM \"file:///etc/passwd\">]>"
            + "<Invoice>&xxe;</Invoice>";

        assertThrows(IllegalArgumentException.class, () -> Xml.parse(xml));
    }
}
