package com.example.foodexpress_backend.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size


@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(message = "El nombre no puede estar vacío")
    @field:Pattern(
        regexp = ".*\\s+.*",
        message = "El nombre debe contener al menos un espacio (nombre y apellido)"
    )
    var nombre: String,

    @field:NotBlank(message = "El correo no puede estar vacío")
    @field:Email(message = "El correo debe tener un formato válido")
    @Column(unique = true)
    var correo: String,

    @field:Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$",
        message = "La contraseña debe incluir mayúscula, minúscula, número y símbolo"
    )
    var password: String
)