package a.b.c.ingresos;

import a.b.c.ingresos.modelo.entidad.Ingreso;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class IngresoTest {

    @Test
    public void deberiaCrearIngresoConDescripcionYValor() {
        // Arrange (AAA Pattern)
        String descripcionEsperada = "Ingreso por salario";
        String valorEsperado = "1000";
        IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder()
                .conDescripcion(descripcionEsperada)
                .conValor(new BigDecimal(valorEsperado));

        // Act
        Ingreso ingreso = ingresoTestDataBuilder.build();

        // Assert
        assertEquals(descripcionEsperada, ingreso.getDescripcion());
        assertEquals(new BigDecimal(valorEsperado), ingreso.getValor());
    }
}