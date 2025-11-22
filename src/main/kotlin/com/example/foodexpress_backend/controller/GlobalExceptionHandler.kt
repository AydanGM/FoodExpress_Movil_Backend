package com.example.foodexpress_backend.controller

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errores = ex.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Error") }
        return ResponseEntity(errores, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDuplicateKey(ex: DataIntegrityViolationException): ResponseEntity<Map<String, String>> {
        val error = mapOf("email" to "El email ya está registrado")
        return ResponseEntity(error, HttpStatus.CONFLICT) // 409 Conflict
    }
}
