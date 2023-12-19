package a.b.c.presupuesto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class NuevoPresupuesto {
    private final String nombre;
    private final String descripcion;
    private final boolean porcentual;
    private final BigDecimal valor;
}
