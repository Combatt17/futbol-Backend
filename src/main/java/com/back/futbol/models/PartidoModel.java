package com.back.futbol.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="partidos")
public class PartidoModel {
    
    @Id
    private String id;

    private UsuarioModel usuario;
    private EquipoModel local;
    private EquipoModel visitante;
    private String fecha;
    private int golesvisitante;
    private int goleslocal;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public void setLocal(EquipoModel local){
        this.local = local;
    }
    public EquipoModel getLocal(){
        return local;
    }

    public void setVisitante(EquipoModel visitante){
        this.visitante = visitante;
    }

    public EquipoModel getVisitante(){
        return visitante;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setGolesVisitante(int golesvisitante){
        this.golesvisitante = golesvisitante;
    }

    public int getGolesVisitante() {
        return golesvisitante;
    }

    public void setGolesLocal(int goleslocal){
        this.goleslocal = goleslocal;
    }

    public int getGolesLocal() {
        return goleslocal;
    }


}
