package a.b.c.gastos.controlador;

import a.b.c.ComandoRespuesta;
import a.b.c.gastos.comando.ComandoNuevoGasto;
import a.b.c.gastos.comando.manejador.ManejadorNuevoGasto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gasto")
@Tag(name = "Controlador comando gasto")
public class ControladorComandoGasto {

    private final ManejadorNuevoGasto manejadorNuevoGasto;

    public ControladorComandoGasto(ManejadorNuevoGasto manejadorNuevoGasto) {
        this.manejadorNuevoGasto = manejadorNuevoGasto;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Nuevo gasto", description = "Metodo utilizado para crear un nuevo gasto")
    public ComandoRespuesta<Long> nuevoGasto(@RequestBody ComandoNuevoGasto comandoNuevoGasto) {
        return this.manejadorNuevoGasto.ejecutar(comandoNuevoGasto);
    }
}
