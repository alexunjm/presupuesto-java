package a.b.c.presupuesto.excepcion;

public class ExcepcionPresupuestoNoEncontrado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionPresupuestoNoEncontrado(String mensaje, Long idPresupuesto) {
        super(mensaje + "\n idPresupuesto:\t\t" + idPresupuesto + "\n");
    }
}
