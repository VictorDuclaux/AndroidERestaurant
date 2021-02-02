package fr.isen.duclaux.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item (
    @SerializedName("id") val id: String,
    @SerializedName("name_fr") val nameItem: String,
    @SerializedName("id_category") val idCateg: String,
    @SerializedName("categ_name_fr") val categNameFr: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("ingredients") val ingredients: List<ingredient>
):Serializable {

    

    /*fun getAllPictures() = if (images.isNotEmpty() && images.any { it.isNotEmpty() }){
        images.filter { it.isNotEmpty() }
    }else {
        null
    }*/
}