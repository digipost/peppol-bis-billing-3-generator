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
package peppol.bis.invoice3.domain;

import org.eaxy.Element;
import org.eaxy.Namespace;
import org.eaxy.Xml;

import java.util.Arrays;

import static java.lang.String.format;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public abstract class BillingCommon<SUBCLASS> implements XmlRootElement, XmlElement {

    private final String customizationID = "urn:cen.eu:en16931:2017#compliant#urn:fdc:peppol.eu:2017:poacc:billing:3.0";

    private String profileID;
    private final String id;
    private final String issueDate;

    protected String taxPointDate;

    protected abstract Namespace ROOT_NS();

    public BillingCommon(String id, String issueDate) {
        this.id        = id;
        this.issueDate = issueDate;
        this.withProcessNumber(1);
    }

    public SUBCLASS withProcessNumber(int processNumber) {
        this.profileID = "urn:fdc:peppol.eu:2017:poacc:billing:NN:1.0".replace("NN", format("%02d", processNumber));
        return (SUBCLASS) this;
    }

    public SUBCLASS withTaxPointDate(String taxPointDate) {
        this.taxPointDate = taxPointDate;
        return (SUBCLASS) this;
    }

    public String getProfileID() {
        return profileID;
    }

    @Override
    public Element node() {
        final Element elm = Xml.el(name());
        elm.extendNamespaces(Arrays.asList(ROOT_NS(), CAC_NS, CBC_NS));

        required(this.customizationID, "CustomizationID", elm, CBC_NS);

        required(this.profileID, "ProfileID", elm, CBC_NS);
        required(this.id, "ID", elm, CBC_NS);
        required(this.issueDate, "IssueDate", elm, CBC_NS);


        return elm;
    }

}
