package br.com.edu.forumhub.topico;

public record DadosListagemTopico (Long id, String titulo, String mensagem, String curso, boolean ativo, String dataCriacao) {

    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), topico.isAtivo(), topico.getDataCriacao());
    }
}
