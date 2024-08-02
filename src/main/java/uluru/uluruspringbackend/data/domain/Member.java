package uluru.uluruspringbackend.data.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import uluru.uluruspringbackend.data.dto.member.MemberDTO;
import uluru.uluruspringbackend.data.embed.Address;
import uluru.uluruspringbackend.data.enummer.TypeOfAlcohol;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor // This is required for the Builder
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String email;

    //body information
    private String gender;
    @NotNull
    private String name;
    @NotNull
    private int age;
    @NotNull
    private float height;
    @NotNull
    private float weight;
    private float bodyFatPercentage;


    //drink information
    private String drinkingFrequencyReferenceValue;
    private float drinkingFrequency;
    @Enumerated(EnumType.STRING)
    private TypeOfAlcohol typeOfAlcohol;


    private String averageAlcoholIntake;
    private float numberOfBottles;

    private String degreeOfIntoxication;
    private float percentPerReferenceValue;

    //home
    private float currentBloodAlcoholLevel;
    private float currentLevelOfIntoxication;

    //etc
    @Embedded
    private Address address;
    private String emergencyContact;


    private boolean isOauth;

    @OneToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberFriend> memberFriend;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    public Member(){

    }

    public MemberDTO toDto() {
        MemberDTO dto = new MemberDTO();
        dto.setId(this.id);
        dto.setEmail(this.email);
        dto.setGender(this.gender);
        dto.setName(this.name);
        dto.setAge(this.age);
        dto.setHeight(this.height);
        dto.setWeight(this.weight);
        dto.setBodyFatPercentage(this.bodyFatPercentage);
        dto.setDrinkingFrequencyReferenceValue(this.drinkingFrequencyReferenceValue);
        dto.setDrinkingFrequency(this.drinkingFrequency);
        dto.setTypeOfAlcohol(this.typeOfAlcohol);
        dto.setAverageAlcoholIntake(this.averageAlcoholIntake);
        dto.setNumberOfBottles(this.numberOfBottles);
        dto.setDegreeOfIntoxication(this.degreeOfIntoxication);
        dto.setPercentPerReferenceValue(this.percentPerReferenceValue);
        dto.setCurrentBloodAlcoholLevel(this.currentBloodAlcoholLevel);
        dto.setCurrentLevelOfIntoxication(this.currentLevelOfIntoxication);
        dto.setAddress(this.address);
        dto.setEmergencyContact(this.emergencyContact);
        dto.setOauth(this.isOauth);
        dto.setCalendar(this.calendar);
        //dto.setMemberFriends(this.memberFriend.stream().collect(Collectors.toList()));
        dto.setRoom(this.room);
        return dto;
    }

}
