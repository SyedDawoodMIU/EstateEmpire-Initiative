package property.application.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.ErrorResponse;
import property.application.exception.BadRequestException;
import property.application.exception.BaseException;
import property.application.exception.InternalServerException;

@Controller
@ControllerAdvice
public class BaseController extends ResponseEntityExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e, HttpServletRequest request) {
        LOGGER.error("Exception caught on base controller ", e);
        BaseException baseException;
        if (e instanceof BaseException) baseException = ((BaseException) e);
        else
            baseException = new BadRequestException(BaseErrorCode.UNAUTHORIZED_USER, e.getMessage());
        baseException = addPathToErrorResponse(baseException, request);
        return new ResponseEntity<>(baseException.getErrorResponse(), baseException.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        LOGGER.error("Exception caught on base controller ", e);
        BaseException baseException;
        if (e instanceof BaseException) baseException = ((BaseException) e);
        else
            baseException = new InternalServerException(BaseErrorCode.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again later.",e.getMessage());
        baseException = addPathToErrorResponse(baseException, request);
        return new ResponseEntity<>(baseException.getErrorResponse(), baseException.getHttpStatus());
    }

    private BaseException addPathToErrorResponse(BaseException baseException, HttpServletRequest request) {
        return baseException.setPath(request.getRequestURI());
    }

}
