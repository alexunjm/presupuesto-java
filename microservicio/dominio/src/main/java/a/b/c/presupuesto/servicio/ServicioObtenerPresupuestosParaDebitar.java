package a.b.c.presupuesto.servicio;

import java.util.function.Predicate;

import a.b.c.gastos.puerto.dao.DaoGastos;
import a.b.c.presupuesto.excepcion.ExcepcionPresupuestoNoEncontrado;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.modelo.entidad.Presupuestos;
import a.b.c.presupuesto.puerto.dao.DaoPresupuestos;

public class ServicioObtenerPresupuestosParaDebitar {

    private static final int PRIMERO = 0;
    private static final String PRESUPUESTO_NO_ENCONTRADO = "Presupuesto no encontrado";
    private final DaoPresupuestos daoPresupuestos;
    private final DaoGastos daoGastos;

    public ServicioObtenerPresupuestosParaDebitar(DaoPresupuestos daoPresupuestos, DaoGastos daoGastos) {
        this.daoPresupuestos = daoPresupuestos;
        this.daoGastos = daoGastos;
    }

    public Presupuesto reconstruirUno(Long idPresupuesto) {

        var presupuestosEncontrados = reconstruirTodos().stream().filter(buscarPresupuesto(idPresupuesto)).toList();
        if (!presupuestosEncontrados.isEmpty()) {
            return presupuestosEncontrados.get(PRIMERO);
        }
        throw new ExcepcionPresupuestoNoEncontrado(PRESUPUESTO_NO_ENCONTRADO, idPresupuesto);
    }

    private Predicate<? super Presupuesto> buscarPresupuesto(Long idPresupuesto) {
        return presupuesto -> presupuesto.getId().equals(idPresupuesto);
    }

    public Presupuestos reconstruirTodos() {

        var presupuestos = Presupuestos.reconstruir(daoPresupuestos.obtenerPresupuestosMesEnCurso());

        Presupuesto presupuesto;
        for (int indice = 0; indice < presupuestos.size(); indice++) {
            presupuesto = presupuestos.get(indice);
            presupuesto.reconstruirGastos(daoGastos.obtenerGastosDe(presupuesto.getId()));
        }
        return presupuestos;
    }
}
