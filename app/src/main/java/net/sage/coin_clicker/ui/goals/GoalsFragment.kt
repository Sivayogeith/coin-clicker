package net.sage.coin_clicker.ui.goals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import net.sage.coin_clicker.FragmentWithSave
import net.sage.coin_clicker.GOAL
import net.sage.coin_clicker.R
import net.sage.coin_clicker.databinding.FragmentGoalsBinding

class GoalsFragment : FragmentWithSave() {
    private var _binding: FragmentGoalsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentGoalsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val goalDescView: TextView = binding.goalDesc
        val goalButtonView: Button = binding.goalButton
        val gfView: ImageView = binding.gf
        val gfTextView: TextView = binding.gfText

        if (save.upgrades["gf"] != null){
            showGF(goalButtonView, goalDescView, gfView, gfTextView)
        } else {
            gfView.visibility = View.INVISIBLE
            gfTextView.visibility = View.INVISIBLE
            if (save.count >= GOAL) {
                goalButtonView.text = getString(R.string.completeGoalButton)
                goalDescView.text = getString(R.string.completeGoalDesc)
                goalButtonView.setOnClickListener {
                    showGF(goalButtonView, goalDescView, gfView, gfTextView)
                    save.upgrades["gf"] = 1
                    save.count -= GOAL
                    save()
                }
            } else {
                goalButtonView.text = getString(R.string.incompleteGoalButton, save.count, GOAL)
                goalDescView.text = getString(R.string.incompleteGoalDesc)
            }
        }

        return root
    }

    fun showGF(goalButtonView: Button, goalDescView: TextView, gfView: ImageView, gfTextView: TextView) {
        goalButtonView.visibility = View.INVISIBLE
        goalDescView.visibility = View.INVISIBLE

        gfView.visibility = View.VISIBLE
        gfTextView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}