package uluru.uluruspringbackend.data.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Room {

    @Id
    @Column(name = "room_id")
    private Long id;
    private String roomCode;

    @OneToMany(mappedBy = "room")
    private List<Member> members;

}
