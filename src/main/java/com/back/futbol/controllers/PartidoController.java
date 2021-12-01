package com.back.futbol.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.back.futbol.exceptions.CustomException;
import com.back.futbol.models.PartidoModel;
import com.back.futbol.services.PartidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PartidoController {

    @Autowired
    PartidoService service;


    @PostMapping("/partidos/registrar") 
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody PartidoModel partido, Errors error){
        if(error.hasErrors()){
               throwError(error);
        }

        this.service.registrarPartido(partido);
        Map<String, String> respuesta= new HashMap<>();
        respuesta.put("mensaje", "Se agregó correctamente el partido");
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/partidos")
    public List<PartidoModel> traerPartidos(){
        return this.service.traerPartidos();
    }

    @GetMapping("/partidos/local/{id}")
    public int cantidadGolesLocal(@PathVariable String id){
        return this.service.contarPartidosJugadosLocal(id);
    }

    @PutMapping("/partidos")
    public ResponseEntity<Map<String, String>> actualizar(@RequestBody PartidoModel partido, Errors error){
         if(error.hasErrors()){
             throwError(error);
         }

        this.service.registrarPartido(partido);
        Map<String, String> respuesta= new HashMap<>();
        respuesta.put("mensaje", "Se actualizó correctamente");
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
