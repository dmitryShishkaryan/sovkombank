package ru.dshishkaryan.sovkombank.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dshishkaryan.sovkombank.dao.interf.InvitesDao;
import ru.dshishkaryan.sovkombank.exceptions.MessageException;
import ru.dshishkaryan.sovkombank.exceptions.PhoneNumberException;
import ru.dshishkaryan.sovkombank.services.interf.Invites;
import ru.dshishkaryan.sovkombank.util.MessageValidation;
import ru.dshishkaryan.sovkombank.util.PhoneValidation;
import ru.dshishkaryan.sovkombank.util.Transliteration;

import java.util.List;

@Service
@AllArgsConstructor
public class InvitesImpl implements Invites {

    private final InvitesDao invites;

    @Override
    public void authSendInvites(List<String> phoneNumbers, String message) {

        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            throw new PhoneNumberException("PHONE_NUMBERS_EMPTY: Phone_numbers is missing.");
        } else if (!PhoneValidation.isPhoneNumberValid(phoneNumbers)) {
            throw new PhoneNumberException("PHONE_NUMBERS_INVALID: One or several phone numbers do not match with international format.");
        } else if (phoneNumbers.size() > 16) {
            throw new PhoneNumberException("PHONE_NUMBERS_INVALID: Too much phone numbers, should be less or equal to 16 per request.");
        } else if (invites.getCountInvitations() > 128) {
            throw new PhoneNumberException("PHONE_NUMBERS_INVALID: Too much phone numbers, should be less or equal to 128 per day.");
        } else if (!PhoneValidation.isContainsDuplicates(phoneNumbers)) {
            throw new PhoneNumberException("PHONE_NUMBERS_INVALID: Duplicate numbers detected.");
        } else if (message == null || message.isEmpty()) {
            throw new MessageException("MESSAGE_EMPTY: Invite message is missing.");
        } else if (!MessageValidation.IsUnicodeSms(Transliteration.сyrillicToLatin(message))) {
            throw new MessageException("MESSAGE_INVALID: Invite message should contain only characters in 7-bit GSM encoding or Cyrillic letters as well");
        } else if (message.length() > 128) {
            throw new MessageException("MESSAGE_INVALID: Invite message too long, should be less or equal to 128 characters of 7-bit GSM charset.");
        }
        invites.addInvitation(phoneNumbers);
    }
}
