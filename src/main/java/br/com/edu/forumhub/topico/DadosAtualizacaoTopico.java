package br.com.edu.forumhub.topico;

import java.time.LocalDateTime;

public record DadosAtualizacaoTopico(String titulo, String mensagem, String autor, String curso) {


 public DadosAtualizacaoTopico(Topico topico){
     this(topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
 }
}
