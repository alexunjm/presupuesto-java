package a.b.c.presupuesto.controlador;

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
@WebMvcTest(ControladorConsultaPresupuesto.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControladorConsultaPresupuestoTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void consultarPresupuesto() throws Exception {
        mockMvc.perform(
                get("/presupuesto").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nombre", is("Gastos Fijos")))
                .andExpect(jsonPath("$[0].descripcion",
                        is("Arriendo, Servicios Públicos, Claro, Funeraria, Vigilancia, Celular")))
                .andExpect(jsonPath("$[0].porcentual", is(false)))
                .andExpect(jsonPath("$[0].valorFijo", comparesEqualTo(900.00)))
                .andExpect(jsonPath("$[0].valorPorcentual", comparesEqualTo(90.00)));
    }
}
