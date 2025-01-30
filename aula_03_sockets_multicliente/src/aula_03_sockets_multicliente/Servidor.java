package aula_03_sockets_multicliente;

import java.net.Socket;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.io.IOException;

public class Servidor {

    private ServerSocket soquete_servidor;
    private ArrayList<Mensagem> mensagens;
    private ArrayList<Mensagem> mensagensDiretas;
    private static ArrayList<String> nomesconectados;

    public Servidor(int porta) throws Exception {
        super();
        this.soquete_servidor = new ServerSocket(porta);
        this.mensagens = new ArrayList<Mensagem>();
        this.nomesconectados = new ArrayList<>();
    }

    public void finalizar() throws IOException {
        this.soquete_servidor.close();
    }

    public static void main(String[] args) throws Exception {
        Servidor servidor = new Servidor(15500);
        Socket soqueteCliente = null;
        while (true) {
            try {
                soqueteCliente = servidor.soquete_servidor.accept();
                new Thread(new TrataCliente(soqueteCliente, servidor.mensagens, servidor.mensagensDiretas, servidor.nomesconectados)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
