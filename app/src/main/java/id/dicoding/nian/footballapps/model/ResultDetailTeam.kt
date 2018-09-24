package id.dicoding.nian.footballapps.model

import com.google.gson.annotations.SerializedName

data class ResultDetailTeam(
        @SerializedName("teams") val teams: List<Team>
)