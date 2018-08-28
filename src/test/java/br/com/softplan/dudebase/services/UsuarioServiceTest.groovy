package br.com.softplan.dudebase.services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService

    @Test
    void "SSS"(){

    }

}