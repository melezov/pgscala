package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.util.UUID;

public enum PGNullableUUIDConverter implements StringConverter<UUID> {
  INSTANCE;

  public static final String pgType = "text";

  @ToString
  public static String uUIDToString(final UUID uuid) {
    return null == uuid ? null : uuid.toString();
  }

  @FromString
  public static UUID stringToUUID(final String uuid) {
    return null == uuid ? null : UUID.fromString(uuid);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final UUID uuid) {
    return uUIDToString(uuid);
  }

  public UUID convertFromString(final Class<? extends UUID> clazz, final String uuid) {
    return stringToUUID(uuid);
  }
}
