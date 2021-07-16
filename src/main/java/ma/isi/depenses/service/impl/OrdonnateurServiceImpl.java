package ma.isi.depenses.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.isi.depenses.dao.OrdonnateurDAO;
import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.entity.Ordonnateur;
import ma.isi.depenses.entity.User;
import ma.isi.depenses.exception.exception.CustomException;
import ma.isi.depenses.security.security.JwtTokenProvider;
import ma.isi.depenses.service.facade.DepenseService;
import ma.isi.depenses.service.facade.OrdonnateurService;
import ma.isi.depenses.web.dto.DepenseDTO;
import ma.isi.depenses.web.dto.LoginResponse;

@Service
public class OrdonnateurServiceImpl implements OrdonnateurService {

	@Autowired
	OrdonnateurDAO dao;
	@Autowired
	DepenseService depSer;
	  @Autowired
	  private JwtTokenProvider jwtTokenProvider;
	  
	public Ordonnateur findById(Long id) {
		return dao.findById(id).orElse(null);
	}




	public int deleteById(Long id) {
		dao.deleteById(id);
		return 1;
	}

	public int delete(Ordonnateur entity) {
		dao.delete(entity);
		return 1;
	}

	@Override
	public int save(Ordonnateur o) {
		 dao.save(o);
		return 1;
	}



	@Override
	public int update(Ordonnateur o) {
		 dao.save(o);
		return 1;
	}

	@Override
	public List<Ordonnateur> findall() {
		return dao.findAll();
		
	}




	@Override
	public Ordonnateur findByLogin(String Login) {
		// TODO Auto-generated method stub
		return dao.findByLogin(Login);
	}




	@Override
	public Ordonnateur loggedInOrdonnateur(HttpServletRequest req) {
		
		return this.findByLogin(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}




	@Override
	public List<Depense> loggedInOrdonnateurDepenses(HttpServletRequest req) {
		
		return this.loggedInOrdonnateur(req).getDepences();
	}





	@Override
	public void validateDepense(HttpServletRequest req, DepenseDTO d, Boolean b) {
		Depense de = depSer.findById(d.getNumero());
		if(this.loggedInOrdonnateurDepenses(req).contains(de)) {
			de.setAcceptee(b);
			depSer.update(de);
		} else {
			throw new CustomException("Unauthorized action", HttpStatus.UNAUTHORIZED);
		}
		
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
		    	throw new CustomException("Ce compte n'est pas un ordonnateur", HttpStatus.UNPROCESSABLE_ENTITY);
		    }
	}

	


}
