package a.b.c.gastos.adaptador.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.infraestructura.jdbc.MapperResult;

@Component
public class MapeoGastos implements RowMapper<Gasto>, MapperResult {

    @Override
    public Gasto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        long idPresupuesto = resultSet.getLong("idPresupuesto");
        String descripcion = resultSet.getString("descripcion");
        BigDecimal valor = resultSet.getBigDecimal("valor");

        return Gasto.reconstruir(id, idPresupuesto, descripcion, valor);
    }
}
