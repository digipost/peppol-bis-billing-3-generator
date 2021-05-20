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
import peppol.bis.invoice3.domain.BillingCommon;
import peppol.bis.invoice3.domain.CreditNote;
import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.validation.ValidationResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PeppolBillingApi<T> {

    private static final String XML_FIRST_LINE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

    public static PeppolBillingApi<Invoice> create(Invoice xmlRootElement) {
        return new PeppolBillingApi<>(xmlRootElement);
    }

    public static PeppolBillingApi<CreditNote> create(CreditNote xmlRootElement) {
        return new PeppolBillingApi<>(xmlRootElement);
    }

    public static PeppolBillingApi<Document> create(Document document) {
        return new PeppolBillingApi<>(document);
    }

    private final T object;

    public PeppolBillingApi(T xmlRootElement) {
        this.object = xmlRootElement;
    }

    public ValidationResult validate() {
        return new Validate(this.object).result();
    }

    public String prettyPrint() {
        return XML_FIRST_LINE + (this.object instanceof BillingCommon ? ((BillingCommon) this.object).xmlRoot() : ((Document) this.object).getRootElement()).toIndentedXML();
    }


    public InputStream inputStream() {
        return new ByteArrayInputStream(this.prettyPrint().getBytes(StandardCharsets.UTF_8));
    }
}
