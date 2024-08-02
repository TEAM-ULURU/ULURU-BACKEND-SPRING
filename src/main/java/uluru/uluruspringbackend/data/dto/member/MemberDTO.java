package uluru.uluruspringbackend.data.dto.member;

import lombok.Data;
import uluru.uluruspringbackend.data.domain.Calendar;
import uluru.uluruspringbackend.data.domain.Member;
import uluru.uluruspringbackend.data.domain.Room;
import uluru.uluruspringbackend.data.embed.Address;
import uluru.uluruspringbackend.data.enummer.TypeOfAlcohol;

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

    private String drinkingFrequencyReferenceValue;
    private float drinkingFrequency;
    private TypeOfAlcohol typeOfAlcohol;
    private String averageAlcoholIntake;
    private float numberOfBottles;

    private String degreeOfIntoxication;
    private float percentPerReferenceValue;

    //home
    private float currentBloodAlcoholLevel;
    private float currentLevelOfIntoxication;

    private Address address;
    private String emergencyContact;

    private boolean isOauth;
    private Calendar calendar;
    //private List<MemberFriend> memberFriends;
    private Room room;

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .email(this.email)
                .gender(this.gender)
                .name(this.name)
                .age(this.age)
                .height(this.height)
                .weight(this.weight)
                .bodyFatPercentage(this.bodyFatPercentage)
                .drinkingFrequencyReferenceValue(this.drinkingFrequencyReferenceValue)
                .drinkingFrequency(this.drinkingFrequency)
                .typeOfAlcohol(this.typeOfAlcohol)
                .averageAlcoholIntake(this.averageAlcoholIntake)
                .numberOfBottles(this.numberOfBottles)
                .degreeOfIntoxication(this.degreeOfIntoxication)
                .percentPerReferenceValue(this.percentPerReferenceValue)
                .address(this.address)
                .emergencyContact(this.emergencyContact)
                .currentBloodAlcoholLevel(this.currentBloodAlcoholLevel)
                .currentLevelOfIntoxication(this.currentLevelOfIntoxication)
                .isOauth(this.isOauth)
                .calendar(this.calendar)
                //.memberFriend(this.memberFriends)
                .room(this.room)
                .build();
    }

}
