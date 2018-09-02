package br.com.softplan.dudebase.services

import br.com.softplan.dudebase.entities.UsuarioEntity
import br.com.softplan.dudebase.repositories.UsuarioRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.collection.IsCollectionWithSize
import org.hamcrest.collection.IsEmptyCollection
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

import static org.hamcrest.MatcherAssert.assertThat
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class UsuarioServiceTest {

    public static final String EMAIL = "cafecanudo@gmail.com"
    @MockBean
    private UsuarioRepository usuarioRepository

    @Autowired
    private UsuarioService usuarioService

    @Before
    void "Inicializar Mockito"() {
        //Mockando SAVE
        UsuarioEntity user = new UsuarioEntity()
        user.idUsuario = 1L
        user.setEmail(EMAIL)
        BDDMockito.given(this.usuarioRepository.save(Mockito.any(UsuarioEntity.class))).willReturn(user)

        //Mockando FindByID
        BDDMockito.given(this.usuarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(user))

        //Mockando FindByEmail
        BDDMockito.given(this.usuarioRepository.findByEmail(Mockito.anyString())).willReturn(Optional.of(user))

        //Mockando Lista pagina
        BDDMockito.given(this.usuarioRepository.findByNomeContainsOrEmailContaining(
                Mockito.anyString(), Mockito.anyString(), Mockito.any(PageRequest.class)))
                .willReturn(new PageImpl<UsuarioEntity>(Arrays.asList(user)))
    }

    @Test
    void "Persistencia de usuario"() {
        UsuarioEntity user = this.usuarioService.persistir(new UsuarioEntity())
        assertNotNull(user)
    }

    @Test
    void "Buscar de usuario por ID"() {
        Optional<UsuarioEntity> user = this.usuarioService.buscarUsuarioPorID(1L)
        assertTrue(user.isPresent())
    }

    @Test
    void "Buscar de usuario"() {
        Optional<UsuarioEntity> user = this.usuarioService.buscarUsuarioPorEmail(EMAIL)
        assertTrue(user.isPresent())
    }

    @Test
    void "Paginacao registros"() {
        Page<UsuarioEntity> page = this.usuarioService.buscarUsuarioContendoNomeOuEmail(EMAIL, new PageRequest(0, 10))
        assertNotNull(page)
        assertThat(page.getContent(), CoreMatchers.not(IsEmptyCollection.empty()))
        assertThat(page.getContent(), IsCollectionWithSize.hasSize(1))
    }

}