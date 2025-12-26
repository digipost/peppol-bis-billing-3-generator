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
import peppol.bis.invoice3.domain.codes.ElectronicAddressScheme;
import peppol.bis.invoice3.domain.codes.PeppolCodeResolver;

import java.util.Optional;

import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class EndpointID implements XmlElement{
    private final String value;
    private ElectronicAddressScheme schemeID;

    public EndpointID(String value) {
        this.value = value;
    }

    /**
     * @deprecated
     */
    public EndpointID withSchemeID(String schemeID) {
       this.schemeID = PeppolCodeResolver.fromCode(ElectronicAddressScheme.class, schemeID);
       return this;
    }

    public EndpointID withSchemeID(ElectronicAddressScheme schemeID) {
        this.schemeID = schemeID;
        return this;
    }

    @Override
    public Node node() {
        final Element el = Xml.el(
            new QualifiedName(CBC_NS, this.name())
            , Xml.text(this.value)
        );

        Optional.ofNullable(this.schemeID).ifPresent(v -> el.attr("schemeID", v.getCode()));

        return el;
    }
}
