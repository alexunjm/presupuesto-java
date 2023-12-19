package a.b.c.presupuesto.controlador;

import a.b.c.ComandoRespuesta;
import a.b.c.presupuesto.comando.ComandoCrearPresupuesto;
import a.b.c.presupuesto.comando.manejador.ManejadorCrearPresupuesto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presupuesto")
@Tag(name = "Controlador comando presupuesto")
public class ControladorComandoPresupuesto {

    private final ManejadorCrearPresupuesto manejadorCrearPresupuesto;

    public ControladorComandoPresupuesto(ManejadorCrearPresupuesto manejadorCrearPresupuesto) {
        this.manejadorCrearPresupuesto = manejadorCrearPresupuesto;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Crear presupuesto", description = "Metodo utilizado para crear un nuevo presupuesto")
    public ComandoRespuesta<Long> crearPresupuesto(@RequestBody ComandoCrearPresupuesto comandoCrearPresupuesto) {
        return this.manejadorCrearPresupuesto.ejecutar(comandoCrearPresupuesto);
    }
}