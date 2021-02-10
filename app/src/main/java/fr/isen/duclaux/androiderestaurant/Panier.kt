package fr.isen.duclaux.androiderestaurant

import android.content.Context
import com.google.gson.GsonBuilder
import java.io.File
import java.io.Serializable

class Panier(val items: MutableList<PanierItem>) : Serializable {

    var itemsCount: Int = 0
        get() {
            return if (items.count() > 0) {
                items
                    .map { it.nb }
                    .reduce { acc, i -> acc + i }
            } else {
                0
            }
        }

    fun addItem(item: PanierItem) {
        val existingItem = items.firstOrNull {
            it.item.nameItem == item.item.nameItem
        }
        existingItem?.let {
            existingItem.nb += item.nb
        } ?: run {
            items.add(item)
        }
    }

    fun save(context: Context) {
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        jsonFile.writeText(GsonBuilder().create().toJson(this))
        updateCounter(context)
    }

    fun clear() {
        items.clear()
    }

    private fun updateCounter(context: Context) {
        val sharedPreferences =
            context.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, itemsCount)
        editor.apply()
    }
        companion object {
            fun getBasket(context: Context): Panier {
                val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
                return if (jsonFile.exists()) {
                    val json = jsonFile.readText()
                    GsonBuilder().create().fromJson(json, Panier::class.java)
                } else {
                    Panier(mutableListOf())
                }
            }

            const val BASKET_FILE = "basket.json"
            const val ITEMS_COUNT = "ITEMS_COUNT"
            const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
        }
    }
