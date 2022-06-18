package cohen.dafna.middletermtest.ui

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import cohen.dafna.middletermtest.Logger
import cohen.dafna.middletermtest.R

fun flipCard(context: Context, visibleView: CardView, invisibleView: CardView) {
    try {
        visibleView.visibility = CardView.VISIBLE
        val scale = context.resources.displayMetrics.density
        val cameraDist = 8000 * scale
        visibleView.cameraDistance = cameraDist
        invisibleView.cameraDistance = cameraDist
        val flipOutAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_out) as AnimatorSet
        flipOutAnimatorSet.setTarget(invisibleView)
        val flipInAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_in) as AnimatorSet
        flipInAnimatorSet.setTarget(visibleView)
        flipOutAnimatorSet.start()
        flipInAnimatorSet.start()
        flipInAnimatorSet.doOnEnd { 
            invisibleView.visibility = View.GONE
        }
    } catch (e: Exception) {
        Logger.logHandledException(e)
    }
}