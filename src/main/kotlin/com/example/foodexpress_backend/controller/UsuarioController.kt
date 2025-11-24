package com.example.foodexpress_backend.controller

import com.example.foodexpress_backend.model.Usuario
import com.example.foodexpress_backend.model.LoginRequest
import com.example.foodexpress_backend.service.UsuarioService

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

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
        return if (usuario != null) ResponseEntity.ok(usuario)
        else ResponseEntity.notFound().build()
    }

    // Obtener usuario por correo
    @GetMapping("/correo/{correo}")
    fun obtenerUsuarioPorCorreo(@PathVariable correo: String): ResponseEntity<Usuario> {
        val usuario = usuarioService.obtenerUsuarioPorCorreo(correo)
        return if (usuario != null) ResponseEntity.ok(usuario)
        else ResponseEntity.notFound().build()
    }

    // Verificar si existe usuario por correo
    @GetMapping("/existe/{correo}")
    fun existeUsuario(@PathVariable correo: String): ResponseEntity<Boolean> {
        val existe = usuarioService.existeUsuario(correo)
        return ResponseEntity.ok(existe)
    }

    // Añadir nuevo usuario
    @PostMapping
    fun añadirUsuario(@Valid @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val nuevoUsuario = usuarioService.añadirUsuario(usuario)
        return ResponseEntity.ok(nuevoUsuario)
    }

    // Login con correo y password
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Usuario> {
        val usuario = usuarioService.login(request.correo, request.password)
        return if (usuario != null) ResponseEntity.ok(usuario)
        else ResponseEntity.status(401).build() // Unauthorized si no coincide
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val usuarioActualizado = usuarioService.actualizarUsuario(id, usuario)
        return if (usuarioActualizado != null) ResponseEntity.ok(usuarioActualizado)
        else ResponseEntity.notFound().build()
    }

    // Eliminar usuario por id
    @DeleteMapping("/{id}")
    fun eliminarUsuarioPorId(@PathVariable id: Long): ResponseEntity<Void> {
        return if (usuarioService.eliminarUsuarioPorId(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}
