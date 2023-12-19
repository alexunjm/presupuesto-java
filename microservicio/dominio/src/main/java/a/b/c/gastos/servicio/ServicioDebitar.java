package a.b.c.gastos.servicio;

import a.b.c.gastos.modelo.dto.NuevoGasto;
import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.gastos.puerto.repositorio.RepositorioGastos;
import a.b.c.ingresos.puerto.dao.DaoIngresos;

public class ServicioDebitar {
    private final DaoIngresos daoIngresos;
    private final RepositorioGastos repositorioGastos;

    public ServicioDebitar(DaoIngresos daoIngresos, RepositorioGastos repositorioGastos) {
        this.daoIngresos = daoIngresos;
        this.repositorioGastos = repositorioGastos;
    }

    public Long ejecutar(NuevoGasto nuevoGasto) {
        Gasto gasto = Gasto.crear(nuevoGasto, daoIngresos.obtenerTotalDisponible());
        gasto.debitarDelPresupuesto(nuevoGasto.getPresupuesto());
        return repositorioGastos.guardar(gasto);
    }

}
