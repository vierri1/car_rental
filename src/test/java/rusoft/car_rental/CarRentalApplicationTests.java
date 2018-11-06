package rusoft.car_rental;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import rusoft.car_rental.repository.CarRepository;
import rusoft.car_rental.repository.UserRepository;
import rusoft.car_rental.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rusoft.car_rental.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CarRentalApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CarRepository carRepository;
    @Autowired
    private UserService userService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_REQUEST_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(USER_RESPONSE_JSON));
    }

    @Test
    public void testUserExistsAdd() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_EXIST_REQUEST_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCarHasOwner() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_CAR_HAS_OWNER))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testInvalidDate() throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_INVALID_DATE))
                .andExpect(status().isNotFound());
    }
}
