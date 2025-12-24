package peppol.bis.invoice3.domain.codes;

/**
 * VAT Exemption Reason Codes (CEF VATEX)
 *
 * Codes used in cbc:TaxExemptionReasonCode according to
 * Peppol BIS Billing 3.0 / EN 16931.
 *
 * NOTE:
 * Not all combinations of TaxCategoryIdentifier and VATEX codes are valid.
 * Invalid combinations will be rejected by Peppol validators.
 */
public enum TaxExemptionReasonCode {

  /** Exempt based on article 79, point c of Council Directive 2006/112/EC */
  VATEX_EU_79_C("VATEX-EU-79-C"),

  /** Exempt based on article 132 of Council Directive 2006/112/EC */
  VATEX_EU_132("VATEX-EU-132"),

  VATEX_EU_132_1A("VATEX-EU-132-1A"),
  VATEX_EU_132_1B("VATEX-EU-132-1B"),
  VATEX_EU_132_1C("VATEX-EU-132-1C"),
  VATEX_EU_132_1D("VATEX-EU-132-1D"),
  VATEX_EU_132_1E("VATEX-EU-132-1E"),
  VATEX_EU_132_1F("VATEX-EU-132-1F"),
  VATEX_EU_132_1G("VATEX-EU-132-1G"),
  VATEX_EU_132_1H("VATEX-EU-132-1H"),
  VATEX_EU_132_1I("VATEX-EU-132-1I"),
  VATEX_EU_132_1J("VATEX-EU-132-1J"),
  VATEX_EU_132_1K("VATEX-EU-132-1K"),
  VATEX_EU_132_1L("VATEX-EU-132-1L"),
  VATEX_EU_132_1M("VATEX-EU-132-1M"),
  VATEX_EU_132_1N("VATEX-EU-132-1N"),
  VATEX_EU_132_1O("VATEX-EU-132-1O"),
  VATEX_EU_132_1P("VATEX-EU-132-1P"),
  VATEX_EU_132_1Q("VATEX-EU-132-1Q"),

  VATEX_EU_143("VATEX-EU-143"),
  VATEX_EU_143_1A("VATEX-EU-143-1A"),
  VATEX_EU_143_1B("VATEX-EU-143-1B"),
  VATEX_EU_143_1C("VATEX-EU-143-1C"),
  VATEX_EU_143_1D("VATEX-EU-143-1D"),
  VATEX_EU_143_1E("VATEX-EU-143-1E"),
  VATEX_EU_143_1F("VATEX-EU-143-1F"),
  VATEX_EU_143_1FA("VATEX-EU-143-1FA"),
  VATEX_EU_143_1G("VATEX-EU-143-1G"),
  VATEX_EU_143_1H("VATEX-EU-143-1H"),
  VATEX_EU_143_1I("VATEX-EU-143-1I"),
  VATEX_EU_143_1J("VATEX-EU-143-1J"),
  VATEX_EU_143_1K("VATEX-EU-143-1K"),
  VATEX_EU_143_1L("VATEX-EU-143-1L"),

  VATEX_EU_144("VATEX-EU-144"),
  VATEX_EU_146_1E("VATEX-EU-146-1E"),

  VATEX_EU_148("VATEX-EU-148"),
  VATEX_EU_148_A("VATEX-EU-148-A"),
  VATEX_EU_148_B("VATEX-EU-148-B"),
  VATEX_EU_148_C("VATEX-EU-148-C"),
  VATEX_EU_148_D("VATEX-EU-148-D"),
  VATEX_EU_148_E("VATEX-EU-148-E"),
  VATEX_EU_148_F("VATEX-EU-148-F"),
  VATEX_EU_148_G("VATEX-EU-148-G"),

  VATEX_EU_151("VATEX-EU-151"),
  VATEX_EU_151_1A("VATEX-EU-151-1A"),
  VATEX_EU_151_1AA("VATEX-EU-151-1AA"),
  VATEX_EU_151_1B("VATEX-EU-151-1B"),
  VATEX_EU_151_1C("VATEX-EU-151-1C"),
  VATEX_EU_151_1D("VATEX-EU-151-1D"),
  VATEX_EU_151_1E("VATEX-EU-151-1E"),

  VATEX_EU_153("VATEX-EU-153"),
  VATEX_EU_159("VATEX-EU-159"),
  VATEX_EU_309("VATEX-EU-309"),

  /** Reverse charge – only with TaxCategoryIdentifier.AE */
  VATEX_EU_AE("VATEX-EU-AE"),

  VATEX_EU_D("VATEX-EU-D"),
  VATEX_EU_F("VATEX-EU-F"),
  VATEX_EU_G("VATEX-EU-G"),
  VATEX_EU_I("VATEX-EU-I"),
  VATEX_EU_IC("VATEX-EU-IC"),
  VATEX_EU_J("VATEX-EU-J"),

  /** Not subject to VAT – only with TaxCategoryIdentifier.O */
  VATEX_EU_O("VATEX-EU-O"),

  // ---- France specific --------------------------------------------------

  VATEX_FR_FRANCHISE("VATEX-FR-FRANCHISE"),
  VATEX_FR_CNWVAT("VATEX-FR-CNWVAT"),

  VATEX_FR_CGI261_1("VATEX-FR-CGI261-1"),
  VATEX_FR_CGI261_2("VATEX-FR-CGI261-2"),
  VATEX_FR_CGI261_3("VATEX-FR-CGI261-3"),
  VATEX_FR_CGI261_4("VATEX-FR-CGI261-4"),
  VATEX_FR_CGI261_5("VATEX-FR-CGI261-5"),
  VATEX_FR_CGI261_7("VATEX-FR-CGI261-7"),
  VATEX_FR_CGI261_8("VATEX-FR-CGI261-8"),

  VATEX_FR_CGI261A("VATEX-FR-CGI261A"),
  VATEX_FR_CGI261B("VATEX-FR-CGI261B"),

  VATEX_FR_CGI261C_1("VATEX-FR-CGI261C-1"),
  VATEX_FR_CGI261C_2("VATEX-FR-CGI261C-2"),
  VATEX_FR_CGI261C_3("VATEX-FR-CGI261C-3"),

  VATEX_FR_CGI261D_1("VATEX-FR-CGI261D-1"),
  VATEX_FR_CGI261D_1BIS("VATEX-FR-CGI261D-1BIS"),
  VATEX_FR_CGI261D_2("VATEX-FR-CGI261D-2"),
  VATEX_FR_CGI261D_3("VATEX-FR-CGI261D-3"),
  VATEX_FR_CGI261D_4("VATEX-FR-CGI261D-4"),

  VATEX_FR_CGI261E_1("VATEX-FR-CGI261E-1"),
  VATEX_FR_CGI261E_2("VATEX-FR-CGI261E-2"),

  VATEX_FR_CGI277A("VATEX-FR-CGI277A"),
  VATEX_FR_CGI275("VATEX-FR-CGI275"),
  VATEX_FR_298SEXDECIESA("VATEX-FR-298SEXDECIESA"),
  VATEX_FR_CGI295("VATEX-FR-CGI295"),
  VATEX_FR_AE("VATEX-FR-AE");

  private final String code;

  TaxExemptionReasonCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  // Méthode pour récupérer l'enum depuis le code
  public static TaxExemptionReasonCode fromCode(String code) {
    for (TaxExemptionReasonCode ve : values()) {
      if (ve.code.equals(code)) {
        return ve;
      }
    }
    throw new IllegalArgumentException("No VatExemption with code " + code);
  }

  @Override
  public String toString() {
    return code;
  }
}
