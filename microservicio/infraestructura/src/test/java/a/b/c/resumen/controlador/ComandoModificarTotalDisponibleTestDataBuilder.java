package a.b.c.resumen.controlador;

import java.math.BigDecimal;

import a.b.c.resumen.comando.ComandoModificarTotalDisponible;

public class ComandoModificarTotalDisponibleTestDataBuilder {

    private BigDecimal valor;

    public ComandoModificarTotalDisponibleTestDataBuilder conTotalDisponibleEnMil() {
        this.valor = BigDecimal.valueOf(1000);
        return this;
    }

    public ComandoModificarTotalDisponible construir() {
        return new ComandoModificarTotalDisponible(this.valor);
    }
}
