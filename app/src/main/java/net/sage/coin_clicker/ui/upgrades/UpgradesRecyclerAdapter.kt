package net.sage.coin_clicker.ui.upgrades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.sage.coin_clicker.FragmentWithSave
import net.sage.coin_clicker.R
import org.mariuszgromada.math.mxparser.Expression

class UpgradesRecyclerAdapter(
    private val dataSet: Array<out Map<String, Any>>,
    private val save: FragmentWithSave.Save,
    private val itemClickListener: OnItemClickListener? = null
) : RecyclerView.Adapter<UpgradesRecyclerAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val upgradeName: TextView = view.findViewById(R.id.upgradeName)
        val price: TextView = view.findViewById(R.id.price)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClickListener?.onItemClick(view, bindingAdapterPosition)
            onBindViewHolder(this, bindingAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.upgrade, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        var bought: Int
        var price = item["price"]

        if (save.upgrades.contains(item["id"])) {
            bought = save.upgrades.get(item["id"])?.plus(1) as Int
            price = Expression("${item["price"]}*${bought}${item["mul"]}").calculate().toInt()
        }

        viewHolder.price.text = "${price} Humans"
        viewHolder.upgradeName.text = item["name"].toString()
    }

    override fun getItemCount() = dataSet.size
}
