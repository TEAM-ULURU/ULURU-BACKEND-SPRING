package uluru.uluruspringbackend.data.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @Column(name = "room_id")
    private Long id;
    private String roomCode;

    @OneToMany(mappedBy = "room")
    private List<Member> members;


    public void addMember(Member member) {
        members.add(member);
        member.setRoom(this);
    }

    public void removeMember(Member member) {
        members.remove(member);
        member.setRoom(null);
    }

}
