package io.megasoby.settlebatchproc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tb1RowMapper implements RowMapper {
    @Override
    public TB1 mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TB1.builder()
                .num(rs.getInt("num"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .build();
    }
}
