package solid.icon.english.main_adapters;

import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.animation.DecelerateInterpolator;

public class CustomScaleTransition {

    public static TransitionSet enterTransitionSet = new TransitionSet()
            .setDuration(400)
            .setInterpolator(new DecelerateInterpolator())
            .addTransition(new Slide(Gravity.END));


    public static TransitionSet exitTransitionSet = new TransitionSet()
            .setDuration(400)
            .setInterpolator(new DecelerateInterpolator())
            .addTransition(new Slide(Gravity.START));

}

