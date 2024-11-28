package in.mshitlearner.expectionHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.mshitlearner.controller.CourseController;

//@ControllerAdvice(assignableTypes = {CourseController.class}) //It will applicable for single class
@ControllerAdvice //It will applicable for all Controller Class
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Log log = LogFactory.getLog(APIExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        log.error("Error occured :: ", ex);
        return new ResponseEntity<Object>("Exeception occured ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<Object>("Please change http Request method type ", HttpStatus.BAD_REQUEST);
	}

}
