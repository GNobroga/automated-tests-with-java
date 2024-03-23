package br.com.gabriel.test.domain.enums;

public enum PessoaGenero {
    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private final String valor;

    private PessoaGenero(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
