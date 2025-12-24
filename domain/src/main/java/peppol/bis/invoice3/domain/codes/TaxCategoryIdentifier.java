package peppol.bis.invoice3.domain.codes;

public enum TaxCategoryIdentifier {

  /**
   * Vat Reverse Charge
   * Code specifying that the standard VAT rate is levied from the invoicee.
   */
  AE,
  /**
   * Exempt from Tax
   * Code specifying that taxes are not applicable (usually exempt per local rules).
   */
  E,

  /**
   * Standard rate
   * Code specifying the standard rate.
   */
  S,
  /**
   * Zero rated goods
   * Code specifying that the goods are at a zero rate.
   */
  Z,
  /**
   * Free export item, VAT not charged
   */
  G,
  /**
   * Services outside scope of tax
   */
  O,
  /**
   * VAT exempt for EEA intra-community supply of goods and services
   */
  K,
  /**
   * Canary Islands general indirect tax (IGIC)
   */
  L,
  /**
   * Tax for production, services and importation in Ceuta and Melilla
   */
  M,
  /**
   * Transferred (VAT), In Italy (Italian deviation)
   * VAT not to be paid to the issuer of the invoice but directly to relevant tax authority.
   */
  B
}
