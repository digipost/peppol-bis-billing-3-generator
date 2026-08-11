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
import peppol.bis.invoice3.xml.Xml;

import java.util.Optional;

import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class PaymentMeansCode implements XmlElement {

    private final String code;
    private String name;

    public PaymentMeansCode(String code) {
        this.code = code;
    }

    public PaymentMeansCode withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Element node() {
        final Element el = Xml.el(
            CBC_NS.name(this.name())
            , Xml.text(this.code)
        );

        Optional.ofNullable(this.name).ifPresent(v -> Xml.attr(el, "name", v));

        return el;
    }
}
