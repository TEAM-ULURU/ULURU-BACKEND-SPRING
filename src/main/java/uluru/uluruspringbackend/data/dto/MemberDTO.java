package uluru.uluruspringbackend.dto;

import lombok.Data;
import uluru.uluruspringbackend.data.domain.Calendar;
import uluru.uluruspringbackend.data.domain.MemberFriend;
import uluru.uluruspringbackend.data.domain.Room;
import uluru.uluruspringbackend.data.embed.Address;
import uluru.uluruspringbackend.data.enummer.TypeOfAlcohol;

import java.util.List;

@Data
public class MemberDTO {

    private Long id;
    private String email;
    private String gender;
    private String name;
    private int age;
    private float height;
    private float weight;
    private float bodyFatPercentage;
    private float drinkingFrequencyReferenceValue;
    private float drinkingFrequency;
    private TypeOfAlcohol typeOfAlcohol;
    private float averageAlcoholIntake;
    private float degreeOfIntoxication;
    private Address address;
    private String emergencyContact;
    private boolean isOauth;
    private Calendar calendar;
    private List<MemberFriend> memberFriends;
    private Room room;

}
