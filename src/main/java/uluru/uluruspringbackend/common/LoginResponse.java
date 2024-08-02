package uluru.uluruspringbackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class LoginResponse {

    private boolean isSuccess;
    HttpStatus status;
    private String msg;
    private Object object;
    private Object isRedirectHomePage;



}
