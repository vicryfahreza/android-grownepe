package com.vicry.grownepe.model.repo

object NepenthesInjection {
    fun provideRepository(): NepenthesRepository {
        return NepenthesRepository.getInstance()
    }
}