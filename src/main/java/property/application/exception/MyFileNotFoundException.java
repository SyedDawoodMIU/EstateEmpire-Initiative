package property.application.exception;

import org.springframework.http.HttpStatus;
import property.application.controller.constants.BaseErrorCode;

public class MyFileNotFoundException extends BaseException{
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public MyFileNotFoundException(String message) {
        super(BaseErrorCode.INTERNAL_SERVER_ERROR, message, HTTP_STATUS);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }
}
