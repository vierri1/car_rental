package rusoft.car_rental.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @ApiModelProperty(notes = "Name of the User", name = "userName", required = true, value = "Travis")
    private String userName;
    @ApiModelProperty(notes = "User birth year", name = "userBirthYear", required = true, value = "1990")
    private String userBirthYear;
    @ApiModelProperty(notes = "Brand of the car", name = "carBrand", required = true, value = "Audi")
    private String carBrand;
    @ApiModelProperty(notes = "Car release year", name = "carReleaseYear", required = true, value = "2017")
    private String carReleaseYear;
}
