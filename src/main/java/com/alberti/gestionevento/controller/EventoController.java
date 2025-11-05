package com.alberti.gestionevento.controller;

import com.alberti.gestionevento.model.Evento;
import com.alberti.gestionevento.repo.EventoRepo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")

public class EventoController {

    private final EventoRepo eventoRepository;

    public EventoController(EventoRepo eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping
    public List<Evento> getAll() {
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Evento getById(@PathVariable Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Evento create(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }

    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @RequestBody Evento evento) {
        evento.setId(id);
        return eventoRepository.save(evento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventoRepository.deleteById(id);
    }

    // Consulta adicional
    @GetMapping("/buscar")
    public List<Evento> buscarPorTitulo(@RequestParam String titulo) {
        return eventoRepository.findByTitulo(titulo);
    }
}