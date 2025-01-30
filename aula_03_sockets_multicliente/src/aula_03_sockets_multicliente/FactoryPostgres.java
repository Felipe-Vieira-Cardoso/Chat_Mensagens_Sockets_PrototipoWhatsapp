package aula_03_sockets_multicliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoryPostgres {

    private static Connection conexaoPG;

    public static Connection getConexaoPostgres() {
        //nao existe conexao ainda...
        if (conexaoPG == null) {
            try {
                conexaoPG = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aula_03_sockets_multicliente", "postgres", "postgres");
            } catch (SQLException ex) {
                Logger.getLogger(FactoryPostgres.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Houve um problema na conexao");
            }
        }
        return conexaoPG;
    }
}
