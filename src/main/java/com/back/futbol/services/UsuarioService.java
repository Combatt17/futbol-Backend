package com.back.futbol.services;

import com.back.futbol.models.UsuarioModel;
import com.back.futbol.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public void guardarUsuario(UsuarioModel usuario){
           this.usuarioRepository.save(usuario);
    }

    public UsuarioModel buscarPorNombreUsuario(String username){
      return this.usuarioRepository.findByUsername(username).orElse(new UsuarioModel());
    }
}
