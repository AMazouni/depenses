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
public class LoginResponse {

	String accessToken;
	UserDTO user;
	
	public LoginResponse(String token, User u) {
		super();
		this.user = new UserDTO(u);
		this.accessToken=token;
	}
}
