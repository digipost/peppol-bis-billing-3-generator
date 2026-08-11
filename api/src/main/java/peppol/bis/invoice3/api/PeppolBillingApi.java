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
import org.w3c.dom.Element;
import peppol.bis.invoice3.domain.BillingCommon;
import peppol.bis.invoice3.domain.CreditNote;
import peppol.bis.invoice3.domain.Invoice;
import peppol.bis.invoice3.validation.ValidationResult;
import peppol.bis.invoice3.xml.Xml;

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
            return ((Document) this.object).getDocumentElement().getNamespaceURI().endsWith("CreditNote-2");
        }
        return false;
    }

    public boolean isInvoice() {
        if (this.object instanceof Invoice) {
            return true;
        }
        if (this.object instanceof Document) {
            return ((Document) this.object).getDocumentElement().getNamespaceURI().endsWith("Invoice-2");
        }
        return false;
    }

    public String getSupplierCountryIdentifier() {
        if (this.object instanceof Document) {
            return Xml.single(Xml.find((Document) this.object, "AccountingSupplierParty", "Party", "PostalAddress", "Country", "IdentificationCode")).getTextContent().trim();
        }
        throw new RuntimeException("Mandatory property missing in document: AccountingSupplierParty -> Party -> PostalAddress -> Country -> IdentificationCode");
    }

    public String getCustomerCountryIdentifier() {
        if (this.object instanceof Document) {
            return Xml.single(Xml.find((Document) this.object, "AccountingCustomerParty", "Party", "PostalAddress", "Country", "IdentificationCode")).getTextContent().trim();
        }
        throw new RuntimeException("Mandatory property missing in document: AccountingCustomerParty -> Party -> PostalAddress -> Country -> IdentificationCode");
    }

    public String getSupplierEndpointID() {
        if (this.object instanceof Document) {
            Element element = Xml.single(Xml.find((Document) this.object, "AccountingSupplierParty", "Party", "EndpointID"));
            return element.getAttribute("schemeID").trim() + ":" + element.getTextContent().trim();
        }
        throw new RuntimeException("Mandatory property missing in document: AccountingSupplierParty -> Party -> EndpointID");
    }

    public String prettyPrint() {
        Element root = this.object instanceof BillingCommon
            ? ((BillingCommon) this.object).xmlRoot()
            : ((Document) this.object).getDocumentElement();
        return XML_FIRST_LINE + Xml.toIndentedXml(root);
    }


    public InputStream inputStream() {
        return new ByteArrayInputStream(this.prettyPrint().getBytes(StandardCharsets.UTF_8));
    }
}
