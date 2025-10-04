package net.sage.coin_clicker.ui.upgrades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import net.sage.coin_clicker.databinding.FragmentUpgradesBinding

class UpgradesFragment : Fragment() {

    private var _binding: FragmentUpgradesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(UpgradesModel::class.java)

        _binding = FragmentUpgradesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textUpgrades
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}