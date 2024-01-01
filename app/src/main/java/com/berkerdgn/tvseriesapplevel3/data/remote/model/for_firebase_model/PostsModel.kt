package com.berkerdgn.tvseriesapplevel3.data.remote.model.for_firebase_model

data class PostsModel(
    var comment: String ?= "",
    var date: String ?="",
    var tvSeriesName: String ? ="",
    var userEmail: String ?= "",
)
