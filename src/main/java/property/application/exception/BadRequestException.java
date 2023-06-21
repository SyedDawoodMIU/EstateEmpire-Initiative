package property.application.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String code, String message) {
        super(code, message, HTTP_STATUS);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }
}
