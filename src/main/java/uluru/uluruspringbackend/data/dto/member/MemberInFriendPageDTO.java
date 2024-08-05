package uluru.uluruspringbackend.data.dto.member;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInFriendPageDTO {

    private String name;
    private int numberOfDrinks;

}
