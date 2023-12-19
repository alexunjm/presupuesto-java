package a.b.c.presupuesto;

import a.b.c.presupuesto.modelo.dto.NuevoPresupuesto;
import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;

import java.math.BigDecimal;

public class PresupuestoTestDataBuilder {

    private Long id;
    private String nombre;
    private String descripcion;
    private boolean esPorcentual;
    private BigDecimal valor;

    public PresupuestoTestDataBuilder conPresupuestoPorDefecto() {
        this.id = 0l;
        this.nombre = "";
        this.descripcion = "";
        this.esPorcentual = false;
        this.valor = BigDecimal.ZERO;

        return this;
    }

    public Presupuesto crear() {
        return Presupuesto.crear(new NuevoPresupuesto(this.nombre, this.descripcion, this.esPorcentual, this.valor));
    }

    public Presupuesto reconstruir() {
        return Presupuesto.reconstruir(this.id, this.nombre, this.descripcion, this.esPorcentual, this.valor);
    }

    public PresupuestoDTO construirDTO() {
        if (esPorcentual) {
            return new PresupuestoDTO(id, nombre, descripcion, esPorcentual, BigDecimal.ZERO, valor, BigDecimal.ZERO);
        }
        return new PresupuestoDTO(id, nombre, descripcion, esPorcentual, valor, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public PresupuestoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PresupuestoTestDataBuilder conId(long id) {
        this.id = id;
        return this;
    }

    public PresupuestoTestDataBuilder conValorFijo(BigDecimal valor) {
        this.esPorcentual = false;
        this.valor = valor;
        return this;
    }

    public PresupuestoTestDataBuilder conValorPorcentual(int valor) {
        this.esPorcentual = true;
        this.valor = new BigDecimal(valor);
        return this;
    }
}
