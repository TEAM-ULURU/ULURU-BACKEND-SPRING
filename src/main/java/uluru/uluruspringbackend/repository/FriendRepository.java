package uluru.uluruspringbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uluru.uluruspringbackend.data.domain.Member;
import uluru.uluruspringbackend.data.domain.MemberFriend;

import java.util.List;

public interface FriendRepository extends JpaRepository<MemberFriend, Long> {

    @Query("select f from MemberFriend as f join fetch f.friend where f.member = :m")
    List<MemberFriend> findFriendsByMember(@Param("m") Member member);


}
