package pe.edu.idat.appmastercine.classCard

import android.os.Parcel
import android.os.Parcelable

data class CardModel(override val nombrePeli: String,
                     override val sinopsis: String,
                     override val description: String,
                     override val imageResId: Int,
                     override val imageResId2: Int
) : CardDetails, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sinopsis)
        parcel.writeString(description)
        parcel.writeInt(imageResId)
        parcel.writeInt(imageResId2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardModel> {
        override fun createFromParcel(parcel: Parcel): CardModel {
            return CardModel(parcel)
        }

        override fun newArray(size: Int): Array<CardModel?> {
            return arrayOfNulls(size)
        }
    }
}
