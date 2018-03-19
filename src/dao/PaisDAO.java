package dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TO.PaisTO;

public class PaisDAO {

	public void criar(PaisTO to) {
		String sqlInsert = "INSERT INTO pais(id, nome, populacao, area) VALUES (?, ?, ?, ?)";
			try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getId());
			stm.setString(2, to.getNome());
			stm.setLong(3, to.getPopulacao());
			stm.setDouble(4, to.getArea());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(PaisTO to) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, to.getNome());
			stm.setLong(2, to.getPopulacao());
			stm.setDouble(3, to.getArea());
			stm.setInt(4, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(PaisTO to) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PaisTO carregar(int id) {
		PaisTO to = new PaisTO();
		String sqlSelect = "SELECT nome, pupulacao, area FROM pais WHERE pais.id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setPopulacao(rs.getLong("populacao"));
					to.setArea(rs.getDouble("area"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return to;
	}
	
	public void maiorPopulacao(PaisTO to) {
		String sqlSelect = "SELECT nome, populacao FROM pais ORDER BY populacao DESC";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			stm.setInt(1, to.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setPopulacao(rs.getLong("populacao"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}

	public void menorArea(PaisTO to) {
		String sqlSelect = "SELECT nome, area FROM pais ORDER BY area ASC";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			stm.setInt(1, to.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setArea(rs.getDouble("area"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}

}

