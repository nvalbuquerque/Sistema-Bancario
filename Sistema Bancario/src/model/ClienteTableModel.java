package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    private List<Cliente> clientesOriginais;
    final private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Endereço", "Saldo", "Tipo Conta", "Editar", "Vincular", "Conta"};

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
        this.clientesOriginais = new ArrayList<>();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        this.clientesOriginais = new ArrayList<>(clientes);
        fireTableDataChanged();
    }

    public Cliente getCliente(int linha) {
        if (linha >= 0 && linha < clientes.size()) {
            return clientes.get(linha);
        }
        return null;
    }

    // FILTROS

    public void filtrarPorNome(String nomeBusca) {
        if (nomeBusca == null || nomeBusca.trim().isEmpty()) {
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String buscaEmMinusculo = nomeBusca.toLowerCase();
            for (Cliente c : this.clientesOriginais) {
                if (c.getNome().toLowerCase().contains(buscaEmMinusculo)) {
                    clientesFiltrados.add(c);
                }
            }
            this.clientes = clientesFiltrados;
        }
        fireTableDataChanged();
    }

    public void filtrarPorCpf(String cpfBusca) {
        if (cpfBusca == null || cpfBusca.trim().isEmpty()) {
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String busca = cpfBusca.replaceAll("\\s+", "");
            for (Cliente c : this.clientesOriginais) {
                if (c.getCpf().replaceAll("\\s+", "").contains(busca)) {
                    clientesFiltrados.add(c);
                }
            }
            this.clientes = clientesFiltrados;
        }
        fireTableDataChanged();
    }

    public void filtrarPorSobrenome(String sobrenomeBusca) {
        if (sobrenomeBusca == null || sobrenomeBusca.trim().isEmpty()) {
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String buscaEmMinusculo = sobrenomeBusca.toLowerCase();
            for (Cliente c : this.clientesOriginais) {
                if (c.getSobrenome().toLowerCase().contains(buscaEmMinusculo)) {
                    clientesFiltrados.add(c);
                }
            }
            this.clientes = clientesFiltrados;
        }
        fireTableDataChanged();
    }

    public void filtrarPorRg(String rgBusca) {
        if (rgBusca == null || rgBusca.trim().isEmpty()) {
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String busca = rgBusca.replaceAll("\\s+", "");
            for (Cliente c : this.clientesOriginais) {
                if (c.getRg().replaceAll("\\s+", "").contains(busca)) {
                    clientesFiltrados.add(c);
                }
            }
            this.clientes = clientesFiltrados;
        }
        fireTableDataChanged();
    }

    public void filtrarClientePorLinha(int linha) {
        if (linha >= 0 && linha < clientes.size()) {
            Cliente c = clientes.get(linha);
            List<Cliente> clienteFiltrado = new ArrayList<>();
            clienteFiltrado.add(c);
            this.clientes = clienteFiltrado;
            System.out.println("Filtrou cliente: " + c.getCpf());
        }
    }

    // MÉTODOS 

    public void recarregar() {
        this.clientes = new ArrayList<>(this.clientesOriginais);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return c.getNome();
            case 1: return c.getSobrenome();
            case 2: return c.getRg();
            case 3: return c.getCpf();
            case 4: return c.getEndereco();
            case 5: 
                if (c.getConta() != null) {
                    return String.format("R$ %.2f", c.getConta().getSaldo());
                }
                return "Sem conta";
            case 6: 
                if (c.getConta() != null) {
                    if (c.getConta() instanceof ContaCorrente) {
                        return "Corrente";
                    } else if (c.getConta() instanceof ContaInvestimento) {
                        return "Investimento";
                    }
                }
                return "Sem conta";
            case 7: return "Editar";
            case 8: return "Vincular";  
            case 9: return "Operar";
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 7; 
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex >= 7) {
            return Object.class;
        }
        return String.class;
    }
}