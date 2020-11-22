package ru.dshishkaryan.sovkombank.dao.interf;

import java.util.List;

public interface InvitesDao {
    Long getCountInvitations();

    void addInvitation(List<String> numbers);
}
