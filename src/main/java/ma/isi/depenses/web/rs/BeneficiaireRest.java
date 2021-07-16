package ma.isi.depenses.web.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.isi.depenses.entity.Beneficiaire;
import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.service.facade.BeneficiaireService;
import ma.isi.depenses.web.dto.BeneficiaireDTO;
import ma.isi.depenses.web.dto.DepenseDTO;
import ma.isi.depenses.web.dto.LoginResponse;

@RestController
@RequestMapping("/v2/beneficiaire")
public class BeneficiaireRest {

	
	@Autowired
	BeneficiaireService benser;

	@GetMapping("/")
	public List<BeneficiaireDTO> findAll() {
		return benser.findAll().stream().map(b-> new BeneficiaireDTO(b)).collect(Collectors.toList());
	}

	@GetMapping("/loggedin")
	@PreAuthorize("hasAuthority('beneficiaire')")
	public BeneficiaireDTO loggedInBeneficiaire(HttpServletRequest req) {
		return new BeneficiaireDTO(benser.loggedInBeneficiaire(req));
	}

	@GetMapping("/loggedin/depenses")
	@PreAuthorize("hasAuthority('beneficiaire')")
	public List<DepenseDTO> loggedInBeneficiaireDepenses(HttpServletRequest req) {
		return benser.loggedInBeneficiaireDepenses(req).stream().map(d->new DepenseDTO(d)).collect(Collectors.toList());
	}

	@PostMapping("/loggedin/depenses")
	@PreAuthorize("hasAuthority('beneficiaire')")
	public int addNewDepense(HttpServletRequest req,@RequestBody Depense d) {
		return benser.addNewDepense(req, d);
	}
	@PostMapping("/signin")
	public LoginResponse signin(@RequestParam String username, @RequestParam String password) {
		return benser.signin(username, password);
	}
	
	
}
