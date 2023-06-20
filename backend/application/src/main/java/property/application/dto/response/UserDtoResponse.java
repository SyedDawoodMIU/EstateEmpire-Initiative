package property.application.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDtoResponse {

    private Long userId;
    private String name;
    @NotNull(message = "email cannot be null")
    private String email;
    private Boolean isDisabled = false;

}
