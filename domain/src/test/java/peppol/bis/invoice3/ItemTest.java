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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peppol.bis.invoice3.domain.AdditionalItemProperty;
import peppol.bis.invoice3.domain.BuyersItemIdentification;
import peppol.bis.invoice3.domain.ClassifiedTaxCategory;
import peppol.bis.invoice3.domain.CommodityClassification;
import peppol.bis.invoice3.domain.Item;
import peppol.bis.invoice3.domain.ItemClassificationCode;
import peppol.bis.invoice3.domain.OriginCountry;
import peppol.bis.invoice3.domain.SellersItemIdentification;
import peppol.bis.invoice3.domain.StandardItemIdentification;
import peppol.bis.invoice3.domain.TaxScheme;

import static org.hamcrest.Matchers.equalTo;
import static peppol.bis.invoice3.XmlAsserts.assertElementNameIs;
import static peppol.bis.invoice3.XmlAsserts.assertRequiredElement;
import static peppol.bis.invoice3.XmlAsserts.assertUnsetOptionalElement;
import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item("Laptop computer", new ClassifiedTaxCategory("S", new TaxScheme("VAT")));
    }

    @Test
    void to_xml_basic_elements() {
        final Element element = (Element) item.node();
        assertElementNameIs(element, "Item", CAC_NS);

        assertRequiredElement(element, "Name", equalTo("Laptop computer"));
        assertRequiredElement(element, "ClassifiedTaxCategory");

        assertUnsetOptionalElement(element, "Description");
        assertUnsetOptionalElement(element, "BuyersItemIdentification");
        assertUnsetOptionalElement(element, "SellersItemIdentification");
        assertUnsetOptionalElement(element, "StandardItemIdentification");
        assertUnsetOptionalElement(element, "OriginCountry");
        assertUnsetOptionalElement(element, "CommodityClassification");
        assertUnsetOptionalElement(element, "AdditionalItemProperty");
    }

    @Test
    void to_xml_optional_elements() {
        item
            .withDescription("A description")
            .withAdditionalItemProperty(new AdditionalItemProperty("key", "val"))
            .withBuyersItemIdentification(new BuyersItemIdentification("1"))
            .withStandardItemIdentification(new StandardItemIdentification("2"))
            .withSellersItemIdentification(new SellersItemIdentification("3"))
            .withCommodityClassification(new CommodityClassification(new ItemClassificationCode("65434568", "STI")))
            .withOriginCountry(new OriginCountry("NO"))
        ;
        final Element element = (Element) item.node();

        assertRequiredElement(element, "Description", equalTo("A description"));
        assertRequiredElement(element, "AdditionalItemProperty");
        assertRequiredElement(element, "BuyersItemIdentification");
        assertRequiredElement(element, "SellersItemIdentification");
        assertRequiredElement(element, "CommodityClassification");
        assertRequiredElement(element, "OriginCountry");
    }

}
