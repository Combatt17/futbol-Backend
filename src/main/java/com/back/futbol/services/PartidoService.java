package com.back.futbol.services;

import java.util.List;

import com.back.futbol.models.EquipoModel;
import com.back.futbol.models.PartidoModel;
import com.back.futbol.repositories.PartidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {
    
    @Autowired
    PartidoRepository partidoRepository;

    public void registrarPartido(PartidoModel partido){
     this.partidoRepository.save(partido);
    }

    public List<PartidoModel> traerPartidos(){
        return this.partidoRepository.findAll();
    }

    public int contarPartidosJugadosLocal(String idEquipo){

        List<PartidoModel> todos=traerPartidos();
        int suma=0;
        for (PartidoModel dato : todos) {
            if(idEquipo==dato.getLocal().getId()){
                suma++;
            }
        }
        return suma;
    }

    
}
