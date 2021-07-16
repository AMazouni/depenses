package ma.isi.depenses.service.facade;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.entity.Ordonnateur;
import ma.isi.depenses.web.dto.DepenseDTO;
import ma.isi.depenses.web.dto.LoginResponse;

public interface OrdonnateurService {
	
	public int save(Ordonnateur o);
	public int delete(Ordonnateur o);
	public int deleteById(Long id);
	
   
	public int update (Ordonnateur o);
	
	public List<Ordonnateur> findall();

	
	public Ordonnateur findById(Long id);
	public Ordonnateur findByLogin(String Login);
	
	public Ordonnateur loggedInOrdonnateur(HttpServletRequest req);
	public List<Depense> loggedInOrdonnateurDepenses(HttpServletRequest req);
	public void validateDepense(HttpServletRequest req,DepenseDTO d,Boolean b);
	//public Ordonnateur findByIntitule(String intitule);
	
	 public LoginResponse signin(String username, String password);

}
