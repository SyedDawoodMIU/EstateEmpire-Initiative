package property.application.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ErrorResponse {

    private String code;
    private String message;
    private String timestamp;
    private String status;
    private Integer statusCode;
    private Object errors;
    @Setter
    private String path;

}
