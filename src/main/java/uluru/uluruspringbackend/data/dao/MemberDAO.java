package uluru.uluruspringbackend.data.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uluru.uluruspringbackend.data.domain.Member;
import uluru.uluruspringbackend.data.dto.MemberDTO;
import uluru.uluruspringbackend.data.dto.MemberSignup1PageDTO;
import uluru.uluruspringbackend.data.dto.MemberSignup2PageDTO;
import uluru.uluruspringbackend.data.dto.MemberSignup3PageDTO;
import uluru.uluruspringbackend.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDAO {

    private final MemberRepository memberRepository;

    public Optional<MemberDTO> getUserByEmail(String email) {
        log.info("[UserDAO]-[getUserByEmail] UserEmail로 User 정보 접근 {}", email);

        Optional<Member> byEmail = memberRepository.findByEmail(email);

        if(byEmail.isPresent()) {

            log.info("[UserDAO]-[getUserByEmail] UserEmail로 User 정보 찾기 성공 : {}", byEmail.get().getEmail());

            return Optional.of(byEmail.get().toDto());
        }
        else {

            log.info("[UserDAO]-[getUserByEmail] UserEmail로 User 정보 찾기 실패");

            return Optional.empty();
        }

    }

    @Transactional
    public boolean saveUser(MemberDTO memberDTO) {

        Member member = memberDTO.toEntity();
        log.info("[UserDAO]-[userSignup] UserEntitiy 저장 : {}", member.getEmail());

        // 이메일 중복 여부 확인
        if(memberRepository.existsByEmail(member.getEmail())){
            log.info("[UserDAO]-[userSignup] 회원 이메일 중복 : {}", member.getEmail());
            return false;
        }

        memberRepository.save(member);
        log.info("[UserDAO]-[userSignup] UserEntitiy 저장 성공 : {}", member.getEmail());

        return true;
    }


    public Optional<MemberDTO> getUserById(Long memberId){

        log.info("[UserDAO]-[getUserById] UserID로 User 정보 접근");
        Optional<Member> byId = memberRepository.findById(memberId);

        if(byId.isPresent()) {

            log.info("[UserDAO]-[getUserById] UserID로 User 정보 찾기 성공 : {}", byId.get().getEmail());

            return Optional.of(byId.get().toDto());
        }
        else {

            log.info("[UserDAO]-[getUserById] UserID로 User 정보 찾기 실패");
            return Optional.empty();
        }
    }

    public boolean addUserInfo(MemberSignup1PageDTO memberSignup1PageDTO){

        log.info("[UserDAO]-[addUserInfo 1] UserEmail로 User 정보 접근");

        Member firstByEmail = memberRepository.findFirstByEmail(memberSignup1PageDTO.getEmail());

        if(firstByEmail==null){
            log.info("[UserDAO]-[addUserInfo 1] UserEmail로 User 정보 접근 실패");

            return false;
        }

        firstByEmail.setGender(memberSignup1PageDTO.getGender());
        firstByEmail.setAge(memberSignup1PageDTO.getAge());
        firstByEmail.setHeight(memberSignup1PageDTO.getHeight());
        firstByEmail.setWeight(memberSignup1PageDTO.getWeight());
        firstByEmail.setBodyFatPercentage(memberSignup1PageDTO.getBodyFatPercentage());

        return true;
    }
    public boolean addUserInfo(MemberSignup2PageDTO memberSignup2PageDTO){

        log.info("[UserDAO]-[addUserInfo 2] UserEmail로 User 정보 접근");

        Member firstByEmail = memberRepository.findFirstByEmail(memberSignup2PageDTO.getEmail());

        if(firstByEmail==null){
            log.info("[UserDAO]-[addUserInfo 2] UserEmail로 User 정보 접근 실패");

            return false;
        }

        firstByEmail.setDrinkingFrequencyReferenceValue(memberSignup2PageDTO.getDrinkingFrequencyReferenceValue());
        firstByEmail.setDrinkingFrequency(memberSignup2PageDTO.getDrinkingFrequency());
        firstByEmail.setTypeOfAlcohol(memberSignup2PageDTO.getTypeOfAlcohol());
        firstByEmail.setAverageAlcoholIntake(memberSignup2PageDTO.getAverageAlcoholIntake());
        firstByEmail.setDegreeOfIntoxication(memberSignup2PageDTO.getDegreeOfIntoxication());




        return true;
    }
    public boolean addUserInfo(MemberSignup3PageDTO memberSignup3PageDTO){

        log.info("[UserDAO]-[addUserInfo 3] UserEmail로 User 정보 접근");

        Member firstByEmail = memberRepository.findFirstByEmail(memberSignup3PageDTO.getEmail());

        if(firstByEmail==null){
            log.info("[UserDAO]-[addUserInfo 3] UserEmail로 User 정보 접근 실패");

            return false;
        }

        firstByEmail.setAddress(memberSignup3PageDTO.getAddress());
        firstByEmail.setEmergencyContact(memberSignup3PageDTO.getEmergencyContact());

        return true;
    }



}
