package aula_03_sockets_multicliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Serviços {

    private Connection c;

    public Serviços() {
        this.c = FactoryPostgres.getConexaoPostgres();
    }

    public ArrayList<String> consultarNomesConectados() {
        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT nome FROM usuariosconectados";

        try (PreparedStatement consulta = c.prepareStatement(sql)) {
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                String nome = resultado.getString("nome");
                nomes.add(nome);
            }

            return nomes;
        } catch (SQLException ex) {
            System.out.println("Erro ConsultarNomesConectados");
            return null;
        }
    }

    public ArrayList<Mensagem> consultarMensagens() {
        ArrayList<Mensagem> todasMensagens = new ArrayList<>();
        String sql = "SELECT remetente, mensagem FROM mensagens WHERE destinatario = ?";
        
        String geral = "Geral";

        try (PreparedStatement consulta = c.prepareStatement(sql)) {
            consulta.setString(1, geral);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                String nome = resultado.getString("remetente");
                String texto = resultado.getString("mensagem");
                Mensagem tempMens = new Mensagem(nome, texto);
                todasMensagens.add(tempMens);
            }

            return todasMensagens;
        } catch (SQLException ex) {
            System.out.println("Erro ConsultarTodasMensagens");
            return null;
        }
    }

    public ArrayList<Mensagem> consultarMensagemDireta(String nome) {
        ArrayList<Mensagem> todasMensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagens where destinatario = ?";

        try (PreparedStatement consulta = c.prepareStatement(sql)) {
            consulta.setString(1, nome);
            System.out.println(nome);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                String remetente = resultado.getString("remetente");
                String texto = resultado.getString("mensagem");
                Mensagem tempMens = new Mensagem(remetente, texto);
                todasMensagens.add(tempMens);
            }

            return todasMensagens;
        } catch (SQLException ex) {
            System.out.println("Erro ConsultarMensagemDireta" + ex.getMessage());
            return null;
        }
    }

    public boolean gravarIp(String ip, String nome) {
        String sql = "INSERT INTO usuario(ip, nome) VALUES (?, ?)";

        try (PreparedStatement insercao = c.prepareStatement(sql)) {
            insercao.setString(1, ip);
            insercao.setString(2, nome);

            insercao.execute();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean gravarIpConectados(String ip, String nome) {
        String sql = "INSERT INTO usuariosconectados(ip, nome) VALUES (?, ?)";

        try (PreparedStatement insercao = c.prepareStatement(sql)) {

            insercao.setString(1, ip);
            insercao.setString(2, nome);

            insercao.execute();

            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de Insercao de IP");
            return false;
        }
    }

    public boolean gravarMensagens(String remetente, String texto, String destinatario) {
        String sql = "INSERT INTO mensagens(remetente, mensagem, destinatario) VALUES (?, ?, ?)";

        try (PreparedStatement insercao = c.prepareStatement(sql)) {
            insercao.setString(1, remetente);
            insercao.setString(2, texto);
            insercao.setString(3, destinatario);

            insercao.execute();

            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de Insercao de Mensagem" + ex.getMessage());
            return false;
        }
    }

    public void removeIP(String nome) {
        String sql = "DELETE FROM usuariosconectados WHERE nome = ?";

        try (PreparedStatement insercao = c.prepareStatement(sql)) {

            insercao.setString(1, nome);

            insercao.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao apagar IP" + ex.getMessage());
        }
    }

    public boolean verificarUsuarioConectado(String nome) {
        String sql = "SELECT * FROM usuariosconectados where nome = ?";

        try (PreparedStatement consulta = c.prepareStatement(sql)) {
            consulta.setString(1, nome);
            System.out.println(nome);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                String ip = resultado.getString("ip");
                String nomeResultado = resultado.getString("nome");
                return true;
            }

            return false;
        } catch (SQLException ex) {
            System.out.println("Erro ConsultarMensagemDireta" + ex.getMessage());
            return false;
        }
    }

    public boolean consultarLogin(String nome, String senha) {
        String sql = "SELECT * FROM cadastro where nome = ? AND senha = ?";

        try (PreparedStatement consulta = c.prepareStatement(sql)) {
            consulta.setString(1, nome);
            consulta.setString(2, senha);

            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("Erro ConsultarMensagemDireta" + ex.getMessage());
            return false;
        }
    }

    public boolean gravarCliente(String nome, String senha) {
        String sql = "INSERT INTO cadastro(nome, senha) VALUES (?,?) returning nome";

        try (PreparedStatement consulta = c.prepareStatement(sql)) {
            consulta.setString(1, nome);
            consulta.setString(2, senha);

            consulta.executeQuery();

            return true;
        } catch (SQLException ex) {
            System.out.println("Erro gravarCadastro" + ex.getMessage());
            return false;
        }
    }

}
