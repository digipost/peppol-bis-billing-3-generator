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
import org.eaxy.Node;
import org.eaxy.QualifiedName;
import org.eaxy.Xml;

import java.util.ArrayList;
import java.util.List;

import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class Party implements XmlElement {

    private EndpointID endpointID;
    private PartyIdentification partyIdentification;
    private PartyName partyName;
    private PostalAddress postalAddress;
    private List<XmlElement> partyTaxSchemes = new ArrayList<>();
    private PartyLegalEntity partyLegalEntity;
    private Contact contact;

    public Party(EndpointID endpointID, PostalAddress postalAddress, PartyLegalEntity partyLegalEntity) {
        this.endpointID = endpointID;
        this.postalAddress = postalAddress;
        this.partyLegalEntity = partyLegalEntity;
    }

    public Party withPartyIdentification(PartyIdentification partyIdentification) {
        this.partyIdentification = partyIdentification;
        return this;
    }

    public Party withPartyName(PartyName partyName) {
        this.partyName = partyName;
        return this;
    }

    public Party withPartyTaxScheme(PartyTaxScheme partyTaxScheme) {
        if (this.partyTaxSchemes.size() >= 2) throw new IllegalArgumentException("Max 2 PartyTaxScheme pr Party");
        this.partyTaxSchemes.add(partyTaxScheme);
        return this;
    }

    public Party withContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    @Override
    public Node node() {
        final Element elm = Xml.el(new QualifiedName(CAC_NS, name()));

        required(this.endpointID, elm);
        optional(this.partyIdentification, elm);
        optional(this.partyName, elm);
        required(this.postalAddress, elm);
        list(this.partyTaxSchemes, elm);
        required(this.partyLegalEntity, elm);
        optional(this.contact, elm);

        return elm;
    }

}
