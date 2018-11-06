package rusoft.car_rental;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rusoft.car_rental.dto.DeleteDto;
import rusoft.car_rental.dto.OrderDto;
import rusoft.car_rental.model.Car;
import rusoft.car_rental.model.User;
import rusoft.car_rental.repository.CarRepository;
import rusoft.car_rental.repository.UserRepository;
import rusoft.car_rental.service.UserServiceImpl;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentalApplicationUnitTests {

    private static User NEW_USER;
    private static User SAVED_USER;
    private static Car FREE_CAR;
    private static Car BUSY_CAR;
    private static String USER_NAME;
    private static String CAR_BRAND;
    private static LocalDateTime CAR_RELEASE_YEAR;
    private static LocalDateTime USER_BIRTH_DAY;
    private static OrderDto ORDER_DTO;
    private static DeleteDto DELETE_DTO;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        NEW_USER = new User(null, "Travis", LocalDateTime.parse("2000-01-01T03:00:00"));
        SAVED_USER = new User(1, "Travis", LocalDateTime.parse("2000-01-01T03:00:00"));
        FREE_CAR = new Car(3, "Audi", LocalDateTime.parse("2017-01-01T03:00:00"), null);
        BUSY_CAR = new Car(3, "Audi", LocalDateTime.parse("2017-01-01T03:00:00"), SAVED_USER);
        USER_NAME = "Travis";
        CAR_BRAND = "Audi";
        CAR_RELEASE_YEAR = LocalDateTime.parse("2017-01-01T03:00:00");
        USER_BIRTH_DAY = LocalDateTime.parse("2000-01-01T03:00:00");
        ORDER_DTO = new OrderDto(USER_NAME, "2000", CAR_BRAND, "2017");
        DELETE_DTO = new DeleteDto(USER_NAME, CAR_BRAND);
    }

    @Test
    public void testAdd() {
        Mockito.when(userRepository.existsByNameAndBirthDay(USER_NAME, USER_BIRTH_DAY)).thenReturn(false);
        Mockito.when(carRepository.existsByBrandAndReleaseYear(CAR_BRAND, CAR_RELEASE_YEAR)).thenReturn(true);
        Mockito.when(userRepository.save(NEW_USER)).thenReturn(SAVED_USER);
        Mockito.when(carRepository.findByBrandAndReleaseYear(CAR_BRAND, CAR_RELEASE_YEAR)).thenReturn(FREE_CAR);
        userService.add(ORDER_DTO);
        Mockito.verify(userRepository, Mockito.times(1)).save(NEW_USER);
        Mockito.verify(carRepository, Mockito.times(1)).save(BUSY_CAR);
    }

    @Test
    public void testUserExistAdd() {
        Mockito.when(userRepository.existsByNameAndBirthDay(USER_NAME, USER_BIRTH_DAY)).thenReturn(true);
        Mockito.when(carRepository.existsByBrandAndReleaseYear(CAR_BRAND, CAR_RELEASE_YEAR)).thenReturn(true);
        userService.add(ORDER_DTO);
        Mockito.verify(userRepository, Mockito.times(0)).save(NEW_USER);
        Mockito.verify(carRepository, Mockito.times(0)).save(BUSY_CAR);
    }

    @Test
    public void testBusyCarAdd() {
        Mockito.when(userRepository.existsByNameAndBirthDay(USER_NAME, USER_BIRTH_DAY)).thenReturn(false);
        Mockito.when(carRepository.existsByBrandAndReleaseYear(CAR_BRAND, CAR_RELEASE_YEAR)).thenReturn(true);
        Mockito.when(carRepository.findByBrandAndReleaseYear(CAR_BRAND, CAR_RELEASE_YEAR)).thenReturn(BUSY_CAR);
        userService.add(ORDER_DTO);
        Mockito.verify(userRepository, Mockito.times(0)).save(NEW_USER);
        Mockito.verify(carRepository, Mockito.times(0)).save(BUSY_CAR);
    }

    @Test
    public void testDelete() {
        Mockito.when(userRepository.existsByName(USER_NAME)).thenReturn(true);
        Mockito.when(carRepository.existsByBrand(CAR_BRAND)).thenReturn(true);
        Mockito.when(userRepository.findByName(USER_NAME)).thenReturn(SAVED_USER);
        Mockito.when(carRepository.findByBrand(CAR_BRAND)).thenReturn(BUSY_CAR);
        userService.delete(DELETE_DTO);
        Mockito.verify(userRepository, Mockito.times(1)).delete(SAVED_USER);
        Mockito.verify(carRepository, Mockito.times(1)).save(FREE_CAR);
    }

    @Test
    public void testCarNotBusyDelete() {
        Mockito.when(userRepository.existsByName(USER_NAME)).thenReturn(true);
        Mockito.when(carRepository.existsByBrand(CAR_BRAND)).thenReturn(true);
        Mockito.when(userRepository.findByName(USER_NAME)).thenReturn(SAVED_USER);
        Mockito.when(carRepository.findByBrand(CAR_BRAND)).thenReturn(FREE_CAR);
        userService.delete(DELETE_DTO);
        Mockito.verify(userRepository, Mockito.times(0)).delete(SAVED_USER);
        Mockito.verify(carRepository, Mockito.times(0)).save(FREE_CAR);
    }

    @Test
    public void testCarNotFindDelete() {
        Mockito.when(userRepository.existsByName(USER_NAME)).thenReturn(true);
        Mockito.when(carRepository.existsByBrand(CAR_BRAND)).thenReturn(false);
        userService.delete(DELETE_DTO);
        Mockito.verify(userRepository, Mockito.times(0)).delete(SAVED_USER);
        Mockito.verify(carRepository, Mockito.times(0)).save(FREE_CAR);
    }

    @Test
    public void testUserNotFindDelete() {
        Mockito.when(userRepository.existsByName(USER_NAME)).thenReturn(false);
        Mockito.when(carRepository.existsByBrand(CAR_BRAND)).thenReturn(true);
        userService.delete(DELETE_DTO);
        Mockito.verify(userRepository, Mockito.times(0)).delete(SAVED_USER);
        Mockito.verify(carRepository, Mockito.times(0)).save(FREE_CAR);
    }
}