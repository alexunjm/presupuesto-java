package a.b.c.gastos.comando.manejador;

import a.b.c.ComandoRespuesta;
import a.b.c.gastos.comando.fabrica.FabricaGastos;
import a.b.c.gastos.comando.ComandoNuevoGasto;
import a.b.c.gastos.servicio.ServicioDebitar;
import a.b.c.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorNuevoGasto implements ManejadorComandoRespuesta<ComandoNuevoGasto, ComandoRespuesta<Long>> {

    private final FabricaGastos fabricaGastos;
    private final ServicioDebitar servicioDebitar;

    public ManejadorNuevoGasto(FabricaGastos fabricaGastos, ServicioDebitar servicioDebitar) {
        this.fabricaGastos = fabricaGastos;
        this.servicioDebitar = servicioDebitar;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoNuevoGasto comandoNuevoGasto) {
        return new ComandoRespuesta<>(servicioDebitar.ejecutar(fabricaGastos.crear(comandoNuevoGasto)));
    }
}
