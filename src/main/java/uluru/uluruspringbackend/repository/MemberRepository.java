package uluru.uluruspringbackend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uluru.uluruspringbackend.data.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Find By
    Member findFirstById(Long id);
    Member findFirstByEmail(String email);
    Optional<Member> findByEmail(String email);

    @EntityGraph(attributePaths = {"memberFriend"})
    Optional<Member> findFriendEntityGraphByEmail(String email);

    @EntityGraph(attributePaths = {"memberFriend"})
    Optional<Member> findFriendEntityGraphById(Long id);


    // Exist By
    boolean existsByEmail(String email);

}
