package pe.edu.idat.appmastercine.model
import android.os.Parcel
import android.os.Parcelable
data class Pelicula(
    val titulo: String = "",
    val sinopsis: String = "",
    val pais: String = "",
    val clasificacion: String = "",
    val duracion: String = "",
    val fecha: String = "",
    val genero: String = "",
    val imagen_principal: String?,
    val imagen_secundaria: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(sinopsis)
        parcel.writeString(pais)
        parcel.writeString(clasificacion)
        parcel.writeString(duracion)
        parcel.writeString(fecha)
        parcel.writeString(genero)
        parcel.writeString(imagen_principal)
        parcel.writeString(imagen_secundaria)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        null,
        null
    )
}
