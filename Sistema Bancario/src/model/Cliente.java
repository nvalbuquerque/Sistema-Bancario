package model;

public class Cliente implements Comparable<Cliente> {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private String endereco;
    private Conta conta;

    public Cliente(String nome, String sobrenome, String rg, String cpf, String endereco) {
        setNome(nome);
        setSobrenome(sobrenome);
        setRg(rg);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public static void validarCpf(String cpf) {
        if (cpf == null) throw new IllegalArgumentException("CPF não pode ser nulo.");
        String apenasDigitos = cpf.replaceAll("\\D", "");
        if (!apenasDigitos.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos.");
        }
    }

    public static void validarRg(String rg) {
        if (rg == null) throw new IllegalArgumentException("RG não pode ser nulo.");
        String apenasDigitos = rg.replaceAll("\\D", "");
        if (!apenasDigitos.matches("\\d{7,10}")) {
            throw new IllegalArgumentException("RG deve conter apenas números e ter entre 7 e 10 dígitos.");
        }
    }

    // setters
    public void setCpf(String cpf) {
        validarCpf(cpf);
        this.cpf = cpf.replaceAll("\\D", "");
    }

    public void setRg(String rg) {
        validarRg(rg);
        this.rg = rg.replaceAll("\\D", "");
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome é obrigatório.");
        this.nome = nome.trim();
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = (sobrenome == null) ? "" : sobrenome.trim();
    }

    public void setEndereco(String endereco) {
        this.endereco = (endereco == null) ? "" : endereco.trim();
    }

    // getters
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getRg() { return rg; }         // já salvo sem formatação
    public String getCpf() { return cpf; }       // já salvo sem formatação
    public String getEndereco() { return endereco; }
    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }

    @Override
    public int compareTo(Cliente outro) {
        int comparacaoNome = this.nome.compareToIgnoreCase(outro.nome);
        if (comparacaoNome != 0) return comparacaoNome;
        return this.sobrenome.compareToIgnoreCase(outro.sobrenome);
    }
}
