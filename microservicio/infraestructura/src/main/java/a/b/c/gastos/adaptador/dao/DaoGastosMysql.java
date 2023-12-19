package a.b.c.gastos.adaptador.dao;

import a.b.c.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import a.b.c.infraestructura.jdbc.sqlstatement.SqlStatement;
import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.gastos.puerto.dao.DaoGastos;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class DaoGastosMysql implements DaoGastos {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoGastos mapeoGastos;

    @SqlStatement(namespace = "gastos", value = "obtenergastosdeunpresupuesto")
    private static String sqlObtenerGastosDePresupuesto;

    public DaoGastosMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
            MapeoGastos mapeoGastos) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoGastos = mapeoGastos;
    }

    @Override
    public List<Gasto> obtenerGastosDe(Long idPresupuesto) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPresupuesto", idPresupuesto);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlObtenerGastosDePresupuesto,
                paramSource,
                mapeoGastos);

    }
}
