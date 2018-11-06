package rusoft.car_rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rusoft.car_rental.dto.DeleteDto;
import rusoft.car_rental.dto.OrderDto;
import rusoft.car_rental.model.Car;
import rusoft.car_rental.model.User;
import rusoft.car_rental.repository.CarRepository;
import rusoft.car_rental.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CarRepository carRepository;
    private DateTimeFormatter formatter;


    private SimpleDateFormat format;

    public UserServiceImpl() {
        this.formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 3)
                .toFormatter();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    @Override
    public User add(OrderDto orderDto) {
        String userName = null;
        String userBirthYear = null;
        String carBrand = null;
        String carReleaseYear = null;
        if (orderDto != null) {
            userName = orderDto.getUserName();
            userBirthYear = orderDto.getUserBirthYear();
            carBrand = orderDto.getCarBrand();
            carReleaseYear = orderDto.getCarReleaseYear();
        }
        if (userName == null && userBirthYear == null &&
                carBrand == null && carReleaseYear == null) {
            return null;
        }
        LocalDateTime userBirthYearDate = parseYear(userBirthYear);
        LocalDateTime carReleaseYearDate = parseYear(carReleaseYear);
        if (userBirthYearDate != null && carReleaseYearDate != null) {
            if (checkPossibleAdd(carBrand, carReleaseYearDate, userName, userBirthYearDate)) {
                User newUser = new User(userName, userBirthYearDate);
                Car car = carRepository.findByBrandAndReleaseYear(carBrand, carReleaseYearDate);
                if (car.getUser() == null) {
                    newUser = userRepository.save(newUser);
                    car.setUser(newUser);
                    carRepository.save(car);
                    return newUser;
                }
            }
        }
        return null;
    }

    @Transactional
    @Override
    public boolean delete(DeleteDto deleteDto) {
        String userName = null;
        String carBrand = null;
        if (deleteDto != null) {
            userName = deleteDto.getUserName();
            carBrand = deleteDto.getCarBrand();
        }
        if (userName != null && carBrand != null) {
            if (userRepository.existsByName(userName) && carRepository.existsByBrand(carBrand)) {
                Car car = carRepository.findByBrand(carBrand);
                User user = userRepository.findByName(userName);
                if (user != null && car != null && user.equals(car.getUser())) {
                    car.setUser(null);
                    carRepository.save(car);
                    userRepository.delete(user);
                    return true;
                }
            }
        }
        return false;
    }

    private LocalDateTime parseYear(String year) {
        try {
            return LocalDateTime.parse(year, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    private boolean checkPossibleAdd(String carBrand, LocalDateTime carReleaseYearDate, String userName,
                                     LocalDateTime userBirthYearDate) {
        return carRepository.existsByBrandAndReleaseYear(carBrand, carReleaseYearDate) &&
                !userRepository.existsByNameAndBirthDay(userName, userBirthYearDate);
    }
}