package com.example.carnumber.mapper;


import com.example.carnumber.converter.PlateNumberConverter;
import com.example.carnumber.model.PlateNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class PlateRowMapper implements RowMapper<PlateNumber> {
    private final PlateNumberConverter converter;

    @Override
    public PlateNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
        return converter.fromString(
                rs.getLong("id"),
                rs.getString("plateNumber"));
    }
}
