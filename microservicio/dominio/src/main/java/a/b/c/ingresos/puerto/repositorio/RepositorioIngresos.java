package a.b.c.ingresos.puerto.repositorio;

import java.math.BigDecimal;

import a.b.c.ingresos.modelo.entidad.Ingreso;

public interface RepositorioIngresos {
    Long guardar(Ingreso nuevoIngreso);

    void actualizarAhorrosIniciales(BigDecimal ingresosIniciales);
}
