package a.b.c.ingresos.adaptador.repositorio;

import a.b.c.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import a.b.c.infraestructura.jdbc.sqlstatement.SqlStatement;
import a.b.c.ingresos.modelo.entidad.Ingreso;
import a.b.c.ingresos.puerto.repositorio.RepositorioIngresos;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class RepositorioIngresosMysql implements RepositorioIngresos {

    private static final int ID_AHORRO_INICIAL = 1;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    RepositorioIngresosMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "ingresos", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "ingresos", value = "actualizarahorroinicial")
    private static String sqlActualizarEstado;

    @Override
    public Long guardar(Ingreso nuevoIngreso) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("descripcion", nuevoIngreso.getDescripcion());
        paramSource.addValue("valor", nuevoIngreso.getValor());
        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }

    @Override
    public void actualizarAhorrosIniciales(BigDecimal ahorrosIniciales) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", ID_AHORRO_INICIAL);
        paramSource.addValue("valor", ahorrosIniciales);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarEstado, paramSource);
    }
}
