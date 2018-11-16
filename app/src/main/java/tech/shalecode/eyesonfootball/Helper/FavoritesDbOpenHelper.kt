package tech.shalecode.eyesonfootball.Helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import tech.shalecode.eyesonfootball.Models.EventsItem

class FavoritesDbOpenHelper(context : Context) : ManagedSQLiteOpenHelper(context, "FavMatch.db", null, 1) {

    companion object {
        private var instance : FavoritesDbOpenHelper? = null
        @Synchronized
        fun getInstance(context: Context) : FavoritesDbOpenHelper {
            if (instance == null) {
                instance = FavoritesDbOpenHelper(context.applicationContext)
            }
            return instance as FavoritesDbOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(EventsItem.TABLE_FAVORITES, true,
            EventsItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            EventsItem.DATE_EVENT to TEXT,
            EventsItem.ID_AWAY_TEAM to TEXT,
            EventsItem.ID_EVENT to TEXT,
            EventsItem.ID_HOME_TEAM to TEXT,
            EventsItem.ID_LEAGUE to TEXT,
            EventsItem.INT_AWAY_SCORE to TEXT,
            EventsItem.INT_HOME_SCORE to TEXT,
            EventsItem.STR_AWAY_TEAM to TEXT,
            EventsItem.STR_HOME_TEAM to TEXT)
//            EventsItem.HOME_GOAL_DETAILS to TEXT,
//            EventsItem.HOME_SHOTS to TEXT,
//            EventsItem.HOME_LINEUP_GOALKEEPER to TEXT,
//            EventsItem.HOME_LINEUP_DEFENSE to TEXT,
//            EventsItem.HOME_LINEUP_MIDFIELD to TEXT,
//            EventsItem.HOME_LINEUP_FORWARD to TEXT,
//            EventsItem.HOME_LINEUP_SUBSTITUTES to TEXT,
//            EventsItem.AWAY_ID to TEXT,
//            EventsItem.AWAY_TEAM to TEXT,
//            EventsItem.AWAY_SCORE to TEXT,
//            EventsItem.AWAY_FORMATION to TEXT,
//            EventsItem.AWAY_GOAL_DETAILS to TEXT,
//            EventsItem.AWAY_SHOTS to TEXT,
//            EventsItem.AWAY_LINEUP_GOALKEEPER to TEXT,
//            EventsItem.AWAY_LINEUP_DEFENSE to TEXT,
//            EventsItem.AWAY_LINEUP_MIDFIELD to TEXT,
//            EventsItem.AWAY_LINEUP_FORWARD to TEXT,
//            EventsItem.AWAY_LINEUP_SUBSTITUTES to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.dropIndex(EventsItem.TABLE_FAVORITES, true)
        }
    }
}

val Context.database: FavoritesDbOpenHelper
get() = FavoritesDbOpenHelper.getInstance(applicationContext)