package a.b.c.presupuesto.modelo.entidad;

import a.b.c.dominio.listas.Reductor;

public final class TotalizadorPresupuestos implements Reductor<Presupuesto, TotalPresupuestado> {

    private TotalizadorPresupuestos() {
    }

    public static Reductor<Presupuesto, TotalPresupuestado> crear() {
        return new TotalizadorPresupuestos();
    }

    @Override
    public TotalPresupuestado acumular(TotalPresupuestado acumuladoParcial, Presupuesto actual) {
        return combinar(
                acumuladoParcial,
                TotalPresupuestado.nuevo().agregar(actual));
    }

    @Override
    public TotalPresupuestado combinar(TotalPresupuestado acumuladoParcial1,
            TotalPresupuestado acumuladoParcial2) {
        TotalPresupuestado totales = TotalPresupuestado.nuevo();

        totales.sumarTotalFijo(acumuladoParcial1.getTotalFijo());
        totales.sumarTotalPorcentual(acumuladoParcial1.getTotalPorcentual());

        totales.sumarTotalFijo(acumuladoParcial2.getTotalFijo());
        totales.sumarTotalPorcentual(acumuladoParcial2.getTotalPorcentual());

        return totales;
    }

}