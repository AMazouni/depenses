package ma.isi.depenses.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.isi.depenses.entity.Beneficiaire;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class BeneficiaireDTO {

	private Long code;
	private String login;
	private String intitule;
	private String coordonnee;
	
	public BeneficiaireDTO(Beneficiaire b){
		super();
		if(b != null) {
		this.code=b.getCode();
		this.coordonnee=b.getCoordonnee();
		this.intitule=b.getIntitule();
		this.login=b.getLogin();}
	}
}
