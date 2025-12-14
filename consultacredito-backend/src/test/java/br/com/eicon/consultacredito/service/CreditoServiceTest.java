package br.com.eicon.consultacredito.service;

import br.com.eicon.consultacredito.dtos.CreditoDTO;
import br.com.eicon.consultacredito.entities.Credito;
import br.com.eicon.consultacredito.execptions.NotFoundException;
import br.com.eicon.consultacredito.producer.KafkaProducerFake;
import br.com.eicon.consultacredito.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

public class CreditoServiceTest {

    @Mock
    CreditoRepository repository;

    @Mock
    KafkaProducerFake producer;

    @InjectMocks
    CreditoService creditoService;

    private List<Credito> creditosMocks = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Credito credito1 = new Credito();
        credito1.setNumeroNfse("7891011");
        credito1.setNumeroCredito("789012");
        Credito credito2 = new Credito();
        credito2.setNumeroNfse("7891011");
        credito2.setNumeroCredito("123456");
        creditosMocks.add(credito1);
        creditosMocks.add(credito2);
    }

    @Test
    public void TestFindByNumeroNfseSuccess() throws NotFoundException {
        Mockito.when(repository.findByNumeroNfse(anyString())).thenReturn(creditosMocks);
        List<?> creditos = creditoService.findByNumeroNfse("7891011");
        assertTrue(creditos.size() == 2);
    }

    @Test
    public void testFindByNumeroCreditoSuccess() throws NotFoundException {
        Mockito.when(repository.findByNumeroCredito(anyString())).thenReturn(creditosMocks.get(0));
        CreditoDTO credito = creditoService.findByNumeroCredito("789012");
        assertTrue("789012".equals(credito.getNumeroCredito()));
    }

    @Test
    public void testFindByNumeroCreditoNotFoundException() throws NotFoundException {
        String numeroCredito = "789012";
        Mockito.when(repository.findByNumeroCredito(anyString())).thenReturn(null);
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> creditoService.findByNumeroCredito(numeroCredito));
        assertEquals("Crédito não localizado pelo número crédito: " + numeroCredito, ex.getMessage());
    }
}
