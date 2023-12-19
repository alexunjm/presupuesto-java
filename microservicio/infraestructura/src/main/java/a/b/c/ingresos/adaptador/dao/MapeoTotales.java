package a.b.c.ingresos.adaptador.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import a.b.c.infraestructura.jdbc.MapperResult;

@Component
public class MapeoTotales implements RowMapper<BigDecimal>, MapperResult {

    @Override
    public BigDecimal mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return resultSet.getBigDecimal("total");
    }
}
