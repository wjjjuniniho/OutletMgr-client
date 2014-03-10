package tongji.sse.outletmanager.ui.global;

import android.view.animation.AlphaAnimation;

public class Global {
	public static AlphaAnimation inAnimation;
    public static AlphaAnimation outAnimation;
    private static final float alpha = 0.2f;
    private static final int ANIMATION_DURATION = 1000;
    static {
        inAnimation = new AlphaAnimation(alpha, 1);
        inAnimation.setDuration(ANIMATION_DURATION);
        inAnimation.setFillAfter(true);
        outAnimation = new AlphaAnimation(1, alpha);
        outAnimation.setDuration(ANIMATION_DURATION);
        outAnimation.setFillAfter(true);
        
    }
}
