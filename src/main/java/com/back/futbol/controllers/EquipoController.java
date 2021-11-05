package com.back.futbol.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import com.back.futbol.exceptions.CustomException;
import com.back.futbol.models.EquipoModel;
import com.back.futbol.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EquipoController {
    
    @Autowired
    EquipoService equipoService;

    @PostMapping("/equipos")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody EquipoModel equipo,Errors error) {
        if(error.hasErrors()){
            throwError(error);
        }

        this.equipoService.guardarEquipo(equipo);
        Map<String, String> response= new HashMap<>();
        response.put("mensaje","El equipo se agregó correctamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/equipos")
    public List<EquipoModel> traerTodos(){
        return this.equipoService.obtenerEquipos();
    }

    @GetMapping("/equipos/{id}")
    public EquipoModel buscarID(@PathVariable String id) {
       return this.equipoService.buscarPorId(id).get();
    }

    private void throwError(Errors error) {
        String message="";
        int index=0;
        for(ObjectError e: error.getAllErrors()){
            if(index>0){
                message += " | ";
            }
            message += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(),e.getDefaultMessage());
        }
        throw new CustomException(message);
    }



}
