package a.b.c.gastos.puerto.repositorio;

import a.b.c.gastos.modelo.entidad.Gasto;

public interface RepositorioGastos {
    Long guardar(Gasto nuevoGasto);
}
