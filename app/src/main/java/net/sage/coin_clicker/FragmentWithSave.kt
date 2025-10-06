package net.sage.coin_clicker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

open class FragmentWithSave: Fragment() {

    @Serializable
    data class Save (
        var count: Int,
        var upgrades: MutableMap<String, Int> = mutableMapOf(),
        var multiplier: String
    ) {}

    var save: Save = Save(0, mutableMapOf(), "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            save = Json.decodeFromString<Save>(context?.openFileInput("save.json")?.bufferedReader().use { it?.readText() } as String)
        } catch (_: Exception) { save() }

        return null
    }

    fun save() {
        context?.openFileOutput("save.json", Context.MODE_PRIVATE).use {
            it?.write(Json.encodeToString(save).toByteArray())
        }
    }

    override fun onDestroyView() {
        save()
        super.onDestroyView()
    }
}