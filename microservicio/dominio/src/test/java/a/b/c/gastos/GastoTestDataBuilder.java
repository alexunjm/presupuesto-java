package a.b.c.gastos;

import a.b.c.gastos.modelo.dto.NuevoGasto;
import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;

import java.math.BigDecimal;

public class GastoTestDataBuilder {

    private Long id;
    private Long idPresupuesto;
    private Presupuesto presupuesto;
    private String descripcion;
    private BigDecimal valor;

    public NuevoGasto crearNuevo() {
        return new NuevoGasto(this.presupuesto, this.descripcion, this.valor);
    }

    public Gasto crear() {
        return Gasto.crear(new NuevoGasto(this.presupuesto, this.descripcion, this.valor), this.valor);
    }

    public Gasto reconstruir() {
        return Gasto.reconstruir(id, getIdPresupuesto(), descripcion, valor);
    }

    private Long getIdPresupuesto() {
        if (presupuesto != null) {
            return presupuesto.getId();
        }
        return idPresupuesto;
    }

    public GastoTestDataBuilder conGastoPorDefecto() {
        this.id = 0l;
        this.idPresupuesto = null;
        this.presupuesto = null;
        this.descripcion = "Ofrenda";
        this.valor = BigDecimal.ZERO;
        return this;
    }

    public GastoTestDataBuilder utilizandoPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
        return this;
    }

    public GastoTestDataBuilder conValorEntero(int valor) {
        this.valor = new BigDecimal(valor);
        return this;
    }

    public GastoTestDataBuilder conDescripion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public GastoTestDataBuilder conIdPresupuesto(Long idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
        return this;
    }
}
