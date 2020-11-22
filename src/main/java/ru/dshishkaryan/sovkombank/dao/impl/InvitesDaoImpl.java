package ru.dshishkaryan.sovkombank.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import ru.dshishkaryan.sovkombank.dao.interf.InvitesDao;

import javax.sql.DataSource;
import java.util.List;

@Repository
@AllArgsConstructor
public class InvitesDaoImpl implements InvitesDao {

    private final DataSource dataSource;

    @Override
    public Long getCountInvitations() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("getcountinvitations");
        MapSqlParameterSource out = new MapSqlParameterSource()
                .addValue("apiid", 4);

        return (Long) jdbcCall.execute(out).get("count_of_invites");
    }

    @Override
    public void addInvitation(List<String> numbers) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("invite");
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("user_id", 7)
                .addValue("phones", numbers);
    }
}
