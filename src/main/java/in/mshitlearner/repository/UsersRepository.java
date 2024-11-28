package in.mshitlearner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.mshitlearner.entity.UserInfo;

@Repository
public interface UsersRepository extends JpaRepository<UserInfo, Long> {

	public Optional<UserInfo> findByUserId(String username);

}
