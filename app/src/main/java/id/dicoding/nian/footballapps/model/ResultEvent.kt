package id.dicoding.nian.footballapps.model

import com.google.gson.annotations.SerializedName

data class ResultEvent(
        @SerializedName("events") val events: List<Event>
)