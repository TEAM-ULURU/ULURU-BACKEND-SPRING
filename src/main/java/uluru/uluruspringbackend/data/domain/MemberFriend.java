package uluru.uluruspringbackend.data.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MemberFriend {

    @Id
    @GeneratedValue
    @Column(name = "member_friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="friend_id")
    private Member friend;

    private int numberOfDrinkingTogether = 0;


    public MemberFriend(Member member, Member friend, int numberOfDrinkingTogether) {
        this.member = member;
        this.friend = friend;
        this.numberOfDrinkingTogether = numberOfDrinkingTogether;
    }
}
