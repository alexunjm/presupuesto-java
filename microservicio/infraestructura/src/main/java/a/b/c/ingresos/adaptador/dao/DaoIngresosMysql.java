package a.b.c.ingresos.adaptador.dao;

import a.b.c.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import a.b.c.infraestructura.jdbc.sqlstatement.SqlStatement;
import a.b.c.ingresos.puerto.dao.DaoIngresos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DaoIngresosMysql implements DaoIngresos {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoTotales mapeoTotales;

    @SqlStatement(namespace = "ingresos", value = "obtenertotaldisponible")
    private static String sqlObtenerTotalDisponible;

    @SqlStatement(namespace = "ingresos", value = "obtenertotalingresos")
    private static String sqlObtenerTotalIngresos;

    public DaoIngresosMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
            MapeoTotales mapeoTotales) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoTotales = mapeoTotales;
    }

    @Override
    public BigDecimal obtenerTotalDisponible() {
        return obtenerTotal(sqlObtenerTotalDisponible);
    }

    @Override
    public BigDecimal obtenerTotalIngresos() {
        return obtenerTotal(sqlObtenerTotalIngresos);
    }

    private BigDecimal obtenerTotal(String sql) {
        List<BigDecimal> result = customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sql, mapeoTotales);

        if (result.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return result.get(0);
    }
}
