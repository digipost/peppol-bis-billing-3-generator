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

import org.eaxy.Element;
import org.eaxy.Namespace;
import org.eaxy.QualifiedName;
import org.eaxy.Xml;
import peppol.bis.invoice3.domain.Invoice;

import java.util.Arrays;

public class InvoiceToXml {

    private final Namespace ROOT_NS;
    private final Namespace CAC_NS;
    private final Namespace CBC_NS;

    public InvoiceToXml() {
        ROOT_NS = new Namespace("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
        CAC_NS = new Namespace("urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2", "cac");
        CBC_NS = new Namespace("urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", "cbc");
    }

    public Element transform(Invoice invoice) {
        final Element root = Xml.el("Invoice");
        root.extendNamespaces(Arrays.asList(ROOT_NS, CAC_NS, CBC_NS));

        root.add(Xml.el(new QualifiedName(CBC_NS, "CustomizationID"), Xml.text(invoice.getCustomizationID())));
        root.add(Xml.el(new QualifiedName(CBC_NS, "ProfileID"), Xml.text(invoice.getProfileID())));
        root.add(Xml.el(new QualifiedName(CBC_NS, "ID"), Xml.text(invoice.getId())));
        root.add(Xml.el(new QualifiedName(CBC_NS, "IssueDate"), Xml.text(invoice.getIssueDate())));
        invoice.getDueDate().map(val -> root.add(Xml.el(new QualifiedName(CBC_NS, "DueDate"), Xml.text(val))));
        root.add(Xml.el(new QualifiedName(CBC_NS, "InvoiceTypeCode"), Xml.text(invoice.getInvoiceTypeCode())));
        root.add(Xml.el(new QualifiedName(CBC_NS, "DocumentCurrencyCode"), Xml.text(invoice.getDocumentCurrencyCode())));

        return root;
    }
}
