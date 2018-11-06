package rusoft.car_rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private LocalDateTime releaseYear;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Car(String brand, LocalDateTime releaseYear) {
        this.brand = brand;
        this.releaseYear = releaseYear;
    }
}