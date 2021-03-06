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

import org.eaxy.Document;
import peppol.bis.invoice3.domain.BillingCommon;
import peppol.bis.invoice3.validation.DefaultPeppolBilling3Validation;
import peppol.bis.invoice3.validation.NoOpPeppolBilling3Validation;
import peppol.bis.invoice3.validation.PeppolBilling3Validation;
import peppol.bis.invoice3.validation.ValidationResult;

public class Validate {

    private final Object object;
    private final PeppolBilling3Validation billing3Validation;

    public Validate(Object object) {
        this.object             = object;
        this.billing3Validation = peppolValidatorOnClasspath() ? new DefaultPeppolBilling3Validation() : new NoOpPeppolBilling3Validation();
    }

    public ValidationResult result() {
        if (this.object instanceof BillingCommon) {
            return this.billing3Validation.isValid((BillingCommon) this.object);
        } else {
            return this.billing3Validation.isValid((Document) this.object);
        }
    }

    public static boolean peppolValidatorOnClasspath() {
        try {
            Class.forName("peppol.bis.invoice3.validation.DefaultPeppolBilling3Validation");
            return true;
        } catch (Throwable ex) {
            return false;
        }
    }

}
