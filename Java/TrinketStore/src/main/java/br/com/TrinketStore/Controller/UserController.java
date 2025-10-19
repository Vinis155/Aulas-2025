package br.com.TrinketStore.Controller;

import br.com.TrinketStore.Model.Usuario;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.TrinketStore.Service.UserService;

/*Esta classe faz o registro do usuario*/


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Usuario registrar (@RequestBody Usuario uusario) {
        return userService.SalvarUsurio(uusario);
    }
}
