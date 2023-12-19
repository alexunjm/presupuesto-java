package a.b.c.presupuesto.adaptador.repositorio;

import a.b.c.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import a.b.c.infraestructura.jdbc.sqlstatement.SqlStatement;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.puerto.repositorio.RepositorioPresupuesto;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPresupuestoMysql implements RepositorioPresupuesto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "presupuesto", value = "crear")
    private static String sqlCrear;

    RepositorioPresupuestoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long guardar(Presupuesto nuevoPresupuesto) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nuevoPresupuesto.getNombre());
        paramSource.addValue("descripcion", nuevoPresupuesto.getDescripcion());
        paramSource.addValue("esPorcentual", nuevoPresupuesto.esPorcentual());
        if (nuevoPresupuesto.esPorcentual()) {
            paramSource.addValue("valor", nuevoPresupuesto.getValorPorcentual());
        } else {
            paramSource.addValue("valor", nuevoPresupuesto.getValorFijo());
        }
        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }
}
