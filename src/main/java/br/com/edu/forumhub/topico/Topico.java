package br.com.edu.forumhub.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "topico")
@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo")
    private String titulo;

    private String mensagem;

    private String dataCriacao;

    @Column(name = "status")
    private boolean ativo;

    private String curso;

    private String autor;


    public Topico(String titulo, String mensagem, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = LocalDate.now().toString();
        this.ativo = true;
        this.curso = curso;
        this.autor = autor;
    }
    public Topico(){

    }

    public Topico(Topico topico) {
        this(topico.titulo, topico.mensagem,topico.autor, topico.curso);
    }





    @Override
    public String toString() {
        return String.format(
                "Tópico:\n" +
                        " - Título do Comentário: %s\n" +
                        " - Mensagem: %s\n" +
                        " - Data de Criação: %s\n" +
                        " - Estado do Tópico: %s\n" +
                        " - Autor: %s\n" +
                        " - Curso: %s",
                id,
                titulo,
                mensagem,
                dataCriacao,
                ativo ? "Ativo" : "Inativo",
                autor,
                curso
        );
    }

    public void atualizarInformacao(DadosAtualizacaoTopico dados){
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if(dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getCurso() {
        return curso;
    }

    public String getAutor() {
        return autor;
    }
}