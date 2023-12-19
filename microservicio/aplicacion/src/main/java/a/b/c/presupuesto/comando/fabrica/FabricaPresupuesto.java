package a.b.c.presupuesto.comando.fabrica;

import a.b.c.presupuesto.comando.ComandoCrearPresupuesto;
import a.b.c.presupuesto.modelo.dto.NuevoPresupuesto;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.servicio.ServicioObtenerPresupuestosParaDebitar;

import org.springframework.stereotype.Component;

@Component
public class FabricaPresupuesto {

    private final ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar;

    public FabricaPresupuesto(ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar) {
        this.servicioObtenerPresupuestosParaDebitar = servicioObtenerPresupuestosParaDebitar;
    }

    public Presupuesto nuevoPresupuesto(ComandoCrearPresupuesto comandoCrearPresupuesto) {
        return Presupuesto.crear(
                new NuevoPresupuesto(
                        comandoCrearPresupuesto.getNombre(),
                        comandoCrearPresupuesto.getDescripcion(),
                        comandoCrearPresupuesto.isPorcentual(),
                        comandoCrearPresupuesto.getValor()));
    }

    public Presupuesto reconstruirPresupuestoParaDebitar(Long idPresupuesto) {
        return servicioObtenerPresupuestosParaDebitar.reconstruirUno(idPresupuesto);
    }
}
