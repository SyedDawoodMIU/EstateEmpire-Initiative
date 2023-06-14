package property.application.exception;

import org.springframework.http.HttpStatus;
import property.application.dto.ErrorResponse;

import java.util.Date;

public abstract class BaseException extends RuntimeException {

    private ErrorResponse errorResponse;

    public BaseException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public BaseException(String code, String message, String timestamp, String status, Integer statusCode, Object errors) {
        this(ErrorResponse.builder()
                .code(code)
                .message(message)
                .timestamp(timestamp)
                .status(status)
                .statusCode(statusCode)
                .errors(errors)
                .build());
    }

    public BaseException(String code, String message, String timestamp, HttpStatus status, Object errors) {
        this(code, message, timestamp, status.getReasonPhrase(), status.value(), errors);
    }

    public BaseException(String code, String message, HttpStatus status, Object errors) {
        this(code, message, new Date().toInstant().toString(), status.getReasonPhrase(), status.value(), errors);
    }

    public BaseException(String code, String message, HttpStatus status) {
        this(code, message, new Date().toInstant().toString(), status.getReasonPhrase(), status.value(), null);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public abstract HttpStatus getHttpStatus();

    public BaseException setPath(String path) {
        this.getErrorResponse().setPath(path);
        return this;
    }
}
