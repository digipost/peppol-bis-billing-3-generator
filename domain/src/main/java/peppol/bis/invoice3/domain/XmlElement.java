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
import org.eaxy.Node;
import org.eaxy.QualifiedName;
import org.eaxy.Xml;

import java.util.List;
import java.util.Optional;

public interface XmlElement {
    Node node();

    default String name() {
        return this.getClass().getSimpleName();
    }

    default void required(XmlElement node, Element elm) {
        elm.add(node.node());
    }

    default void required(String value, String name, Element elm, Namespace ns) {
        elm.add(Xml.el(new QualifiedName(ns, name), Xml.text(value)));
    }
    default void optional(XmlElement node, Element elm) {
        Optional.ofNullable(node).ifPresent(n -> elm.add(n.node()));
    }

    default void optional(String value, String name, Element elm, Namespace ns) {
        Optional.ofNullable(value).map((v) -> elm.add(Xml.el(new QualifiedName(ns, name), Xml.text(v))));
    }

    default void list(List<XmlElement> list, Element elm){
        list.stream().map(XmlElement::node).forEach(elm::add);
    }
}
