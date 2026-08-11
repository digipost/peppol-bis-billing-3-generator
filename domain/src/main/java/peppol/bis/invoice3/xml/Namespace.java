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
package peppol.bis.invoice3.xml;

import javax.xml.namespace.QName;
import java.util.Objects;

/** A namespace declaration used by the small DOM builder. */
public final class Namespace {

    private final String uri;
    private final String prefix;

    public Namespace(String uri) {
        this(uri, "");
    }

    public Namespace(String uri, String prefix) {
        this.uri = Objects.requireNonNull(uri, "uri");
        this.prefix = Objects.requireNonNull(prefix, "prefix");
    }

    public String getUri() {
        return uri;
    }

    public String getPrefix() {
        return prefix;
    }

    public QName name(String localName) {
        return new QName(uri, localName, prefix);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Namespace)) return false;
        Namespace namespace = (Namespace) object;
        return uri.equals(namespace.uri) && prefix.equals(namespace.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, prefix);
    }
}
