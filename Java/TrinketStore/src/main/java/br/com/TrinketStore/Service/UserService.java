package br.com.TrinketStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.TrinketStore.Model.Usuario;
import br.com.TrinketStore.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para salvar um novo usuário
    public Usuario SalvarUsurio(Usuario uusario) {
        return userRepository.save(uusario);
    }
}
