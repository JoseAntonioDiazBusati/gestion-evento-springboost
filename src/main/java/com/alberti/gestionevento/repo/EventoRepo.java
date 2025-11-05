package com.alberti.gestionevento.repo;

import com.alberti.gestionevento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepo extends JpaRepository<Evento, Long> {

    List<Evento> EncontrarPorTitulo(String titulo);

}
