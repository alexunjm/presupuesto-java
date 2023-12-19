package a.b.c.presupuesto.modelo.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ValorPresupuesto {
    private static final BigDecimal CIEN = new BigDecimal(100);
    private boolean esPorcentual;
    private BigDecimal porcentaje;
    private BigDecimal valor;

    private ValorPresupuesto() {}

    public static ValorPresupuesto crear(boolean esPorcentual, BigDecimal valor, BigDecimal totalIngresosMes) {
        ValorPresupuesto valorPresupuesto = new ValorPresupuesto();
        valorPresupuesto.esPorcentual = esPorcentual;
        if (esPorcentual) {
            valorPresupuesto.setPorcentaje(valor, totalIngresosMes);
            return valorPresupuesto;
        }
        valorPresupuesto.setValor(valor, totalIngresosMes);
        return valorPresupuesto;
    }

    public BigDecimal obtenerPorcentajeRelativo() {
        return porcentaje.divide(CIEN);
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje, BigDecimal totalIngresosMes) {
        this.porcentaje = porcentaje;
        this.valor = obtenerPorcentajeRelativo().multiply(totalIngresosMes);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor, BigDecimal totalIngresosMes) {
        this.valor = valor;
        if (totalIngresosMes.compareTo(BigDecimal.ZERO) <= 0) {
            this.porcentaje = BigDecimal.ZERO;
            return;
        }
        this.porcentaje = valor.multiply(CIEN).divide(totalIngresosMes, 2, RoundingMode.HALF_UP);
    }

    public void actualizarTotalPresupuestadoMes(BigDecimal totalPresupuestado) {
        if (esPorcentual) {
            this.setPorcentaje(porcentaje, totalPresupuestado);
        } else {
            this.setValor(valor, totalPresupuestado);
        }
    }

    public boolean esPorcentual() {
        return esPorcentual;
    }
}
