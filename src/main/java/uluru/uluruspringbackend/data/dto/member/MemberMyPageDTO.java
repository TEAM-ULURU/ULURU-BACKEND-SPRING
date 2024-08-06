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
    private float height;
    private float weight;
    private int age;
    private float bodyFat;
    private String address;
    private String addressDetail;
    private String gender;
    private String emergencyContact;

    public static MemberMyPageDTO toMemberMyPageDTO(MemberDTO memberDTO){
        MemberMyPageDTO memberMyPageDTO = new MemberMyPageDTO();
        memberMyPageDTO.setGender(memberDTO.getGender());
        memberMyPageDTO.setName(memberDTO.getName());
        memberMyPageDTO.setEmail(memberDTO.getEmail());
        memberMyPageDTO.setHeight(memberDTO.getHeight());
        memberMyPageDTO.setWeight(memberDTO.getWeight());
        memberMyPageDTO.setAge(memberDTO.getAge());
        memberMyPageDTO.setBodyFat(memberDTO.getBodyFatPercentage());
        if(memberDTO.getAddress() != null) {
            memberMyPageDTO.setAddress(memberDTO.getAddress().getStreetNameAddress());
            memberMyPageDTO.setAddressDetail(memberDTO.getAddress().getDetailAddress());
        }
        memberMyPageDTO.setEmergencyContact(memberDTO.getEmergencyContact());

        return memberMyPageDTO;

    }
}
