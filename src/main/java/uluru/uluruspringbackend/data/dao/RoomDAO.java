package uluru.uluruspringbackend.data.dao;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uluru.uluruspringbackend.common.CommonResponse;
import uluru.uluruspringbackend.data.domain.Member;
import uluru.uluruspringbackend.data.dto.member.MemberInRoomDTO;
import uluru.uluruspringbackend.data.dto.room.RoomDTO;
import uluru.uluruspringbackend.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomDAO {

    private final MemberRepository memberRepository;

    public RoomDTO getRoom(Long memberId){

        log.info("[RoomDAO] [getRoom] memberId = {} ", memberId);

        Member member = memberRepository.findFirstById(memberId);

        if(member != null) {

            RoomDTO roomDTO = new RoomDTO();

            if(member.getRoom() == null) {
                log.info("[RoomDAO] [getRoom] No Room");
                return null;
            }

            roomDTO.setMembersInRoom(member.getRoom().getMembers()
                    .stream()
                    .map(m -> new MemberInRoomDTO(m))
                    .toList()
            );
            roomDTO.setRoomCode(member.getRoom().getRoomCode());

            log.info("[RoomDAO] [getRoom] success");

            return roomDTO;

        }
        else{
            log.info("[RoomDAO] [getRoom] member not found");
            return null;
        }

    }


}
