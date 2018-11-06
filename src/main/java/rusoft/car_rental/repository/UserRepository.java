package rusoft.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rusoft.car_rental.model.User;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByNameAndBirthDay(String name, LocalDateTime birthDay);

    boolean existsByName(String name);

    User findByName(String userName);
}
