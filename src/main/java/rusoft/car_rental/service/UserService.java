package rusoft.car_rental.service;

import rusoft.car_rental.dto.DeleteDto;
import rusoft.car_rental.dto.OrderDto;
import rusoft.car_rental.model.User;

public interface UserService {

    User add(OrderDto orderDto);

    boolean delete(DeleteDto deleteDto);
}