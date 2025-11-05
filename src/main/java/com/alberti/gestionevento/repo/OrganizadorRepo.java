package com.alberti.gestionevento.repo;

import com.alberti.gestionevento.model.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrganizadorRepo extends JpaRepository<Organizador, Long> {

    List<Organizador> findByNombre(String nombre);

}
