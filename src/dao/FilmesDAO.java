
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Filmes;

public class FilmesDAO {
    Connection con = null;
    public FilmesDAO(){
        con = Conexao.abrirConexao();
    }
    
    public List<Filmes> pesquisarAll(){
        List<Filmes> lista = new ArrayList<>();
        try {            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM FILMES");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Filmes filmes = new Filmes();
                filmes.setId(rs.getInt("id"));
                filmes.setNome(rs.getString("nome"));
                filmes.setDescricao(rs.getString("descricao"));
                filmes.setLancamento(String.valueOf(rs.getDate("lancamento")));
                lista.add(filmes);                
            }
            return lista;
        } catch (Exception e) {
        }
        return null;
    }
    
    
    public String salvar(Filmes filme){        
        try {
            String sql = "INSERT INTO FILMES(nome, descricao, lancamento) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, filme.getNome());
            ps.setString(2, filme.getDescricao());
            ps.setDate(3, Date.valueOf(filme.getLancamento()));
            if(ps.executeUpdate() != 0){
                return "Cadastrado com sucesso";
            }else{
                return "Erro ao cadastrar";
            }
        } catch (SQLException e) {
            return String.valueOf(e);
                    
        }
    }
    
    public boolean deletar(int id){
        try {
            String sql = "DELETE FROM FILMES WHERE id = '"+id+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void atualizar(Filmes filme){
        try {
            String sql = "UPDATE FILMES SET NOME = ?, descricao = ?, lancamento = ? WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, filme.getNome());
            ps.setString(2, filme.getDescricao());
            ps.setDate(3, Date.valueOf(filme.getLancamento()));
            ps.setInt(4, filme.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
