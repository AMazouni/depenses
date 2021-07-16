package ma.isi.depenses.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.isi.depenses.entity.Ordonnateur;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class OrdonnateurDTO {

	private Long code;
	private String login;
	private String intitule;
	private String categorie;
	private String droit;
	
	public OrdonnateurDTO(Ordonnateur o){
		super();
		this.code=o.getCode();
		this.login=o.getLogin();
		this.intitule=o.getIntitule();
		this.categorie=o.getCategorie();
		this.droit=o.getDroit();
	}
	
}
