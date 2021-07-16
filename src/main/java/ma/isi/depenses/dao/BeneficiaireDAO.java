package ma.isi.depenses.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import ma.isi.depenses.entity.Beneficiaire;

@RepositoryRestResource(collectionResourceRel = "beneficiaire", path = "beneficiaire")
@Repository
public interface BeneficiaireDAO extends JpaRepository<Beneficiaire, Long> {

	
	List<Beneficiaire> findByIntitule(String intitule);
	Beneficiaire findByLogin(String login);
}
