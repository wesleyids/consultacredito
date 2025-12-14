package br.com.eicon.consultacredito.service;

import br.com.eicon.consultacredito.dtos.CreditoDTO;
import br.com.eicon.consultacredito.entities.Credito;
import br.com.eicon.consultacredito.execptions.NotFoundException;
import br.com.eicon.consultacredito.producer.IProducer;
import br.com.eicon.consultacredito.repository.CreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditoService {

    private final String NOT_FOUND_MESSAGE = "Crédito não localizado pelo %s: %s";

    private final String MESSAGE_LOG = "Consulta realiza pelo %s com nro %s";

    private final String MESSAGE_ERROR_LOG = "Não foi localizado credito pelo %s e nro %s";

    private CreditoRepository creditoRepository;

    private IProducer producer;

    public CreditoService(
            CreditoRepository creditoRepository,
            IProducer producer) {
        this.creditoRepository = creditoRepository;
        this.producer = producer;
    }

    public List<CreditoDTO> findByNumeroNfse(String numeroNfse) throws NotFoundException {
        List<Credito> creditos = creditoRepository.findByNumeroNfse(numeroNfse);
        if (creditos.isEmpty()) {

            /**
             * Melhorias
             *
             * 1. Usar variável para o texto "número NFSe"
             * 2. Criar um evento async para o producer e desaclopar mais ainda
             *
             */
            producer.send(String.format(MESSAGE_ERROR_LOG, "número NFSe", numeroNfse));

            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, "número NFSe", numeroNfse));
        }

        producer.send(String.format(MESSAGE_LOG, "número NFSe", numeroNfse));

        return creditos.stream().map((credito) -> CreditoDTO.of(credito)).collect(Collectors.toList());
    }

    public CreditoDTO findByNumeroCredito(String numeroCredito) throws NotFoundException {
        try {
            Credito credito = creditoRepository.findByNumeroCredito(numeroCredito);
            if (credito == null) {

                producer.send(String.format(MESSAGE_ERROR_LOG, "número crédito", numeroCredito));

                throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, "número crédito", numeroCredito));
            }

            producer.send(String.format(MESSAGE_LOG, "número crédito", numeroCredito));

            return CreditoDTO.of(credito);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
