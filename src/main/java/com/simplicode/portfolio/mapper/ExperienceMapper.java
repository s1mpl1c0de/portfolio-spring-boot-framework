package com.simplicode.portfolio.mapper;

import com.simplicode.portfolio.model.Experience;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ExperienceMapper implements RowMapper<Experience> {

    @Override
    public Experience mapRow(ResultSet rs, int rowNum) throws SQLException {
        short endedMonth = rs.getShort("ended_month");
        short endedYear = rs.getShort("ended_year");
        Timestamp lastModifiedDate = rs.getTimestamp("last_modified_date");

        return new Experience()
           .setId(rs.getLong("id"))
           .setJobTitle(rs.getString("job_title"))
           .setCompanyName(rs.getString("company_name"))
           .setStartedMonth(rs.getShort("started_month"))
           .setStartedYear(rs.getShort("started_year"))
           .setEndedMonth(endedMonth != 0 ? endedMonth : null)
           .setEndedYear(endedYear != 0 ? endedYear : null)
           .setIsStillInRole(rs.getBoolean("is_still_in_role"))
           .setDescription(rs.getString("description"))
           .setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime())
           .setLastModifiedDate(lastModifiedDate != null ? lastModifiedDate.toLocalDateTime() : null);
    }

}
