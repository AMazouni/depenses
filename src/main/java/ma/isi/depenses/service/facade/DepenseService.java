package ma.isi.depenses.service.facade;

import java.util.Date;
import java.util.List;

import ma.isi.depenses.entity.Depense;

public interface DepenseService {

	public int save(Depense o);

	public int delete(Depense o);


	public int update(Depense o);

	public List<Depense> findall();

    public List<Depense> findByDateBetween(Date d1,Date d2);

    public List<Depense> findByOrdonnateurCode(Long code);
    public List<Depense> findByBeneficiaireCode(Long code);
    List<Depense>  findByBeneficiaireLogin(String login);
	List<Depense>  findByOrdonnateurLogin(String Login);
	public Depense findById(Long id);
	
	public int acceptDepense(Depense o);
	
	public int denyDepense(Depense o);
	// public Depense findByIntitule(String intitule);
}
