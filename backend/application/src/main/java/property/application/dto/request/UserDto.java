package property.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "Password cannot be null")
    private String password;
    private String name;
    @NotNull(message = "Role cannot be null")
    private String role;
    @NotNull(message = "email cannot be null")
    private String email;
    private Boolean isDisabled = false;

}
