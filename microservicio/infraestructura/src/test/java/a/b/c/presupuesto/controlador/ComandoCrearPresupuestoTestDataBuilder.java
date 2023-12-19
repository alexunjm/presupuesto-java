package a.b.c.presupuesto.controlador;

import a.b.c.presupuesto.comando.ComandoCrearPresupuesto;

import java.math.BigDecimal;


public class ComandoCrearPresupuestoTestDataBuilder {


    private String nombre;
    private String descripcion;
    private boolean esPorcentual;
    private BigDecimal valor;

    public ComandoCrearPresupuestoTestDataBuilder conPresupuestoPorDefecto() {
        this.nombre = "Gastos fijos";
        this.descripcion = "Arriendo, Servicios Públicos,\n" +
                "Claro, Funeraria, Vigilancia,\n" +
                "Celular";
        this.esPorcentual = false;
        this.valor = BigDecimal.valueOf(1000);
        return this;
    }

    public ComandoCrearPresupuesto construir() {
        return new ComandoCrearPresupuesto(this.nombre, this.descripcion, this.esPorcentual, this.valor);
    }
}
