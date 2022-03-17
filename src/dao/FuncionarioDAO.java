/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import principal.ConnectionFactory;

/**
 *
 * @author geraldo
 */
public class FuncionarioDAO {
    private Connection conexao=null;
    
    public FuncionarioDAO() throws SQLException {

		this.conexao = new ConnectionFactory().getConnection();
		
	}
    
    public void adicionar(Funcionario funcionario) throws SQLException {

		PreparedStatement stmt = this.conexao
				.prepareStatement("insert into tbl_funcionarios " +
						"(nome,sobrenome,email,idade,salario,"
                                              + "cidade,estado) values (?,?,?,?,?,?,?)");

		stmt.setString(1, funcionario.getNome());
		stmt.setString(2, funcionario.getSobrenome());
                stmt.setString(3, funcionario.getEmail());
                stmt.setInt(4, funcionario.getIdade());
                stmt.setDouble(5, funcionario.getSalario());
                stmt.setString(6, funcionario.getCidade());                
		stmt.setString(7, funcionario.getEstado());
		
		stmt.execute();
		
		stmt.close();
                
                this.conexao.close();

	}       

	
	
	
	public List<Funcionario> getLista() throws SQLException {

		
		
		PreparedStatement stmt = this.conexao.prepareStatement("select * from tbl_funcionarios");
		
		ResultSet rs = stmt.executeQuery();
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		while (rs.next()) { 
			
			Funcionario funcionario = new Funcionario();
			funcionario.setId(rs.getInt("id"));
			funcionario.setNome(rs.getString("nome"));
                        funcionario.setSobrenome(rs.getString("sobrenome"));
			funcionario.setEmail(rs.getString("email"));
                        funcionario.setIdade(rs.getInt("idade"));
                        funcionario.setSalario(rs.getDouble("salario"));
                        funcionario.setCidade(rs.getString("cidade"));
			funcionario.setEstado(rs.getString("estado"));
			
			funcionarios.add(funcionario);
		}
	
		rs.close();
		
		stmt.close();

                this.conexao.close();
                
		return funcionarios;
	}

        
        public List<Funcionario> getListaOrdemAlfabetica() throws SQLException {

		
		
		PreparedStatement stmt = this.conexao.prepareStatement("select * from tbl_funcionarios order by nome");
		
		ResultSet rs = stmt.executeQuery();
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		while (rs.next()) { 
			
			Funcionario funcionario = new Funcionario();
			funcionario.setId(rs.getInt("id"));
			funcionario.setNome(rs.getString("nome"));
                        funcionario.setSobrenome(rs.getString("sobrenome"));
			funcionario.setEmail(rs.getString("email"));
                        funcionario.setIdade(rs.getInt("idade"));
                        funcionario.setSalario(rs.getDouble("salario"));
                        funcionario.setCidade(rs.getString("cidade"));
			funcionario.setEstado(rs.getString("estado"));
			
			funcionarios.add(funcionario);
		}
	
		rs.close();
		
		stmt.close();

                this.conexao.close();
                
		return funcionarios;
	}
        
	public Funcionario pesquisarID(int id) throws SQLException {

		Funcionario funcionario = new Funcionario();
		PreparedStatement stmt = this.conexao
                    .prepareStatement("select * from tbl_funcionarios where id=?");
		
                stmt.setInt(1, id);

             
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			funcionario.setId(rs.getInt("id"));
			funcionario.setNome(rs.getString("nome"));
                        funcionario.setSobrenome(rs.getString("sobrenome"));
			funcionario.setEmail(rs.getString("email"));
                        funcionario.setIdade(rs.getInt("idade"));
                        funcionario.setSalario(rs.getDouble("salario"));
                        funcionario.setCidade(rs.getString("cidade"));
			funcionario.setEstado(rs.getString("estado"));
		}

		return funcionario;
	}

	public void alterar(Funcionario funcionario) throws SQLException {

		PreparedStatement stmt = this.conexao.prepareStatement("update tbl_funcionarios set nome=?,sobrenome=?,email=?, "
                        + "idade=?,salario=?,cidade=?,estado=? where id=?");
		stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getSobrenome());
		stmt.setString(3, funcionario.getEmail());
                stmt.setInt(4, funcionario.getIdade());
                stmt.setDouble(5, funcionario.getSalario());
		stmt.setString(6, funcionario.getCidade());
                stmt.setString(7, funcionario.getEstado());
		stmt.setInt(8, funcionario.getId());
		stmt.execute();
		stmt.close();

                this.conexao.close();
	}
	
	public void remover(int id) throws SQLException {

		PreparedStatement stmt = this.conexao.prepareStatement("delete from tbl_funcionarios where id=?");
		
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();

                this.conexao.close();

		
	}
  
}
