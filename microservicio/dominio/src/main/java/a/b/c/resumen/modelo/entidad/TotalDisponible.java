package a.b.c.resumen.modelo.entidad;

import java.math.BigDecimal;

import a.b.c.dominio.ValidadorArgumento;

public final class TotalDisponible {
    private static final String ERROR_VALOR_NUMERICO_ES_OBLIGATORIO = "Valor numérico del total disponible es obligatorio";
    private static final String ERROR_VALOR_NUMERICO_DEBE_SER_POSITIVO_O_CERO = "Valor numérico del total disponible debe ser positivo o cero";
    private final BigDecimal valor;

    private TotalDisponible(BigDecimal valor) {
        this.valor = valor;
    }

    public static TotalDisponible crear(BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(valor, ERROR_VALOR_NUMERICO_ES_OBLIGATORIO);
        ValidadorArgumento.validarNoNegativo(valor, ERROR_VALOR_NUMERICO_DEBE_SER_POSITIVO_O_CERO);
        return new TotalDisponible(valor);
    }

    public BigDecimal getValor() {
        return valor;
    }
}
