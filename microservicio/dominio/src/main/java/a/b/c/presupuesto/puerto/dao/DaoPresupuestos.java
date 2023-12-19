package a.b.c.presupuesto.puerto.dao;

import java.util.List;

import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;

public interface DaoPresupuestos {

    List<PresupuestoDTO> obtenerPresupuestosMesEnCurso();
}
