package br.com.dezaoti.agenda.resource;

import br.com.dezaoti.agenda.model.Contato;
import br.com.dezaoti.agenda.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/contatos")
public class ContatoResource {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public ResponseEntity<List<Contato>> listAll() {
        return new ResponseEntity<>(contatoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Contato>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(contatoRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contato> save(@Valid @RequestBody Contato contato) {
        return new ResponseEntity<>(contatoRepository.save(contato), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        verifyContatoExists(id);

        contatoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Optional<Contato>> update(@Valid @RequestBody Contato newContato) {
        verifyContatoExists(newContato.getId());

        return contatoRepository.findById(newContato.getId())
                .map(contato -> {
                   contato.setName(newContato.getName());
                   contato.setEmail(newContato.getEmail());

                   return new ResponseEntity(contatoRepository.save(contato), HttpStatus.OK);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void verifyContatoExists(Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);

        if (!contato.isPresent()) {
            throw new RuntimeException("Contato not found for ID: " + id + " - by d3z40");
        }
    }
}
