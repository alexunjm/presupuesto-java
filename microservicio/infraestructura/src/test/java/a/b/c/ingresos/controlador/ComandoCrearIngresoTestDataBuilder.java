package a.b.c.ingresos.controlador;

import a.b.c.ingresos.comando.ComandoCrearIngreso;

import java.math.BigDecimal;

public class ComandoCrearIngresoTestDataBuilder {


    private String descripcion;
    private BigDecimal valor;

    public ComandoCrearIngresoTestDataBuilder conIngresoPorDefecto() {
        this.descripcion = "Salario";
        this.valor = new BigDecimal("5000");
        return this;
    }

    public ComandoCrearIngreso construir() {
        return new ComandoCrearIngreso(this.descripcion, this.valor);
    }
}
