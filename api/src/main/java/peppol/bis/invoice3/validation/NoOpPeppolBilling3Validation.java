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
package peppol.bis.invoice3.validation;

import org.eaxy.Document;
import peppol.bis.invoice3.domain.BillingCommon;
import peppol.bis.invoice3.domain.XmlRootElement;

import java.util.Collections;
import java.util.List;

public class NoOpPeppolBilling3Validation implements PeppolBilling3Validation {
    @Override
    public ValidationResult isValid(Document billingDocument) {
        return getValidationResult();
    }

    @Override
    public <TYPE extends BillingCommon<TYPE>> ValidationResult isValid(TYPE billing) {
        return getValidationResult();
    }

    private ValidationResult getValidationResult() {
        return new ValidationResult() {
            @Override
            public Validity getValidity() {
                return Validity.VALID;
            }

            @Override
            public List<String> errors() {
                return Collections.emptyList();
            }

            @Override
            public List<String> warns() {
                return Collections.emptyList();
            }
        };
    }
}
