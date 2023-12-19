package a.b.c.gastos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import a.b.c.core.BasePrueba;
import a.b.c.dominio.excepcion.ExcepcionValorObligatorio;
import a.b.c.gastos.modelo.entidad.Gasto;

public class GastoTest {

    private static final long ID_PRESUPUESTO = 0L;
    private static final int VALOR_ENTERO_GASTO = 50;
    private static final String DESCRIPCION_GASTO = "Prueba de gasto";

    @Test
    public void deberiaCrearGastoCuandoLosArgumentosSonValidos() {
        // Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conGastoPorDefecto()
                .conIdPresupuesto(ID_PRESUPUESTO)
                .conDescripion(DESCRIPCION_GASTO).conValorEntero(VALOR_ENTERO_GASTO);

        // Act
        Gasto gasto = gastoTestDataBuilder.reconstruir();

        // Assert
        assertNotNull(gasto);
        assertEquals(0L, gasto.getId());
        assertEquals(ID_PRESUPUESTO, gasto.getIdPresupuesto());
        assertEquals(DESCRIPCION_GASTO, gasto.getDescripcion());
        assertEquals(VALOR_ENTERO_GASTO, gasto.getValor().intValue());
    }

    @Test
    public void deberiaLanzarExcepcionCuandoElIdPresupuestoEsNulo() {
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conGastoPorDefecto()
                .conIdPresupuesto(null)
                .conDescripion(DESCRIPCION_GASTO).conValorEntero(VALOR_ENTERO_GASTO);

        BasePrueba.assertThrows(() -> gastoTestDataBuilder.reconstruir(),
                ExcepcionValorObligatorio.class,
                "El id de presupuesto es obligatorio");
    }
}
