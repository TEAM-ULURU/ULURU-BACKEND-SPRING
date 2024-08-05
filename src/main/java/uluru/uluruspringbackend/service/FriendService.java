package uluru.uluruspringbackend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uluru.uluruspringbackend.common.response.CommonResponse;
import uluru.uluruspringbackend.common.response.FriendPageInfoResponse;
import uluru.uluruspringbackend.data.domain.Member;
import uluru.uluruspringbackend.data.domain.MemberFriend;
import uluru.uluruspringbackend.data.dto.friend.FriendDTO;
import uluru.uluruspringbackend.repository.FriendRepository;
import uluru.uluruspringbackend.repository.MemberRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FriendPageInfoResponse addFriend(String friendEmail, Long myId){


        //member 찾기. 1차 캐시 저장
        Optional<Member> friend = memberRepository.findFriendEntityGraphByEmail(friendEmail);
        Optional<Member> me = memberRepository.findById(myId);

        //Validation
        if(friend.isEmpty()){
            return new FriendPageInfoResponse(false, HttpStatus.BAD_REQUEST, "유저 정보 없음");
        }
        if(me.isEmpty()){
            return new FriendPageInfoResponse(false, HttpStatus.BAD_REQUEST, "로그인 실패");
        }
        for (MemberFriend memberFriend : me.get().getMemberFriend()) {
            if(memberFriend.getFriend().getEmail().equals(friendEmail)){
                return new FriendPageInfoResponse(false, HttpStatus.BAD_REQUEST, "이미 친구입니다.");
            }
        }

        //MemberFriend 셋팅
        MemberFriend mf = new MemberFriend(me.get(), friend.get(), 0);

        //친구에게서 내가 이미 친구인지 확인
        for (MemberFriend memberFriend : friend.get().getMemberFriend()) {
            if(memberFriend.getFriend().getId() == myId){
                mf.setNumberOfDrinkingTogether(memberFriend.getNumberOfDrinkingTogether());
                break;
            }
        }
        
        //DB에 저장
        friendRepository.save(mf);


        FriendPageInfoResponse response = getFriend(myId);
        response.setMsg("친구추가를 성공했습니다.");
        return response;
    }

    public FriendPageInfoResponse getFriend(Long myId){

        //member 찾기
        Optional<Member> me = memberRepository.findById(myId);

        //validation
        if(me.isEmpty()){
            return new FriendPageInfoResponse(false, HttpStatus.BAD_REQUEST, "로그인 실패");
        }


        List<MemberFriend> memberFriend = friendRepository.findFriendsByMember(me.get());
        //MemberFriend

        if(memberFriend.isEmpty())
            return new FriendPageInfoResponse(false, HttpStatus.OK, "친구가 없습니다.");

        //이름 오름차순으로 정렬한 list 생성
        List<FriendDTO> friendData = memberFriend.stream().map(mf -> FriendDTO.MemberFriendToFriendDTO(mf)).sorted(new Comparator<FriendDTO>() {
            @Override
            public int compare(FriendDTO o1, FriendDTO o2) {
                
                // Null 체크를 추가하여 NullPointerException 방지
                if (o1 == null || o1.getFriend() == null || o1.getFriend().getName() == null) {
                    return -1;
                }
                if (o2 == null || o2.getFriend() == null || o2.getFriend().getName() == null) {
                    return 1;
                }
                // 이름을 기준으로 비교
                return o1.getFriend().getName().compareTo(o2.getFriend().getName());
            }

        }).toList();

        Optional<FriendDTO> mostDrinkPerWeekFriend = friendData.stream().max(new Comparator<FriendDTO>() {
            @Override
            public int compare(FriendDTO o1, FriendDTO o2) {
                // numberOfDrinks 값을 비교
                return Integer.compare(o1.getFriend().getNumberOfDrinks(), o2.getFriend().getNumberOfDrinks());
            }
        });

        Optional<FriendDTO> mostDrinkTogetherFriend = friendData.stream().max(new Comparator<FriendDTO>() {
            @Override
            public int compare(FriendDTO o1, FriendDTO o2) {
                return Integer.compare(o1.getNumberOfDrinkingTogether(), o2.getNumberOfDrinkingTogether());
            }
        });


        return new FriendPageInfoResponse(true, HttpStatus.OK, "친구 정보 가져오기 성공", friendData, mostDrinkPerWeekFriend.get(), mostDrinkTogetherFriend.get());

    }
}
