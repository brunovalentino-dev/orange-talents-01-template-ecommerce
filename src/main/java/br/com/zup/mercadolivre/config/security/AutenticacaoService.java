package br.com.zup.mercadolivre.config.security;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Usuario;

@Service
public class AutenticacaoService implements UserDetailsService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String queryString = "SELECT u FROM Usuario u WHERE u.login =:valor";
		
		Query query = entityManager.createQuery(queryString);
		query.setParameter("valor", username);
		
		List<?> resultado = query.getResultList();
		
		if (!resultado.isEmpty()) {
			Usuario usuario = (Usuario) resultado.get(0);
			
			return usuario;
		}
		
		throw new UsernameNotFoundException("Dados inválidos!!!");
	}

}
