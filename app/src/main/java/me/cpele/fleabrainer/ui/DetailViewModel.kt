package me.cpele.fleabrainer.ui

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.graphics.drawable.Drawable
import android.preference.PreferenceManager
import android.widget.Toast
import me.cpele.fleabrainer.R
import me.cpele.fleabrainer.domain.DatapointBo
import me.cpele.fleabrainer.domain.GoalTiming
import me.cpele.fleabrainer.domain.StatusChange
import me.cpele.fleabrainer.repository.BeeRepository

class DetailViewModel(
    private val app: Application,
    private val beeRepository: BeeRepository,
    private val userName: String,
    private val slug: String
) : ViewModel() {

    val goalTiming: LiveData<GoalTiming> = beeRepository.asyncFindGoalTimingBySlug(slug)
    val status: LiveData<StatusChange> = beeRepository.findLatestStatus()

    fun startStopDrawable(context: Context): LiveData<Drawable> {
        val drawableData = MediatorLiveData<Drawable>()
        drawableData.addSource(goalTiming) {
            drawableData.value =
                when (it?.stopwatch?.running) {
                    false -> context.getDrawable(R.drawable.ic_play_arrow_black_24dp)
                    else -> context.getDrawable(R.drawable.ic_pause_black_24dp)
                }
        }
        return drawableData
    }

    fun forceRefresh() {
        beeRepository.forceRefreshGoalTimingBySlug(slug)
    }

    fun onToggle() {
        Toast.makeText(
            app,
            app.getString(R.string.detail_other_timers_stopping),
            Toast.LENGTH_SHORT
        ).show()
        beeRepository.toggleThenStopOthers(slug)
    }

    fun onCancel() {
        beeRepository.asyncCancelStopwatch(slug)
    }

    fun onSubmit(context: Context) {
        beeRepository.asyncSubmit(
            slug,
            PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(SignInActivity.PREF_ACCESS_TOKEN, null)
        )
    }

    fun findDatapoints(context: Context): LiveData<List<DatapointBo>> {
        return beeRepository.asyncFindDatapointsBySlug(
            slug = slug,
            userName = userName,
            accessToken = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(SignInActivity.PREF_ACCESS_TOKEN, null)
        )
    }

    class Factory(
        private val app: Application,
        private val beeRepository: BeeRepository,
        private val userName: String,
        private val slug: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.cast(DetailViewModel(app, beeRepository, userName, slug)) as T
        }
    }
}
