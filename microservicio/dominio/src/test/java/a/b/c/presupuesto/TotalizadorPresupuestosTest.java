package a.b.c.presupuesto;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import a.b.c.core.BasePrueba;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.modelo.entidad.TotalPresupuestado;
import a.b.c.presupuesto.modelo.entidad.TotalizadorPresupuestos;

class TotalizadorPresupuestosTest {

    private static final String EL_TOTAL_PORCENTUAL_NO_ES_EL_ESPERADO = "El total porcentual no es el esperado";
    private static final String EL_TOTAL_FIJO_NO_ES_EL_ESPERADO = "El total fijo no es el esperado";

    @Test
    public void dadoUnTotalPresupuestadoParcialesYUnPresupuesto_cuandoAcumular_entoncesTotalesSonLaSumaDeLosValores() {
        // Arrange
        TotalPresupuestado totalParcial1 = TotalPresupuestado.nuevo();
        totalParcial1.sumarTotalFijo(BigDecimal.valueOf(100.00));
        totalParcial1.sumarTotalPorcentual(BigDecimal.valueOf(10.00));

        Presupuesto presupuesto = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto().conValorPorcentual(20)
                .conValorFijo(new BigDecimal("200")).reconstruir();
        presupuesto.actualizarTotalPresupuestadoMes(new BigDecimal("1000"));

        // Act
        TotalPresupuestado totalPresupuestadoAcumulado = TotalizadorPresupuestos.crear().acumular(totalParcial1,
                presupuesto);

        // Assert
        BasePrueba.assertCompareEquals(
                BigDecimal.valueOf(300.00).compareTo(totalPresupuestadoAcumulado.getTotalFijo()),
                EL_TOTAL_FIJO_NO_ES_EL_ESPERADO);
        BasePrueba.assertCompareEquals(
                BigDecimal.valueOf(30.00).compareTo(totalPresupuestadoAcumulado.getTotalPorcentual()),
                EL_TOTAL_PORCENTUAL_NO_ES_EL_ESPERADO);
    }

    @Test
    public void dadoDosTotalesPresupuestadosParciales_cuandoCombinar_entoncesTotalesSonLaSumaDeAmbos() {
        // Arrange
        TotalPresupuestado totalParcial1 = TotalPresupuestado.nuevo();
        totalParcial1.sumarTotalFijo(BigDecimal.valueOf(100.00));
        totalParcial1.sumarTotalPorcentual(BigDecimal.valueOf(10.00));

        TotalPresupuestado totalParcial2 = TotalPresupuestado.nuevo();
        totalParcial2.sumarTotalFijo(BigDecimal.valueOf(200.00));
        totalParcial2.sumarTotalPorcentual(BigDecimal.valueOf(20.00));

        // Act
        TotalPresupuestado totalPresupuestadoCombinado = TotalizadorPresupuestos.crear().combinar(totalParcial1,
                totalParcial2);

        // Assert
        BasePrueba.assertCompareEquals(
                BigDecimal.valueOf(300.00).compareTo(totalPresupuestadoCombinado.getTotalFijo()),
                EL_TOTAL_FIJO_NO_ES_EL_ESPERADO);
        BasePrueba.assertCompareEquals(
                BigDecimal.valueOf(30.00).compareTo(totalPresupuestadoCombinado.getTotalPorcentual()),
                EL_TOTAL_PORCENTUAL_NO_ES_EL_ESPERADO);
    }
}
