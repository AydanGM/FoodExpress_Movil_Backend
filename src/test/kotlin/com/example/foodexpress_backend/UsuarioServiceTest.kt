package com.example.foodexpress_backend
 
import com.example.foodexpress_backend.model.Usuario
import com.example.foodexpress_backend.service.UsuarioService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UsuarioServiceTests(@Autowired val usuarioService: UsuarioService) {

    @Test
    fun `crear usuario funciona`() {
        val usuario = Usuario(nombre = "Test User", correo = "test@example.com", password = "Password1!")
        val creado = usuarioService.añadirUsuario(usuario)
        assertNotNull(creado.id)
        assertEquals("Test User", creado.nombre)
    }

    @Test
    fun `obtener usuario inexistente devuelve null`() {
        val usuario = usuarioService.obtenerUsuarioPorId(9999L)
        assertNull(usuario)
    }
}
