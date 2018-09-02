package br.com.softplan.dudebase.controllers;

import br.com.softplan.dudebase.dtos.UsuarioDto;
import br.com.softplan.dudebase.services.UsuarioService;
import br.com.softplan.dudebase.utils.Response;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Response<Page<UsuarioDto>>> listaUsuarios() {
        Response<Page<UsuarioDto>> response = new Response<>();

        return null;
    }


}
