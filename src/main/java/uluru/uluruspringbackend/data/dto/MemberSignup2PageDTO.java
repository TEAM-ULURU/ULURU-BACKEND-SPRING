package uluru.uluruspringbackend.data.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import uluru.uluruspringbackend.data.enummer.TypeOfAlcohol;


@Data
public class MemberSignup2PageDTO {

    private String email;
    //drink information
    private String drinkingFrequencyReferenceValue;
    private float drinkingFrequency;
    private TypeOfAlcohol typeOfAlcohol;
    private float averageAlcoholIntake;
    private float degreeOfIntoxication;
}

