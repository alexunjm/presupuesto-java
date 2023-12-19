package a.b.c.presupuesto.modelo.entidad;

import a.b.c.dominio.ValidadorArgumento;
import a.b.c.dominio.excepcion.ExcepcionValorInvalido;
import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.presupuesto.excepcion.ExcepcionPresupuestoExcedido;
import a.b.c.presupuesto.modelo.dto.NuevoPresupuesto;
import a.b.c.presupuesto.modelo.valueobject.ValorPresupuesto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Presupuesto {
    private static final String ERROR_NOMBRE_DEL_PRESUPUESTO_ES_REQUERIDO = "Nombre del presupuesto es requerido";
    private static final String ERROR_VALOR_NUMERICO_DEL_PRESUPUESTO_ES_REQUERIDO = "Valor numérico del presupuesto es requerido";
    private static final String ERROR_EL_VALOR_DEL_PRESUPUESTO_DEBE_SER_MAYOR_A_CERO = "El valor del presupuesto debe ser mayor a cero";

    private static final String ERROR_INTENTANDO_AGREGAR_UN_GASTO_AL_PRESUPUESTO_INCORRECTO = "Intentando agregar un gasto al presupuesto incorrecto";
    private static final String ERROR_NO_ES_POSIBLE_GASTAR_MAS_DE_LO_PRESUPUESTADO = "No es posible gastar más de lo presupuestado";

    private static final BigDecimal INGRESOS_MES_POR_DEFECTO = BigDecimal.ZERO;
    private static final BigDecimal TOTAL_GASTADO_INICIAL = BigDecimal.ZERO;

    private Long id;
    private String nombre;
    private String descripcion;
    private ValorPresupuesto valorPresupuesto;
    private List<Gasto> gastos;
    private BigDecimal totalGastado;

    private Presupuesto(String nombre, String descripcion, boolean esPorcentual, BigDecimal valor) {
        asignarValores(nombre, descripcion, esPorcentual, valor);
    }

    private Presupuesto(Long id, String nombre, String descripcion, boolean esPorcentual, BigDecimal valorPresupuesto) {
        this.id = id;
        asignarValores(nombre, descripcion, esPorcentual, valorPresupuesto);
    }

    private void asignarValores(String nombre, String descripcion, boolean esPorcentual, BigDecimal valor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorPresupuesto = ValorPresupuesto.crear(esPorcentual, valor, INGRESOS_MES_POR_DEFECTO);
        this.totalGastado = TOTAL_GASTADO_INICIAL;
        this.gastos = new ArrayList<>();
    }

    public static Presupuesto crear(NuevoPresupuesto nuevoPresupuesto) {
        validarArgumentos(nuevoPresupuesto.getNombre(), nuevoPresupuesto.getValor());
        return new Presupuesto(nuevoPresupuesto.getNombre(), nuevoPresupuesto.getDescripcion(),
                nuevoPresupuesto.isPorcentual(), nuevoPresupuesto.getValor());
    }

    public static Presupuesto reconstruir(Long id, String nombre, String descripcion, boolean esPorcentual,
            BigDecimal valor) {
        validarArgumentos(nombre, valor);
        return new Presupuesto(id, nombre, descripcion, esPorcentual, valor);
    }

    private static void validarArgumentos(String nombre, BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(nombre, ERROR_NOMBRE_DEL_PRESUPUESTO_ES_REQUERIDO);
        ValidadorArgumento.validarObligatorio(valor, ERROR_VALOR_NUMERICO_DEL_PRESUPUESTO_ES_REQUERIDO);
        ValidadorArgumento.validarPositivo(valor, ERROR_EL_VALOR_DEL_PRESUPUESTO_DEBE_SER_MAYOR_A_CERO);
    }

    public void actualizarTotalPresupuestadoMes(BigDecimal totalPresupuestado) {
        valorPresupuesto.actualizarTotalPresupuestadoMes(totalPresupuestado);
    }

    public void reconstruirGastos(List<Gasto> gastos) {
        this.gastos.clear();

        gastos.stream().forEach(this::debitar);
    }

    public void debitar(Gasto gasto) {
        validarPresupuestoAlQuePertenece(gasto);
        validarSiPuedeRealizarElGasto(gasto);
        agregar(gasto);
    }

    private void validarPresupuestoAlQuePertenece(Gasto gasto) {
        if (noPerteneceAEstePresupuesto(gasto)) {
            throw new ExcepcionValorInvalido(ERROR_INTENTANDO_AGREGAR_UN_GASTO_AL_PRESUPUESTO_INCORRECTO);
        }
    }

    private boolean noPerteneceAEstePresupuesto(Gasto gasto) {
        return !gasto.getIdPresupuesto().equals(this.getId());
    }

    private void validarSiPuedeRealizarElGasto(Gasto gasto) {
        if (noAlcanzaElPresupuestoPara(gasto)) {
            throw new ExcepcionPresupuestoExcedido(ERROR_NO_ES_POSIBLE_GASTAR_MAS_DE_LO_PRESUPUESTADO, this, gasto);
        }
    }

    private boolean noAlcanzaElPresupuestoPara(Gasto gasto) {
        return calcularDisponible().subtract(gasto.getValor()).compareTo(BigDecimal.ZERO) < 0;
    }

    private void agregar(Gasto gasto) {
        totalGastado = gasto.getValor().add(totalGastado);
        gastos.add(gasto);
    }

    public BigDecimal calcularDisponible() {
        return valorPresupuesto.getValor().subtract(totalGastado);
    }

    public boolean esPorcentual() {
        return this.valorPresupuesto.esPorcentual();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getValorFijo() {
        return valorPresupuesto.getValor();
    }

    public BigDecimal getValorPorcentual() {
        return valorPresupuesto.getPorcentaje();
    }

    public BigDecimal getTotalGastado() {
        return totalGastado;
    }

    public List<Gasto> getGastos() {
        return Collections.unmodifiableList(gastos);
    }

    @Override
    public String toString() {
        return this.nombre + "\n $ " + this.getValorFijo() + "\t\t" + this.getValorPorcentual() + " %";
    }
}
