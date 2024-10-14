package com.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.controller.UserController;
import com.practice.entity.User;
import com.practice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
 class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void testUpdateUser() throws Exception {
		Long userId = 1L;
		User updatedUser = new User();
		updatedUser.setName("Updated Name");
		updatedUser.setEmail("updated.email@example.com");

		// Mocking the service call
		Mockito.when(userService.update(Mockito.eq(userId), Mockito.any(User.class)))
				.thenReturn(updatedUser);

		// Perform a PUT request to update the user
		mockMvc.perform(put("/users/" + userId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(updatedUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Updated Name"))
				.andExpect(jsonPath("$.email").value("updated.email@example.com"));
	}
}
