package a.b.c.ingresos.controlador;

import a.b.c.ComandoRespuesta;
import a.b.c.ingresos.comando.ComandoCrearIngreso;
import a.b.c.ingresos.comando.manejador.ManejadorCrearIngreso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingreso")
@Tag(name = "Controlador comando ingreso")
public class ControladorComandoIngreso {

    private final ManejadorCrearIngreso manejadorCrearIngreso;

    public ControladorComandoIngreso(ManejadorCrearIngreso manejadorCrearIngreso) {
        this.manejadorCrearIngreso = manejadorCrearIngreso;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Crear ingreso", description = "Metodo utilizado para crear un nuevo ingreso")
    public ComandoRespuesta<Long> crearIngreso(@RequestBody ComandoCrearIngreso comandoCrearIngreso) {
        return this.manejadorCrearIngreso.ejecutar(comandoCrearIngreso);
    }
}
