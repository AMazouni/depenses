package ma.isi.depenses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import ma.isi.depenses.entity.User;

@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserDAO extends JpaRepository<User, Long>{
User findByLogin(String login);

boolean existsByLogin(String login);

void deleteByLogin(String username);
}
