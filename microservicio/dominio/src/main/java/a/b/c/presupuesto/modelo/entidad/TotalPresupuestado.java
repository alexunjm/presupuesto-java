package a.b.c.presupuesto.modelo.entidad;

import java.math.BigDecimal;
import java.util.List;

public final class TotalPresupuestado {

    private BigDecimal totalFijo;
    private BigDecimal totalPorcentual;

    private TotalPresupuestado() {
        totalFijo = BigDecimal.ZERO;
        totalPorcentual = BigDecimal.ZERO;
    }

    public static TotalPresupuestado nuevo() {
        return new TotalPresupuestado();
    }

    public static TotalPresupuestado calcular(List<Presupuesto> presupuestos) {
        return TotalizadorPresupuestos.crear().reducir(presupuestos, TotalPresupuestado.nuevo());
    }

    public void sumarTotalFijo(BigDecimal valor) {
        totalFijo = totalFijo.add(valor);
    }

    public void sumarTotalPorcentual(BigDecimal valor) {
        totalPorcentual = totalPorcentual.add(valor);
    }

    public TotalPresupuestado agregar(Presupuesto presupuesto) {
        sumarTotalFijo(presupuesto.getValorFijo());
        sumarTotalPorcentual(presupuesto.getValorPorcentual());
        return this;
    }

    public BigDecimal getTotalFijo() {
        return totalFijo;
    }

    public BigDecimal getTotalPorcentual() {
        return totalPorcentual;
    }
}
