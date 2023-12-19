package a.b.c.resumen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import a.b.c.presupuesto.PresupuestoTestDataBuilder;
import a.b.c.presupuesto.modelo.entidad.Presupuesto;
import a.b.c.presupuesto.modelo.entidad.Presupuestos;
import a.b.c.resumen.modelo.dto.ResumenDTO;
import a.b.c.resumen.servicio.ServicioConsultarResumen;

public class ServicioConsultarResumenTest {
    Presupuestos presupuestos;
    private ServicioConsultarResumen servicioConsultarResumen;

    @BeforeEach
    public void setUp() {
        presupuestos = Mockito.mock(Presupuestos.class);
        servicioConsultarResumen = new ServicioConsultarResumen();
    }

    @Test
    public void cuandoGastosMenoresAIngresosMesEnCurso_DeberiaCalcularResumenCorrectamente() {
        // Arrange (AAA Pattern)
        BigDecimal totalIngresos = new BigDecimal("1000");
        BigDecimal totalGastosPresupuestados = new BigDecimal("500");
        BigDecimal totalDisponibleEsperado = new BigDecimal("500");

        BigDecimal totalIngresosMesEnCurso = new BigDecimal("800");
        BigDecimal totalGastadoMesEnCursoEsperado = new BigDecimal("500");

        Mockito.when(presupuestos.getTotalPresupuestado()).thenReturn(totalGastosPresupuestados);
        Mockito.when(presupuestos.getTotalGastado()).thenReturn(totalGastadoMesEnCursoEsperado);
        Mockito.when(presupuestos.stream()).thenReturn(stream(totalGastosPresupuestados));

        // Act
        ResumenDTO resultado = servicioConsultarResumen.ejecutar(totalIngresos, totalIngresosMesEnCurso, presupuestos);

        // Assert
        assertEquals(totalDisponibleEsperado, resultado.getTotalDisponible());
        assertEquals(totalIngresosMesEnCurso, resultado.getTotalIngresosMesEnCurso());
        assertEquals(totalGastadoMesEnCursoEsperado, resultado.getTotalGastadoMesEnCurso());
        assertEquals(totalGastosPresupuestados, resultado.getTotalGastosPresupuestados());
    }

    @Test
    public void cuandoGastosMayoresAIngresosMesEnCurso_DeberiaDisminuirElTotalDisponible() {
        // Arrange (AAA Pattern)
        BigDecimal totalIngresos = new BigDecimal("1000");
        BigDecimal totalGastosPresupuestados = new BigDecimal("500");
        BigDecimal totalDisponibleEsperado = new BigDecimal("500");

        BigDecimal totalIngresosMesEnCurso = new BigDecimal("400");
        BigDecimal totalGastadoMesEnCursoEsperado = new BigDecimal("500");

        Mockito.when(presupuestos.getTotalPresupuestado()).thenReturn(totalGastosPresupuestados);
        Mockito.when(presupuestos.getTotalGastado()).thenReturn(totalGastadoMesEnCursoEsperado);
        Mockito.when(presupuestos.stream()).thenReturn(stream(totalGastosPresupuestados));

        // Act
        ResumenDTO resultado = servicioConsultarResumen.ejecutar(totalIngresos, totalIngresosMesEnCurso, presupuestos);

        // Assert
        assertEquals(totalDisponibleEsperado, resultado.getTotalDisponible());
        assertEquals(totalIngresosMesEnCurso, resultado.getTotalIngresosMesEnCurso());
        assertEquals(totalGastadoMesEnCursoEsperado, resultado.getTotalGastadoMesEnCurso());
        assertEquals(totalGastosPresupuestados, resultado.getTotalGastosPresupuestados());
    }

    private Stream<Presupuesto> stream(BigDecimal totalPresupuestado) {
        return Arrays.asList(new PresupuestoTestDataBuilder().conPresupuestoPorDefecto()
                .conValorFijo(totalPresupuestado).reconstruir()).stream();
    }
}
