package ma.isi.depenses.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import ma.isi.depenses.entity.Depense;

@RepositoryRestResource(collectionResourceRel = "depense", path = "depense")
@Repository
public interface DepenseDAO extends JpaRepository<Depense, Long> {
	
	 List<Depense> findByOrdonnateurCode(Long Code);
	 List<Depense> findByBeneficiaireCode(Long Code);
	 List<Depense>  findByBeneficiaireLogin(String login);
	 List<Depense>  findByOrdonnateurLogin(String Login);
	 List<Depense> findByDateBetween(@DateTimeFormat(pattern = "yyyy-mm-dd") @Param(value = "d1") Date d1,@DateTimeFormat(pattern = "yyyy-mm-dd")  @Param(value = "d2")Date d2);
}
