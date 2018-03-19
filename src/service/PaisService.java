package service;

import dao.PaisDAO;
import model.Pais;

public class PaisService {

	PaisDAO dao = new PaisDAO();
	
	public int criar(Pais to) {
		dao.criar(to);
	}
	
	public void atualizar(Pais to) {
		dao.atualizar(to);
	}
	
	public void excluir(Pais to) {
		dao.excluir(to);
	}
	
	public Pais carregar(int id) {
		Pais to = dao.carregar(id);
			return to;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", populacao=" + populacao + ", area=" + area + "]";
	}
	
}

