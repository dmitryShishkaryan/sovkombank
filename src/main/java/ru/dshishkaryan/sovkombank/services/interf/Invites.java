package ru.dshishkaryan.sovkombank.services.interf;

import java.util.List;

public interface Invites {
    void authSendInvites(List<String> phoneNumbers, String message);
}
