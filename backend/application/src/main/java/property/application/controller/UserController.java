package property.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import property.application.dto.UserDto;
import property.application.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void saveUser(@RequestBody UserDto userDto){
        userService.save(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUser(){
        return userService.findAll();
    }

}
