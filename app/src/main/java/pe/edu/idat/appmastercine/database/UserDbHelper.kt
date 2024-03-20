package pe.edu.idat.appmastercine.database

import android.provider.BaseColumns
import java.text.SimpleDateFormat
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date
import java.util.Locale

object UserContract {
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "usuarios"
        const val COLUMN_NAME_NOMBRES = "nombres"
        const val COLUMN_NAME_DNI = "dni"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_PASSWORD = "password"
        const val COLUMN_NAME_FECHA_NACIMIENTO = "fecha_nacimiento"
    }
}
class UserDbHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Usuarios.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_USERS_TABLE = """
            CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${UserContract.UserEntry.COLUMN_NAME_NOMBRES} TEXT,
                ${UserContract.UserEntry.COLUMN_NAME_DNI} TEXT,
                ${UserContract.UserEntry.COLUMN_NAME_EMAIL} TEXT,
                ${UserContract.UserEntry.COLUMN_NAME_PASSWORD} TEXT,
                ${UserContract.UserEntry.COLUMN_NAME_FECHA_NACIMIENTO} TEXT
            )
        """

        db.execSQL(SQL_CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME}")
        onCreate(db)
    }

    fun insertUser(
        nombres: String,
        dni: String,
        email: String,
        password: String,
        fechaNacimiento: Date
    ): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(UserContract.UserEntry.COLUMN_NAME_NOMBRES, nombres)
            put(UserContract.UserEntry.COLUMN_NAME_DNI, dni)
            put(UserContract.UserEntry.COLUMN_NAME_EMAIL, email)
            put(UserContract.UserEntry.COLUMN_NAME_PASSWORD, password)
            put(
                UserContract.UserEntry.COLUMN_NAME_FECHA_NACIMIENTO,
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(fechaNacimiento)
            )
        }
        return db.insert(UserContract.UserEntry.TABLE_NAME, null, values)
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val selection =
            "${UserContract.UserEntry.COLUMN_NAME_EMAIL} = ? AND ${UserContract.UserEntry.COLUMN_NAME_PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor: Cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val count = cursor.count
        cursor.close()
        return count > 0
    }
}