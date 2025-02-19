package com.vicry.grownepe.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.screen.article.cultivar.articlecultivar.CultivarViewModel
import com.vicry.grownepe.ui.screen.article.cultivar.detailcultivar.DetailCultivarViewModel
import com.vicry.grownepe.ui.screen.article.naturalhybrid.articlehybrid.NaturalHybridViewModel
import com.vicry.grownepe.ui.screen.article.naturalhybrid.detailhybrid.DetailNHViewModel
import com.vicry.grownepe.ui.screen.article.spesies.articlespecies.SpeciesViewModel
import com.vicry.grownepe.ui.screen.article.spesies.detailspecies.DetailSpeciesViewModel
import com.vicry.grownepe.ui.screen.home.HomeViewModel
import com.vicry.grownepe.ui.screen.home.homenepe.HomeNepeViewModel
import com.vicry.grownepe.ui.screen.home.homesoil.HomeSoilViewModel
import com.vicry.grownepe.ui.screen.lms.LmsViewModel
import com.vicry.grownepe.ui.screen.lms.detailLms.DetailLmsModel

class ViewModelFactory(private val repo: NepenthesRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NaturalHybridViewModel::class.java)) {
            return NaturalHybridViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailNHViewModel::class.java)) {
            return DetailNHViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(CultivarViewModel::class.java)) {
            return CultivarViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailCultivarViewModel::class.java)) {
            return DetailCultivarViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailSpeciesViewModel::class.java)) {
            return DetailSpeciesViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(SpeciesViewModel::class.java)) {
            return SpeciesViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(LmsViewModel::class.java)) {
            return LmsViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailLmsModel::class.java)) {
            return DetailLmsModel(repo) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(HomeNepeViewModel::class.java)) {
            return HomeNepeViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(HomeSoilViewModel::class.java)) {
            return HomeSoilViewModel(repo) as T
        }

        throw IllegalArgumentException("Not Found ViewModel class: " + modelClass.name)
    }

}