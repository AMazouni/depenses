package ma.isi.depenses.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.isi.depenses.dao.DepenseDAO;
import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.service.facade.BeneficiaireService;
import ma.isi.depenses.service.facade.DepenseService;
import ma.isi.depenses.service.facade.OrdonnateurService;

@Service
public class DepenseServiceImpl implements DepenseService{

	@Autowired
	OrdonnateurService ordSer;
	@Autowired
	BeneficiaireService benSer;
	@Autowired
	DepenseDAO dao;
	
	public List<Depense> findByOrdonnateurCode(Long Code) {
		return dao.findByOrdonnateurCode(Code);
	}

	public List<Depense> findByBeneficiaireCode(Long Code) {
		return dao.findByBeneficiaireCode(Code);
	}

	public List<Depense> findByBeneficiaireLogin(String login) {
		return dao.findByBeneficiaireLogin(login);
	}

	public List<Depense> findByOrdonnateurLogin(String Login) {
		return dao.findByOrdonnateurLogin(Login);
	}

	@Override
	public int save(Depense o) {
		//Testing input contain all required fields
		if(  o.getBeneficiaire() == null || o.getOrdonnateur() == null || o.getDate() == null || o.getMontant() == null ) return -2;
	    //Test if input already exist in database
		//if(dao.findById(o.getNumero()) != null) return -2;
		if(ordSer.findByLogin(o.getOrdonnateur().getLogin())==null || benSer.findByLogin(o.getBeneficiaire().getLogin()) == null ) return -1;
 		// TODO : populate Ordonnateur and Beneficiaire from database using Id
		o.setOrdonnateur(ordSer.findByLogin(o.getOrdonnateur().getLogin()));
		o.setBeneficiaire( benSer.findByLogin(o.getBeneficiaire().getLogin()));
		o.setAcceptee(null);
		dao.save(o);
		return 1;
	}

	@Override
	public int delete(Depense o) {
		dao.deleteById(o.getNumero());
		return 1;
	}



	@Override
	public int update(Depense o) {
		if(this.findById(o.getNumero())==null) 
		return -1;
		dao.save(o);
		return 1;
	}

	@Override
	public List<Depense> findall() {
		
		return dao.findAll();
	}


	@Override
	public Depense findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<Depense> findByDateBetween(Date d1, Date d2) {
		
		return dao.findByDateBetween(d1, d2);
	}

	@Override
	public int acceptDepense(Depense o) {
		o.setAcceptee(true);
		return this.update(o);
	}

	@Override
	public int denyDepense(Depense o) {
		// TODO Auto-generated method stub
		return this.update(o);
	}

}
