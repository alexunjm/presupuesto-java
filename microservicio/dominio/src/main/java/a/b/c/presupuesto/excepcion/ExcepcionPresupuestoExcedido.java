package a.b.c.presupuesto.excepcion;

import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;

public class ExcepcionPresupuestoExcedido extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionPresupuestoExcedido(String mensaje, Presupuesto presupuesto, Gasto gasto) {
        super(mensaje + "\n Gasto:\t\t" + gasto + "\n Presupuesto:\t" + presupuesto + "\n");
    }
}
