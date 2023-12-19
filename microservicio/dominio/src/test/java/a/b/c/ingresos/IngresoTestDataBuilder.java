package a.b.c.ingresos;

import a.b.c.ingresos.modelo.entidad.Ingreso;

import java.math.BigDecimal;

public class IngresoTestDataBuilder {
    private String descripcion;
    private BigDecimal valor;

    public IngresoTestDataBuilder() {
        this.descripcion = "Ingreso por servicio";
        this.valor = new BigDecimal("1000");
    }

    public IngresoTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public IngresoTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Ingreso build() {
        return Ingreso.crear(this.descripcion, this.valor);
    }
}