package a.b.c.presupuesto.comando.manejador;

import a.b.c.ComandoRespuesta;
import a.b.c.presupuesto.comando.fabrica.FabricaPresupuesto;
import a.b.c.presupuesto.comando.ComandoCrearPresupuesto;
import a.b.c.manejador.ManejadorComandoRespuesta;
import a.b.c.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearPresupuesto
        implements ManejadorComandoRespuesta<ComandoCrearPresupuesto, ComandoRespuesta<Long>> {

    private final FabricaPresupuesto fabricaPresupuesto;
    private final RepositorioPresupuesto repositorioPresupuesto;

    public ManejadorCrearPresupuesto(FabricaPresupuesto fabricaPresupuesto,
            RepositorioPresupuesto repositorioPresupuesto) {
        this.fabricaPresupuesto = fabricaPresupuesto;
        this.repositorioPresupuesto = repositorioPresupuesto;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoCrearPresupuesto comandoCrearPresupuesto) {
        return new ComandoRespuesta<>(
                repositorioPresupuesto.guardar(fabricaPresupuesto.nuevoPresupuesto(comandoCrearPresupuesto)));
    }
}
