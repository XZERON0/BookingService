package project.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@WebMvcTest(ServiceCategoryController.class)
class ServiceCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetServiceCategoryById() throws Exception {

        mockMvc.perform(get("/service/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Test Category"));
    }

    @Test
    void testGetAllServiceCategories() throws Exception {

        mockMvc.perform(get("/service"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Category1"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("Category2"));
    }

    @Test
    void testPostServiceCategory() throws Exception {

        mockMvc.perform(post("/service")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Category\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string("Success"));
    }
}