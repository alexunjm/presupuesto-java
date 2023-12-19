package a.b.c.resumen.controlador;

import a.b.c.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ControladorConsultaResumen.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControladorConsultaResumenTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void consultarResumen() throws Exception {
        mockMvc.perform(
                get("/resumen").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalDisponible", comparesEqualTo(500.00)))
                .andExpect(jsonPath("$.totalIngresosMesEnCurso", comparesEqualTo(500.00)))
                .andExpect(jsonPath("$.totalGastadoMesEnCurso", comparesEqualTo(850.00)))
                .andExpect(jsonPath("$.totalGastosPresupuestados", comparesEqualTo(1000.00)))
                .andExpect(jsonPath("$.presupuestos").isArray())
                .andExpect(jsonPath("$.presupuestos[0].nombre", is("Gastos Fijos")))
                .andExpect(jsonPath("$.presupuestos[0].valorFijo", comparesEqualTo(900.00)));
    }
}