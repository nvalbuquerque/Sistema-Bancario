package model;

public abstract class Conta implements ContaInterface {

    protected Cliente dono;
    protected int numero;
    protected double saldo;

    public Conta(Cliente dono, int numero, double depositoInicial) {
        if (depositoInicial < 0) {
            throw new IllegalArgumentException("Depósito inicial não pode ser negativo.");
        }
        this.dono = dono;
        this.numero = numero;
        this.saldo = depositoInicial;
    }

    @Override
    public boolean deposita(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        saldo += valor;
        return true;
    }

    @Override
    public boolean saca(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }

        if (saldo < valor) {
            throw new IllegalStateException("Saldo insuficiente para saque.");
        }

        saldo -= valor;
        return true;
    }

    @Override
    public Cliente getDono() {
        return dono;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
}
