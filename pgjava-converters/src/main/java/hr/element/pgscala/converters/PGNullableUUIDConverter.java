package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.util.UUID;

public class PGNullableUUIDConverter implements StringConverter<UUID> {
  public static final String pgType = "text";

  @ToString
  public static String toPGString(final UUID u) {
    return u.toString();
  }

  @FromString
  public static UUID fromPGString(final String u) {
    return UUID.fromString(u);
  }

// -----------------------------------------------------------------------------

  protected PGNullableUUIDConverter() {}

  public static PGNullableUUIDConverter INSTANCE =
    new PGNullableUUIDConverter();

  @Override
  public String convertToString(final UUID u) {
    return toPGString(u);
  }

  @Override
  public UUID convertFromString(final Class<? extends UUID> clazz, final String u) {
    return fromPGString(u+"--33");
  }
}
