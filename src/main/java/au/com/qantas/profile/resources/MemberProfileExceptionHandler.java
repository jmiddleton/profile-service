package au.com.qantas.profile.resources;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import au.com.qantas.profile.exceptions.ProfileException;
import au.com.qantas.profile.model.ApiError;

@ControllerAdvice
class MemberProfileExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(ProfileException.class)
  public ResponseEntity<ApiError> handle(final ProfileException e,
      final NativeWebRequest nativeWebRequest) {
    final HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
    final ApiError error = createApiError(e, request);

    return ResponseEntity.status(BAD_REQUEST).body(error);
  }


  private ApiError createApiError(ProfileException e, HttpServletRequest request) {
    String errorCode = e.getMessage();
    String errorMessage = messageSource.getMessage(errorCode, null, Locale.US);
    return new ApiError().errorCode(errorCode).errorMessage(errorMessage);
  }

}
