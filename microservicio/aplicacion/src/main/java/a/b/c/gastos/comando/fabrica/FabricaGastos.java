package a.b.c.gastos.comando.fabrica;

import a.b.c.gastos.comando.ComandoNuevoGasto;
import a.b.c.gastos.modelo.dto.NuevoGasto;
import a.b.c.presupuesto.comando.fabrica.FabricaPresupuesto;
import org.springframework.stereotype.Component;

@Component
public class FabricaGastos {

    private final FabricaPresupuesto fabricaPresupuesto;

    public FabricaGastos(FabricaPresupuesto fabricaPresupuesto) {
        this.fabricaPresupuesto = fabricaPresupuesto;
    }

    public NuevoGasto crear(ComandoNuevoGasto comandoNuevoGasto) {
        return new NuevoGasto(
                fabricaPresupuesto.reconstruirPresupuestoParaDebitar(comandoNuevoGasto.getIdPresupuesto()),
                comandoNuevoGasto.getDescripcion(),
                comandoNuevoGasto.getValor());
    }
}
