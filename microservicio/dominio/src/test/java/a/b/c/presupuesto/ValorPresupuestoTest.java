package a.b.c.presupuesto;

import a.b.c.core.BasePrueba;
import a.b.c.presupuesto.modelo.valueobject.ValorPresupuesto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ValorPresupuestoTest {

    private static final String EL_VALOR_FIJO_NO_ES_EL_ESPERADO = "El valor fijo no es el esperado";
    private static final String EL_VALOR_PORCENTUAL_NO_ES_EL_ESPERADO = "El valor porcentual no es el esperado";

    @Test
    void deberiaCrearUnValorPresupuestoConPorcentaje() {
        ValorPresupuesto valorPresupuesto = new ValorPresupuestoTestDataBuilder().conIngresosDefecto()
                .conTotalIngresosMes(200)
                .crearValorPorcentual(10);

        Assertions.assertTrue(valorPresupuesto.esPorcentual());
        BasePrueba.assertCompareEquals(BigDecimal.valueOf(10.00).compareTo(valorPresupuesto.getPorcentaje()),
                EL_VALOR_PORCENTUAL_NO_ES_EL_ESPERADO);
        BasePrueba.assertCompareEquals(BigDecimal.valueOf(20.00).compareTo(valorPresupuesto.getValor()),
                EL_VALOR_FIJO_NO_ES_EL_ESPERADO);
    }

    @Test
    void deberiaCrearUnValorPresupuestoFijo() {
        ValorPresupuesto valorPresupuesto = new ValorPresupuestoTestDataBuilder().conIngresosDefecto()
                .conTotalIngresosMes(200)
                .crearValorFijo(50);

        Assertions.assertFalse(valorPresupuesto.esPorcentual());
        BasePrueba.assertCompareEquals(BigDecimal.valueOf(25.00).compareTo(valorPresupuesto.getPorcentaje()),
                EL_VALOR_PORCENTUAL_NO_ES_EL_ESPERADO);
        BasePrueba.assertCompareEquals(BigDecimal.valueOf(50.00).compareTo(valorPresupuesto.getValor()),
                EL_VALOR_FIJO_NO_ES_EL_ESPERADO);
    }

    @Test
    void deberiaActualizarPorcentajeAlActualizarTotalIngresosMes() {
        ValorPresupuesto valorPresupuesto = new ValorPresupuestoTestDataBuilder().conIngresosDefecto()
                .conTotalIngresosMes(200)
                .crearValorFijo(50);

        BigDecimal por2 = new BigDecimal(2);
        BigDecimal dobleDeIngresos = new BigDecimal(200).multiply(por2);

        valorPresupuesto.actualizarTotalPresupuestadoMes(dobleDeIngresos);

        Assertions.assertFalse(valorPresupuesto.esPorcentual());
        BasePrueba.assertCompareEquals(
                BigDecimal.valueOf(25.00 / 2).compareTo(valorPresupuesto.getPorcentaje()),
                EL_VALOR_PORCENTUAL_NO_ES_EL_ESPERADO);
        BasePrueba.assertCompareEquals(BigDecimal.valueOf(50.00).compareTo(valorPresupuesto.getValor()),
                EL_VALOR_FIJO_NO_ES_EL_ESPERADO);
    }
}
