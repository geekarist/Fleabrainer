package me.cpele.fleabrainer.ui

import android.support.v7.widget.RecyclerView
import me.cpele.fleabrainer.databinding.ViewItemBinding
import me.cpele.fleabrainer.domain.GoalTiming

class GoalRecyclerViewHolder(
        binding: ViewItemBinding,
        listener: GoalGeneralViewHolder.Listener
) : RecyclerView.ViewHolder(binding.root), GoalViewListener {

    private var generalViewHolder = GoalGeneralViewHolder(binding, listener)

    fun bind(goalTiming: GoalTiming) = generalViewHolder.bind(goalTiming)

    fun attach() = generalViewHolder.attach()

    override fun onClickItem(goalTiming: GoalTiming) = generalViewHolder.onClickItem(goalTiming)
    override fun onClickTimer(goalTiming: GoalTiming) = generalViewHolder.onClickTimer(goalTiming)
    override fun onClickReset(goalTiming: GoalTiming) = generalViewHolder.onClickReset(goalTiming)
    override fun onClickSubmit(goalTiming: GoalTiming) = generalViewHolder.onClickSubmit(goalTiming)

    override fun onLongClickTimer(goalTiming: GoalTiming): Boolean {
        return generalViewHolder.onLongClickTimer(goalTiming)
    }

    fun detach() = generalViewHolder.detach()
}