package ma.isi.depenses.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "usertype",discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@DiscriminatorValue("admin")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;
	
	@Column(unique = true)
	private String login;
	
	@Lob 
	private String password;
	
	@Column(name="usertype",insertable = false,updatable = false)
	private String usertype;
}
