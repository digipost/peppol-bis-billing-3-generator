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

import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.api.Validate;
import peppol.bis.invoice3.domain.ExampleUsage1;
import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.validation.ValidationResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvoiceApiTest {

    @Test
    void code_examples_for_api() {
        final Invoice invoice = ExampleUsage1.example1();
        assertThat(invoice.xmlRoot().toXML(), containsString("<Invoice"));

        final ValidationResult result = new Validate(invoice).result();
        assertTrue(result.isValid(), "We except the example to be a valid peppol billing. But has errors: \n" + String.join("\n", result.errors()));

        System.out.println(String.join("Warns: \n", result.warns()));

        // Psudo: DigipostClient.send(invoice.xmlRoot().asInputStream());
    }
}
