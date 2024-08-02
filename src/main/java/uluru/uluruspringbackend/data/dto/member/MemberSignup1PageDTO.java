package uluru.uluruspringbackend.data.dto.member;

import lombok.Data;

@Data
public class MemberSignup1PageDTO {

    private String email;
    private String gender;
    private int age;
    private float height;
    private float weight;
    private float bodyFatPercentage;
}
