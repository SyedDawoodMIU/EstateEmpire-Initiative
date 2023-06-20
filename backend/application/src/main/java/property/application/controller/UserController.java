package property.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import property.application.dto.request.UserDto;
import property.application.dto.response.LoginResponse;
import property.application.dto.response.UserDtoResponse;
import property.application.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public LoginResponse saveUser(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    public UserDtoResponse update(@RequestBody @Valid UserDto userDto, @PathVariable("id")Long id){
        return userService.update(userDto,id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDtoResponse> getAllUser() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public UserDtoResponse getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

}
