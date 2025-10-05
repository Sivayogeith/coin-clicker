package net.sage.coin_clicker.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.sage.coin_clicker.R
import net.sage.coin_clicker.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    @Serializable
    data class Save (
        var count: Int = 0,
        var upgrades: Array<Int> = arrayOf(0)) {}

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var save: Save = Save(0, arrayOf(0))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        println(Json.encodeToString(save))

        try {
            save = Json.decodeFromString<Save>(context?.openFileInput("save.json")?.bufferedReader().use { it?.readText() } as String)
        } catch (_: Exception) { save() }

        val countView: TextView = binding.count
        val clickerView: ImageView = binding.clicker

        clickerView.setOnClickListener {
            save.count++
            countView.text = getString(R.string.count, save.count)
            save()
        }

        countView.text = getString(R.string.count, save.count)

        return root
    }

    fun save() {
        context?.openFileOutput("save.json", Context.MODE_PRIVATE).use {
            it?.write(Json.encodeToString(save).toByteArray())
        }
    }

    override fun onDestroyView() {
        save()
        super.onDestroyView()
        _binding = null
    }
}