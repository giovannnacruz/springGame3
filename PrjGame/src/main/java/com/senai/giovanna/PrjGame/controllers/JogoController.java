package com.senai.giovanna.PrjGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.giovanna.PrjGame.entities.Jogo;
import com.senai.giovanna.PrjGame.services.JogoService;


@RestController
@RequestMapping("/jogos")
public class JogoController {

	
	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}

	private final JogoService jogoService;

	@Autowired
	public JogoController(JogoService jogoService) {
		this.jogoService = jogoService;
	}	
	
	@PostMapping
	public Jogo createProduct(@RequestBody Jogo jogo) {
		return jogoService.saveJogo(jogo);
	}


	@DeleteMapping("/{id}")
	public void deleteJogo(@PathVariable Long id) {
		jogoService.deletejogo(id);
	}
	@GetMapping("/{id}")	
    public ResponseEntity<Jogo> getJogo(@PathVariable Long id) {
        Jogo jogo = jogoService.getJogoById(id);
        if (jogo != null) {
            return ResponseEntity.ok(jogo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
	
	//Utilizando o ResponseEntity e RequestEntity
		@GetMapping
		public ResponseEntity<List<Jogo>> getAllJogos(RequestEntity<Void> requestEntity) {
			String method = requestEntity.getMethod().name();
			String contentType = requestEntity.getHeaders().getContentType().toString();
			List<Jogo> jogos = jogoService.getAllJogo();
			return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
					.body(jogos);
		}
		
		@PutMapping("/{id}")
		public Jogo updateJogo(@PathVariable Long id, @RequestBody Jogo jogo) {
		    return jogoService.updateJogo(id, jogo);
		}

}
