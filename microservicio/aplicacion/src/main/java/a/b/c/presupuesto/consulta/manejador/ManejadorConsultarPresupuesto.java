package a.b.c.presupuesto.consulta.manejador;

import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;
import a.b.c.presupuesto.modelo.mapeo.MapeadorEntidad;
import a.b.c.presupuesto.servicio.ServicioObtenerPresupuestosParaDebitar;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarPresupuesto {

    private final ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar;

    public ManejadorConsultarPresupuesto(
            ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar) {
        this.servicioObtenerPresupuestosParaDebitar = servicioObtenerPresupuestosParaDebitar;
    }

    public List<PresupuestoDTO> ejecutar() {

        return servicioObtenerPresupuestosParaDebitar.reconstruirTodos().stream()
                .map(MapeadorEntidad::aDTO).toList();
    }
}
