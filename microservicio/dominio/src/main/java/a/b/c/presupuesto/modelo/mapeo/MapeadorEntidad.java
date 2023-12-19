package a.b.c.presupuesto.modelo.mapeo;

import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;

public final class MapeadorEntidad {

    private MapeadorEntidad() {
        throw new IllegalStateException("Utility class");
    }

    public static PresupuestoDTO aDTO(Presupuesto presupuesto) {
        return new PresupuestoDTO(presupuesto.getId(), presupuesto.getNombre(), presupuesto.getDescripcion(),
                presupuesto.esPorcentual(), presupuesto.getValorFijo(), presupuesto.getValorPorcentual(),
                presupuesto.calcularDisponible());
    }
}
