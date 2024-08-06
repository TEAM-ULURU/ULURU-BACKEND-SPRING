package uluru.uluruspringbackend.data.dto.member;

import lombok.Data;
import uluru.uluruspringbackend.data.embed.Address;

@Data
public class MemberUpdateDTO {
    private String name;
    private String email;
    private String gender;

    private int age;
    private float height;
    private float weight;
    private float bodyFat;
    private String address;
    private String addressDetail;
    private String emergencyContact;

    public boolean isUpdate(MemberDTO memberDTO){
        if (this.age != memberDTO.getAge()) {
            return true;
        }
        if (this.height != memberDTO.getHeight()) {
            return true;
        }
        if (this.weight != memberDTO.getWeight()) {
            return true;
        }
        if (this.bodyFat != memberDTO.getBodyFatPercentage()) {
            return true;
        }
        if (!this.address.equals(memberDTO.getAddress().getDetailAddress())) {
            return true;
        }
        if (!this.addressDetail.equals(memberDTO.getAddress().getStreetNameAddress())) {
            return true;
        }
        if (!this.emergencyContact.equals(memberDTO.getEmergencyContact())) {
            return true;
        }
        return false;
    }

}
