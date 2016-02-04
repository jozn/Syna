package com.fima.glowpadview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.jivesoftware.smackx.delay.packet.DelayInformation;

class Tweener {
    private static final boolean DEBUG = false;
    private static final String TAG = "Tweener";
    private static AnimatorListener mCleanupListener;
    private static HashMap<Object, Tweener> sTweens;
    ObjectAnimator animator;

    /* renamed from: com.fima.glowpadview.Tweener.1 */
    class C00931 extends AnimatorListenerAdapter {
        C00931() {
        }

        public void onAnimationCancel(Animator animator) {
            Tweener.remove(animator);
        }

        public void onAnimationEnd(Animator animator) {
            Tweener.remove(animator);
        }
    }

    static {
        sTweens = new HashMap();
        mCleanupListener = new C00931();
    }

    public Tweener(ObjectAnimator objectAnimator) {
        this.animator = objectAnimator;
    }

    private static void remove(Animator animator) {
        Iterator it = sTweens.entrySet().iterator();
        while (it.hasNext()) {
            if (((Tweener) ((Entry) it.next()).getValue()).animator == animator) {
                it.remove();
                return;
            }
        }
    }

    private static void replace(ArrayList<PropertyValuesHolder> arrayList, Object... objArr) {
        for (Object obj : objArr) {
            Tweener tweener = (Tweener) sTweens.get(obj);
            if (tweener != null) {
                tweener.animator.cancel();
                if (arrayList != null) {
                    tweener.animator.setValues((PropertyValuesHolder[]) arrayList.toArray(new PropertyValuesHolder[arrayList.size()]));
                } else {
                    sTweens.remove(tweener);
                }
            }
        }
    }

    public static void reset() {
        sTweens.clear();
    }

    public static Tweener to(Object obj, long j, Object... objArr) {
        ObjectAnimator ofPropertyValuesHolder;
        Tweener tweener;
        long j2 = 0;
        AnimatorUpdateListener animatorUpdateListener = null;
        AnimatorListener animatorListener = null;
        TimeInterpolator timeInterpolator = null;
        ArrayList arrayList = new ArrayList(objArr.length / 2);
        int i = 0;
        while (i < objArr.length) {
            if (objArr[i] instanceof String) {
                TimeInterpolator timeInterpolator2;
                AnimatorListener animatorListener2;
                AnimatorUpdateListener animatorUpdateListener2;
                long j3;
                String str = (String) objArr[i];
                Object obj2 = objArr[i + 1];
                if (!"simultaneousTween".equals(str)) {
                    if ("ease".equals(str)) {
                        timeInterpolator2 = (TimeInterpolator) obj2;
                        animatorListener2 = animatorListener;
                        animatorUpdateListener2 = animatorUpdateListener;
                        j3 = j2;
                    } else if ("onUpdate".equals(str) || "onUpdateListener".equals(str)) {
                        timeInterpolator2 = timeInterpolator;
                        animatorUpdateListener2 = (AnimatorUpdateListener) obj2;
                        animatorListener2 = animatorListener;
                        j3 = j2;
                    } else if ("onComplete".equals(str) || "onCompleteListener".equals(str)) {
                        animatorListener2 = (AnimatorListener) obj2;
                        timeInterpolator2 = timeInterpolator;
                        animatorUpdateListener2 = animatorUpdateListener;
                        j3 = j2;
                    } else if (DelayInformation.ELEMENT.equals(str)) {
                        animatorUpdateListener2 = animatorUpdateListener;
                        AnimatorListener animatorListener3 = animatorListener;
                        j3 = ((Number) obj2).longValue();
                        timeInterpolator2 = timeInterpolator;
                        animatorListener2 = animatorListener3;
                    } else if (!"syncWith".equals(str)) {
                        if (obj2 instanceof float[]) {
                            arrayList.add(PropertyValuesHolder.ofFloat(str, new float[]{((float[]) obj2)[0], ((float[]) obj2)[1]}));
                            timeInterpolator2 = timeInterpolator;
                            animatorListener2 = animatorListener;
                            animatorUpdateListener2 = animatorUpdateListener;
                            j3 = j2;
                        } else if (obj2 instanceof int[]) {
                            arrayList.add(PropertyValuesHolder.ofInt(str, new int[]{((int[]) obj2)[0], ((int[]) obj2)[1]}));
                            timeInterpolator2 = timeInterpolator;
                            animatorListener2 = animatorListener;
                            animatorUpdateListener2 = animatorUpdateListener;
                            j3 = j2;
                        } else if (obj2 instanceof Number) {
                            arrayList.add(PropertyValuesHolder.ofFloat(str, new float[]{((Number) obj2).floatValue()}));
                            timeInterpolator2 = timeInterpolator;
                            animatorListener2 = animatorListener;
                            animatorUpdateListener2 = animatorUpdateListener;
                            j3 = j2;
                        } else {
                            throw new IllegalArgumentException("Bad argument for key \"" + str + "\" with value " + obj2.getClass());
                        }
                    }
                    i += 2;
                    j2 = j3;
                    animatorListener = animatorListener2;
                    animatorUpdateListener = animatorUpdateListener2;
                    timeInterpolator = timeInterpolator2;
                }
                timeInterpolator2 = timeInterpolator;
                animatorListener2 = animatorListener;
                animatorUpdateListener2 = animatorUpdateListener;
                j3 = j2;
                i += 2;
                j2 = j3;
                animatorListener = animatorListener2;
                animatorUpdateListener = animatorUpdateListener2;
                timeInterpolator = timeInterpolator2;
            } else {
                throw new IllegalArgumentException("Key must be a string: " + objArr[i]);
            }
        }
        Tweener tweener2 = (Tweener) sTweens.get(obj);
        if (tweener2 == null) {
            ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(obj, (PropertyValuesHolder[]) arrayList.toArray(new PropertyValuesHolder[arrayList.size()]));
            tweener = new Tweener(ofPropertyValuesHolder);
            sTweens.put(obj, tweener);
        } else {
            ObjectAnimator objectAnimator = ((Tweener) sTweens.get(obj)).animator;
            replace(arrayList, obj);
            ObjectAnimator objectAnimator2 = objectAnimator;
            tweener = tweener2;
            ofPropertyValuesHolder = objectAnimator2;
        }
        if (timeInterpolator != null) {
            ofPropertyValuesHolder.setInterpolator(timeInterpolator);
        }
        ofPropertyValuesHolder.setStartDelay(j2);
        ofPropertyValuesHolder.setDuration(j);
        if (animatorUpdateListener != null) {
            ofPropertyValuesHolder.removeAllUpdateListeners();
            ofPropertyValuesHolder.addUpdateListener(animatorUpdateListener);
        }
        if (animatorListener != null) {
            ofPropertyValuesHolder.removeAllListeners();
            ofPropertyValuesHolder.addListener(animatorListener);
        }
        ofPropertyValuesHolder.addListener(mCleanupListener);
        return tweener;
    }

    Tweener from(Object obj, long j, Object... objArr) {
        return to(obj, j, objArr);
    }
}
