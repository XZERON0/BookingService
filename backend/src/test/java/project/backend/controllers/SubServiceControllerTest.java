package project.backend.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import project.backend.repository.SubServiceCategoryRepository;


@Disabled
@WebMvcTest(SubServiceCategoryController.class)
class SubServiceCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubServiceCategoryRepository rep;

    @Test
    @Disabled
    void testAllSubServiceCategories() throws Exception {

        mockMvc.perform(get("/service/sub"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("SubCategory1"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("SubCategory2"));
    }

    @Test
    @Disabled
    void testPostSubServiceCategory() throws Exception {

        mockMvc.perform(post("/service/sub")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New SubCategory\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string("Success"));
    }

    @Test
    @Disabled
    void testGetSubServiceCategoryById() throws Exception {

        mockMvc.perform(get("/service/sub/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("SubCategory1"));
    }
}