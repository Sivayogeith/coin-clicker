package net.sage.coin_clicker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import net.sage.coin_clicker.FragmentWithSave
import net.sage.coin_clicker.R
import net.sage.coin_clicker.databinding.FragmentHomeBinding
import org.mariuszgromada.math.mxparser.Expression

class HomeFragment : FragmentWithSave() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val countView: TextView = binding.count
        val clickerView: ImageView = binding.clicker

        clickerView.setOnClickListener {
            save.count += Expression("1" + save.multiplier).calculate().toInt()
            countView.text = getString(R.string.count, save.count)
            save()
        }

        countView.text = getString(R.string.count, save.count)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}