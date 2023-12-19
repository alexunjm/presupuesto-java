package a.b.c.presupuesto;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import a.b.c.core.BasePrueba;
import a.b.c.gastos.GastoTestDataBuilder;
import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.modelo.entidad.TotalizadorGastadoPorPresupuesto;

class TotalizadorGastadoPorPresupuestoTest {

    /**
     *
     */
    private static final String ERROR_EL_TOTAL_GASTADO_NO_ES_CORRECTO = "El total gastado no es correcto";

    @Test
    public void dadoUnTotalParcialYUnPresupuesto_cuandoAcumular_entoncesTotalesSonLaSumaDeLosValores() {
        // Arrange
        BigDecimal totalParcial = new BigDecimal("100");
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                .conValorFijo(new BigDecimal("200")).reconstruir();
        ArrayList<Gasto> gastos = new ArrayList<Gasto>();
        gastos.add(new GastoTestDataBuilder().conGastoPorDefecto().conIdPresupuesto(presupuesto.getId())
                .conValorEntero(200).reconstruir());
        presupuesto.reconstruirGastos(gastos);
        // Act
        BigDecimal totalGastadoAcumulado = TotalizadorGastadoPorPresupuesto.crear().acumular(
                totalParcial,
                presupuesto);

        // Assert
        BasePrueba.assertCompareEquals(
                BigDecimal.valueOf(300.00).compareTo(totalGastadoAcumulado),
                "Acumulado: " + ERROR_EL_TOTAL_GASTADO_NO_ES_CORRECTO);
    }

    @Test
    public void dadoDosTotalesParciales_cuandoCombinar_entoncesTotalesSonLaSumaDeAmbos() {
        // Arrange
        BigDecimal totalParcial1 = new BigDecimal("100");
        BigDecimal totalParcial2 = new BigDecimal("200");

        // Act
        BigDecimal totalGastadoCombinado = TotalizadorGastadoPorPresupuesto.crear().combinar(
                totalParcial1,
                totalParcial2);

        // Assert
        BasePrueba.assertCompareEquals(
                BigDecimal.valueOf(300.00).compareTo(totalGastadoCombinado),
                "Combinado: " + ERROR_EL_TOTAL_GASTADO_NO_ES_CORRECTO);
    }
}
