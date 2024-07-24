package com.irvza.login.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.irvza.login.Repository.UsersRepository;
import com.irvza.login.models.UserModel;

import jakarta.transaction.Transactional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Transactional
    public ResponseEntity<?> addUser(String username, String password, String role){
        UserModel usuario=new UserModel();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRole(role);

        try {
            if (username==null || password==null || role ==null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos los campos son requeridos");
            }else{
               
                return ResponseEntity.status(HttpStatus.OK).body(usersRepository.save(usuario));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> updateUser(Integer id , String username, String password, String role){
        UserModel usuario=new UserModel();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRole(role);

        Optional<UserModel> exists=usersRepository.findById(id);

        try {
            if (!exists.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
            }else{
               
                return ResponseEntity.status(HttpStatus.OK).body(usersRepository.save(usuario));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> readUser(Integer id ){
        
        Optional<UserModel> exists=usersRepository.findById(id);

        try {
            if (!exists.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
            }else{
               
                return ResponseEntity.status(HttpStatus.OK).body(exists);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> deleteUser(Integer id ){
        
        Optional<UserModel> exists=usersRepository.findById(id);

        try {
            if (!exists.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
            }else{
               usersRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
