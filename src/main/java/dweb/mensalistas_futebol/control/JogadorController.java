package dweb.mensalistas_futebol.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dweb.mensalistas_futebol.repository.JogadorRepository;
import dweb.mensalistas_futebol.model.Jogador;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class JogadorController {
    @Autowired
    JogadorRepository jR;

    @GetMapping("/jogadores")
    public ResponseEntity<List<Jogador>> getAlljogador(@RequestParam(required = false) String nome) {
        try {
            List<Jogador> lj = new ArrayList<Jogador>();
            if (nome == null) {
                jR.findAll().forEach(lj::add);
            } else {
                jR.findByNome(nome).forEach(lj::add);
            }

            if (lj.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lj, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/jogadores")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador j) {
        try {
            Jogador _j = jR.save(new Jogador(j.getNome(), j.getEmail(), j.getDataNasc()));
            return new ResponseEntity<>(_j, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/jogadores/{codJogador}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("codJogador") int codJogador, @RequestBody Jogador j) {
        Optional<Jogador> data = jR.findById(codJogador);
        if (data.isPresent()) {
            Jogador _j = data.get();
            _j.setNome(j.getNome());
            _j.setEmail(j.getEmail());
            _j.setDataNasc(j.getDataNasc());

            return new ResponseEntity<>(jR.save(_j), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/jogadores/{codJogador}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("codJogador") int codJogador) {
        try {
            jR.deleteById(codJogador);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
