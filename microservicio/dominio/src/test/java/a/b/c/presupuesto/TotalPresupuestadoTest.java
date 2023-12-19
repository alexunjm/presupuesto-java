package a.b.c.presupuesto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import a.b.c.core.BasePrueba;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.modelo.entidad.TotalPresupuestado;

class TotalPresupuestadoTest {

        private static final String EL_TOTAL_PORCENTUAL_NO_ES_EL_ESPERADO = "El total porcentual no es el esperado";
        private static final String EL_TOTAL_FIJO_NO_ES_EL_ESPERADO = "El total fijo no es el esperado";

        @Test
        public void dadoListaPresupuestosConValorFijo_cuandoCalculaTotalPresupuestado_entoncesTotalFijoYTotalPorcentualSonCorrectos() {
                // Arrange (Preparar)
                Presupuesto presupuesto1 = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                                .conValorFijo(BigDecimal.valueOf(100.00))
                                .reconstruir();
                Presupuesto presupuesto2 = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                                .conValorFijo(BigDecimal.valueOf(200.00))
                                .reconstruir();
                List<Presupuesto> presupuestos = Arrays.asList(presupuesto1, presupuesto2);

                // Act (Actuar)
                TotalPresupuestado totalPresupuestado = TotalPresupuestado.calcular(presupuestos);

                // Assert (Afirmar)
                BasePrueba.assertCompareEquals(BigDecimal.valueOf(300.00).compareTo(totalPresupuestado.getTotalFijo()),
                                EL_TOTAL_FIJO_NO_ES_EL_ESPERADO);
        }

        @Test
        public void dadoListaPresupuestosConValoresMixtos_cuandoCalculaTotalPresupuestado_entoncesTotalFijoYTotalPorcentualSonCorrectos() {
                // Arrange (Preparar)
                Presupuesto presupuesto1 = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                                .conValorFijo(BigDecimal.valueOf(80.00))
                                .reconstruir();
                Presupuesto presupuesto2 = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                                .conValorPorcentual(20)
                                .reconstruir();
                List<Presupuesto> presupuestos = Arrays.asList(presupuesto1, presupuesto2);

                // Act (Actuar)
                TotalPresupuestado totalPresupuestado = TotalPresupuestado.calcular(presupuestos);

                // Assert (Afirmar)
                BasePrueba.assertCompareEquals(BigDecimal.valueOf(80.00).compareTo(totalPresupuestado.getTotalFijo()),
                                EL_TOTAL_FIJO_NO_ES_EL_ESPERADO);
                BasePrueba.assertCompareEquals(
                                BigDecimal.valueOf(20.00).compareTo(totalPresupuestado.getTotalPorcentual()),
                                EL_TOTAL_PORCENTUAL_NO_ES_EL_ESPERADO);
        }

        @Test
        public void dadoPresupuesto_cuandoAgregarPresupuestoATotalPresupuestado_entoncesTotalFijoYTotalPorcentualSonActualizados() {
                // Arrange
                TotalPresupuestado totalPresupuestado = TotalPresupuestado.nuevo();
                Presupuesto presupuesto = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                                .conValorFijo(BigDecimal.valueOf(150.00))
                                .reconstruir();

                // Act
                totalPresupuestado.agregar(presupuesto);

                // Assert
                BasePrueba.assertCompareEquals(BigDecimal.valueOf(150.00).compareTo(totalPresupuestado.getTotalFijo()),
                                EL_TOTAL_FIJO_NO_ES_EL_ESPERADO);
                BasePrueba.assertCompareEquals(
                                BigDecimal.valueOf(0.00).compareTo(totalPresupuestado.getTotalPorcentual()),
                                EL_TOTAL_PORCENTUAL_NO_ES_EL_ESPERADO);
        }

        @Test
        public void dadoListaVacia_cuandoCalculaTotalPresupuestado_entoncesTotalesSonCero() {
                // Arrange
                List<Presupuesto> presupuestos = Arrays.asList();

                // Act
                TotalPresupuestado totalPresupuestado = TotalPresupuestado.calcular(presupuestos);

                // Assert
                BasePrueba.assertCompareEquals(BigDecimal.ZERO.compareTo(totalPresupuestado.getTotalFijo()),
                                EL_TOTAL_FIJO_NO_ES_EL_ESPERADO);
                BasePrueba.assertCompareEquals(BigDecimal.ZERO.compareTo(totalPresupuestado.getTotalPorcentual()),
                                EL_TOTAL_PORCENTUAL_NO_ES_EL_ESPERADO);
        }
}
