package id.dicoding.nian.footballapps.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.dicoding.nian.footballapps.model.FavoriteMatch
import id.dicoding.nian.footballapps.model.FavoriteTeam
import org.jetbrains.anko.db.*

/**
 * Created by nian on 9/21/18.
 */

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatch.TABLE_FAVORITE, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.IDEVENT to TEXT,
                FavoriteMatch.HOMETEAM to TEXT,
                FavoriteMatch.AWAYTEAM to TEXT,
                FavoriteMatch.HOMESCORE to TEXT,
                FavoriteMatch.ROUND to TEXT,
                FavoriteMatch.AWAYSCORE to TEXT,
                FavoriteMatch.HOMEGOALDETAILS to TEXT,
                FavoriteMatch.HOMEREDCARDS to TEXT,
                FavoriteMatch.HOMEYELLOWCARDS to TEXT,
                FavoriteMatch.HOMELINEUPGOALKEEPER to TEXT,
                FavoriteMatch.HOMELINEUPDEFENSE to TEXT,
                FavoriteMatch.HOMELINEUPMIDFIELD to TEXT,
                FavoriteMatch.HOMELINEUPFORWARD to TEXT,
                FavoriteMatch.HOMELINEUPSUBSTITUTES to TEXT,
                FavoriteMatch.HOMEFORMATION to TEXT,
                FavoriteMatch.AWAYREDCARDS to TEXT,
                FavoriteMatch.AWAYYELLOWCARDS to TEXT,
                FavoriteMatch.AWAYGOALDETAILS to TEXT,
                FavoriteMatch.AWAYLINEUPGOALKEEPER to TEXT,
                FavoriteMatch.AWAYLINEUPDEFENSE to TEXT,
                FavoriteMatch.AWAYLINEUPMIDFIELD to TEXT,
                FavoriteMatch.AWAYLINEUPFORWARD to TEXT,
                FavoriteMatch.AWAYLINEUPSUBSTITUTES to TEXT,
                FavoriteMatch.AWAYFORMATION to TEXT,
                FavoriteMatch.HOMESHOTS to TEXT,
                FavoriteMatch.AWAYSHOTS to TEXT,
                FavoriteMatch.DATEEVENT to TEXT,
                FavoriteMatch.STRTIME to TEXT,
                FavoriteMatch.IDHOMETEAM to TEXT,
                FavoriteMatch.IDAWAYTEAM to TEXT)
        db.createTable(FavoriteTeam.TABLE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.IDTEAM to TEXT,
                FavoriteTeam.STRTEAM to TEXT,
                FavoriteTeam.STRTEAMBADGE to TEXT,
                FavoriteTeam.STRDESCRIPTIONEN to TEXT,
                FavoriteTeam.INTFORMEDYEAR to TEXT
                )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
    }
}