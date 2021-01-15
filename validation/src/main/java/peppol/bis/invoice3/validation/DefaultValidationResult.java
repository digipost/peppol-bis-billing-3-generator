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

import java.util.List;

public class DefaultValidationResult implements ValidationResult {

    private final Validity validity;
    private final List<String> errors;
    private final List<String> warns;

    public DefaultValidationResult(Validity validity, List<String> errors, List<String> warns) {
        this.validity = validity;
        this.errors   = errors;
        this.warns    = warns;
    }

    @Override
    public Validity getValidity() {
        return this.validity;
    }

    @Override
    public List<String> errors() {
        return this.errors;
    }

    @Override
    public List<String> warns() {
        return this.warns;
    }
}
