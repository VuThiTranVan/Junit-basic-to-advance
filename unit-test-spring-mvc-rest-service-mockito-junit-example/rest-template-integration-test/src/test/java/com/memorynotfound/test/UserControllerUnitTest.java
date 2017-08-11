package com.memorynotfound.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memorynotfound.config.WebConfig;
import com.memorynotfound.controller.UserController;
import com.memorynotfound.filter.CORSFilter;
import com.memorynotfound.model.User;
import com.memorynotfound.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class UserControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    
    /**
     * Config Mockito with SpringMVC
     * 
     * Bằng cách chú thích UserService với @Mock Adnotation, 
     * 		chúng ta có thể trả lại mocked dữ liệu khi chúng ta gọi một phương thức từ dịch vụ này. 
     * 
     * Sử dụng chú thích @InjectMocks, chúng ta có thể chèn các dịch vụ mocked bên trong UserController
     * Trước mỗi test, chúng ta phải khởi tạo các mocks bằng MockitoAnnotations 
     * 		# initMocks (this)
     * 		Hoặc nếu không dùng Annotation thì có thể khai báo như sau:
     * 			userService = mock(UserService.class);
     * 
     * MockMvc được khởi tạo bằng cách sử dụng phương pháp MockMvcBuilders # 
     * 		standaloneSetup (...). Build ().
     * Theo tùy chọn, chúng ta có thể thêm các bộ lọc, các interceptors hoặc vv 
     * 		bằng cách sử dụng các phương thức .addFilter () hoặc .addInterceptor ().
     * */
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CORSFilter())
                .build();
    }
    
    /** Giải thích
     * 
     * MockMvc is the main entry point for server-side Spring MVC test support. Perform a request and return a type that allows chaining further actions, such as asserting expectations, on the result.
     * @Mock creating a mock. This can also be achieved by using org.mockito.mock(..) method.
     * @InjectMocks injects mock or spy fields into tested objects automatically.
     * MockitoAnnotations.initMocks(this) initializes fields annotated with Mockito annotations.
     * MockMvcBuilders.standaloneSetup(..).build() builds a MockMvc instance by registering one or more @Controller instances and configuring Spring MVC infrastructure programmatically.
     */

    // =========================================== Get All Users ==========================================

    /**
     * Step
     * 1. Create test data which’ll be returned as a response in the rest service.
     * 2. Configure mock object to return the test data when the getAll() method of the UserService is invoked.
     * 3. Invoke an HTTP GET request to the /users URI.
     * 4. Validate if the response is correct.
     * 		Verify that the HTTP status code is 200 (OK).
     * 		Verify that the content-type of the response is application/json and its character set is UTF-8.
     * 		Verify that the collection contains 2 items.
     * 		Verify that the id attribute of the first element equals to 1.
     * 		Verify that the username attribute of the first element equals to Daenerys Targaryen.
     * 		Verify that the getAll() method of the UserService is invoked exactly once.
     * 		Verify that after the response, no more interactions are made to the UserService
     * 
     * ---
     * userService is invoked
     * */
    @Test
    @Ignore
    public void test_get_all_success() throws Exception {
        List<User> users = Arrays.asList(
                new User(1, "Daenerys Targaryen"),
                new User(2, "John Snow"));

        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("John Snow")));

        verify(userService, times(1)).getAll();
        verifyNoMoreInteractions(userService);
    }

    // =========================================== Get User By ID =========================================

    /**
     * 1. Create test data and configure mock object 
     * 		to return the data when the findById() method of the UserService is invoked.
     * 2. Invoke an HTTP GET request to the /users/1 URI.
     * 3. Validate if the response is correct.
     * 		Verify that the HTTP status code is 200 (OK).
     * 		Verify that the content-type of the response is application/json and its character set is UTF-8.
     * 		Verify that the id and username attributes are equal to the mocked test data.
     * 		Verify that the findById() method of the UserService is invoked exactly once.
     * 		Verify that after the response, no more interactions are made to the UserService
     * */
    @Test
    @Ignore
    public void test_get_by_id_success() throws Exception {
        User user = new User(1, "Daenerys Targaryen");

        when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("Daenerys Targaryen")));

        verify(userService, times(1)).findById(1);
        verifyNoMoreInteractions(userService);
    }

    
    /**
     * 1. Configure mock object to return null when the findById() method of the UserService is invoked.
     * 2. Invoke an HTTP GET request to the /users/1 URI.
     * 3. Validate if the response is correct.
     * 		Verify that the HTTP status code is 404 (Not Found).
     * 		Verify that the findById() method of the UserService is invoked exactly once.
     * 		Verify that after the response, no more interactions are made to the UserService
     * */
    @Test
    @Ignore
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(userService.findById(1)).thenReturn(null);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(1);
        verifyNoMoreInteractions(userService);
    }

    // =========================================== Create New User ========================================

    
    /**
     * 1. Configure mocked responses for the UserService exists() and create methods.
     * 2. Invoke an HTTP POST request to the /users URI. Make sure the content-type is set to application/json. Convert the User object to JSON and add it to the request.
     * 3. Validate if the response is correct.
     * 		Verify that the HTTP status code is 201 (CREATED).
     * 		Verify that the location header is set with the path to the created resource.
     * 		Verify that the exists() and create() methods of the UserService are invoked exactly once.
     * 		Verify that after the response, no more interactions are made to the UserService
     * */
    @Test
    @Ignore
    public void test_create_user_success() throws Exception {
        User user = new User("Arya Stark");

        when(userService.exists(user)).thenReturn(false);
        doNothing().when(userService).create(user);

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/users/0")));

        verify(userService, times(1)).exists(user);
        verify(userService, times(1)).create(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_create_user_fail_404_not_found() throws Exception {
        User user = new User("username exists");

        when(userService.exists(user)).thenReturn(true);
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isConflict());

        verify(userService, times(1)).exists(user);
        verifyNoMoreInteractions(userService);
    }

    // =========================================== Update Existing User ===================================

    /**
     * 1. Configure mocked responses for the UserService findById() and update methods.
     * 2. Invoke an HTTP PUT request to the /users/1 URI. Make sure the content-type is set to application/json. Convert the User object to JSON and add it to the request.
     * 3. Validate if the response is correct.
     * 		Verify that the HTTP status code is 200 (OK).
     * 		Verify that the findById() and update() methods of the UserService are invoked exactly once.
     * 		Verify that after the response, no more interactions are made to the UserService
     * */
    @Test
    @Ignore
    public void test_update_user_success() throws Exception {
        User user = new User(1, "Arya Stark");

        when(userService.findById(user.getId())).thenReturn(user);
        doNothing().when(userService).update(user);

        mockMvc.perform(
                put("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());

        verify(userService, times(1)).findById(user.getId());
        verify(userService, times(1)).update(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    @Ignore
    public void test_update_user_fail_404_not_found() throws Exception {
        User user = new User(999, "user not found");

        when(userService.findById(user.getId())).thenReturn(null);

        mockMvc.perform(
                put("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(user.getId());
        verifyNoMoreInteractions(userService);
    }

    // =========================================== Delete User ============================================

    /**
     * 1. Configure mocked responses for the UserService findById() and delete methods.
     * 2. Invoke an HTTP DELETE request to the /users/1 URI. Make sure the content-type is set to application/json. Convert the User object to JSON and add it to the request.
     * 3. Validate if the response is correct.
     * 		Verify that the HTTP status code is 200 (OK).
     * 		Verify that the findById() and delete() methods of the UserService are invoked exactly once.
     * 		Verify that after the response, no more interactions are made to the UserService
     * */
    @Test
    @Ignore
    public void test_delete_user_success() throws Exception {
        User user = new User(1, "Arya Stark");

        when(userService.findById(user.getId())).thenReturn(user);
        doNothing().when(userService).delete(user.getId());

        mockMvc.perform(
                delete("/users/{id}", user.getId()))
                .andExpect(status().isOk());

        verify(userService, times(1)).findById(user.getId());
        verify(userService, times(1)).delete(user.getId());
        verifyNoMoreInteractions(userService);
    }

    @Test
    @Ignore
    public void test_delete_user_fail_404_not_found() throws Exception {
        User user = new User(999, "user not found");

        when(userService.findById(user.getId())).thenReturn(null);

        mockMvc.perform(
                delete("/users/{id}", user.getId()))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(user.getId());
        verifyNoMoreInteractions(userService);
    }

    // =========================================== CORS Headers ===========================================
    /**
     * 1. Invoke an HTTP GET request to the /users URI.
     * 2. Validate if the correct headers are set.
     * 		Access-Control-Allow-Origin should be equal to *.
     * 		Access-Control-Allow-Methods should be equal to POST, GET, PUT, OPTIONS, DELETE.
     * 		Access-Control-Allow-Headers should be equal to *.
     * 		Access-Control-Max-Age should be equal to 3600.
     * */
    @Test
    @Ignore
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
