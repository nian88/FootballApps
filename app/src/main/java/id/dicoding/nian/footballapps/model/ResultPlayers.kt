package id.dicoding.nian.footballapps.model

import com.google.gson.annotations.SerializedName

data class ResultPlayers(
        @SerializedName("player") val player: List<Player>
)