package br.com.eicon.consultacredito.repository;

import br.com.eicon.consultacredito.entities.Credito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditoRepository extends CrudRepository<Credito, Long> {

    public List<Credito> findByNumeroNfse(String numeroNfse);

    public Credito findByNumeroCredito(String NumeroCredito);

    /**
     * Apenas um exemplo usando SQL nativo
     * Ideal para performance de objetos complexos e com muitos joins
     *
     * @param numeroCredito
     * @return Credito
     */
    @Deprecated
    @Query(value = "select * from credito where numero_credito = :numeroCredito", nativeQuery = true)
    public Credito buscarPorNumeroCredito(String numeroCredito);
}
