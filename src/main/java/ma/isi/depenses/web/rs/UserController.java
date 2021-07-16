package ma.isi.depenses.web.rs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import ma.isi.depenses.entity.User;
import ma.isi.depenses.service.impl.UserService;
import ma.isi.depenses.web.dto.LoginResponse;
import ma.isi.depenses.web.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;



  @PostMapping("/signin")

  public LoginResponse login(//
      @ApiParam("Username") @RequestParam String username, //
      @ApiParam("Password") @RequestParam String password) {
    return userService.signin(username, password);
  }

  @PostMapping("/signup")

  public String signup(@ApiParam("Signup User") @RequestBody UserDTO user) {
    return userService.signup(new User(null, user.getLogin(), user.getPassword(), user.getType()));
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasAuthority('admin')")

  public String delete(@ApiParam("Username") @PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasAuthority('admin')")
 
  public UserDTO search(@ApiParam("Username") @PathVariable String username) {
    return new UserDTO(userService.search(username), 0);
  }

  @GetMapping(value = "/me")
 
  public UserDTO whoami(HttpServletRequest req) {
    return new UserDTO(userService.whoami(req), 0);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasAuthority('admin') or hasAuthority('beneficiaire') or hasAuthority('ordonnateur')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
