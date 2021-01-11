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

public class PostalAddress {

    private String streetName;
    private String additionalStreetName;
    private String cityName;
    private String postalZone;
    private String countrySubentity;
    private Country country;
    private AddressLine addressLine;

    public PostalAddress(Country country) {
        this.country = country;
    }

    public PostalAddress withAddressLine(AddressLine addressLine) {
        this.addressLine = addressLine;
        return this;
    }

    public PostalAddress withCountrySubentity(String countrySubentity) {
        this.countrySubentity = countrySubentity;
        return this;
    }

    public PostalAddress withPostalZone(String postalZone) {
        this.postalZone = postalZone;
        return this;
    }

    public PostalAddress withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public PostalAddress withAdditionalStreetName(String additionalStreetName) {
        this.additionalStreetName = additionalStreetName;
        return this;
    }

    public PostalAddress withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

}
