/*
 */
package modelo;

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
public class DAOCidade {

    public List<Cidade> getLista() {
        String sql = "select * from cidades";
        List<Cidade> lista = new ArrayList<>();
        try {
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
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }
        return lista;
    }

    public boolean salvar(Cidade obj) {
        if (obj.getCodigo() == null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }
    }

    public boolean incluir(Cidade obj) {
        String sql = "insert into cidades (nome,uf) values(?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getUf());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cidade incluida com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cidade não incluida com sucesso");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Cidade obj) {
        String sql = "update cidades set nome = ?, uf = ? where codigo = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getUf());
            pst.setInt(3, obj.getCodigo());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cidade alterada com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cidade não alterada com sucesso");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(Cidade obj) {
        String sql = "delete from cidades where codigo = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigo());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cidade excluida com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cidade não excluida com sucesso");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    public Cidade localizar(Integer id) {
        String sql = "select * from cidades where codigo = ?";
        Cidade obj = new Cidade();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigo(rs.getInt("codigo"));
                obj.setNome(rs.getString("nome"));
                obj.setUf(rs.getString("uf"));
                return obj;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }
        return null;
    }

}
