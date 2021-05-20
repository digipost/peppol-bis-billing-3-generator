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

import com.helger.phive.peppol.PeppolValidation391;
import com.helger.phive.peppol.PeppolValidation3_11_1;
import org.eaxy.Document;
import org.eaxy.Xml;
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.api.PeppolBillingApi;
import peppol.bis.invoice3.api.Validate;
import peppol.bis.invoice3.domain.ExampleUsage1;
import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.validation.DefaultPeppolBilling3Validation;
import peppol.bis.invoice3.validation.ValidationResult;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PeppolBillingpiTest {

    @Test
    void code_examples_for_api_billing_3_0_9() {
        DefaultPeppolBilling3Validation.setVesid_invoice(PeppolValidation3_11_1.VID_OPENPEPPOL_INVOICE_V3);

        final Invoice invoice = ExampleUsage1.example1();
        assertThat(invoice.xmlRoot().toXML(), containsString("<Invoice"));

        final ValidationResult result = new Validate(invoice).result();
        System.out.println(String.join("Warns: \n", result.warns()));
        assertFalse(result.isValid(), "We except the example to be an invalid peppol billing");
    }

    @Test
    void code_examples_for_api_billing_3_0_5h() {
        DefaultPeppolBilling3Validation.setVesid_invoice(PeppolValidation391.VID_OPENPEPPOL_INVOICE_V3);

        final Invoice invoice = ExampleUsage1.example1();
        assertThat(invoice.xmlRoot().toXML(), containsString("<Invoice"));

        final ValidationResult result = new Validate(invoice).result();
        System.out.println(String.join("Warns: \n", result.warns()));

        assertTrue(result.isValid(), "We except the example to be a valid peppol billing. But has errors: \n" + String.join("\n", result.errors()));

    }

    @Test
    void Norwegian_code_examples_for_api_billing_3_0_5h() {
        DefaultPeppolBilling3Validation.setVesid_invoice(PeppolValidation3_11_1.VID_OPENPEPPOL_INVOICE_V3);

        final Invoice invoice = ExampleUsage1.norwegianExample();
        assertThat(invoice.xmlRoot().toXML(), containsString("<Invoice"));

        final ValidationResult result = new Validate(invoice).result();
        System.out.println(String.join("Warns: \n", result.warns()));

        assertTrue(result.isValid(), "We except the example to be a valid peppol billing. But has errors: \n" + String.join("\n", result.errors()));

    }

    @Test
    void should_extract_type_from_namespace() throws IOException {
        final Document document = Xml.readResource("norwegian-example.xml");

        assertThat(document.getRootElement().getNamespace(null).getUri(), containsString("Invoice-2"));
    }

    @Test
    void should_validate_xml_file() throws IOException {
        final Document document = Xml.readResource("norwegian-example.xml");

        final PeppolBillingApi<Document> api = PeppolBillingApi.create(document);

        final ValidationResult result = api.validate();
        assertTrue(result.isValid(), "We except the example to be a valid peppol billing. But has errors: \n" + String.join("\n", result.errors()));
    }
}
