package uluru.uluruspringbackend.data.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import uluru.uluruspringbackend.data.embed.Address;
import uluru.uluruspringbackend.data.enummer.TypeOfAlcohol;

import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    //body information
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
    private float drinkingFrequency;
    @Enumerated(EnumType.STRING)
    private TypeOfAlcohol typeOfAlcohol;
    private float averageAlcoholIntake;
    private float degreeOfIntoxication;



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

}
