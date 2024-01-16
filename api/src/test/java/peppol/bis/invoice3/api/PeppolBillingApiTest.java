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

import org.eaxy.Document;
import org.eaxy.Xml;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PeppolBillingApiTest {

    @Test
    public void test() throws IOException {
        try (InputStream inputStream = PeppolBillingApiTest.class.getResourceAsStream("/norwegian-example.xml")) {
            assertNotNull(inputStream);
            Document document = Xml.xml(new String(inputStream.readAllBytes()));
            PeppolBillingApi<Document> peppolBillingApi = new PeppolBillingApi<>(document);
            assertEquals("NO", peppolBillingApi.getCustomerCountryIdentifier());
            assertEquals("NO", peppolBillingApi.getSupplierCountryIdentifier());
            assertEquals("0192:123456785", peppolBillingApi.getSupplierEndpointID());
        }
    }

}
