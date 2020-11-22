package ru.dshishkaryan.sovkombank.util;

import com.ibm.icu.text.Transliterator;

public class Transliteration {

    public static String сyrillicToLatin(String s) {
        Transliterator toLatin = Transliterator.getInstance("Russian-Latin/BGN");
        String result = toLatin.transliterate(s);
        return result.replaceAll(" ", "_");
    }

    public static String latinToCyrillic(String s) {
        Transliterator toCyrillic = Transliterator.getInstance("Latin-Russian/BGN");
        String result = toCyrillic.transliterate(s);
        return result.replaceAll("_", " ");
    }
}
