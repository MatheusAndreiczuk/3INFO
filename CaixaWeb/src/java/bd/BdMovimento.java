/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

/**
 *
 * @author 2info2021
 */
import vo.Movimento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BdMovimento {

    public void insere(Movimento Movimento) {
        String sql = "insert into Movimento(descricao,valor,tipo,data) values(?,?,?,?)";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setString(1, Movimento.getDescricao());
            ps.setDouble(2, Movimento.getValor());
            ps.setString(3, Movimento.getTipo());
            ps.setDate(4, Movimento.getData());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
        }
    }

    public void salva(Movimento Movimento) {
        if (Movimento.getId() == 0) {
            insere(Movimento);
        } else {
            String sql = "update Movimento set descricao=?,valor=?,tipo=?,data=? where id=?";
            try {
                PreparedStatement ps = Bd.getCon().prepareStatement(sql);
                ps.setInt(5, Movimento.getId());

                ps.setString(1, Movimento.getDescricao());
                ps.setDouble(2, Movimento.getValor());
                ps.setString(3, Movimento.getTipo());
                ps.setDate(4, Movimento.getData());
                ps.execute();
            } catch (SQLException e) {
                System.out.println("Erro SQL: " + e.getMessage());
            }
        }
    }

    public Movimento localiza(int id) {
        String sql = "select * from Movimento where id=?";
        Movimento registro = new Movimento();
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                registro.setId(rs.getInt("id"));
                registro.setDescricao(rs.getString("descricao"));
                registro.setValor(rs.getDouble("valor"));
                registro.setTipo(rs.getString("tipo"));
                registro.setData(rs.getDate("data"));
            }
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
        }
        return registro;
    }

    public boolean validaData(Date dataInicio, Date dataFinal) {
        if (dataInicio.before(dataFinal)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Date> pesquisaData(Date dataInicio, Date dataFinal) throws SQLException {
        String sql = "select * from Movimento where data between Date('dataInicio') and Date('dataFinal')";
        List lista = new ArrayList<>();
        if (validaData(dataInicio, dataFinal)) {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    Movimento registro = new Movimento();
                    registro.setData(dataInicio);
                    lista.add(registro);
                }
            } catch (SQLException e) {
                System.out.println("Erro SQL: " + e.getMessage());
            }
        }
        return lista;
    }

    public void exclui(int id) {
        String sql = "delete from Movimento where id=?";
        try {
            PreparedStatement ps = Bd.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
        }
    }
}
