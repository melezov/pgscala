package org.pgscala.converters;

import org.joda.convert.*;

import java.net.URL;
import java.net.MalformedURLException;

/** Do not edit - generated in Builder / PGNullableURLConverterBuilder.scala */

public enum PGNullableURLConverter implements StringConverter<URL> {
    INSTANCE;

    public static final String pgType = "varchar";

    @ToString
    public static String urlToString(final URL url) {
        return null == url ? null : url.toString();
    }

    @FromString
    public static URL stringToURL(final String url) throws MalformedURLException {
        return null == url ? null : new URL(url);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final URL url) {
        return urlToString(url);
    }

    public URL convertFromString(final Class<? extends URL> clazz, final String url) {
        try {
            return stringToURL(url);
        } catch (final MalformedURLException e) {
            throw new RuntimeException("Could not convert String to URL");
        }
    }
}
