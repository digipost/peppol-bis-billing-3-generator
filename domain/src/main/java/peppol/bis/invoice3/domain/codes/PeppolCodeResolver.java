package peppol.bis.invoice3.domain.codes;

public final class PeppolCodeResolver {

  private PeppolCodeResolver() {}

  public static <T extends Enum<T> & PeppolCode> T fromCode(
          Class<T> enumClass,
          String code
  ) {
    for (T e : enumClass.getEnumConstants()) {
      if (e.getCode().equals(code)) {
        return e;
      }
    }
    throw new IllegalArgumentException(
            "No " + enumClass.getSimpleName() + " with code " + code
    );
  }
}
