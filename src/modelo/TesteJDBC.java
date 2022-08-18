/*
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge Luis Boeira Bavaresco jlbavaresco@gmail.com
 */
public class TesteJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String banco = "jdbc:postgresql://localhost:5432/cadastros";
        String driver = "org.postgresql.Driver";
        String usuario = "postgres";
        String senha = "postgres";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(banco, usuario, senha);
        } catch (SQLException e) {
            System.out.println("Não conseguiu conectar com o banco" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Não encontrou o Driver" + ex.getMessage());
        }
        List<Cidade> lista = new ArrayList<>();
        try {
            String sql = "select * from cidades";
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cidade obj = new Cidade();
                obj.setCodigo(rs.getInt("codigo"));
                obj.setNome(rs.getString("nome"));
                obj.setUf(rs.getString("uf"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar a lista de cidades!");
        }
        for (Cidade c : lista) {
            System.out.println("Codigo: " + c.getCodigo() + " Nome: " + c.getNome()
                    + " UF: " + c.getUf());
        }
    }

}
