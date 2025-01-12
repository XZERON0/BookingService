package project.backend.controllers;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import project.backend.models.User;
import project.backend.repository.UserReposity;
import project.backend.service.UserService;



@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserReposity userRepository;

    @Test
    @Disabled
    void testGetCurrentUser() throws Exception {
        mockMvc.perform(get("/user/current"))
            .andExpect(status().isOk())
            .andExpect(content().string("Нет авторизованного пользователя"));
    }

    @Test
    @Disabled
    void testRegisterUser() throws Exception {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string("Пользователь успешно зарегистрирован"));

        verify(userService).registerUser(Mockito.any(User.class));
    }

    @Test
    @Disabled
    void testRegisterUserEmailExists() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setName("Cherry");
        when(userRepository.findByEmail("test@example.com"))
            .thenReturn(Optional.of(user));

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
            .andExpect(status().is(403))
            .andExpect(content().string("Пользователь с таким email уже существует"));
    }

    @Test
    @Disabled
    void testLoginUser() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setName("Cherry");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(userService.checkPassword("password", "encodedPassword")).thenReturn(true);

        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string("Успешный вход"));
    }

    @Test
    @Disabled
    void testGetUserById() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setName("Cherry");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/user/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @Disabled
    void testGetAllUsers() throws Exception {
        when(userService.getAllUser(Mockito.any(Pageable.class)))
            .thenReturn(Page.empty());

        mockMvc.perform(get("/user/users")
                .param("page", "0")
                .param("size", "5")
                .param("sortBy", "id")
                .param("ascending", "true"))
            .andExpect(status().isOk());
    }

    @Test
    @Disabled
void testUpdateUser() throws Exception {
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setName("Cherry");
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    mockMvc.perform(put("/user/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"new@example.com\", \"password\":\"newpassword\"}"))
        .andExpect(status().isOk())
        .andExpect(content().string("Пользователь успешно обновлен"));

    verify(userRepository).save(Mockito.any(User.class));
}

@Test
@Disabled
void testUpdateUserNotFound() throws Exception {
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    mockMvc.perform(put("/user/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"new@example.com\", \"password\":\"newpassword\"}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Пользователь не найден"));
}

@Test
@Disabled
void testDeleteUser() throws Exception {
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setName("Cherry");
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    mockMvc.perform(delete("/user/delete/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Пользователь успешно удален"));

    verify(userRepository).delete(Mockito.any(User.class));
}

@Test
@Disabled
void testDeleteUserNotFound() throws Exception {
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    mockMvc.perform(delete("/user/delete/1"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Пользователь не найден"));
}
}