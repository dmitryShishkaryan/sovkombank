package ru.dshishkaryan.sovkombank.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dshishkaryan.sovkombank.domain.invitesDto.InvitesDto;
import ru.dshishkaryan.sovkombank.services.interf.Invites;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/invites")
public class MessageController {

    private final Invites invites;

    @PostMapping()
    public ResponseEntity<String> sendInvites(@RequestBody InvitesDto invitesDto) {
        try {
            invites.authSendInvites(invitesDto.getPhoneNumbers(), invitesDto.getMessage());
        } catch (RuntimeException e) {
            switch (e.getMessage()) {
                case "PHONE_NUMBERS_EMPTY: Phone_numbers is missing.":
                    return ResponseEntity.status(401).body(e.getMessage());
                case "PHONE_NUMBERS_INVALID: One or several phone numbers do not match with international format.":
                    return ResponseEntity.status(400).body(e.getMessage());
                case "PHONE_NUMBERS_INVALID: Too much phone numbers, should be less or equal to 16 per request.":
                    return ResponseEntity.status(402).body(e.getMessage());
                case "PHONE_NUMBERS_INVALID: Too much phone numbers, should be less or equal to 128 per day.":
                    return ResponseEntity.status(403).body(e.getMessage());
                case "PHONE_NUMBERS_INVALID: Duplicate numbers detected.":
                    return ResponseEntity.status(404).body(e.getMessage());
                case "MESSAGE_EMPTY: Invite message is missing.":
                    return ResponseEntity.status(405).body(e.getMessage());
                case "MESSAGE_INVALID: Invite message should contain only characters in 7-bit GSM encoding or Cyrillic letters as well":
                    return ResponseEntity.status(406).body(e.getMessage());
                case "MESSAGE_INVALID: Invite message too long, should be less or equal to 128 characters of 7-bit GSM charset.":
                    return ResponseEntity.status(407).body(e.getMessage());
                default:
                    return ResponseEntity.status(500).body(e.getMessage());
            }
        }
        return ResponseEntity.ok().build();
    }
}
