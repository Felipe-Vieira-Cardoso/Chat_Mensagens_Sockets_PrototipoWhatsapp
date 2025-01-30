package aula_03_sockets_multicliente;

import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrataCliente implements Runnable {

    private Socket soquete_cliente;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;

    private ArrayList<Mensagem> mensagens;
    private ArrayList<Mensagem> mensagensDiretas;
    private ArrayList<String> nomesconectados;
    private Serviços servicos = new Serviços();

    public TrataCliente(Socket soquete_cliente, ArrayList<Mensagem> mensagens, ArrayList<Mensagem> mensagensDiretas, ArrayList<String> nomesconectados) throws Exception {
        super();
        this.soquete_cliente = soquete_cliente;
        this.saida = new ObjectOutputStream(this.soquete_cliente.getOutputStream());
        this.entrada = new ObjectInputStream(this.soquete_cliente.getInputStream());
        this.mensagens = mensagens;
        this.nomesconectados = nomesconectados;
        this.mensagensDiretas = mensagensDiretas;
    }

    public void enviar_mensagem(Object mensagem) throws Exception {
        this.saida.writeObject(mensagem);
    }

    public Object receber_mensagem() throws Exception {
        return this.entrada.readObject();
    }

    public void finalizar() throws IOException {
        this.soquete_cliente.close();
    }

    @Override
    public void run() {
        try {
            Mensagem mensagem = (Mensagem) receber_mensagem();

            while (true) {
                System.out.println(mensagem.getTexto());

                if (mensagem.getTexto().equalsIgnoreCase("conectar")) {
                    
                    System.out.println("teste");

                    if (servicos.verificarUsuarioConectado(mensagem.getNome()) == false) {
                        servicos.gravarIp(soquete_cliente.getInetAddress().toString(), mensagem.getNome());
                        servicos.gravarIpConectados(soquete_cliente.getInetAddress().toString(), mensagem.getNome());
                        enviar_mensagem("conectado");
                    } else {
                        enviar_mensagem("jaconectado");
                    }

                } else if (mensagem.getTexto().equalsIgnoreCase("testarlogin")) {
                    String nome = "" + receber_mensagem();
                    String senha = "" + receber_mensagem();

                    boolean resultado = servicos.consultarLogin(nome, senha);

                    if (resultado) {
                        enviar_mensagem("logado");
                    } else {
                        enviar_mensagem("erro");
                    }

                } else if (mensagem.getTexto().equalsIgnoreCase("cadastrar")) {
                    String nome = "" + receber_mensagem();
                    String senha = "" + receber_mensagem();

                    boolean resultado = servicos.gravarCliente(nome, senha);

                    if (resultado) {
                        enviar_mensagem("cadastrado");
                    } else {
                        enviar_mensagem("erro");
                    }

                } else if (mensagem.getTexto().equalsIgnoreCase("listarnomesconectados")) {
                    this.nomesconectados = servicos.consultarNomesConectados();
                    enviar_mensagem(nomesconectados);

                } else if (mensagem.getTexto().equalsIgnoreCase("listarMensagem")) {
                    this.mensagens = servicos.consultarMensagens();
                    System.out.println("enviando - " + this.mensagens);
                    enviar_mensagem(this.mensagens);

                } else if (mensagem.getTexto().equalsIgnoreCase("sair")) {
                    System.out.println("finalizar");
                    servicos.removeIP(mensagem.getNome());
                    finalizar();
                    break;

                } else if (mensagem.getTexto().equalsIgnoreCase("listarMensagemDiretas")) {
                    this.mensagensDiretas = servicos.consultarMensagemDireta(mensagem.getNome());
                    System.out.println(mensagensDiretas);
                    System.out.println("enviando...");
                    enviar_mensagem(mensagensDiretas);
                    System.out.println("enviado...");

                } else if (mensagem.getTexto().equalsIgnoreCase("enviarMensagemDiretas")) {
                    mensagem = (Mensagem) receber_mensagem();
                    String destRemet[] = mensagem.getNome().split(",");

                    servicos.gravarMensagens(destRemet[0], mensagem.getTexto(), destRemet[1]);

                } else {
                    servicos.gravarMensagens(mensagem.getNome(), mensagem.getTexto(), "Geral");
                }

                mensagem = (Mensagem) receber_mensagem();
            }
        } catch (Exception ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
