package a.b.c.presupuesto.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCrearPresupuesto {
    private String nombre;
    private String descripcion;
    private boolean porcentual;
    private BigDecimal valor;
}
