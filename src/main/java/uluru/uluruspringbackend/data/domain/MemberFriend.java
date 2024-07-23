package uluru.uluruspringbackend.data.domain;


import jakarta.persistence.*;

@Entity
public class MemberFriend {

    @Id
    @GeneratedValue
    @Column(name = "member_friend_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name ="friend_id")
    private Member friend;

}
