package rusoft.car_rental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rusoft.car_rental.dto.DeleteDto;
import rusoft.car_rental.dto.OrderDto;
import rusoft.car_rental.exception.RecordNotFoundException;
import rusoft.car_rental.model.User;
import rusoft.car_rental.service.UserService;

@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "REST APIs car rental")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "book a car", response = User.class, tags = "bookCar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the car is booked"),
            @ApiResponse(code = 404, message = "error while booking")})
    public User add(@RequestBody OrderDto orderDto) {
        User user = userService.add(orderDto);
        if (user == null) {
            throw new RecordNotFoundException("add user exception");
        } else {
            return user;
        }
    }

    @DeleteMapping
    @ApiOperation(value = "delete user from book", tags = "bookCar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "user successful deleted"),
            @ApiResponse(code = 404, message = "error while deleting")})
    public void delete(@RequestBody DeleteDto deleteDtoDto) {
        boolean deleted = userService.delete(deleteDtoDto);
        if (!deleted) {
            throw new RecordNotFoundException("delete user exception");
        }
    }
}