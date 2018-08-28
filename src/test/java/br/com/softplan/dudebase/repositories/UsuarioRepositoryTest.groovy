package br.com.softplan.dudebase.repositories

import br.com.softplan.dudebase.entities.UsuarioEntity
import br.com.softplan.dudebase.utils.CryptPassword
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.hamcrest.collection.IsEmptyCollection
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

import java.lang.reflect.Type

import static org.hamcrest.CoreMatchers.not
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.junit.Assert.*

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository

    @Before
    void setUp() throws FileNotFoundException {
        Type listType = new TypeToken<List<UsuarioEntity>>() {}.getType()

        List<UsuarioEntity> list = new Gson().fromJson(
                new FileReader(
                        UsuarioRepositoryTest.class.getResource("/json/usuario-insert.json").getFile()
                ), listType)
        this.usuarioRepository.saveAll(list)
    }

    @After
    void tearDown() {
        this.usuarioRepository.deleteAll()
    }

    @Test
    void "Buscar usuario por email"() {
        UsuarioEntity user = this.usuarioRepository.findByEmail("cafecanudo@gmail.com")
        assertNotNull(user)
    }

    @Test
    void "Nao deve encontrar usuario por email"() {
        UsuarioEntity user = this.usuarioRepository.findByEmail("12312321312@gmail.com")
        assertNull(user)
    }

    @Test
    void "Efetuar login de usuario"() {
        UsuarioEntity user = this.usuarioRepository.findByEmail("cafecanudo@gmail.com")
        assertNotNull(user)

        assertTrue("Senha e igual", CryptPassword.compare("123", user.getSenha()))
        assertEquals("Senha nao pode ser igual", CryptPassword.compare("1123", user.getSenha()), false)
    }

    @Test
    void "Buscar usuario por nome"() {
        Page<UsuarioEntity> list = this.usuarioRepository.findByNomeContainsOrEmailContaining("%LLTON%", "%LLTON%", null)
        assertNotNull(list)

        assertThat(list.getContent(), not(IsEmptyCollection.empty()))
        assertThat(list.getContent(), hasSize(1))
    }

}
