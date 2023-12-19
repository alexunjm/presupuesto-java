package a.b.c.resumen.controlador;

import a.b.c.resumen.comando.ComandoModificarTotalDisponible;
import a.b.c.resumen.comando.manejador.ManejadorComandoResumen;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumen")
@Tag(name = "Controlador comando resumen")
public class ControladorComandoResumen {

    private final ManejadorComandoResumen manejadorComandorResumen;

    public ControladorComandoResumen(ManejadorComandoResumen manejadorComandorResumen) {
        this.manejadorComandorResumen = manejadorComandorResumen;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("modificar-total-disponible")
    @Operation(summary = "Modificar el total disponible", description = "Metodo utilizado para modificar el total disponible")
    public void modificarTotalDisponible(
            @RequestBody ComandoModificarTotalDisponible comandoModificarTotalDisponible) {
        this.manejadorComandorResumen.ejecutar(comandoModificarTotalDisponible);
    }
}
