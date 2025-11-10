package com.alberti.gestionevento.controller;

import com.alberti.gestionevento.model.Participante;
import com.alberti.gestionevento.repo.EventoRepo;
import com.alberti.gestionevento.model.Evento;
import com.alberti.gestionevento.repo.ParticipanteRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/participantes")
@CrossOrigin(origins = "*")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepo participanteRepository;

    @Autowired
    private EventoRepo eventoRepository;

    @GetMapping
    public List<Participante> getAll() {
        return participanteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Participante> create(@RequestBody Participante participante) {
        if (participante.getEvento() != null && participante.getEvento().getId() != null) {
            Evento evento = eventoRepository.findById(participante.getEvento().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Evento con id " + participante.getEvento().getId() + " no encontrado"));
            participante.setEvento(evento);
        }
        participante.setId(null);
        Participante saved = participanteRepository.save(participante);

        return ResponseEntity.ok(saved);
    }

    @GetMapping("/buscar")
    public List<Participante> buscarPorEmail(@RequestParam String email) {
        return participanteRepository.findByEmail(email);
    }
}