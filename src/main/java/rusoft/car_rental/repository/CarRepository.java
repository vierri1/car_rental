package rusoft.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rusoft.car_rental.model.Car;

import java.time.LocalDateTime;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsByBrandAndReleaseYear(String brand, LocalDateTime releaseYear);

    boolean existsByBrand(String brand);

    Car findByBrandAndReleaseYear(String brand, LocalDateTime releaseYear);

    Car findByBrand(String brand);
}
