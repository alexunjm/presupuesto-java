package a.b.c.resumen.adaptador.repositorio;

import java.math.BigDecimal;

import a.b.c.ingresos.puerto.dao.DaoIngresos;
import a.b.c.ingresos.puerto.repositorio.RepositorioIngresos;
import a.b.c.resumen.modelo.entidad.TotalDisponible;
import a.b.c.resumen.puerto.repositorio.RepositorioTotalDisponible;

import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTotalDisponibleMysql implements RepositorioTotalDisponible {

    private final DaoIngresos daoIngresos;
    private final RepositorioIngresos repositorioIngresos;

    public RepositorioTotalDisponibleMysql(DaoIngresos daoIngresos, RepositorioIngresos repositorioIngresos) {
        this.daoIngresos = daoIngresos;
        this.repositorioIngresos = repositorioIngresos;
    }

    @Override
    public void guardar(TotalDisponible totalDisponible) {
        actualizarAhorrosIniciales(
                calcularTotalDisponibleInicial(totalDisponible, daoIngresos.obtenerTotalIngresos()));
    }

    private BigDecimal calcularTotalDisponibleInicial(TotalDisponible totalDisponible, BigDecimal totalIngresos) {
        return totalDisponible.getValor().subtract(totalIngresos);
    }

    private void actualizarAhorrosIniciales(BigDecimal ahorrosIniciales) {
        repositorioIngresos.actualizarAhorrosIniciales(ahorrosIniciales);
    }

}
