package me.cpele.beetimer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import java.util.concurrent.TimeUnit

class GoalViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private val mContext: Context
        get() = itemView.context

    fun bind(goal: Goal) {
        val idView: TextView = itemView.findViewById(R.id.item_id)
        idView.text = goal.slug

        val titleView: TextView = itemView.findViewById(R.id.item_title)
        titleView.text = goal.title

        val rateView: TextView = itemView.findViewById(R.id.item_rate)
        rateView.text = mContext.getString(R.string.item_rate, goal.rate, goal.runits)

        val bareMinView: TextView = itemView.findViewById(R.id.item_bare_min)
        bareMinView.text = goal.limsum

        @Suppress("DEPRECATION")
        goal.losedate?.let {

            val color = goal.color

            val numDerailDays =
                    TimeUnit.SECONDS.toDays(goal.losedate) -
                            TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis())
            val derailDaysView: TextView = itemView.findViewById(R.id.item_derail_days)
            derailDaysView.text = mContext.getString(R.string.item_derail_date, numDerailDays)
            derailDaysView.setBackgroundColor(mContext.resources.getColor(color))

            val derailHoursView: TextView = itemView.findViewById(R.id.item_derail_time)

            val numDerailHours =
                    TimeUnit.SECONDS.toHours(goal.losedate) -
                            TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis()) -
                            TimeUnit.DAYS.toHours(numDerailDays)

            val numDerailMin = TimeUnit.SECONDS.toMinutes(goal.losedate) -
                    TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()) -
                    TimeUnit.DAYS.toMinutes(numDerailDays) -
                    TimeUnit.HOURS.toMinutes(numDerailHours)

            val numDerailSec = TimeUnit.SECONDS.toSeconds(goal.losedate) -
                    TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) -
                    TimeUnit.DAYS.toSeconds(numDerailDays) -
                    TimeUnit.HOURS.toSeconds(numDerailHours) -
                    TimeUnit.MINUTES.toSeconds(numDerailMin)

            derailHoursView.text = mContext.getString(
                    R.string.item_derail_time, numDerailHours, numDerailMin, numDerailSec)

            derailHoursView.setBackgroundColor(mContext.resources.getColor(color))
        }
    }
}