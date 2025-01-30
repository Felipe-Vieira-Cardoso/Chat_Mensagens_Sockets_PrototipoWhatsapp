package aula_03_sockets_multicliente;

import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Cliente {

    private Socket soquete;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
    private ArrayList<Mensagem> mensagens;
    private static ArrayList<Mensagem> listaMensagem = new ArrayList<>();
    private static String nome;

    public Cliente(String endereco, int porta) throws Exception {
        super();
        this.soquete = new Socket(endereco, porta);
        this.saida = new ObjectOutputStream(this.soquete.getOutputStream());
        this.entrada = new ObjectInputStream(this.soquete.getInputStream());
    }

    public void enviar_mensagem(Object mensagem) throws Exception {
        this.saida.writeObject(mensagem);
    }

    public Object receber_mensagem() throws Exception {
        return this.entrada.readObject();
    }

    public void finalizar() throws IOException {
        this.soquete.close();
    }

    public static void main(String[] args) throws Exception {
        /*
        Scanner teclado = new Scanner(System.in);

        System.out.println("Informe seu nome: ");
        nome = teclado.next();

        System.out.println("1 - entrar\n"
                + "2 - sair\n"
                + "3 - enviar mensagem\n"
                + "4 - listar os usuarios conectados\n"
                + "5 - listar as mensagens\n"
                + "6 - listar mensagens diretas\n"
                + "7 - enviar mensagens diretas\n");

        int opcao = teclado.nextInt();

        while (opcao != 1) {
            System.out.println("Conecte ao servidor primeiro");
            opcao = teclado.nextInt();
        }

        Cliente cliente = new Cliente("10.90.37.91", 15500);
        Mensagem mensagemConexao = new Mensagem(nome, "conectar");
        cliente.enviar_mensagem(mensagemConexao);

        String msgRecebidaConexao = "" + cliente.receber_mensagem();

        while (msgRecebidaConexao.equalsIgnoreCase("trocarnome")) {
            System.out.println("Informe outro nome: ");
            String novoNome = teclado.next();

            nome = novoNome;

            Mensagem mensagemTrocaNome = new Mensagem(novoNome, "conectar");

            cliente.enviar_mensagem(mensagemTrocaNome);
            msgRecebidaConexao = "" + cliente.receber_mensagem();
        }

        while (opcao != 2) {
            System.out.println("1 - entrar\n"
                    + "2 - sair\n"
                    + "3 - enviar mensagem\n"
                    + "4 - listar os usuarios conectados\n"
                    + "5 - listar as mensagens\n"
                    + "6 - listar mensagens diretas\n"
                    + "7 - enviar mensagens diretas\n");

            opcao = teclado.nextInt();

            if (opcao == 1) {
                System.out.println("Voce ja esta conectado!");
                System.out.println();
            }
            if (opcao == 3) {
                System.out.println("Informe a mensagem: ");
                teclado.skip("\n");
                String mens = teclado.nextLine();
                Mensagem mensagem = new Mensagem(nome, mens);
                cliente.enviar_mensagem(mensagem);
                System.out.println(cliente.receber_mensagem());
            }
            if (opcao == 4) {
                ArrayList<String> listaIP = new ArrayList<>();
                Mensagem mensagem = new Mensagem(nome, "listarIP");

                cliente.enviar_mensagem(mensagem);
                listaIP = (ArrayList<String>) cliente.receber_mensagem();

                System.out.println(listaIP);

            }
            if (opcao == 5) {
                Mensagem pedido = new Mensagem(nome, "listarMensagem");

                cliente.enviar_mensagem(pedido);

                listaMensagem = (ArrayList<Mensagem>) cliente.receber_mensagem();

                for (int i = 0; i < listaMensagem.size(); i++) {
                    System.out.println(listaMensagem.get(i));
                }
                System.out.println();
            }

            if (opcao == 6) {
                Mensagem pedido = new Mensagem(nome, "listarMensagemDiretas");

                cliente.enviar_mensagem(pedido);

                ArrayList<Mensagem> mensagensDiretas = new ArrayList<>();

                mensagensDiretas = (ArrayList<Mensagem>) cliente.receber_mensagem();

                for (int i = 0; i < mensagensDiretas.size(); i++) {
                    System.out.println(mensagensDiretas.get(i));
                }
                System.out.println();
            }

            if (opcao == 7) {
                Mensagem pedido = new Mensagem(nome, "enviarMensagemDiretas");

                System.out.println("Informe o destinatario: ");
                String destinatario = teclado.next();

                System.out.println("Informe a mensagem: ");
                teclado.skip("\n");
                String mens = teclado.nextLine();

                cliente.enviar_mensagem(pedido);

                Mensagem mensagem = new Mensagem(nome + "," + destinatario, mens);
                cliente.enviar_mensagem(mensagem);
            }
        }
        Mensagem mensagemSair = new Mensagem(nome, "sair");
        cliente.enviar_mensagem(mensagemSair);
        cliente.finalizar();
        */
    }
}
