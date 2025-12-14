package br.com.eicon.consultacredito.rest;

import br.com.eicon.consultacredito.execptions.NotFoundException;
import br.com.eicon.consultacredito.service.CreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // Atenção: só estou usando CORS(*) para testes localmente.
@RequestMapping("/api/creditos")
public class CreditoRest {

    private CreditoService creditoService;

    public CreditoRest(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<?> buscarCreditoPorNumeroNfse(@PathVariable String numeroNfse) {
        try {
            return new ResponseEntity<>(creditoService.findByNumeroNfse(numeroNfse), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<?> buscarCreditoPorNumeroCredito(@PathVariable String numeroCredito) {
        try {
            return new ResponseEntity<>(creditoService.findByNumeroCredito(numeroCredito), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
