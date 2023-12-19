package a.b.c.gastos.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoNuevoGasto {
    private Long idPresupuesto;
    private String descripcion;
    private BigDecimal valor;
}
