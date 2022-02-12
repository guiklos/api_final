package dweb.mensalistas_futebol.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dweb.mensalistas_futebol.model.Jogador;
import dweb.mensalistas_futebol.model.Pagamento;
import dweb.mensalistas_futebol.repository.JogadorRepository;
import dweb.mensalistas_futebol.repository.PagamentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class PagamentoController {
    @Autowired
    PagamentoRepository pR;

    @Autowired
    JogadorRepository jR;

    @GetMapping("/pagamentos")
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {

        try {
            List<Pagamento> lp = new ArrayList<Pagamento>();

            pR.findAll().forEach(lp::add);
            if (lp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lp, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagamentos/{codJogador}")
    public ResponseEntity<List<Pagamento>> getAllPagamentos(
            @PathVariable("codJogador") int codJogador) {

        try {
            List<Pagamento> lp = new ArrayList<Pagamento>();

            lp.add(pR.findById(codJogador).get());
            if (lp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lp, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST /api/pagamentos -> criar pagamento
    @PostMapping("/pagamentos/{codJogador}/jogadores")
    public ResponseEntity<Pagamento> createPagamento(@PathVariable("codJogador") int codJogador,
            @RequestBody Pagamento pag) {
        try {
            Optional<Jogador> jogador = jR.findById(codJogador);
            if (jogador.isPresent()) {
                Pagamento _p = pR.save(
                        new Pagamento(pag.getAno(), pag.getMes(), pag.getValor(), jogador.get()));
                return new ResponseEntity<>(_p, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT /api/pagamentos/:idPagamento -> atualizar pagamento dado um id
    @PostMapping("/pagamentos/{codPagamento}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("codPagamento") int codPagamento,
            @RequestBody Pagamento pag) {

        Optional<Pagamento> data = pR.findById(codPagamento);

        if (data.isPresent()) {
            Pagamento p = data.get();
            pag.setMes(pag.getMes());
            pag.setAno(pag.getMes());
            pag.setValor(pag.getValor());
            pag.setCodPagamento(pag.getCodPagamento());

            return new ResponseEntity<>(pR.save(p), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DEL /api/pagamentos/:idPagamento -> remover pagamento dado id
    @DeleteMapping("/pagamentos/{codPagamento}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("codPagamento") int codPagamento) {
        try {
            pR.deleteById(codPagamento);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}