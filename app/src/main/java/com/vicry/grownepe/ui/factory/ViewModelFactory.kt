package com.vicry.grownepe.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicry.grownepe.model.repo.NepenthesRepository
import com.vicry.grownepe.ui.screen.article.ArticleViewModel

class ViewModelFactory(private val repo: NepenthesRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(repo) as T
        }
        throw IllegalArgumentException("Not Found ViewModel class: " + modelClass.name)
    }

}