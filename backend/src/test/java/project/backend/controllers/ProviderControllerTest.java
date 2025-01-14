package project.backend.controllers;

import java.util.Collections;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import project.backend.repository.ProviderRepository;

@Disabled
@WebMvcTest(ProviderController.class)
class ProviderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProviderRepository rep;

    @Test
    @Disabled
    void testAllProvider() throws Exception {
        when(rep.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/provider"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
    }
    

    @Test
    @Disabled
    void testPostProvider() throws Exception {
        // when(rep.save(Mockito.any(Provider.class))).thenReturn();

        mockMvc.perform(post("/provider")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Provider\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("New Provider"));
    }

    @Test
    @Disabled
    void testGetProviderById() throws Exception {
        // when(rep.findById(1L)).thenReturn(Optional.of(new Provider(1L, "Provider1")));

        mockMvc.perform(get("/provider/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Provider1"));
    }
}