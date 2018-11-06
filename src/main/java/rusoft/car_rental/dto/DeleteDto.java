package rusoft.car_rental.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDto {
    @ApiModelProperty(notes = "Name of the User", name = "userName", required = true, value = "Travis")
    private String userName;
    @ApiModelProperty(notes = "Brand of the car", name = "carBrand", required = true, value = "Audi")
    private String carBrand;
}
