package net.sage.coin_clicker.ui.upgrades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.sage.coin_clicker.FragmentWithSave
import net.sage.coin_clicker.databinding.FragmentUpgradesBinding
import net.sage.coin_clicker.UPGRADES
import org.mariuszgromada.math.mxparser.Expression

class UpgradesFragment : FragmentWithSave() {

    private var _binding: FragmentUpgradesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentUpgradesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val customAdapter = UpgradesRecyclerAdapter(UPGRADES, save, object : UpgradesRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val clickedUpgrade = UPGRADES[position]
                val id = clickedUpgrade["id"].toString()
                var bought = 0
                var price = clickedUpgrade["price"] as Int

                if (save.upgrades.contains(id)) {
                    bought = save.upgrades.get(id)?.plus(1) as Int
                    price = Expression("${clickedUpgrade["price"]}*${bought}${clickedUpgrade["mul"]}").calculate().toInt()
                }

                toast?.cancel()

                if (save.count >= price) {
                    save.upgrades.set(id, bought)
                    save.multiplier =
                        save.multiplier.plus(clickedUpgrade["mul"].toString())

                    toast = Toast.makeText(
                        view.context,
                        "Bought ${clickedUpgrade["name"]}",
                        Toast.LENGTH_SHORT
                    )

                    save.count -= price
                    save()
                } else {
                    toast = Toast.makeText(
                        view.context,
                        "Not enough humans",
                        Toast.LENGTH_SHORT
                    )
                }

                toast?.show()
            }
        })

        val recyclerView: RecyclerView = binding.upgrades
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = customAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}