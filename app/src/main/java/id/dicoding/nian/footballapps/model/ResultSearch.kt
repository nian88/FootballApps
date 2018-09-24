package id.dicoding.nian.footballapps.model

import com.google.gson.annotations.SerializedName

data class ResultSearch(
        @SerializedName("event") val event: List<Event>
)