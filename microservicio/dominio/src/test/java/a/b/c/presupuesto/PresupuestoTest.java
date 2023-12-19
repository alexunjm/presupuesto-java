package a.b.c.presupuesto;

import a.b.c.core.BasePrueba;
import a.b.c.dominio.excepcion.ExcepcionValorInvalido;
import a.b.c.gastos.GastoTestDataBuilder;
import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.presupuesto.excepcion.ExcepcionPresupuestoExcedido;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class PresupuestoTest {

    private static final BigDecimal TOTAL_INGRESOS_MES = BigDecimal.valueOf(5000);
    private static final int VALOR_PORCENTUAL = 10;
    private static final BigDecimal VALOR_FIJO_GENEROSIDAD = new BigDecimal(500);
    private static final double DISPONIBLE_EN_CERO = 0d;
    private static final long PRESUPUESTO_ID_GENEROSIDAD = 1L;
    private static final long PRESUPUESTO_ID_ALIMENTACION = 2L;

    @Test
    void deberiaCrearUnPresupuestoConPorcentaje() {
        Presupuesto presupuestoPorcentualGenerosidad = crearPresupuestoPorcentual("Generosidad",
                VALOR_PORCENTUAL);
        presupuestoPorcentualGenerosidad.actualizarTotalPresupuestadoMes(TOTAL_INGRESOS_MES);

        assertPresupuestoPorcentual(presupuestoPorcentualGenerosidad, "Generosidad",
                VALOR_FIJO_GENEROSIDAD.intValue());
    }

    @Test
    void deberiaCrearUnPresupuestoConValorFijo() {
        Presupuesto presupuestoValorFijoGenerosidad = crearPresupuestoValorFijo("Generosidad",
                VALOR_FIJO_GENEROSIDAD);
        presupuestoValorFijoGenerosidad.actualizarTotalPresupuestadoMes(TOTAL_INGRESOS_MES);

        assertPresupuestoConValorFijo(presupuestoValorFijoGenerosidad, "Generosidad",
                VALOR_FIJO_GENEROSIDAD.intValue());
    }

    @Test
    void deberiaReconstruirUnPresupuestoConGastos() {
        Presupuesto presupuestoPorcentualGenerosidad = reconstruirPresupuestoPorcentual(
                PRESUPUESTO_ID_GENEROSIDAD,
                "Generosidad", VALOR_PORCENTUAL);

        List<Gasto> gastos = Arrays.asList(new GastoTestDataBuilder().conGastoPorDefecto()
                .conDescripion("ofrenda").utilizandoPresupuesto(presupuestoPorcentualGenerosidad)
                .conValorEntero(500).crear());

        presupuestoPorcentualGenerosidad.actualizarTotalPresupuestadoMes(TOTAL_INGRESOS_MES);
        presupuestoPorcentualGenerosidad.reconstruirGastos(gastos);

        assertPresupuestoPorcentual(presupuestoPorcentualGenerosidad, "Generosidad",
                VALOR_FIJO_GENEROSIDAD.intValue());
        Assertions.assertEquals(DISPONIBLE_EN_CERO,
                presupuestoPorcentualGenerosidad.calcularDisponible().doubleValue());
        Assertions.assertEquals(PRESUPUESTO_ID_GENEROSIDAD, presupuestoPorcentualGenerosidad.getId());
    }

    @Test
    void deberiaFallarAlEncontrarGastosQueNoPertenezcanAlPresupuesto() {
        Presupuesto presupuestoPorcentualGenerosidad = reconstruirPresupuestoPorcentual(
                PRESUPUESTO_ID_GENEROSIDAD,
                "Generosidad", VALOR_PORCENTUAL);
        Presupuesto presupuestoPorcentualAlimentacion = reconstruirPresupuestoPorcentual(
                PRESUPUESTO_ID_ALIMENTACION,
                "Alimentacion", VALOR_PORCENTUAL);

        List<Gasto> gastos = Arrays.asList(
                new GastoTestDataBuilder().conGastoPorDefecto()
                        .conDescripion("ofrenda")
                        .utilizandoPresupuesto(presupuestoPorcentualAlimentacion)
                        .conValorEntero(VALOR_FIJO_GENEROSIDAD.intValue()).crear());

        BasePrueba.assertThrows(() -> presupuestoPorcentualGenerosidad.reconstruirGastos(gastos),
                ExcepcionValorInvalido.class,
                "Intentando agregar un gasto al presupuesto incorrecto");
    }

    @Test
    void deberiaFallarAlEncontrarGastosQueSuperenElPresupuesto() {
        Presupuesto presupuestoPorcentualGenerosidad = reconstruirPresupuestoPorcentual(
                PRESUPUESTO_ID_GENEROSIDAD,
                "Generosidad", VALOR_PORCENTUAL);
        presupuestoPorcentualGenerosidad.actualizarTotalPresupuestadoMes(TOTAL_INGRESOS_MES);

        List<Gasto> gastos = Arrays.asList(
                new GastoTestDataBuilder().conGastoPorDefecto()
                        .conDescripion("ofrenda")
                        .conValorEntero(VALOR_FIJO_GENEROSIDAD.intValue())
                        .utilizandoPresupuesto(presupuestoPorcentualGenerosidad).crear(),
                new GastoTestDataBuilder().conGastoPorDefecto()
                        .conDescripion("ofrenda")
                        .conValorEntero(VALOR_FIJO_GENEROSIDAD.intValue())
                        .utilizandoPresupuesto(presupuestoPorcentualGenerosidad).crear());

        BasePrueba.assertThrows(() -> presupuestoPorcentualGenerosidad.reconstruirGastos(gastos),
                ExcepcionPresupuestoExcedido.class,
                "No es posible gastar más de lo presupuestado");
    }

    private Presupuesto crearPresupuestoPorcentual(String nombre, int porcentaje) {
        return new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                .conNombre(nombre)
                .conValorPorcentual(porcentaje)
                .crear();
    }

    private Presupuesto reconstruirPresupuestoPorcentual(long id, String nombre, int porcentaje) {
        return new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                .conNombre(nombre)
                .conValorPorcentual(porcentaje)
                .conId(id)
                .reconstruir();
    }

    private Presupuesto crearPresupuestoValorFijo(String nombre, BigDecimal valorFijo) {
        return new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                .conNombre(nombre)
                .conValorFijo(valorFijo)
                .crear();
    }

    private void assertPresupuestoPorcentual(Presupuesto presupuesto, String nombre, int valorFijo) {
        Assertions.assertEquals(nombre, presupuesto.getNombre());
        Assertions.assertTrue(presupuesto.esPorcentual());
        Assertions.assertEquals(valorFijo, presupuesto.getValorFijo().intValue());
    }

    private void assertPresupuestoConValorFijo(Presupuesto presupuesto, String nombre, int valorFijo) {
        Assertions.assertEquals(nombre, presupuesto.getNombre());
        Assertions.assertFalse(presupuesto.esPorcentual());
        Assertions.assertEquals(valorFijo, presupuesto.getValorFijo().intValue());
    }

}
