package a.b.c.resumen.comando.fabrica;

import a.b.c.resumen.comando.ComandoModificarTotalDisponible;
import a.b.c.resumen.modelo.entidad.TotalDisponible;

import org.springframework.stereotype.Component;

@Component
public class FabricaTotalDisponible {

    public TotalDisponible crear(ComandoModificarTotalDisponible comandoModificarTotalDisponible) {
        return TotalDisponible.crear(comandoModificarTotalDisponible.getValor());
    }

}
