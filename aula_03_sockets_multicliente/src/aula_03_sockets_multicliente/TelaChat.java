package aula_03_sockets_multicliente;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class TelaChat extends javax.swing.JFrame {

    private static Cliente cliente;
    private static String nome;
    private static SwingWorker atualizadorDeUsuariosConectados;
    private static SwingWorker atualizadorDeMensagensSwing;
    private DefaultListModel<String> usuariosconectadosModel;
    private ArrayList<Mensagem> mensagensGerais;
    private ArrayList<Mensagem> msgsDiretasArray;
    private ButtonGroup grupoBtn;
    private boolean flag;
    private boolean controlaExecucaoSwing;

    public TelaChat(String nome, Cliente cliente) {
        initComponents();
        this.nome = nome;
        this.cliente = cliente;
        this.controlaExecucaoSwing = false;
        msgsTxtArea.setEditable(false);

        this.grupoBtn = new ButtonGroup();
        grupoBtn.add(msgDiretasRadioBtn);
        grupoBtn.add(msgGeraisRadioBtn);

        this.usuariosconectadosModel = new DefaultListModel<>();
        this.usuariosConectadosList.setModel(this.usuariosconectadosModel);

        msgDestinatarioTxt.setEnabled(false);

        AtualizaUsuariosConectados();
        AtualizaMensagens();
        
        atualizadorDeMensagensSwing.execute();
        atualizadorDeUsuariosConectados.execute();
    }

    public void AtualizaUsuariosConectados() {
        atualizadorDeUsuariosConectados = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    Mensagem mensagemListaConectados = new Mensagem(nome, "listarnomesconectados");
                    if (flag == false) {
                        flag = true;
                        cliente.enviar_mensagem(mensagemListaConectados);

                        ArrayList<String> nomes = new ArrayList<>();
                        nomes = (ArrayList<String>) cliente.receber_mensagem();
                        flag = false;

                        if (!usuariosconectadosModel.equals(nomes)) {
                            usuariosconectadosModel.removeAllElements();
                            usuariosconectadosModel.addAll(nomes);
                        }
                        Thread.sleep(3000);
                    }
                }
            }
        };        
    }

    public void AtualizaMensagens() {
        this.atualizadorDeMensagensSwing = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Mensagem mensagem;
                System.out.println(flag);

                while (true) {
                    System.out.println("rodouWhile");
                    if (flag == false && msgGeraisRadioBtn.isSelected()) {
                        flag = true;

                        mensagem = new Mensagem(nome, "listarMensagem");
                        cliente.enviar_mensagem(mensagem);
                        System.out.println("requisicaoGeraisFeita");

                        ArrayList<Mensagem> novasMensagensGerais = new ArrayList<>();
                        novasMensagensGerais = (ArrayList<Mensagem>) cliente.receber_mensagem();
                        System.out.println("requisicaoGeraisRecebida");

                        if (!novasMensagensGerais.equals(mensagensGerais)) {
                            System.out.println("comparacaofeita");
                            mensagensGerais = novasMensagensGerais;
                            String msgsGeraisString = "";
                            for (int i = 0; i < mensagensGerais.size(); i++) {
                                msgsGeraisString = msgsGeraisString + "\n" + mensagensGerais.get(i);
                            }
                            msgsTxtArea.setText(msgsGeraisString);
                        }
                        flag = false;

                        Thread.sleep(1000);
                    }
                    if (flag == false && msgDiretasRadioBtn.isSelected()) {
                        flag = true;

                        mensagem = new Mensagem(nome, "listarMensagemDiretas");
                        cliente.enviar_mensagem(mensagem);
                        System.out.println("requisicao realizada");

                        ArrayList<Mensagem> novasMensagensDiretas = new ArrayList<>();
                        novasMensagensDiretas = (ArrayList<Mensagem>) cliente.receber_mensagem();
                        System.out.println("requisicao recebida");
                        System.out.println(novasMensagensDiretas);

                        if (!novasMensagensDiretas.equals(msgsDiretasArray)) {
                            System.out.println("comparacao feita");
                            msgsDiretasArray = novasMensagensDiretas;
                            String msgsDiretasString = "";
                            for (int i = 0; i < msgsDiretasArray.size(); i++) {
                                msgsDiretasString = msgsDiretasString + "\n" + msgsDiretasArray.get(i);
                            }
                            msgsTxtArea.setText(msgsDiretasString);
                        }
                        System.out.println("flagColocadaFalsa");

                        flag = false;

                        Thread.sleep(1000);
                    }
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usuariosConectadosList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        msgDiretasRadioBtn = new javax.swing.JRadioButton();
        msgGeraisRadioBtn = new javax.swing.JRadioButton();
        desconectarBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        msgsTxtArea = new javax.swing.JTextArea();
        msgEnviarTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        enviarMsgDiretaCheck = new javax.swing.JCheckBox();
        msgDestinatarioTxt = new javax.swing.JTextField();
        enviarBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setViewportView(usuariosConectadosList);

        jLabel1.setForeground(new java.awt.Color(102, 255, 0));
        jLabel1.setText("Usuários Online:");

        msgDiretasRadioBtn.setText("Mensagens Diretas");
        msgDiretasRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgDiretasRadioBtnActionPerformed(evt);
            }
        });

        msgGeraisRadioBtn.setSelected(true);
        msgGeraisRadioBtn.setText("Mensagens Gerais");
        msgGeraisRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgGeraisRadioBtnActionPerformed(evt);
            }
        });

        desconectarBtn.setBackground(new java.awt.Color(0, 0, 0));
        desconectarBtn.setForeground(new java.awt.Color(51, 255, 0));
        desconectarBtn.setText("Desconectar");
        desconectarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconectarBtnActionPerformed(evt);
            }
        });

        msgsTxtArea.setColumns(20);
        msgsTxtArea.setRows(5);
        jScrollPane2.setViewportView(msgsTxtArea);

        jLabel2.setForeground(new java.awt.Color(153, 255, 0));
        jLabel2.setText("Mensagem:");

        enviarMsgDiretaCheck.setText("Enviar Msg Direta");
        enviarMsgDiretaCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMsgDiretaCheckActionPerformed(evt);
            }
        });

        enviarBtn.setBackground(new java.awt.Color(0, 0, 0));
        enviarBtn.setForeground(new java.awt.Color(153, 255, 0));
        enviarBtn.setText("Enviar");
        enviarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarBtnActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(153, 255, 0));
        jLabel3.setText("Destinatário:");

        jLabel4.setForeground(new java.awt.Color(153, 255, 0));
        jLabel4.setText("CHAT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desconectarBtn)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(enviarMsgDiretaCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(msgDestinatarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(enviarBtn))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(msgEnviarTxt))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(14, 14, 14)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(msgDiretasRadioBtn)
                            .addComponent(msgGeraisRadioBtn))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(desconectarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(msgDiretasRadioBtn)
                        .addGap(18, 18, 18)
                        .addComponent(msgGeraisRadioBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msgEnviarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(enviarMsgDiretaCheck)
                            .addComponent(msgDestinatarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enviarBtn))
                        .addContainerGap(18, Short.MAX_VALUE))))
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

    private void msgDiretasRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgDiretasRadioBtnActionPerformed
        if (controlaExecucaoSwing == false) {
            atualizadorDeMensagensSwing.execute();
            controlaExecucaoSwing = true;
        }
    }//GEN-LAST:event_msgDiretasRadioBtnActionPerformed

    private void msgGeraisRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgGeraisRadioBtnActionPerformed
        if (controlaExecucaoSwing == false) {
            atualizadorDeMensagensSwing.execute();
            controlaExecucaoSwing = true;
        }
    }//GEN-LAST:event_msgGeraisRadioBtnActionPerformed

    private void enviarMsgDiretaCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarMsgDiretaCheckActionPerformed
        if (enviarMsgDiretaCheck.isSelected()) {
            msgDestinatarioTxt.setEnabled(true);
        }else{
            msgDestinatarioTxt.setEnabled(false);
        }
    }//GEN-LAST:event_enviarMsgDiretaCheckActionPerformed

    private void desconectarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarBtnActionPerformed
        try {
            Mensagem mensagemSair = new Mensagem(this.nome, "sair");
            cliente.enviar_mensagem(mensagemSair);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao desconectar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_desconectarBtnActionPerformed

    private void enviarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarBtnActionPerformed
        if (!msgEnviarTxt.getText().isEmpty()) {
            if (enviarMsgDiretaCheck.isSelected()) {
                if (!msgDestinatarioTxt.getText().isEmpty()) {
                    try {
                        Mensagem mensagemEnviarDireta = new Mensagem(this.nome, "enviarMensagemDiretas");
                        cliente.enviar_mensagem(mensagemEnviarDireta);

                        Mensagem mensagemEnviar = new Mensagem(this.nome + "," + msgDestinatarioTxt.getText(), msgEnviarTxt.getText());
                        cliente.enviar_mensagem(mensagemEnviar);

                        msgEnviarTxt.setText("");
                        msgDestinatarioTxt.setText("");

                    } catch (Exception ex) {
                        System.out.println("mensagem direta nao enviada");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha o campo do destinatário.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (enviarMsgDiretaCheck.isSelected() == false) {
                try {
                    Mensagem mensagemEnviarGeral = new Mensagem(this.nome, msgEnviarTxt.getText());
                    cliente.enviar_mensagem(mensagemEnviarGeral);

                    msgEnviarTxt.setText("");

                } catch (Exception ex) {
                    System.out.println("mensagem geral nao enviada");
                }
            }
        } else if (msgEnviarTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo de mensagem.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_enviarBtnActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaChat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaChat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaChat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaChat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaChat(nome, cliente).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton desconectarBtn;
    private javax.swing.JButton enviarBtn;
    private javax.swing.JCheckBox enviarMsgDiretaCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField msgDestinatarioTxt;
    private javax.swing.JRadioButton msgDiretasRadioBtn;
    private javax.swing.JTextField msgEnviarTxt;
    private javax.swing.JRadioButton msgGeraisRadioBtn;
    private javax.swing.JTextArea msgsTxtArea;
    private javax.swing.JList<String> usuariosConectadosList;
    // End of variables declaration//GEN-END:variables
}
