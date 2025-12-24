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
import peppol.bis.invoice3.domain.CompanyID;
import peppol.bis.invoice3.domain.PaymentMeansCode;
import peppol.bis.invoice3.domain.codes.ElectronicAddressScheme;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CompanyIDTest {

    @Test
    void CompanyID_to_xml() {
        final Element element = (Element) new CompanyID("987654321").node();
        assertThat(element.getName().getName(), equalTo("CompanyID"));
        assertThat(element.text(), equalTo("987654321"));
        assertThat(element.attrs().get("schemeID"), equalTo(null));
    }

    @Test
    void CompanyID_to_xml_with_name() {
        final Element element = (Element) new CompanyID("987654321").withSchemeID(ElectronicAddressScheme.EAS_0002).node();
        assertThat(element.getName().getName(), equalTo("CompanyID"));
        assertThat(element.text(), equalTo("987654321"));
        assertThat(element.attrs().get("schemeID"), equalTo("0002"));
    }

    @Test
    void CompanyID_to_xml_with_nameAsStringValid() {
        final Element element = (Element) new CompanyID("987654321").withSchemeID("0002").node();
        assertThat(element.getName().getName(), equalTo("CompanyID"));
        assertThat(element.text(), equalTo("987654321"));
        assertThat(element.attrs().get("schemeID"), equalTo("0002"));
    }

    @Test
    void CompanyID_to_xml_with_nameAsStringInvalid() {
        assertThrows(IllegalArgumentException.class, ()-> new CompanyID("987654321").withSchemeID("DUMMY"));


    }

}
