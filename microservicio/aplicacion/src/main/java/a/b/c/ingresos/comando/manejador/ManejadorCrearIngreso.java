package a.b.c.ingresos.comando.manejador;

import a.b.c.ComandoRespuesta;
import a.b.c.ingresos.comando.fabrica.FabricaIngresos;
import a.b.c.ingresos.puerto.repositorio.RepositorioIngresos;
import a.b.c.ingresos.comando.ComandoCrearIngreso;
import a.b.c.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearIngreso implements ManejadorComandoRespuesta<ComandoCrearIngreso, ComandoRespuesta<Long>> {

    private final FabricaIngresos fabricaIngresos;
    private final RepositorioIngresos repositorioIngresos;

    public ManejadorCrearIngreso(FabricaIngresos fabricaIngresos, RepositorioIngresos repositorioIngresos) {
        this.fabricaIngresos = fabricaIngresos;
        this.repositorioIngresos = repositorioIngresos;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoCrearIngreso comandoCrearIngreso) {
        return new ComandoRespuesta<>(repositorioIngresos.guardar(fabricaIngresos.crear(comandoCrearIngreso)));
    }
}
