package uluru.uluruspringbackend.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class FriendPageInfoResponse {
    private boolean isSuccess;
    HttpStatus status;
    private String msg;
    private Object friendList;
    private Object mostDrinkPerWeekFriend;
    private Object mostDrinkTogetherFriend;

    public FriendPageInfoResponse(boolean isSuccess, HttpStatus status, String msg) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.msg = msg;
    }
}
