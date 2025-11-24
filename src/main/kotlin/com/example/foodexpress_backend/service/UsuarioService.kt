package com.example.foodexpress_backend.service

import com.example.foodexpress_backend.model.Usuario
import com.example.foodexpress_backend.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    // Mostrar todos los usuarios
    fun mostrarUsuarios(): List<Usuario> = usuarioRepository.findAll()

    // Obtener usuario por id
    fun obtenerUsuarioPorId(id: Long): Usuario? = usuarioRepository.findById(id).orElse(null)

    // Añadir nuevo usuario
    fun añadirUsuario(usuario: Usuario): Usuario = usuarioRepository.save(usuario)

    // Actualizar usuario
    fun actualizarUsuario(id: Long, usuario: Usuario): Usuario? {
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(usuario.copy(id = id))
        } else {
            null
        }
    }

    // Eliminar usuario por id
    fun eliminarUsuarioPorId(id: Long): Boolean {
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    // Obtener usuario por correo
    fun obtenerUsuarioPorCorreo(correo: String): Usuario? =
        usuarioRepository.findByCorreo(correo)

    // Verificar si existe usuario por correo
    fun existeUsuario(correo: String): Boolean =
        usuarioRepository.existsByCorreo(correo)

    // Login con correo y password
    fun login(correo: String, password: String): Usuario? {
        val usuario = usuarioRepository.findByCorreo(correo)
        return if (usuario != null && usuario.password == password) usuario else null
    }
}