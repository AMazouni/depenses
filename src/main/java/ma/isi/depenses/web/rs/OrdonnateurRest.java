package ma.isi.depenses.web.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.service.facade.OrdonnateurService;
import ma.isi.depenses.web.dto.DepenseDTO;
import ma.isi.depenses.web.dto.LoginResponse;
import ma.isi.depenses.web.dto.OrdonnateurDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v2/ordonnateur")
public class OrdonnateurRest {

	@Autowired
	OrdonnateurService ordserv;

	@GetMapping("/")
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAuthority('ordonnateur')")
	public List<OrdonnateurDTO> findall() {
		return ordserv.findall().stream().map(o-> new OrdonnateurDTO(o)).collect(Collectors.toList());
	}

	@GetMapping("/loggedin")
	@PreAuthorize("hasAuthority('ordonnateur')")
	public OrdonnateurDTO loggedInOrdonnateur(HttpServletRequest req) {
		return new OrdonnateurDTO(ordserv.loggedInOrdonnateur(req));
	}

	@GetMapping("/loggedin/depenses/")
	@PreAuthorize("hasAuthority('ordonnateur')")
	public List<DepenseDTO> loggedInOrdonnateurDepenses(HttpServletRequest req) {
		return ordserv.loggedInOrdonnateurDepenses(req).stream().map(d->new DepenseDTO(d)).collect(Collectors.toList());
	}

	@PostMapping("/loggedin/depenses")
	@PreAuthorize("hasAuthority('ordonnateur')")
	public void validateDepense(HttpServletRequest req, @RequestBody DepenseDTO d, @RequestParam Boolean b) {
		ordserv.validateDepense(req, d, b);
	}
	@PostMapping("/signin")
	public LoginResponse signin(@RequestParam String username, @RequestParam String password) {
		return ordserv.signin(username, password);
	}
	
	
}
