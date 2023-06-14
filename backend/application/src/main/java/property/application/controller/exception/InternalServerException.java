package property.application.controller.exception;

import org.springframework.http.HttpStatus;
import property.application.exception.BaseException;

public class InternalServerException extends BaseException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerException(String code, String message, Object errors) {
        super(code, message, HTTP_STATUS, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }
}
