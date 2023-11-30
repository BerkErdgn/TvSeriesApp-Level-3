package com.berkerdgn.tvseriesapplevel3.data.remote.model.searchModels


data class Show(
    val id: Int,
    val name: String,
    val language: String,
    val genres: List<String>,
    val image: İmage,
    val premiered: String,
    val ended: String,
    val schedule: Schedule,
    val rating: Rating
)