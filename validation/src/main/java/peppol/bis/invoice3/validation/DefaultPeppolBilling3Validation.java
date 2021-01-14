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

import com.helger.commons.io.resource.inmemory.ReadableResourceString;
import com.helger.phive.api.execute.ValidationExecutionManager;
import com.helger.phive.api.executorset.IValidationExecutorSet;
import com.helger.phive.api.executorset.ValidationExecutorSetRegistry;
import com.helger.phive.api.result.ValidationResultList;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.source.ValidationSourceXML;
import com.helger.phive.peppol.PeppolValidation;
import com.helger.phive.peppol.PeppolValidation391;
import com.helger.phive.peppol.PeppolValidation3_11_1;
import org.eaxy.Element;
import peppol.bis.invoice3.domain.Invoice;

import java.nio.charset.StandardCharsets;

public class DefaultPeppolBilling3Validation implements PeppolBilling3Validation {

    @Override
    public boolean isInvoiceValid(Invoice invoice) {
        final Element element = invoice.xmlRoot();

        final ValidationExecutorSetRegistry<IValidationSourceXML> validationExecutorSetRegistry = new ValidationExecutorSetRegistry<>();

        PeppolValidation.initStandard (validationExecutorSetRegistry);
        PeppolValidation.initThirdParty (validationExecutorSetRegistry);

        final IValidationExecutorSet<IValidationSourceXML> aVES = validationExecutorSetRegistry.getOfID(PeppolValidation391.VID_OPENPEPPOL_INVOICE_V3);
        if (aVES != null) {
            // Shortcut introduced in v6
            final ValidationSourceXML validationSourceXML = ValidationSourceXML.create(new ReadableResourceString(invoice.xmlRoot().toXML(), StandardCharsets.UTF_8));

            final ValidationResultList aValidationResult = ValidationExecutionManager.executeValidation(aVES, validationSourceXML);
            if (aValidationResult.containsAtLeastOneError()) {
                // errors found ...
            } else {
                // no errors (but maybe warnings) found
            }

            return true;
        }
        return false;
    }
}
