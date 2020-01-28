package by.onexeor.testtask.model.products

import android.os.Parcel
import android.os.Parcelable

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote.model)
 */
data class UIProduct(
    val image: String? = null,
    val price: Int? = null,
    val formattedPrice: String? = null,
    val productId: String? = null,
    val name: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeValue(price)
        parcel.writeString(formattedPrice)
        parcel.writeString(productId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UIProduct> {
        override fun createFromParcel(parcel: Parcel): UIProduct {
            return UIProduct(parcel)
        }

        override fun newArray(size: Int): Array<UIProduct?> {
            return arrayOfNulls(size)
        }
    }
}
