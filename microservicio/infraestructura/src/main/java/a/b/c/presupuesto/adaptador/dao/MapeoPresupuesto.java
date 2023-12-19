package a.b.c.presupuesto.adaptador.dao;

import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;
import a.b.c.infraestructura.jdbc.MapperResult;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoPresupuesto implements RowMapper<PresupuestoDTO>, MapperResult {

    @Override
    public PresupuestoDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String descripcion = resultSet.getString("descripcion");
        boolean esPorcentual = resultSet.getBoolean("esPorcentual");
        BigDecimal valor = resultSet.getBigDecimal("valor");

        if (esPorcentual) {
            return new PresupuestoDTO(id, nombre, descripcion, esPorcentual, BigDecimal.ZERO, valor, BigDecimal.ZERO);
        }
        return new PresupuestoDTO(id, nombre, descripcion, esPorcentual, valor, BigDecimal.ZERO, BigDecimal.ZERO);
    }

}
