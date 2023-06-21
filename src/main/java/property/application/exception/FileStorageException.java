package property.application.exception;

import org.springframework.http.HttpStatus;
import property.application.controller.constants.BaseErrorCode;

public class FileStorageException extends BaseException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public FileStorageException(String message) {
        super(BaseErrorCode.INTERNAL_SERVER_ERROR, message, HTTP_STATUS);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }
}
