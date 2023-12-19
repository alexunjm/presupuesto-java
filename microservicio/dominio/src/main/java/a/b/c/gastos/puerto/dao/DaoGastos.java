package a.b.c.gastos.puerto.dao;

import java.util.List;

import a.b.c.gastos.modelo.entidad.Gasto;

public interface DaoGastos {

    List<Gasto> obtenerGastosDe(Long idPresupuesto);
}
