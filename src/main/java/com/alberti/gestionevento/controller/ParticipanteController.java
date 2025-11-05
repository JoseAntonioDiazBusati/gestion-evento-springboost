package com.alberti.gestionevento.controller;

import com.alberti.gestionevento.model.Participante;
import com.alberti.gestionevento.repo.ParticipanteRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/participantes")
@CrossOrigin(origins = "*")
public class ParticipanteController {

    private final ParticipanteRepo participanteRepository;

    public ParticipanteController(ParticipanteRepo participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @GetMapping
    public List<Participante> getAll() {
        return participanteRepository.findAll();
    }

    @PostMapping
    public Participante create(@RequestBody Participante participante) {
        return participanteRepository.save(participante);
    }

    @GetMapping("/buscar")
    public List<Participante> buscarPorEmail(@RequestParam String email) {
        return participanteRepository.findByEmail(email);
    }
}
