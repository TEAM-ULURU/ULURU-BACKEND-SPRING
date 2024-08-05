package uluru.uluruspringbackend.data.dto.member;

import lombok.Data;
import uluru.uluruspringbackend.data.embed.Address;

import java.util.Objects;

@Data
public class MemberUpdateDTO {
    private int age;
    private float height;
    private float weight;
    private float bodyFatPercentage;
    private Address address;
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
        if (this.bodyFatPercentage != memberDTO.getBodyFatPercentage()) {
            return true;
        }
        if (!this.address.getDetailAddress().equals(memberDTO.getAddress().getDetailAddress())) {
            return true;
        }
        if (!this.address.getStreetNameAddress().equals(memberDTO.getAddress().getStreetNameAddress())) {
            return true;
        }
        if (!this.emergencyContact.equals(memberDTO.getEmergencyContact())) {
            return true;
        }
        return false;
    }

}
