package aula_03_sockets_multicliente;

import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class TelaCadastro extends javax.swing.JFrame {

    private static Cliente cliente;

    public TelaCadastro(Cliente cliente) {
        initComponents();
        this.cliente = cliente;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomeCadastroTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        senhaCadastroTxt = new javax.swing.JTextField();
        concluirBtn = new javax.swing.JButton();
        voltarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 0));
        jLabel1.setText("Cadastro");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 255, 0));
        jLabel2.setText("Nome:");

        nomeCadastroTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 0));
        jLabel3.setText("Senha:");

        senhaCadastroTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        concluirBtn.setBackground(new java.awt.Color(0, 0, 0));
        concluirBtn.setForeground(new java.awt.Color(51, 255, 0));
        concluirBtn.setText("Concluir");
        concluirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                concluirBtnActionPerformed(evt);
            }
        });

        voltarBtn.setBackground(new java.awt.Color(0, 0, 0));
        voltarBtn.setForeground(new java.awt.Color(51, 255, 0));
        voltarBtn.setText("Voltar");
        voltarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(nomeCadastroTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(senhaCadastroTxt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(283, 283, 283))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(concluirBtn)
                        .addGap(307, 307, 307))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(voltarBtn)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeCadastroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaCadastroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(concluirBtn)
                .addGap(43, 43, 43)
                .addComponent(voltarBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void concluirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_concluirBtnActionPerformed
        Mensagem mensagemCadastrar = new Mensagem(nomeCadastroTxt.getText(), "cadastrar");

        try {
            this.cliente.enviar_mensagem(mensagemCadastrar);
            this.cliente.enviar_mensagem(nomeCadastroTxt.getText());
            this.cliente.enviar_mensagem(senhaCadastroTxt.getText());

            String msgRecebida = "" + cliente.receber_mensagem();

            if (msgRecebida.equalsIgnoreCase("cadastrado")) {
                TelaLogin telalogin = new TelaLogin();
                telalogin.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                telalogin.setLocationRelativeTo(null);
                telalogin.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Confira suas informações!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao acessar!");
        }
    }//GEN-LAST:event_concluirBtnActionPerformed

    private void voltarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarBtnActionPerformed
        TelaLogin tela = new TelaLogin();
        tela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_voltarBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro(cliente).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton concluirBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nomeCadastroTxt;
    private javax.swing.JTextField senhaCadastroTxt;
    private javax.swing.JButton voltarBtn;
    // End of variables declaration//GEN-END:variables
}
