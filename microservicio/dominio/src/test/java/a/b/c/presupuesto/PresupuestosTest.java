package a.b.c.presupuesto;

import a.b.c.core.BasePrueba;
import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.modelo.entidad.Presupuestos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

public class PresupuestosTest {

    private static final String VALOR_FIJO = "valor fijo";
    private static final String VALOR_PORCENTUAL = "valor porcentual";
    private static final BigDecimal VALOR_PRESUPUESTO_FIJO = BigDecimal.valueOf(80.0);
    private static final BigDecimal VALOR_PRESUPUESTO_PORCENTUAL = BigDecimal.valueOf(20);
    private static final String EL_VALOR_FIJO_DE = "El valor fijo de ";
    private static final String EL_VALOR_PORCENTUAL_DE = "El valor porcentual de ";
    private static final String NO_ES_EL_ESPERADO = " no es el esperado";

    private PresupuestoTestDataBuilder presupuestoTestDataBuilder;

    @BeforeEach
    public void setUp() {
        presupuestoTestDataBuilder = new PresupuestoTestDataBuilder();
    }

    @Test
    public void deberiaRetornarListaDePresupuestoDTOMapeadoCorrectamente() {
        var listaPresupuestosDto = Arrays.asList(
                presupuestoTestDataBuilder
                        .conNombre(VALOR_FIJO)
                        .conValorFijo(VALOR_PRESUPUESTO_FIJO)
                        .construirDTO(),
                presupuestoTestDataBuilder
                        .conNombre(VALOR_PORCENTUAL)
                        .conValorPorcentual(VALOR_PRESUPUESTO_PORCENTUAL.intValue())
                        .construirDTO());

        var resultado = Presupuestos.reconstruir(listaPresupuestosDto);

        assertEquals(2, resultado.size());

        validarPresupuesto(
                new PresupuestoDTO(0l, VALOR_FIJO, null, false,
                        VALOR_PRESUPUESTO_FIJO, VALOR_PRESUPUESTO_FIJO,
                        BigDecimal.ZERO),
                resultado.get(0), VALOR_FIJO);
        validarPresupuesto(
                new PresupuestoDTO(0l, VALOR_PORCENTUAL, null, true,
                        VALOR_PRESUPUESTO_PORCENTUAL, VALOR_PRESUPUESTO_PORCENTUAL,
                        BigDecimal.ZERO),
                resultado.get(1), VALOR_PORCENTUAL);
    }

    private void validarPresupuesto(PresupuestoDTO presupuestoEsperado, Presupuesto presupuestoActual,
            String nombrePresupuesto) {
        assertEquals(presupuestoEsperado.getNombre(), presupuestoActual.getNombre());
        assertEquals(presupuestoEsperado.getDescripcion(), presupuestoActual.getDescripcion());
        assertEquals(presupuestoEsperado.isPorcentual(), presupuestoActual.esPorcentual());
        BasePrueba.assertCompareEquals(presupuestoEsperado.getValorFijo().compareTo(presupuestoActual.getValorFijo()),
                EL_VALOR_FIJO_DE + nombrePresupuesto + NO_ES_EL_ESPERADO);
        BasePrueba.assertCompareEquals(
                presupuestoEsperado.getValorPorcentual().compareTo(presupuestoActual.getValorPorcentual()),
                EL_VALOR_PORCENTUAL_DE + nombrePresupuesto + NO_ES_EL_ESPERADO);
    }
}
