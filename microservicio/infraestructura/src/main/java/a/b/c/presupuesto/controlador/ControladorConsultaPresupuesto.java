package a.b.c.presupuesto.controlador;

import a.b.c.presupuesto.consulta.manejador.ManejadorConsultarPresupuesto;
import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presupuesto")
@Tag(name = "Controlador consulta presupuesto")
public class ControladorConsultaPresupuesto {

    private final ManejadorConsultarPresupuesto manejadorConsultarPresupuesto;

    public ControladorConsultaPresupuesto(ManejadorConsultarPresupuesto manejadorConsultarPresupuesto) {
        this.manejadorConsultarPresupuesto = manejadorConsultarPresupuesto;
    }

    @GetMapping
    @Operation(summary = "Presupuesto", description = "Metodo utilizado para consultar el presupuesto")
    public List<PresupuestoDTO> obtenerPresupuesto() {
        return this.manejadorConsultarPresupuesto.ejecutar();
    }
}
