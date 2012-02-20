package hr.element.pgscala.converters;

import org.joda.convert.*;

{ imports }

public enum PGNullable{ upperType }Converter implements StringConverter<UUID> {
  INSTANCE;

  public static final String pgType = "uuid";

{ body }

  @ToString
  public static String { lowerType }ToString(final UUID uuid) {
    return null == uuid ? null : uuid.toString();
  }

  @FromString
  public static UUID stringTo{ upperType }(final String uuid) {
    return null == uuid ? null : UUID.fromString(uuid);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final UUID uuid) {
    return { lowerType }ToString(uuid);
  }

  public UUID convertFromString(final Class<? extends UUID> clazz, final String uuid) {
    return stringTo{ upperName }(uuid);
  }
}
