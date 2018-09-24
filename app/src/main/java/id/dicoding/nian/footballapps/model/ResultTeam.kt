package id.dicoding.nian.footballapps.model

import com.google.gson.annotations.SerializedName

data class ResultTeam(
        @SerializedName("teams") val teams: List<FavoriteTeam>
)