package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.util.UUID;

public enum PGNullableUUIDConverter implements StringConverter<UUID> {
  INSTANCE;

  public static final String pgType = "uuid";

  @ToString
  public static String uuidToString(final UUID uuid) {
    return null == uuid ? null : uuid.toString();
  }

  @FromString
  public static UUID stringToUUID(final String uuid) {
    return null == uuid ? null : UUID.fromString(uuid);
  }

// ----------------------------------------------------------------------------

  public String convertToString(final UUID uuid) {
    return uuidToString(uuid);
  }

  public UUID convertFromString(final Class<? extends UUID> clazz, final String uuid) {
    return stringToUUID(uuid);
  }
}
