package a.b.c.presupuesto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import a.b.c.core.BasePrueba;
import a.b.c.gastos.GastoTestDataBuilder;
import a.b.c.gastos.puerto.dao.DaoGastos;
import a.b.c.presupuesto.excepcion.ExcepcionPresupuestoNoEncontrado;
import a.b.c.presupuesto.modelo.dto.PresupuestoDTO;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.puerto.dao.DaoPresupuestos;
import a.b.c.presupuesto.servicio.ServicioObtenerPresupuestosParaDebitar;

public class ServicioObtenerPresupuestosParaDebitarTest {

        private static final String PRESUPUESTO_NO_ENCONTRADO = "Presupuesto no encontrado";
        DaoPresupuestos daoPresupuestos;
        DaoGastos daoGastos;

        ServicioObtenerPresupuestosParaDebitar servicioObtenerPresupuestosParaDebitar;

        @BeforeEach
        public void setUp() {
                daoPresupuestos = Mockito.mock(DaoPresupuestos.class);

                daoGastos = Mockito.mock(DaoGastos.class);

                servicioObtenerPresupuestosParaDebitar = new ServicioObtenerPresupuestosParaDebitar(
                                daoPresupuestos, daoGastos);
        }

        @Test
        public void deberiaReconstruirPresupuestoParaDebitarCuandoElIdEsValido() {
                // Arrange
                Long idPresupuesto = 1L;
                PresupuestoDTO presupuestoDTO = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                                .conId(idPresupuesto)
                                .conValorFijo(BigDecimal.valueOf(50))
                                .construirDTO();
                Mockito.when(daoPresupuestos.obtenerPresupuestosMesEnCurso())
                                .thenReturn(Arrays.asList(presupuestoDTO));
                Mockito.when(daoGastos.obtenerGastosDe(idPresupuesto))
                                .thenReturn(Arrays.asList(
                                                new GastoTestDataBuilder().conGastoPorDefecto()
                                                                .conIdPresupuesto(idPresupuesto)
                                                                .conValorEntero(50).reconstruir()));

                // Act
                Presupuesto presupuestoReconstruido = servicioObtenerPresupuestosParaDebitar
                                .reconstruirUno(idPresupuesto);

                // Assert
                assertNotNull(presupuestoReconstruido);
                assertEquals(idPresupuesto, presupuestoReconstruido.getId());

                Mockito.verify(daoPresupuestos, Mockito.times(1)).obtenerPresupuestosMesEnCurso();
                Mockito.verify(daoGastos, Mockito.times(1)).obtenerGastosDe(idPresupuesto);
        }

        @Test
        public void deberiaLanzarExcepcionCuandoPresupuestoNoEncontrado() {
                // Arrange
                Long idPresupuesto = 1L;
                PresupuestoDTO presupuestoDto = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                                .conId(idPresupuesto)
                                .conValorFijo(BigDecimal.valueOf(50))
                                .construirDTO();
                Mockito.when(daoPresupuestos.obtenerPresupuestosMesEnCurso())
                                .thenReturn(Arrays.asList(presupuestoDto));
                Mockito.when(daoGastos.obtenerGastosDe(idPresupuesto))
                                .thenReturn(Arrays.asList(
                                                new GastoTestDataBuilder().conGastoPorDefecto()
                                                                .conIdPresupuesto(idPresupuesto)
                                                                .conValorEntero(50).reconstruir()));

                // Act
                BasePrueba.assertThrows(() -> {
                        long idPresupuestoNoExistente = 2l;
                        return servicioObtenerPresupuestosParaDebitar.reconstruirUno(idPresupuestoNoExistente);
                }, ExcepcionPresupuestoNoEncontrado.class, PRESUPUESTO_NO_ENCONTRADO);
        }
}
