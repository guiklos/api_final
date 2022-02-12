package dweb.mensalistas_futebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dweb.mensalistas_futebol.model.Pagamento;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    List<Pagamento> findAll();
}