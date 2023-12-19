package a.b.c.resumen.comando.manejador;

import a.b.c.resumen.comando.ComandoModificarTotalDisponible;
import a.b.c.resumen.comando.fabrica.FabricaTotalDisponible;
import a.b.c.resumen.puerto.repositorio.RepositorioTotalDisponible;
import a.b.c.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorComandoResumen
        implements ManejadorComando<ComandoModificarTotalDisponible> {

    private final FabricaTotalDisponible fabricaTotalDisponible;
    private final RepositorioTotalDisponible repositorioTotalDisponible;

    public ManejadorComandoResumen(FabricaTotalDisponible fabricaTotalDisponible,
            RepositorioTotalDisponible repositorioTotalDisponible) {
        this.fabricaTotalDisponible = fabricaTotalDisponible;
        this.repositorioTotalDisponible = repositorioTotalDisponible;
    }

    public void ejecutar(ComandoModificarTotalDisponible comandoModificarTotalDisponible) {
        repositorioTotalDisponible.guardar(fabricaTotalDisponible.crear(comandoModificarTotalDisponible));
    }

}
