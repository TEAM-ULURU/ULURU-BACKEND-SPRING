package uluru.uluruspringbackend.data.dto.friend;

import lombok.Data;
import uluru.uluruspringbackend.data.domain.MemberFriend;
import uluru.uluruspringbackend.data.dto.member.MemberInFriendPageDTO;

import java.util.List;

@Data
public class FriendDTO {

    private MemberInFriendPageDTO friend;
    private int numberOfDrinkingTogether;

    public static FriendDTO MemberFriendToFriendDTO(MemberFriend mf){

        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setFriend(new MemberInFriendPageDTO(mf.getFriend().getName(), mf.getFriend().getNumberOfDrinks()));
        friendDTO.setNumberOfDrinkingTogether(mf.getNumberOfDrinkingTogether());

        return friendDTO;
    }



}
