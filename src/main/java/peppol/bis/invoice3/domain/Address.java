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

public class Address {

    private String streetName;
    private String additionalStreetName;
    private String cityName;
    private String postalZone;
    private String countrySubentity;
    private Country country;
    private AddressLine addressLine;

    public Address(Country country) {
        this.country = country;
    }

    public Address withAddressLine(AddressLine addressLine) {
        this.addressLine = addressLine;
        return this;
    }

    public Address withCountrySubentity(String countrySubentity) {
        this.countrySubentity = countrySubentity;
        return this;
    }

    public Address withPostalZone(String postalZone) {
        this.postalZone = postalZone;
        return this;
    }

    public Address withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public Address withAdditionalStreetName(String additionalStreetName) {
        this.additionalStreetName = additionalStreetName;
        return this;
    }

    public Address withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

}
