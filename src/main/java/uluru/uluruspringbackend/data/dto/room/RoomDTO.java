package uluru.uluruspringbackend.data.dto.room;


import lombok.Data;
import uluru.uluruspringbackend.data.dto.member.MemberInRoomDTO;

import java.util.List;

@Data
public class RoomDTO {

    private String roomCode;
    private List<MemberInRoomDTO> membersInRoom;

}
