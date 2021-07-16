package ma.isi.depenses.entity;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@DiscriminatorValue(value = "beneficiaire")
public class Beneficiaire  extends User{

	
	
	


	
	private String intitule;
	private String coordonnee;

	@OneToMany(mappedBy = "beneficiaire")
	List<Depense> depences;

	public Beneficiaire(String intitule, String coordonnee) {
		super();
		this.intitule = intitule;
		this.coordonnee = coordonnee;
	}

}
