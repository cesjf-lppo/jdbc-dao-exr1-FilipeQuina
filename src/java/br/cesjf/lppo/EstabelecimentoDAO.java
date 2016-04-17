package br.cesjf.lppo;

import Classes.Anuncio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstabelecimentoDAO {

    List<Anuncio> listaTodos() {
        List<Anuncio> todos = new ArrayList<>();
        try {
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2016-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM anuncio");
            while (resultado.next()) {
                Anuncio anu = new Anuncio();
                anu.setId(resultado.getLong("id"));
                anu.setNome(resultado.getString("nome"));
                anu.setDescricao(resultado.getString("descricao"));
                anu.setPreco(resultado.getFloat("preco"));
                todos.add(anu);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return todos;
    }

    void criar(Anuncio novoAnuncio) {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2016-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
         ///   operacao.executeUpdate(String.format("INSERT INTO anuncio(nome, descricao,preco) VALUES('%s','%s',%f)", novoAnuncio.getNome(), novoAnuncio.getDescricao(),novoAnuncio.getPreco()));
         operacao.executeUpdate(String.format("INSERT INTO anuncio(nome, descricao,preco) VALUES('"+novoAnuncio.getNome()+"','"+novoAnuncio.getDescricao()+"',"+novoAnuncio.getPreco()+")"));
        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
    