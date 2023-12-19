package a.b.c.ingresos.comando.fabrica;

import a.b.c.ingresos.comando.ComandoCrearIngreso;
import a.b.c.ingresos.modelo.entidad.Ingreso;
import org.springframework.stereotype.Component;

@Component
public class FabricaIngresos {

    public Ingreso crear(ComandoCrearIngreso comandoCrearIngreso){
        return Ingreso.crear(comandoCrearIngreso.getDescripcion(), comandoCrearIngreso.getValor());
    }
}
