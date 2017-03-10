package lee.com.basedevlibs.lodamoreview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;


public class LoadMoreView extends BaseIndicatorController {

    public static final float SCALE = 1.0f;

    //scale x ,y
    private float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE};
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#2c8dff"));
        float circleSpacing = 4;
        float radius = (Math.min(getWidth(), getHeight()) - circleSpacing * 2) / 6;
        float x = getWidth() / 2 - (radius * 2 + circleSpacing);
        float y = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX = x + (radius * 2) * i + circleSpacing * i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.drawCircle(0, 0, radius, paint);
            canvas.restore();
        }
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators = new ArrayList<>();
        int[] delays = new int[]{120, 240, 360};
        for (int i = 0; i < 3; i++) {
            final int index = i;

            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);

            scaleAnim.setDuration(750);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);

            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();

                }
            });
            scaleAnim.start();
            animators.add(scaleAnim);
        }
        return animators;
    }
}