package a.b.c.resumen.controlador;

import a.b.c.resumen.consulta.manejador.ManejadorConsultarResumen;
import a.b.c.resumen.modelo.dto.ResumenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumen")
@Tag(name = "Controlador consulta resumen")
public class ControladorConsultaResumen {

    private final ManejadorConsultarResumen manejadorConsultarResumen;

    public ControladorConsultaResumen(ManejadorConsultarResumen manejadorConsultarResumen) {
        this.manejadorConsultarResumen = manejadorConsultarResumen;
    }

    @GetMapping
    @Operation(summary = "Resumen", description = "Metodo utilizado para consultar el resumen")
    public ResumenDTO obtenerResumen() {
        return this.manejadorConsultarResumen.ejecutar();
    }
}
