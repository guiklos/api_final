package dweb.mensalistas_futebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dweb.mensalistas_futebol.model.Jogador;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    List<Jogador> findByNome(String nome);

    List<Jogador> findByEmail(String email);

    List<Jogador> findAll();

}
