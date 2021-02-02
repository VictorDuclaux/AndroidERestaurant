package fr.isen.duclaux.androiderestaurant

import com.google.gson.annotations.SerializedName

data class ingredient (
    @SerializedName("id") val id: String,
    @SerializedName("id_shop") val idShop: String,
    @SerializedName("name_fr") val nameIngrediant: String,
    @SerializedName("create_date") val creationDate: String,
    @SerializedName("update_date") val updateDate: String,
    @SerializedName("id_pizza") val idPizza: String
)
