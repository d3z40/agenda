package br.com.dezaoti.agenda.repository;

import br.com.dezaoti.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    public Optional<List<Contato>> findByNameContainingIgnoreCase(String name);
}
