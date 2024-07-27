package com.vicry.grownepe.model.repo

import com.vicry.grownepe.model.nepenthes.StateCultivar
import com.vicry.grownepe.model.nepenthes.StateNaturalHybrid
import com.vicry.grownepe.model.nepenthes.spesies.SpeciesData
import com.vicry.grownepe.model.nepenthes.StateNepenthes
import com.vicry.grownepe.model.nepenthes.cultivar.CultivarData
import com.vicry.grownepe.model.nepenthes.naturalhybrid.NaturalHybridData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class NepenthesRepository {

    private val stateNepenthes = mutableListOf<StateNepenthes>()
    private val stateNaturalHybrid = mutableListOf<StateNaturalHybrid>()
    private val stateCultivar = mutableListOf<StateCultivar>()


    init {
        if (stateNepenthes.isEmpty()) {
            SpeciesData.nepenthes.forEach {
                stateNepenthes.add(StateNepenthes(it, false))
            }
        }
        if (stateNaturalHybrid.isEmpty()) {
            NaturalHybridData.nepenthes.forEach {
                stateNaturalHybrid.add(StateNaturalHybrid(it, false))
            }
        }
        if (stateCultivar.isEmpty()) {
            CultivarData.nepenthes.forEach {
                stateCultivar.add(StateCultivar(it, false))
            }
        }
    }

    fun searchNepenthes(query: String): List<StateNepenthes>{
        return stateNepenthes.filter {
            it.nepenthes.name.contains(query, ignoreCase = true)
        }
    }

    fun searchNaturalHybrid(query: String): List<StateNaturalHybrid>{
        return stateNaturalHybrid.filter {
            it.nepenthes.name.contains(query, ignoreCase = true)
        }
    }

    fun searchCultivar(query: String): List<StateCultivar>{
        return stateCultivar.filter {
            it.nepenthes.name.contains(query, ignoreCase = true)
        }
    }

    fun getAllNepenthes(): Flow<List<StateNepenthes>> {
        return flowOf(stateNepenthes)
    }

    fun getAllNaturalHybrid(): Flow<List<StateNaturalHybrid>> {
        return flowOf(stateNaturalHybrid)
    }

    fun getAllCultivar(): Flow<List<StateCultivar>> {
        return flowOf(stateCultivar)
    }


    fun getNepenthesById(nepenthesId: Long): Flow<StateNepenthes> {
        return flowOf(
            stateNepenthes.first { it.nepenthes.id == nepenthesId }
        )
    }

    fun getNaturalHybridById(nepenthesId: Long): Flow<StateNaturalHybrid> {
        return flowOf(
            stateNaturalHybrid.first { it.nepenthes.id == nepenthesId }
        )
    }

    fun getCultivarById(nepenthesId: Long): Flow<StateCultivar> {
        return flowOf(
            stateCultivar.first { it.nepenthes.id == nepenthesId }
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