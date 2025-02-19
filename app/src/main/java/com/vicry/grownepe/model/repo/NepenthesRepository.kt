package com.vicry.grownepe.model.repo


import com.vicry.grownepe.model.home.StateLowLandNepe
import com.vicry.grownepe.model.home.StateSoil
import com.vicry.grownepe.model.home.data.LowLandNepeData
import com.vicry.grownepe.model.home.data.SoilData
import com.vicry.grownepe.model.lms.StateLMS
import com.vicry.grownepe.model.lms.modul.LmsData
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
    private val stateLMS = mutableListOf<StateLMS>()
    private val stateLowLand = mutableListOf<StateLowLandNepe>()
    private val stateSoil = mutableListOf<StateSoil>()


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
        if (stateLMS.isEmpty()) {
            LmsData.lms.forEach {
                stateLMS.add(StateLMS(it, false))
            }
        }
        if (stateLowLand.isEmpty()) {
            LowLandNepeData.lowLand.forEach {
                stateLowLand.add(StateLowLandNepe(it, false))
            }
        }
        if (stateSoil.isEmpty()) {
            SoilData.soil.forEach {
                stateSoil.add(StateSoil(it, false))
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

    fun getAllLms(): Flow<List<StateLMS>> {
        return flowOf(stateLMS)
    }

    fun getAllLowLand(): Flow<List<StateLowLandNepe>> {
        return flowOf(stateLowLand)
    }

    fun getAllSoil(): Flow<List<StateSoil>> {
        return flowOf(stateSoil)
    }

    fun getHomeNepeById(lowlandId: Long): Flow<StateLowLandNepe> {
        return flowOf(
            stateLowLand.first { it.lowLand.id == lowlandId }
        )
    }

    fun getHomeSoilById(soilId: Long): Flow<StateSoil> {
        return flowOf(
            stateSoil.first { it.soil.id == soilId }
        )
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

    fun getLMSById(lms: Long): Flow<StateLMS> {
        return flowOf(
            stateLMS.first { it.lms.id == lms }
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