package ma.isi.depenses.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.isi.depenses.dao.BeneficiaireDAO;
import ma.isi.depenses.entity.Beneficiaire;
import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.entity.Ordonnateur;
import ma.isi.depenses.entity.User;
import ma.isi.depenses.exception.exception.CustomException;
import ma.isi.depenses.security.security.JwtTokenProvider;
import ma.isi.depenses.service.facade.BeneficiaireService;
import ma.isi.depenses.service.facade.DepenseService;
import ma.isi.depenses.web.dto.LoginResponse;

@Service
public class BeneficiaireServiceImpl implements BeneficiaireService  {


	@Autowired
	BeneficiaireDAO dao;
	@Autowired
	DepenseService depSer;
	  @Autowired
	  private JwtTokenProvider jwtTokenProvider;
	
	public Beneficiaire findByLogin(String login) {
		return dao.findByLogin(login);
	}

	public List<Beneficiaire> findAll() {
		return dao.findAll();
	}

	public Beneficiaire findById(Long id) {
		return dao.findById(id).orElse(null);
	}

	public int deleteById(Long id) {
		dao.deleteById(id);
		return 1;
	}


	@Override
	public int save(Beneficiaire o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Beneficiaire o) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int update(Beneficiaire o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Beneficiaire loggedInBeneficiaire(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return this.findByLogin(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	@Override
	public List<Depense> loggedInBeneficiaireDepenses(HttpServletRequest req) {
		
		return this.loggedInBeneficiaire(req).getDepences();
	}

	@Override
	public int addNewDepense(HttpServletRequest req,Depense d) {
		d.setBeneficiaire(this.loggedInBeneficiaire(req));
		System.out.println(d.getBeneficiaire());
		
		return depSer.save(d);
	}
	
	  @Autowired
	  private AuthenticationManager authenticationManager;
	  @Autowired
	  private PasswordEncoder passwordEncoder;

	@Override
	public LoginResponse signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			  User u = dao.findByLogin(username);
		      if(u==null) throw new Exception();
		      String t= jwtTokenProvider.createToken(username, dao.findByLogin(username).getUsertype());
		      return new LoginResponse(t, u);
		    } catch (AuthenticationException e) {
		      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		    } catch (Exception e) {
		    	throw new CustomException("Ce compte n'est pas un beneficiaire", HttpStatus.UNPROCESSABLE_ENTITY);
		    }
	}


}
