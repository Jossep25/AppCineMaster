package pe.edu.idat.appmastercine.model
import android.os.Parcel
import android.os.Parcelable
data class Producto(
    val name: String = "",
    val price: Double = 0.0,
    val image: String = ""
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeString(image)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}
