package br.com.fecaf.view;

import br.com.fecaf.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroCliente extends JFrame {

    // Campos de texto
    private JTextField txtNome = new JTextField();
    private JTextField txtCpf = new JTextField();
    private JTextField txtRg = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtIdade = new JTextField();

    // Labels
    private JLabel lblNome = new JLabel("Nome:");
    private JLabel lblCpf = new JLabel("CPF:");
    private JLabel lblRg = new JLabel("RG:");
    private JLabel lblEmail = new JLabel("E-mail:");
    private JLabel lblIdade = new JLabel("Idade:");

    // Botões
    private JButton buttonCadastro = new JButton("Cadastrar");
    private JButton buttonLimpar = new JButton("Limpar");
    private JButton buttonSair = new JButton("Sair");

    // Tabela de clientes
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;

    public TelaCadastroCliente() {
        // Configuração da tela
        setTitle("Cadastro de Cliente");
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Labels e campos
        lblNome.setBounds(30, 30, 100, 25);
        txtNome.setBounds(120, 30, 200, 25);

        lblCpf.setBounds(350, 30, 100, 25);
        txtCpf.setBounds(420, 30, 200, 25);

        lblRg.setBounds(30, 70, 100, 25);
        txtRg.setBounds(120, 70, 200, 25);

        lblEmail.setBounds(350, 70, 100, 25);
        txtEmail.setBounds(420, 70, 200, 25);

        lblIdade.setBounds(30, 110, 100, 25);
        txtIdade.setBounds(120, 110, 100, 25);

        add(lblNome);
        add(txtNome);
        add(lblCpf);
        add(txtCpf);
        add(lblRg);
        add(txtRg);
        add(lblEmail);
        add(txtEmail);
        add(lblIdade);
        add(txtIdade);

        // Botões
        buttonCadastro.setBounds(100, 160, 120, 30);
        buttonLimpar.setBounds(250, 160, 120, 30);
        buttonSair.setBounds(400, 160, 120, 30);

        add(buttonCadastro);
        add(buttonLimpar);
        add(buttonSair);

        // Tabela
        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "CPF", "RG", "E-mail", "Idade"}, 0);
        tabelaClientes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.setBounds(30, 220, 640, 200);

        add(scrollPane);

        // Eventos dos botões
        buttonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        buttonLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        buttonSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela
            }
        });
    }

    private void cadastrarCliente() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim().replaceAll("[^0-9]", ""); // só números
        String rg = txtRg.getText().trim();
        String email = txtEmail.getText().trim();
        String idadeStr = txtIdade.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || rg.isEmpty() || email.isEmpty() || idadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        if (!isCPFValido(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido!");
            return;
        }

        try {
            int idade = Integer.parseInt(idadeStr);

            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setRg(rg);
            cliente.setEmail(email);
            cliente.setIdade(idade);

            // CPF formatado para exibição
            String cpfFormatado = formatarCPF(cpf);

            // Adiciona o cliente na tabela
            modeloTabela.addRow(new Object[]{
                    cliente.getNome(),
                    cpfFormatado,
                    cliente.getRg(),
                    cliente.getEmail(),
                    cliente.getIdade()
            });

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número válido!");
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtRg.setText("");
        txtEmail.setText("");
        txtIdade.setText("");
    }

    /**
     * Valida o CPF usando o algoritmo oficial
     */
    private boolean isCPFValido(String cpf) {
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false; // tamanho errado ou todos dígitos iguais
        }

        try {
            int soma = 0, resto;

            // primeiro dígito verificador
            for (int i = 1; i <= 9; i++) {
                soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
            }
            resto = (soma * 10) % 11;
            if ((resto == 10) || (resto == 11)) resto = 0;
            if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;

            // segundo dígito verificador
            soma = 0;
            for (int i = 1; i <= 10; i++) {
                soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
            }
            resto = (soma * 10) % 11;
            if ((resto == 10) || (resto == 11)) resto = 0;
            return resto == Integer.parseInt(cpf.substring(10, 11));

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Formata CPF para o padrão 000.000.000-00
     */
    private String formatarCPF(String cpf) {
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaCadastroCliente().setVisible(true);
        });
    }
}
