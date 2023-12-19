package a.b.c.gastos.modelo.entidad;

import a.b.c.dominio.ValidadorArgumento;
import a.b.c.gastos.excepcion.ExcepcionGastoNoPermitido;
import a.b.c.gastos.modelo.dto.NuevoGasto;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;

import java.math.BigDecimal;

public final class Gasto {
    private static final String ERROR_EL_PRESUPUESTO_SELECCIONADO_NO_EXISTE = "El presupuesto seleccionado no existe";
    private static final String ERROR_EL_ID_DEL_GASTO_ES_OBLIGATORIO = "El id del gasto es obligatorio";
    private static final String ERROR_EL_ID_DE_PRESUPUESTO_ES_OBLIGATORIO = "El id de presupuesto es obligatorio";
    private static final String ERROR_DESCRIPCION_DEL_GASTO_ES_REQUERIDA = "Descripción del gasto es requerida";
    private static final String ERROR_VALOR_NUMERICO_DEL_GASTO_ES_REQUERIDO = "Valor numérico del gasto es requerido";
    private static final String ERROR_EL_GASTO_DEBE_SER_MAYOR_A_CERO = "El gasto debe ser mayor a cero";
    private static final String ERROR_GASTO_NO_PERMITIDO = "El dinero disponible es inferior al gasto que se quiere realizar";
    private Long id;
    private Long idPresupuesto;
    private String descripcion;
    private BigDecimal valor;

    public Gasto(Long id, Long idPresupuesto, String descripcion, BigDecimal valor) {
        this.id = id;
        this.idPresupuesto = idPresupuesto;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public static Gasto crear(NuevoGasto nuevoGasto, BigDecimal totalDisponible) {
        if (noHayDisponibleParaGastar(nuevoGasto, totalDisponible)) {// refactor
            throw new ExcepcionGastoNoPermitido(ERROR_GASTO_NO_PERMITIDO,
                    totalDisponible, nuevoGasto);
        }
        ValidadorArgumento.validarObligatorio(nuevoGasto.getPresupuesto(), ERROR_EL_PRESUPUESTO_SELECCIONADO_NO_EXISTE);
        validar(nuevoGasto.getDescripcion(), nuevoGasto.getValor());
        return new Gasto(null, nuevoGasto.getPresupuesto().getId(), nuevoGasto.getDescripcion(), nuevoGasto.getValor());
    }

    private static boolean noHayDisponibleParaGastar(NuevoGasto nuevoGasto, BigDecimal totalDisponible) {
        return totalDisponible.compareTo(nuevoGasto.getValor()) < 0;
    }

    public static Gasto reconstruir(Long id, Long idPresupuesto, String descripcion, BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(id, ERROR_EL_ID_DEL_GASTO_ES_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(idPresupuesto, ERROR_EL_ID_DE_PRESUPUESTO_ES_OBLIGATORIO);
        validar(descripcion, valor);
        return new Gasto(id, idPresupuesto, descripcion, valor);
    }

    private static void validar(String descripcion, BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(descripcion, ERROR_DESCRIPCION_DEL_GASTO_ES_REQUERIDA);
        ValidadorArgumento.validarObligatorio(valor, ERROR_VALOR_NUMERICO_DEL_GASTO_ES_REQUERIDO);
        ValidadorArgumento.validarPositivo(valor, ERROR_EL_GASTO_DEBE_SER_MAYOR_A_CERO);
    }

    public void debitarDelPresupuesto(Presupuesto presupuesto) {
        presupuesto.debitar(this);
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getIdPresupuesto() {
        return idPresupuesto;
    }

    @Override
    public String toString() {
        return this.descripcion + "\n $ " + this.getValor();
    }
}
