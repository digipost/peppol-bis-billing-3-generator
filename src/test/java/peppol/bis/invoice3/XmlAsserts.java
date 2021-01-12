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
package peppol.bis.invoice3;

import org.eaxy.Element;
import org.eaxy.Namespace;
import org.eaxy.NonMatchingPathException;
import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XmlAsserts {

    public static void assertElementNameIs(Element element, String name, Namespace cacNs) {
        assertThat(element.getName().getName(), equalTo(name));
        assertThat(element.getNamespace(cacNs.getPrefix()), equalTo(cacNs));
    }

    public static void assertRequiredElement(Element element, String name) {
        element.find(name).check();
    }

    public static void assertRequiredElement(Element element, String name, Matcher<String> matcher) {
        assertThat(element.find(name).first().text(), matcher);
    }

    public static void assertUnsetOptionalElement(Element element, String name){
        assertThrows(NonMatchingPathException.class, () -> element.find(name).check());
    }
}
