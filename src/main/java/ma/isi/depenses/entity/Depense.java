package ma.isi.depenses.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Depense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numero;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date date;
	private Double montant;
    private Boolean acceptee;

    @ManyToOne
	private Ordonnateur ordonnateur;
    @ManyToOne
	private Beneficiaire beneficiaire;
	
	public Depense(Date date, Double montant, Ordonnateur ordonnateur, Beneficiaire beneficiaire) {
		super();
		this.date = date;
		this.montant = montant;
		this.ordonnateur = ordonnateur;
		this.beneficiaire = beneficiaire;
	}

	public Depense(Date date, Double montant) {
		super();
		this.date = date;
		this.montant = montant;
		this.ordonnateur = ordonnateur;
		this.beneficiaire = beneficiaire;
	}

}
