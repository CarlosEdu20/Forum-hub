package br.com.edu.forumhub.controller;

import br.com.edu.forumhub.topico.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/topicos")
@RestController
public class TopicoController {

    @Autowired
    private TopicoRespository topicoRespository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico) {
        var titulo = dadosCadastroTopico.tituloComentario();
        var mensagem = dadosCadastroTopico.mensagem();

        Optional<Topico> verificaTitulo = topicoRespository.findByTituloAndMensagem(titulo, mensagem);

        if (verificaTitulo.isPresent()) {
            return ResponseEntity.badRequest().body("Já existe uma publicação igual cadastrada!");
        } else {
            var topico = new Topico(titulo, mensagem, dadosCadastroTopico.autor(), dadosCadastroTopico.curso());
            topicoRespository.save(topico);
            return ResponseEntity.ok().body("Topico cadastrado com sucesso!");
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable pageable) {
        var dadosTopicos = topicoRespository.findByAtivoTrue(pageable).map(DadosListagemTopico::new);
        return ResponseEntity.ok().body(dadosTopicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados, @PathVariable Long id) {
        try {
            var topico = topicoRespository.getReferenceById(id);

            topico.atualizarInformacao(dados);

            return ResponseEntity.ok().body("Topico atualizado com sucesso!");

        } catch (EntityNotFoundException e) {

            return ResponseEntity.badRequest().body("Id não informado não exixte");
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        var topico = topicoRespository.findById(id).orElse(null);

        if (topico == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Id informado não existe.");
        }
        else {
                topicoRespository.deleteById(id);
                return ResponseEntity.ok("Registro exluído com sucesso!");
        }
    }

}




