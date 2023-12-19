package a.b.c.gastos.modelo.dto;

import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class NuevoGasto {
    private final Presupuesto presupuesto;
    private final String descripcion;
    private final BigDecimal valor;
}
