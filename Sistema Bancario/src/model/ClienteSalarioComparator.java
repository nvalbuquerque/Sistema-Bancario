package model;

import java.util.Comparator;

public class ClienteSalarioComparator implements Comparator<Cliente> {
    @Override
    public int compare(Cliente c1, Cliente c2) {
        double saldo1 = (c1.getConta() != null) ? c1.getConta().getSaldo() : 0.0;
        double saldo2 = (c2.getConta() != null) ? c2.getConta().getSaldo() : 0.0;
        
        return Double.compare(saldo2, saldo1); 
    }
}