package com.vicry.grownepe.model.repo

import com.vicry.grownepe.model.nepenthes.NepenthesData
import com.vicry.grownepe.model.nepenthes.StateNepenthes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class NepenthesRepository {

    private val stateNepenthes = mutableListOf<StateNepenthes>()

    init {
        if (stateNepenthes.isEmpty()) {
            NepenthesData.nepenthes.forEach {
                stateNepenthes.add(StateNepenthes(it, false))
            }
        }
    }

    fun searchNepenthes(query: String): List<StateNepenthes>{
        return stateNepenthes.filter {
            it.nepenthes.name.contains(query, ignoreCase = true)
        }
    }

    fun getAllSucculents(): Flow<List<StateNepenthes>> {
        return flowOf(stateNepenthes)
    }

    fun getSucculentById(succulentId: Long): Flow<StateNepenthes> {
        return flowOf(
            stateNepenthes.first { it.nepenthes.id == succulentId }
        )
    }


    companion object {
        @Volatile
        private var instance: NepenthesRepository? = null

        fun getInstance(): NepenthesRepository =
            instance ?: synchronized(this) {
                NepenthesRepository().apply {
                    instance = this
                }
            }
    }
}