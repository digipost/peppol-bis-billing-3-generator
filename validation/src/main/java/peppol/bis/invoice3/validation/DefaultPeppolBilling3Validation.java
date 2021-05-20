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

import com.helger.commons.error.list.ErrorList;
import com.helger.commons.io.resource.inmemory.ReadableResourceString;
import com.helger.phive.api.execute.ValidationExecutionManager;
import com.helger.phive.api.executorset.IValidationExecutorSet;
import com.helger.phive.api.executorset.VESID;
import com.helger.phive.api.executorset.ValidationExecutorSetRegistry;
import com.helger.phive.api.result.ValidationResultList;
import com.helger.phive.engine.source.IValidationSourceXML;
import com.helger.phive.engine.source.ValidationSourceXML;
import com.helger.phive.peppol.PeppolValidation;
import com.helger.phive.peppol.PeppolValidation3_11_1;
import org.eaxy.Document;
import peppol.bis.invoice3.domain.BillingCommon;
import peppol.bis.invoice3.domain.CreditNote;
import peppol.bis.invoice3.domain.Invoice;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class DefaultPeppolBilling3Validation implements PeppolBilling3Validation {

    private static ValidationExecutorSetRegistry<IValidationSourceXML> validationExecutorSetRegistry;
    private static VESID vesid_invoice;
    private static VESID vesid_creditNote;

    {
        if (vesid_invoice == null)
            DefaultPeppolBilling3Validation.setVesid_invoice(PeppolValidation3_11_1.VID_OPENPEPPOL_INVOICE_V3);
        if (vesid_creditNote == null)
            DefaultPeppolBilling3Validation.setVesid_creditNote(PeppolValidation3_11_1.VID_OPENPEPPOL_CREDIT_NOTE_V3);
    }

    @Override
    public <TYPE extends BillingCommon<TYPE>> ValidationResult isValid(TYPE billing) {
        IValidationExecutorSet<IValidationSourceXML> aVES = null;
        if (billing instanceof Invoice) {
            aVES = validationExecutorSetRegistry.getOfID(vesid_invoice);
        }
        if (billing instanceof CreditNote) {
            aVES = validationExecutorSetRegistry.getOfID(vesid_creditNote);
        }

        if (aVES != null) {
            return doValidation(aVES, billing.xmlRoot().toXML());
        }
        throw new IllegalStateException("Expected validation source is not available on classpath");
    }

    @Override
    public ValidationResult isValid(Document billingDocument) {
        IValidationExecutorSet<IValidationSourceXML> aVES = null;
        if (billingDocument.getRootElement().getNamespace(null).getUri().endsWith("Invoice-2")) {
            aVES = validationExecutorSetRegistry.getOfID(vesid_invoice);
        }
        if (billingDocument.getRootElement().getNamespace(null).getUri().endsWith("CreditNote-2")) {
            aVES = validationExecutorSetRegistry.getOfID(vesid_creditNote);
        }

        if (aVES != null) {
            return doValidation(aVES, billingDocument.toXML());
        }
        throw new IllegalStateException("Expected validation source is not available on classpath");
    }

    private ValidationResult doValidation(IValidationExecutorSet<IValidationSourceXML> aVES, String s) {
        // Shortcut introduced in v6
        final ValidationSourceXML validationSourceXML = ValidationSourceXML.create(new ReadableResourceString(s, StandardCharsets.UTF_8));

        final ValidationResultList vResult = ValidationExecutionManager.executeValidation(aVES, validationSourceXML);
        if (vResult.containsAtLeastOneError()) {

            return new DefaultValidationResult(Validity.INVALID, getTextFrom(vResult.getAllErrors()), getTextFrom(vResult.getAllFailures()));
        } else if (vResult.containsAtLeastOneFailure()) {

            return new DefaultValidationResult(Validity.WITH_WARNINGS, emptyList(), getTextFrom(vResult.getAllFailures()));
        }

        return new DefaultValidationResult(Validity.VALID, emptyList(), emptyList());
    }

    public static void setVesid_invoice(VESID vesid_invoice) {
        DefaultPeppolBilling3Validation.validationExecutorSetRegistry = new ValidationExecutorSetRegistry<>();
        DefaultPeppolBilling3Validation.vesid_invoice                 = vesid_invoice;
        PeppolValidation.initStandard(validationExecutorSetRegistry);
        PeppolValidation.initThirdParty(validationExecutorSetRegistry);
    }

    public static void setVesid_creditNote(VESID vesid_creditNote) {
        DefaultPeppolBilling3Validation.validationExecutorSetRegistry = new ValidationExecutorSetRegistry<>();
        DefaultPeppolBilling3Validation.vesid_creditNote              = vesid_creditNote;
        PeppolValidation.initStandard(validationExecutorSetRegistry);
        PeppolValidation.initThirdParty(validationExecutorSetRegistry);
    }

    private List<String> getTextFrom(ErrorList errorList) {
        return errorList.stream()
            .map(s -> s.getAsString(Locale.getDefault()))
            .collect(Collectors.toList());
    }
}
