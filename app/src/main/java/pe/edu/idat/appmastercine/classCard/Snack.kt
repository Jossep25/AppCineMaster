package pe.edu.idat.appmastercine.classCard

import android.os.Parcel
import android.os.Parcelable

data class Snack(
    val nombre: String = "",
    val descripcion: String = "",
    val image: String = "",
    val price: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeString(image)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Snack> {
        override fun createFromParcel(parcel: Parcel): Snack {
            return Snack(parcel)
        }

        override fun newArray(size: Int): Array<Snack?> {
            return arrayOfNulls(size)
        }
    }
}