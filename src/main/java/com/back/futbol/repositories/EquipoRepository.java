package com.back.futbol.repositories;

import com.back.futbol.models.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends MongoRepository<EquipoModel,String>{
    
}
