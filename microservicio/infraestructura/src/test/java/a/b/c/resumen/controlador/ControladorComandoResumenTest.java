package a.b.c.resumen.controlador;

import a.b.c.ApplicationMock;
import a.b.c.resumen.comando.ComandoModificarTotalDisponible;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ControladorComandoResumen.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControladorComandoResumenTest {

        @Autowired
        private ObjectMapper objectMapper;
        @Autowired
        private MockMvc mockMvc;

        @Test
        void modificarTotalDisponible() throws Exception {
                ComandoModificarTotalDisponible comandoModificarTotalDisponible = new ComandoModificarTotalDisponibleTestDataBuilder()
                                .conTotalDisponibleEnMil()
                                .construir();

                mockMvc.perform(
                                post("/resumen/modificar-total-disponible")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper
                                                                .writeValueAsString(comandoModificarTotalDisponible)))
                                .andExpect(status().is2xxSuccessful()).andReturn();
        }
}
