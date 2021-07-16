package ma.isi.depenses.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import io.swagger.annotations.Api;
import ma.isi.depenses.entity.Ordonnateur;



@Repository
@RepositoryRestResource(collectionResourceRel = "ordonnateur", path = "ordonnateur")
public interface OrdonnateurDAO extends JpaRepository<Ordonnateur, Long> {
Ordonnateur findByLogin(String login);
}
