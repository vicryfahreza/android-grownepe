package com.vicry.grownepe.model.nepenthes

data class NaturalHybridNepenthes (
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
    val imageSource: String,
    val bestSoil: String,
    val bestEnvironment: String,
    val cross: String,
    val distribution: String,
)