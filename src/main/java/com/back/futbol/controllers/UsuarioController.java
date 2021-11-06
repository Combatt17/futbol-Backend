package com.back.futbol.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.back.futbol.exceptions.CustomException;
import com.back.futbol.models.UsuarioModel;
import com.back.futbol.services.UsuarioService;
import com.back.futbol.utils.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Registrar usuario
    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, String>> guardarUsuario(@Valid @RequestBody UsuarioModel usuario, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        // Usuario.getPassword()= 123456
        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));

        UsuarioModel u = this.usuarioService.buscarPorNombreUsuario(usuario.getUsername());

        if (u.getId() == null) {
            this.usuarioService.guardarUsuario(usuario);
            respuesta.put("mensaje", "Se resgistro el usuario correctamente");
        } else {
            respuesta.put("mensaje", "El usuario ya se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
    }

    private void throwError(Errors error) {
        String message = "";
        int index = 0;
        for (ObjectError e : error.getAllErrors()) {
            if (index > 0) {
                message += " | ";
            }
            message += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(), e.getDefaultMessage());
        }
        throw new CustomException(message);
    }
}
