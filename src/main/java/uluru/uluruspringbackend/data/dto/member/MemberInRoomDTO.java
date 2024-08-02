package uluru.uluruspringbackend.data.dto.member;

import lombok.Data;
import uluru.uluruspringbackend.data.domain.Member;

@Data
public class MemberInRoomDTO {

    private String name;
    private float currentBloodAlcoholLevel;
    private float currentLevelOfIntoxication;

    public MemberInRoomDTO(Member member) {

        this.name = member.getName();
        this.currentBloodAlcoholLevel = member.getCurrentBloodAlcoholLevel();
        this.currentLevelOfIntoxication = member.getCurrentLevelOfIntoxication();

    }

}
