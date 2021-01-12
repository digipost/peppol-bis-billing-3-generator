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

import java.util.ArrayList;
import java.util.List;

import static peppol.bis.invoice3.domain.Namespaces.CAC_NS;
import static peppol.bis.invoice3.domain.Namespaces.CBC_NS;

public class Item implements XmlElement {
    private final String name;
    private String description;
    private BuyersItemIdentification buyersItemIdentification;
    private SellersItemIdentification sellersItemIdentification;
    private StandardItemIdentification standardItemIdentification;
    private OriginCountry originCountry;
    private final List<XmlElement> commodityClassifications = new ArrayList<>();
    private final ClassifiedTaxCategory classifiedTaxCategory;
    private final List<XmlElement> additionalItemProperties = new ArrayList<>();

    public Item(String name, ClassifiedTaxCategory classifiedTaxCategory) {
        this.name = name;
        this.classifiedTaxCategory = classifiedTaxCategory;
    }

    public Item withDescription(String description) {
        this.description = description;
        return this;
    }

    public Item withBuyersItemIdentification(BuyersItemIdentification buyersItemIdentification) {
        this.buyersItemIdentification = buyersItemIdentification;
        return this;
    }

    public Item withSellersItemIdentification(SellersItemIdentification sellersItemIdentification) {
        this.sellersItemIdentification = sellersItemIdentification;
        return this;
    }

    public Item withStandardItemIdentification(StandardItemIdentification standardItemIdentification) {
        this.standardItemIdentification = standardItemIdentification;
        return this;
    }

    public Item withOriginCountry(OriginCountry originCountry) {
        this.originCountry = originCountry;
        return this;
    }

    public Item withCommodityClassification(CommodityClassification commodityClassification) {
        this.commodityClassifications.add(commodityClassification);
        return this;
    }

    public Item withAdditionalItemProperty(AdditionalItemProperty additionalItemProperty) {
        this.additionalItemProperties.add(additionalItemProperty);
        return this;
    }

    @Override
    public Node node() {
        final Element elm = Xml.el(new QualifiedName(CAC_NS, name()));

        required(this.name, "Name", elm, CBC_NS);
        required(this.classifiedTaxCategory, elm);

        optional(this.description, "Description", elm, CBC_NS);

        optional(this.buyersItemIdentification, elm);
        optional(this.sellersItemIdentification, elm);
        optional(this.standardItemIdentification, elm);
        optional(this.originCountry, elm);

        list(this.commodityClassifications, elm);
        list(this.additionalItemProperties, elm);

        return elm;
    }
}
