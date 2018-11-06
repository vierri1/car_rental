package rusoft.car_rental.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "User Id", name = "userId", required = true, value = "1")
    private Integer id;
    @ApiModelProperty(notes = "User name", name = "userName", required = true, value = "Travis")
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty(notes = "user birth year", name = "birthDay", required = true, value = "1990")
    private LocalDateTime birthDay;

    public User(String name, LocalDateTime birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }
}