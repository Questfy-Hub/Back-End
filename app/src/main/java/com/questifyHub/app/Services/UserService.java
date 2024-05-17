package com.questifyHub.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Exceptions.InvalidEmailException;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Regex.Regex;
import com.questifyHub.app.Repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // TODO: Atualizar tratamento de excessão
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id" + id));

    }

    // TODO: Tratamento de Excessão
    public List<User> getAllUser() {
        return userRepository.findAll();

    }

    // TODO: Tratamento de Excessão
    public User createUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user);
    }

    // TODO: Atualizar tratamento de excessão
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id" + id));

        user.setFullname(userDetails.getFullname());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setCpf(userDetails.getCpf());
        user.setRole(userDetails.getRole());
        user.setUsername(userDetails.getUsername());

        return userRepository.save(user);

    }

    // TODO: Tratamento de Excessão
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean authentication(String login, String password) {
        try {
            if (Regex.validateEmail(login)) {
                User temp = userRepository.getUserByEmail(login);
                if (BCrypt.checkpw(password, temp.getPassword())) {
                    return true;
                }
            }
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return false;
    }
}
