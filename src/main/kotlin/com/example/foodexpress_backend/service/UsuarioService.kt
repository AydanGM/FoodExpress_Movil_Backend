package com.example.foodexpress_backend.service

import com.example.foodexpress_backend.model.Usuario
import com.example.foodexpress_backend.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun mostrarUsuarios(): List<Usuario> = usuarioRepository.findAll()

    fun obtenerUsuarioPorId(id: Long): Usuario? = usuarioRepository.findById(id).orElse(null)

    fun añadirUsuario(usuario: Usuario): Usuario = usuarioRepository.save(usuario)

    fun actualizarUsuario(id: Long, usuario: Usuario): Usuario? {
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(usuario.copy(id = id))
        } else {
            null
        }
    }

    fun eliminarUsuarioPorId(id: Long): Boolean {
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}