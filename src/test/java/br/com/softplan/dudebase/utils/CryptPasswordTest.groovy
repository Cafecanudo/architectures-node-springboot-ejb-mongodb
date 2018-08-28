package br.com.softplan.dudebase.utils

import org.junit.Test

import static org.junit.Assert.*

class CryptPasswordTest {

    public static final String SENHA_DEFAULT_TEST = "123"

    @Test()
    void "Senha nao pode ser NULL"() {
        assertNull(CryptPassword.cript(null))
        assertNotNull(CryptPassword.cript(SENHA_DEFAULT_TEST))
    }

    @Test
    void "Verificar se criptografica sao iguais"() {
        String senha = CryptPassword.cript(SENHA_DEFAULT_TEST)
        assertTrue(CryptPassword.compare(SENHA_DEFAULT_TEST, senha))
        assertNotEquals(CryptPassword.compare("1231", senha), true)
    }
}