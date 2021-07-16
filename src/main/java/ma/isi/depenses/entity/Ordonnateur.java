package ma.isi.depenses.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
@DiscriminatorValue("ordonnateur")
public class Ordonnateur extends User{



	private String intitule;
	private String categorie;
	private String droit;


	@OneToMany(mappedBy = "ordonnateur")
	List<Depense> depences;
}
