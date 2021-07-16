package ma.isi.depenses.web.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.isi.depenses.entity.User;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserDTO {
	
	private String login;
	private String password;
	private String cryptedPassword;
	private String type;
	
	public UserDTO(User u){
		this.login=u.getLogin();
		this.cryptedPassword=u.getPassword();
		this.type=u.getUsertype();
	}

	public UserDTO(User whoami, int i) {
		this(whoami);
		if(i==0) this.cryptedPassword=null;
	}

}
