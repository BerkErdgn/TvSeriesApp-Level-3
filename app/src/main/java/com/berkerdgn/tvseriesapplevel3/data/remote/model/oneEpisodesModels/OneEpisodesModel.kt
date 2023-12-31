package com.berkerdgn.tvseriesapplevel3.data.remote.model.oneEpisodesModels



data class OneEpisodesModel(
    val _links: Links,
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: İmage,
    val name: String,
    val number: Int,
    val rating: Rating,
    val runtime: Int,
    val season: Int,
    val summary: String,
    val type: String,
    val url: String
)