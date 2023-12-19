package a.b.c.presupuesto;

import a.b.c.presupuesto.modelo.valueobject.ValorPresupuesto;

import java.math.BigDecimal;

public class ValorPresupuestoTestDataBuilder {
    private BigDecimal totalIngresosMes;

    public ValorPresupuesto crearValorPorcentual(int valor) {
        BigDecimal valorPorcentual = new BigDecimal(valor);
        return ValorPresupuesto.crear(true, valorPorcentual, totalIngresosMes);
    }
    public ValorPresupuesto crearValorFijo(int valor) {
        BigDecimal valorFijo = new BigDecimal(valor);
        return ValorPresupuesto.crear(false, valorFijo, totalIngresosMes);
    }

    public ValorPresupuestoTestDataBuilder conIngresosDefecto() {
        totalIngresosMes = BigDecimal.ZERO;
        return this;
    }

    public ValorPresupuestoTestDataBuilder conTotalIngresosMes(int totalIngresosMes) {
        this.totalIngresosMes = new BigDecimal(totalIngresosMes);
        return this;
    }
}
