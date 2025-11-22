package com.example.foodexpress_backend.controller

import com.example.foodexpress_backend.model.Usuario
import com.example.foodexpress_backend.service.UsuarioService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import jakarta.validation.Valid

@RestController
@RequestMapping("/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {

    @GetMapping
    fun mostrarUsuarios(): List<Usuario> = usuarioService.mostrarUsuarios()

    @GetMapping("/{id}")
    fun obtenerUsuarioPorId(@PathVariable id: Long): ResponseEntity<Usuario> {
        val usuario = usuarioService.obtenerUsuarioPorId(id)
        return if (usuario != null) {
            ResponseEntity.ok(usuario)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun añadirUsuario(@Valid @RequestBody usuario: Usuario): ResponseEntity<Usuario> { 
        val nuevoUsuario = usuarioService.añadirUsuario(usuario)
        return ResponseEntity.ok(nuevoUsuario)
    }

    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val usuarioActualizado = usuarioService.actualizarUsuario(id, usuario)
        return if (usuarioActualizado != null) {
            ResponseEntity.ok(usuarioActualizado)
        } else {
            ResponseEntity.notFound().build()
        }
    }   

    @DeleteMapping("/{id}")
    fun eliminarUsuarioPorId(@PathVariable id: Long): ResponseEntity<Void> {
        return if (usuarioService.eliminarUsuarioPorId(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}