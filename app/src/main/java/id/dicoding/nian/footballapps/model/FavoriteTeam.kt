package id.dicoding.nian.footballapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by nian on 9/21/18.
 */
@Parcelize
data class FavoriteTeam(
        val id: Long?,
        @SerializedName("idTeam") val idTeam: String?,
        @SerializedName("strTeam") val strTeam: String?,
        @SerializedName("strTeamBadge") val strTeamBadge: String?,
        @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @SerializedName("intFormedYear") val intFormedYear: String?
        ) : Parcelable {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val IDTEAM: String = "ID_TEAM"
        const val STRTEAM: String = "STR_TEAM"
        const val STRTEAMBADGE: String = "STR_TEAMBADGE"
        const val STRDESCRIPTIONEN: String = "STR_DESCRIPTIONEN"
        const val INTFORMEDYEAR: String = "INT_FORMEDYEAR"
    }
}