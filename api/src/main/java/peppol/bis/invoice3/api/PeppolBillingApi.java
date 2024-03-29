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
import org.eaxy.Element;
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

    public boolean isCreditNote() {
        if (this.object instanceof CreditNote) {
            return true;
        }
        if (this.object instanceof Document) {
            return ((Document) this.object).getRootElement().getNamespace(null).getUri().endsWith("CreditNote-2");
        }
        return false;
    }

    public boolean isInvoice() {
        if (this.object instanceof Invoice) {
            return true;
        }
        if (this.object instanceof Document) {
            return ((Document) this.object).getRootElement().getNamespace(null).getUri().endsWith("Invoice-2");
        }
        return false;
    }

    public String getSupplierCountryIdentifier() {
        if (this.object instanceof Document) {
            return ((Document) this.object).find("AccountingSupplierParty", "Party", "PostalAddress", "Country", "IdentificationCode").single().text().trim();
        }
        throw new RuntimeException("Mandatory property missing in document: AccountingSupplierParty -> Party -> PostalAddress -> Country -> IdentificationCode");
    }

    public String getCustomerCountryIdentifier() {
        if (this.object instanceof Document) {
            return ((Document) this.object).find("AccountingCustomerParty", "Party", "PostalAddress", "Country", "IdentificationCode").single().text().trim();
        }
        throw new RuntimeException("Mandatory property missing in document: AccountingCustomerParty -> Party -> PostalAddress -> Country -> IdentificationCode");
    }

    public String getSupplierEndpointID() {
        if (this.object instanceof Document) {
            Element element = ((Document) this.object).find("AccountingSupplierParty", "Party", "EndpointID").single();
            return element.attr("schemeID").trim() + ":" + element.text().trim();
        }
        throw new RuntimeException("Mandatory property missing in document: AccountingSupplierParty -> Party -> EndpointID");
    }

    public String prettyPrint() {
        return XML_FIRST_LINE + (this.object instanceof BillingCommon ? ((BillingCommon) this.object).xmlRoot() : ((Document) this.object).getRootElement()).toIndentedXML();
    }


    public InputStream inputStream() {
        return new ByteArrayInputStream(this.prettyPrint().getBytes(StandardCharsets.UTF_8));
    }
}
