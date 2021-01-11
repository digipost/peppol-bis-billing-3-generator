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

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private String description;
    private BuyersItemIdentification buyersItemIdentification;
    private SellersItemIdentification sellersItemIdentification;
    private StandardItemIdentification standardItemIdentification;
    private Country originCountry;
    private List<CommodityClassification> commodityClassifications = new ArrayList<>();
    private ClassifiedTaxCategory classifiedTaxCategory;
    private List<AdditionalItemProperty> additionalItemProperties = new ArrayList<>();

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

    public Item withOriginCountry(Country originCountry) {
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
}
