package a.b.c.presupuesto.adaptador.dao;

import a.b.c.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import a.b.c.infraestructura.jdbc.sqlstatement.SqlStatement;
import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;
import a.b.c.presupuesto.puerto.dao.DaoPresupuestos;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DaoPresupuestoMysql implements DaoPresupuestos {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoPresupuesto mapeoPresupuesto;

    @SqlStatement(namespace = "presupuesto", value = "obtenerpresupuestosmesencurso")
    private static String sqlObtenerPresupuestosMesEnCurso;

    public DaoPresupuestoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
            MapeoPresupuesto mapeoPresupuesto) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoPresupuesto = mapeoPresupuesto;
    }

    @Override
    public List<PresupuestoDTO> obtenerPresupuestosMesEnCurso() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPresupuestosMesEnCurso, mapeoPresupuesto);
    }
}
