package br.com.edu.forumhub.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank String tituloComentario,
        @NotBlank String mensagem,
        @NotBlank String autor,
        @NotBlank String curso) {

}
