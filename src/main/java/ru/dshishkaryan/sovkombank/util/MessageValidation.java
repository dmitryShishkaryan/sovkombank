package ru.dshishkaryan.sovkombank.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class MessageValidation {
    public static final Pattern GSM_CHARACTERS_REGEX =
            Pattern
                    .compile("^[A-Za-z0-9 \\r\\n@£$¥èéùìòÇØøÅå\u0394_\u03A6\u0393\u039B\u03A9\u03A0" +
                            "\u03A8\u03A3\u0398\u039EÆæßÉ!\"#$%&amp;'()*+,\\-./:;&lt;=&gt;?¡ÄÖÑÜ§¿äöñüà^{}" +
                            "\\\\\\[~\\]|\u20AC]*$", Pattern.CASE_INSENSITIVE);

    public static boolean IsUnicodeSms(String message) {
        return GSM_CHARACTERS_REGEX.matcher(message).matches();
    }
}
