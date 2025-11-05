package com.alberti.gestionevento.controller;

import com.alberti.gestionevento.model.Organizador;
import com.alberti.gestionevento.repo.OrganizadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organizadores")
@CrossOrigin(origins = "*")
public class OrganizadorController {

    @Autowired
    private OrganizadorRepo organizadorRepository;

    public OrganizadorController(OrganizadorRepo organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    @GetMapping
    public List<Organizador> getAllOrganizadores() {
        return organizadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizador> getOrganizadorById(@PathVariable Long id) {
        return organizadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Organizador createOrganizador(@RequestBody Organizador organizador) {
        return organizadorRepository.save(organizador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizador> updateOrganizador(@PathVariable Long id, @RequestBody Organizador organizadorDetails) {
        return organizadorRepository.findById(id).map(organizador -> {
            organizador.setNombre(organizadorDetails.getNombre());
            return ResponseEntity.ok(organizadorRepository.save(organizador));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizador(@PathVariable Long id) {
        if (organizadorRepository.existsById(id)) {
            organizadorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public List<Organizador> buscarPorNombre(@RequestParam String nombre) {
        return organizadorRepository.findByNombre(nombre);
    }
}
