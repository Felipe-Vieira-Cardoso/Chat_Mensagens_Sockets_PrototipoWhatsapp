package aula_03_sockets_multicliente;

import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    private Cliente cliente;

    public TelaLogin() {
        initComponents();
        this.setLocationRelativeTo(null);

        try {
            this.cliente = new Cliente("10.90.37.91", 15500);
        } catch (Exception ex) {
            System.out.println("Erro ao conectar ao servidor!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nomeLoginTxt = new javax.swing.JTextField();
        acessarBtn = new javax.swing.JButton();
        cadastrarBtn = new javax.swing.JButton();
        senhaLoginTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 0));
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 255, 0));
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 0));
        jLabel3.setText("Senha:");

        nomeLoginTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        acessarBtn.setBackground(new java.awt.Color(0, 0, 0));
        acessarBtn.setForeground(new java.awt.Color(102, 255, 0));
        acessarBtn.setText("Acessar");
        acessarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acessarBtnActionPerformed(evt);
            }
        });

        cadastrarBtn.setBackground(new java.awt.Color(0, 0, 0));
        cadastrarBtn.setForeground(new java.awt.Color(102, 255, 0));
        cadastrarBtn.setText("Cadastrar");
        cadastrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarBtnActionPerformed(evt);
            }
        });

        senhaLoginTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acessarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(cadastrarBtn))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(nomeLoginTxt)
                            .addComponent(senhaLoginTxt))))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeLoginTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaLoginTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acessarBtn)
                    .addComponent(cadastrarBtn))
                .addContainerGap(84, Short.MAX_VALUE))
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

    private void acessarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acessarBtnActionPerformed
        Mensagem mensagem = new Mensagem(nomeLoginTxt.getText(), "testarlogin");

        try {
            cliente.enviar_mensagem(mensagem);
            this.cliente.enviar_mensagem(nomeLoginTxt.getText());
            this.cliente.enviar_mensagem(senhaLoginTxt.getText());

            String msgRecebida = "" + cliente.receber_mensagem();

            if (msgRecebida.equalsIgnoreCase("logado")) {
                Mensagem mensagemConectar = new Mensagem(nomeLoginTxt.getText(), "conectar");
                cliente.enviar_mensagem(mensagemConectar);
                String msgValidaConexao = (String) cliente.receber_mensagem();
                System.out.println(msgValidaConexao);

                if (msgValidaConexao.equalsIgnoreCase("conectado")) {
                    TelaChat telaChat = new TelaChat(nomeLoginTxt.getText(), this.cliente);
                    telaChat.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    telaChat.setLocationRelativeTo(null);
                    telaChat.setVisible(true);
                    this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this, "Usuário já conectado!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Confira suas informações!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao acessar!");
        }
    }//GEN-LAST:event_acessarBtnActionPerformed

    private void cadastrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarBtnActionPerformed
        TelaCadastro telaCadastro = new TelaCadastro(cliente);
        telaCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        telaCadastro.setLocationRelativeTo(null);
        telaCadastro.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_cadastrarBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acessarBtn;
    private javax.swing.JButton cadastrarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nomeLoginTxt;
    private javax.swing.JTextField senhaLoginTxt;
    // End of variables declaration//GEN-END:variables
}
