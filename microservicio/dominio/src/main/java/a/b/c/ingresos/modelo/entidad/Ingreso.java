package a.b.c.ingresos.modelo.entidad;

import a.b.c.dominio.ValidadorArgumento;

import java.math.BigDecimal;

public final class Ingreso {
    private final String descripcion;
    private final BigDecimal valor;

    private Ingreso(String descripcion, BigDecimal valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public static Ingreso crear(String descripcion, BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(descripcion, "Descripción del ingreso es requerida");
        ValidadorArgumento.validarObligatorio(valor, "Valor numérico del ingreso es requerido");
        return new Ingreso(descripcion, valor);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
