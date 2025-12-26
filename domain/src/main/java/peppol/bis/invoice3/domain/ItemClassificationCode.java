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
import peppol.bis.invoice3.domain.codes.ItemTypeIdentificationCode;
import peppol.bis.invoice3.domain.codes.PeppolCodeResolver;

import java.util.Optional;

import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class ItemClassificationCode implements XmlElement {
    private final String code;
    private final ItemTypeIdentificationCode listID;
    private String listVersionID;

    /**
     * @deprecated
     */
    public ItemClassificationCode(String code, String listID) {
        this.code = code;
        this.listID = PeppolCodeResolver.fromCode(ItemTypeIdentificationCode.class, listID);
    }

    public ItemClassificationCode(String code, ItemTypeIdentificationCode listID) {
        this.code = code;
        this.listID = listID;
    }

    public ItemClassificationCode withlistVersionID(String listVersionID) {
        this.listVersionID = listVersionID;
        return this;
    }

    @Override
    public Node node() {
        final Element el = Xml.el(
            new QualifiedName(CBC_NS, this.name())
            , Xml.text(this.code)
            , Xml.attr("listID", this.listID.getCode())
        );

        Optional.ofNullable(this.listVersionID).ifPresent(v -> el.attr("listVersionID", v));

        return el;
    }
}
