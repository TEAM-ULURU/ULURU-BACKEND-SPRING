package uluru.uluruspringbackend.data.dto.member;

import lombok.Data;
import uluru.uluruspringbackend.data.enummer.TypeOfAlcohol;


@Data
public class MemberSignup2PageDTO {

    private String email;
    //drink information
    private String drinkingFrequencyReferenceValue;
    private float drinkingFrequency;
    private TypeOfAlcohol typeOfAlcohol;

    private String averageAlcoholIntake;
    private float numberOfBottles;

    private String degreeOfIntoxication;
    private float percentPerReferenceValue;
}

