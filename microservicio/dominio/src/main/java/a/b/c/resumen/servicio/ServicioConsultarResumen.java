package a.b.c.resumen.servicio;

import java.math.BigDecimal;

import a.b.c.presupuesto.modelo.entidad.Presupuestos;
import a.b.c.presupuesto.modelo.mapeo.MapeadorEntidad;
import a.b.c.resumen.modelo.dto.ResumenDTO;

public class ServicioConsultarResumen {

    public ResumenDTO ejecutar(BigDecimal totalIngresos, BigDecimal totalIngresosMesEnCurso,
            Presupuestos presupuestos) {
        BigDecimal totalGastosPresupuestados = presupuestos.getTotalPresupuestado();
        BigDecimal totalDisponible = totalIngresos.subtract(totalGastosPresupuestados);
        BigDecimal totalGastadoMesEnCurso = presupuestos.getTotalGastado();

        return new ResumenDTO(
                totalDisponible,
                totalIngresosMesEnCurso,
                totalGastadoMesEnCurso,
                totalGastosPresupuestados,
                presupuestos.stream().map(MapeadorEntidad::aDTO).toList());
    }
}
