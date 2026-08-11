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

import org.w3c.dom.Element;
import peppol.bis.invoice3.xml.Namespace;
import peppol.bis.invoice3.xml.Xml;

import java.util.List;
import java.util.Optional;

public interface XmlElement {
    Element node();

    default String name() {
        return this.getClass().getSimpleName();
    }

    default void required(XmlElement node, Element elm) {
        Xml.append(elm, node.node());
    }

    default void required(String value, String name, Element elm, Namespace ns) {
        Xml.append(elm, Xml.el(ns.name(name), Xml.text(value)));
    }
    default void optional(XmlElement node, Element elm) {
        Optional.ofNullable(node).filter(s -> !s.empty()).ifPresent(n -> Xml.append(elm, n.node()));
    }

    default void optional(String value, String name, Element elm, Namespace ns) {
        Optional.ofNullable(value).ifPresent(v -> Xml.append(elm, Xml.el(ns.name(name), Xml.text(v))));
    }

    default void list(List<XmlElement> list, Element elm){
        list.stream().map(XmlElement::node).forEach(node -> Xml.append(elm, node));
    }

    default boolean empty(){
        return false;
    }
}
