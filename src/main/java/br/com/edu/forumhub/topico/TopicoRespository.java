package br.com.edu.forumhub.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TopicoRespository extends JpaRepository<Topico, Long> {
    Optional<Topico>  findByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findByAtivoTrue(Pageable paginacao);


}
