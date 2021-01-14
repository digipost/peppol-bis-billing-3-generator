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
package peppol.bis.invoice3.api;

import org.eaxy.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peppol.bis.invoice3.InvoiceApi;
import peppol.bis.invoice3.domain.Invoice;

public class To {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceApi.class);

    private final Invoice invoice;
    private Element asXml;

    public To(Invoice invoice) {
        this.invoice = invoice;
    }

    public To log() {
        LOGGER.info(xml().toIndentedXML());
        return this;
    }

    public Element xml() {
        if (asXml == null) {
            asXml = invoice.xmlRoot();
        }
        return asXml;
    }

}
