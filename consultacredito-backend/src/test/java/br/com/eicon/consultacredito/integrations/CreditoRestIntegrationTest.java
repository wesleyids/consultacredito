package br.com.eicon.consultacredito.integrations;

import br.com.eicon.consultacredito.dtos.CreditoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Profile("testing")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditoRestIntegrationTest {

    @LocalServerPort
    private int servidorPorta;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testBuscarCreditoPorNumeroNfseSuccess() {
        String numeroNfseExistenteNaBase = "7891011";
        ResponseEntity<List<CreditoDTO>> response = restTemplate.exchange(
                String.format("http://localhost:%d/api/creditos/%s", servidorPorta, numeroNfseExistenteNaBase),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CreditoDTO>>() {
                });
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().size() == 2);
    }

    @Test
    public void testbuscarCreditoPorNumeroCreditoSuccess() {
        String numeroCreditoExistenteNaBase = "123456";
        ResponseEntity<String> response = restTemplate.getForEntity(String
                .format("http://localhost:%d/api/creditos/credito/%s", servidorPorta, numeroCreditoExistenteNaBase),
                String.class);
        System.out.println("testbuscarCreditoPorNumeroCreditoSuccess: " + response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testbuscarCreditoPorNumeroCreditoNotFound() {
        String numeroCreditoInexistenteNaBase = "123457";
        ResponseEntity<String> response = restTemplate.getForEntity(String
                .format("http://localhost:%d/api/creditos/credito/%s", servidorPorta, numeroCreditoInexistenteNaBase),
                String.class);
        System.out.println("testbuscarCreditoPorNumeroCreditoNotFound: " + response.getBody());
        assertEquals(404, response.getStatusCodeValue());
    }
}
