package a.b.c.gastos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import a.b.c.core.BasePrueba;
import a.b.c.gastos.excepcion.ExcepcionGastoNoPermitido;
import a.b.c.gastos.modelo.dto.NuevoGasto;
import a.b.c.gastos.modelo.entidad.Gasto;
import a.b.c.gastos.puerto.repositorio.RepositorioGastos;
import a.b.c.gastos.servicio.ServicioDebitar;
import a.b.c.ingresos.puerto.dao.DaoIngresos;
import a.b.c.presupuesto.PresupuestoTestDataBuilder;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;

public class ServicioDebitarTest {
    private static final BigDecimal SIN_DINERO_DISPONIBLE = BigDecimal.ZERO;
    private static final BigDecimal VALOR_FIJO = BigDecimal.valueOf(100);
    private static final long PRESUPUESTO_ID_ALIMENTACION = 2L;

    private DaoIngresos daoIngresos;
    private RepositorioGastos repositorioGastos;

    private Presupuesto presupuestoPorcentualAlimentacion;

    private ServicioDebitar servicioDebitar;

    @BeforeEach
    public void setUp() {
        daoIngresos = Mockito.mock(DaoIngresos.class);
        repositorioGastos = Mockito.mock(RepositorioGastos.class);
        Mockito.when(repositorioGastos.guardar(Mockito.any())).thenReturn(1l);

        presupuestoPorcentualAlimentacion = new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                .conNombre("Alimentacion")
                .conValorFijo(VALOR_FIJO)
                .conId(PRESUPUESTO_ID_ALIMENTACION)
                .reconstruir();

        servicioDebitar = new ServicioDebitar(daoIngresos, repositorioGastos);
    }

    @Test
    public void cuandoNoHayDineroDisponible_debitar_debeLanzarUnaExcepcionGastoNoPermitido() {
        Mockito.when(daoIngresos.obtenerTotalDisponible()).thenReturn(SIN_DINERO_DISPONIBLE);

        NuevoGasto nuevoGasto = new GastoTestDataBuilder().conGastoPorDefecto().conValorEntero(80)
                .utilizandoPresupuesto(presupuestoPorcentualAlimentacion).crearNuevo();

        BasePrueba.assertThrows(() -> servicioDebitar.ejecutar(nuevoGasto), ExcepcionGastoNoPermitido.class,
                "El dinero disponible es inferior al gasto que se quiere realizar");
        Mockito.verify(repositorioGastos, Mockito.times(0)).guardar(Mockito.any());
    }

    @Test
    public void cuandoHayDineroDisponible_debitar_debeDebitarGastoYGuardarEnRepositorio() {
        // Arrange (AAA pattern)
        Mockito.when(daoIngresos.obtenerTotalDisponible()).thenReturn(BigDecimal.valueOf(80));
        NuevoGasto nuevoGasto = new GastoTestDataBuilder().conGastoPorDefecto().conValorEntero(80)
                .utilizandoPresupuesto(presupuestoPorcentualAlimentacion).crearNuevo();
        when(repositorioGastos.guardar(any(Gasto.class))).thenReturn(1L);

        // Act (AAA pattern)
        Long resultadoId = servicioDebitar.ejecutar(nuevoGasto);

        // Assert (AAA pattern)
        ArgumentCaptor<Gasto> captorGasto = ArgumentCaptor.forClass(Gasto.class);
        Mockito.verify(repositorioGastos, Mockito.times(1)).guardar(captorGasto.capture());
        assertEquals(Long.valueOf(1), resultadoId);
    }
}
