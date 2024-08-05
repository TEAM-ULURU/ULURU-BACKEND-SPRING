package uluru.uluruspringbackend.data.dto.member;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMyPageDTO {

    private String name;
    private String email;
    private String basicInfo;
    private String address;
    private String phoneNumber;

    public static MemberMyPageDTO toMemberMyPageDTO(MemberDTO memberDTO){
        MemberMyPageDTO memberMyPageDTO = new MemberMyPageDTO();
        memberMyPageDTO.setName(memberDTO.getName());
        memberMyPageDTO.setEmail(memberDTO.getEmail());
        memberMyPageDTO.setAddress(memberDTO.getAddress().getStreetNameAddress()+",\n"+memberDTO.getAddress().getDetailAddress());
        memberMyPageDTO.setPhoneNumber(memberDTO.getEmergencyContact());

        String basicInfo = memberDTO.getGender() + " / "+memberDTO.getHeight() + " / "+memberDTO.getWeight() +" / "+memberDTO.getBodyFatPercentage() +"%";

        memberMyPageDTO.setBasicInfo(basicInfo);

        return memberMyPageDTO;

    }
}
