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

import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.domain.XmlRootElement;
import peppol.bis.invoice3.validation.ValidationResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PeppolBillingApi<T extends XmlRootElement> {

    public static PeppolBillingApi<Invoice> create(Invoice xmlRootElement) {
        return new PeppolBillingApi<>(xmlRootElement);
    }

    private final T object;

    public PeppolBillingApi(T xmlRootElement) {
        this.object = xmlRootElement;
    }

    public ValidationResult validate() {
        if (this.object instanceof Invoice) {
            return new Validate((Invoice) this.object).result();
        }
        throw new IllegalArgumentException("type " + this.object.getClass().getSimpleName() + " can not be validated now");
    }

    public String prettyPrint() {
        return this.object.xmlRoot().toIndentedXML();
    }

    public InputStream inputStream() {
        return new ByteArrayInputStream(this.object.xmlRoot().toXML().getBytes(StandardCharsets.UTF_8));
    }
}
