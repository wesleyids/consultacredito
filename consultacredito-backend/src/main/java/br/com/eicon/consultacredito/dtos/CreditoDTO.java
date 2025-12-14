package br.com.eicon.consultacredito.dtos;

import br.com.eicon.consultacredito.entities.Credito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditoDTO {
    private String numeroCredito;
    private String numeroNfse;
    private LocalDate dataConstituicao;
    private BigDecimal valorlssqn;
    private String tipoCredito;
    private boolean simplesNacional;
    private BigDecimal aliquota;
    private BigDecimal valorFaturado;
    private BigDecimal valorDeducao;
    private BigDecimal baseCalculo;

    private CreditoDTO() {
    }

    // Atenção: aqui posso utilizar também o Design Patterns Builder
    public static CreditoDTO of(Credito credito) {
        CreditoDTO dto = new CreditoDTO();
        dto.setNumeroCredito(credito.getNumeroCredito());
        dto.setNumeroNfse(credito.getNumeroNfse());
        dto.setDataConstituicao(credito.getDataConstituicao());
        dto.setValorlssqn(credito.getValorlssqn());
        dto.setTipoCredito(credito.getTipoCredito());
        dto.setSimplesNacional(credito.getSimplesNacional());
        dto.setAliquota(credito.getAliquota());
        dto.setValorFaturado(credito.getValorFaturado());
        dto.setValorDeducao(credito.getValorDeducao());
        dto.setBaseCalculo(credito.getBaseCalculo());
        return dto;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public String getNumeroNfse() {
        return numeroNfse;
    }

    public void setNumeroNfse(String numeroNfse) {
        this.numeroNfse = numeroNfse;
    }

    public LocalDate getDataConstituicao() {
        return dataConstituicao;
    }

    public void setDataConstituicao(LocalDate dataConstituicao) {
        this.dataConstituicao = dataConstituicao;
    }

    public BigDecimal getValorlssqn() {
        return valorlssqn;
    }

    public void setValorlssqn(BigDecimal valorlssqn) {
        this.valorlssqn = valorlssqn;
    }

    public String getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public boolean isSimplesNacional() {
        return simplesNacional;
    }

    public void setSimplesNacional(boolean simplesNacional) {
        this.simplesNacional = simplesNacional;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getValorFaturado() {
        return valorFaturado;
    }

    public void setValorFaturado(BigDecimal valorFaturado) {
        this.valorFaturado = valorFaturado;
    }

    public BigDecimal getValorDeducao() {
        return valorDeducao;
    }

    public void setValorDeducao(BigDecimal valorDeducao) {
        this.valorDeducao = valorDeducao;
    }

    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(BigDecimal baseCalculo) {
        this.baseCalculo = baseCalculo;
    }
}
