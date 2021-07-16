package ma.isi.depenses.service.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.isi.depenses.entity.Beneficiaire;
import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.entity.Ordonnateur;
import ma.isi.depenses.web.dto.LoginResponse;

public interface BeneficiaireService {

	public int save(Beneficiaire o);
	public int delete(Beneficiaire o);
	public int deleteById(Long id);
	

	public int update (Beneficiaire o);
	

	
	public Beneficiaire findByLogin(String Login);
	public Beneficiaire findById(Long id);
    public List<Beneficiaire> findAll();
	
    
    public Beneficiaire loggedInBeneficiaire(HttpServletRequest req);
	public List<Depense> loggedInBeneficiaireDepenses(HttpServletRequest req);
	public int addNewDepense(HttpServletRequest req,Depense d);
	 public LoginResponse signin(String username, String password);
}
