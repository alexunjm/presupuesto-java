package a.b.c.resumen.consulta.manejador;

import org.springframework.stereotype.Component;

import a.b.c.ingresos.puerto.dao.DaoIngresos;
import a.b.c.presupuesto.servicio.ServicioObtenerPresupuestosParaDebitar;
import a.b.c.resumen.modelo.dto.ResumenDTO;
import a.b.c.resumen.servicio.ServicioConsultarResumen;

@Component
public class ManejadorConsultarResumen {

    private final ServicioConsultarResumen servicioConsultarResumen;

    private final DaoIngresos daoIngresos;
    private final ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar;

    public ManejadorConsultarResumen(ServicioConsultarResumen servicioConsultarResumen, DaoIngresos daoIngresos,
            ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar) {
        this.servicioConsultarResumen = servicioConsultarResumen;
        this.daoIngresos = daoIngresos;
        this.servicioObtenerPresupuestosParaDebitar = servicioObtenerPresupuestosParaDebitar;
    }

    public ResumenDTO ejecutar() {
        return this.servicioConsultarResumen.ejecutar(daoIngresos.obtenerTotalDisponible(),
                daoIngresos.obtenerTotalIngresos(), servicioObtenerPresupuestosParaDebitar.reconstruirTodos());
    }
}
