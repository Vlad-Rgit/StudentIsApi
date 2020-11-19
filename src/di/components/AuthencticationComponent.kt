package com.studentis.di.components

import com.studentis.authentication.HashService
import com.studentis.authentication.JwtService
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AuthencticationComponent {
    fun getJwtService(): JwtService
    fun getHashService(): HashService
}