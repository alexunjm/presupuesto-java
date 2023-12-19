package a.b.c.presupuesto.modelo.entidad;

import java.math.BigDecimal;

import a.b.c.dominio.listas.Reductor;

public final class TotalizadorGastadoPorPresupuesto implements Reductor<Presupuesto, BigDecimal> {

    private TotalizadorGastadoPorPresupuesto() {
    }

    public static Reductor<Presupuesto, BigDecimal> crear() {
        return new TotalizadorGastadoPorPresupuesto();
    }

    @Override
    public BigDecimal acumular(BigDecimal gastoParcial, Presupuesto presupuesto) {
        return gastoParcial.add(presupuesto.getTotalGastado());
    }

    @Override
    public BigDecimal combinar(BigDecimal gastoParcial1, BigDecimal gastoParcial2) {
        return gastoParcial1.add(gastoParcial2);
    }
}