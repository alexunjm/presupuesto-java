package a.b.c.gastos.controlador;

import a.b.c.gastos.comando.ComandoNuevoGasto;

import java.math.BigDecimal;

public class ComandoNuevoGastoTestDataBuilder {

    private Long presupuesto;
    private String descripcion;
    private BigDecimal valor;

    public ComandoNuevoGastoTestDataBuilder conGastoPorDefecto() {
        this.presupuesto = 1l;
        this.descripcion = "Comida";
        this.valor = BigDecimal.valueOf(100);
        return this;
    }

    public ComandoNuevoGasto construir() {
        return new ComandoNuevoGasto(this.presupuesto, this.descripcion, this.valor);
    }
}
