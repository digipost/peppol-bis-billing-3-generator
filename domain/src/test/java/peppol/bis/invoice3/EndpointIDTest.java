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
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.EndpointID;
import peppol.bis.invoice3.domain.codes.ElectronicAddressScheme;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


class EndpointIDTest {

    @Test
    void EndpointID_to_xml() {
        final Element element = (Element) new EndpointID("7300010000001").node();
        assertThat(element.getName().getName(), equalTo("EndpointID"));
        assertThat(element.text(), equalTo("7300010000001"));
        assertThat(element.attrs().get("schemeID"), equalTo(null));
    }

    @Test
    void EndpointID_to_xml_with_name() {
        final Element element = (Element) new EndpointID("7300010000001").withSchemeID(ElectronicAddressScheme.EAS_0088).node();
        assertThat(element.getName().getName(), equalTo("EndpointID"));
        assertThat(element.text(), equalTo("7300010000001"));
        assertThat(element.attrs().get("schemeID"), equalTo("0088"));
    }

    @Test
    void EndpointID_to_xml_with_nameAsString() {
        final Element element = (Element) new EndpointID("7300010000001").withSchemeID("0088").node();
        assertThat(element.getName().getName(), equalTo("EndpointID"));
        assertThat(element.text(), equalTo("7300010000001"));
        assertThat(element.attrs().get("schemeID"), equalTo("0088"));

        assertThrows(IllegalArgumentException.class, ()-> new EndpointID("7300010000001").withSchemeID("DUMMY"));
    }

}
