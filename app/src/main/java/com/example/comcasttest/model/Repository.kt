package com.example.comcasttest.model

interface Repository {
    suspend fun getData(): RepositoryImpl.AppState
}