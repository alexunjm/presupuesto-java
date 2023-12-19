package a.b.c.gastos.adaptador.repositorio;

import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.gastos.puerto.repositorio.RepositorioGastos;
import a.b.c.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import a.b.c.infraestructura.jdbc.sqlstatement.SqlStatement;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioGastosMysql implements RepositorioGastos {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    RepositorioGastosMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "gastos", value = "crear")
    private static String sqlCrear;

    @Override
    public Long guardar(Gasto gasto) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPresupuesto", gasto.getIdPresupuesto());
        paramSource.addValue("descripcion", gasto.getDescripcion());
        paramSource.addValue("valor", gasto.getValor());
        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }
}
