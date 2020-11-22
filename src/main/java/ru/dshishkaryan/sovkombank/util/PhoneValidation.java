package ru.dshishkaryan.sovkombank.util;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@UtilityClass
public class PhoneValidation {

    public final Pattern VALIDATE_PHONE_NUMBER_REGEX =
            Pattern.compile("^\\+\\d{1,3}\\s?\\(\\d{3}\\)\\s?\\d{3}(-\\d{2}){2}$", Pattern.CASE_INSENSITIVE);

    public boolean isPhoneNumberValid(List<String> phoneNumbers) {
        for (String phoneNumber : phoneNumbers) {
            if (!VALIDATE_PHONE_NUMBER_REGEX.matcher(phoneNumber).matches()) {
                return false;
            }
        }
        return true;
    }

    public boolean isContainsDuplicates(List<String> phoneNumbers) {
        Set<String> strings = new HashSet<>(phoneNumbers);
        return strings.size() == phoneNumbers.size();
    }
}
