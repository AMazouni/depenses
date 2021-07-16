package ma.isi.depenses.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.isi.depenses.entity.Depense;
import ma.isi.depenses.util.DateUtil;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class DepenseDTO {
	private Long numero;
	private String date;
	private Double montant;
    private Boolean acceptee;
    BeneficiaireDTO beneficiaire;
    OrdonnateurDTO ordonnateur;
    
    public DepenseDTO(Depense d){
    	this.numero=d.getNumero();
    	this.date=DateUtil.formateDate("yyyy-MM-dd",d.getDate());
    	this.montant=d.getMontant();
    	this.acceptee=d.getAcceptee();
    	this.beneficiaire = new BeneficiaireDTO(d.getBeneficiaire());
    	this.ordonnateur = new OrdonnateurDTO(d.getOrdonnateur());
    }
}
