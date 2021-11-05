package com.back.futbol.services;

import org.springframework.stereotype.Service;
import java.util.*;
import com.back.futbol.repositories.*;
import com.back.futbol.models.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class EquipoService {
    
    @Autowired
    EquipoRepository equipoRepository;


    public void guardarEquipo(EquipoModel equipo){
         this.equipoRepository.save(equipo);
    }

    public List<EquipoModel> obtenerEquipos(){
        return this.equipoRepository.findAll();
    }

    public Boolean existById(String id){
        return this.equipoRepository.existsById(id);
    }

    public Optional<EquipoModel> buscarPorId(String id){
        return this.equipoRepository.findById(id);
    }

    public void eliminarPorId(String id){
        this.equipoRepository.deleteById(id);
    }

}
