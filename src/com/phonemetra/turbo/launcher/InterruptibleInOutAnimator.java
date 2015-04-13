/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.phonemetra.turbo.launcher;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;

public class InterruptibleInOutAnimator {
    private long mOriginalDuration;
    private float mOriginalFromValue;
    private float mOriginalToValue;
    private ValueAnimator mAnimator;

    private boolean mFirstRun = true;

    private Object mTag = null;

    private static final int STOPPED = 0;
    private static final int IN = 1;
    private static final int OUT = 2;

    private int mDirection = STOPPED;

    public InterruptibleInOutAnimator(View view, long duration, float fromValue, float toValue) {
        mAnimator = LauncherAnimUtils.ofFloat(view, fromValue, toValue).setDuration(duration);
        mOriginalDuration = duration;
        mOriginalFromValue = fromValue;
        mOriginalToValue = toValue;

        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mDirection = STOPPED;
            }
        });
    }

    private void animate(int direction) {
        final long currentPlayTime = mAnimator.getCurrentPlayTime();
        final float toValue = (direction == IN) ? mOriginalToValue : mOriginalFromValue;
        final float startValue = mFirstRun ? mOriginalFromValue :
                ((Float) mAnimator.getAnimatedValue()).floatValue();

       
        cancel();

        mDirection = direction;

        long duration = mOriginalDuration - currentPlayTime;
        mAnimator.setDuration(Math.max(0, Math.min(duration, mOriginalDuration)));

        mAnimator.setFloatValues(startValue, toValue);
        mAnimator.start();
        mFirstRun = false;
    }

    public void cancel() {
        mAnimator.cancel();
        mDirection = STOPPED;
    }

    public void end() {
        mAnimator.end();
        mDirection = STOPPED;
    }

    
    public boolean isStopped() {
        return mDirection == STOPPED;
    }

    /**
     * This is the equivalent of calling Animator.start(), except that it can be called when
     * the animation is running in the opposite direction, in which case we reverse
     * direction and animate for a correspondingly shorter duration.
     */
    public void animateIn() {
        animate(IN);
    }

    /**
     * This is the roughly the equivalent of calling Animator.reverse(), except that it uses the
     * same interpolation curve as animateIn(), rather than mirroring it. Also, like animateIn(),
     * if the animation is currently running in the opposite direction, we reverse
     * direction and animate for a correspondingly shorter duration.
     */
    public void animateOut() {
        animate(OUT);
    }

    public void setTag(Object tag) {
        mTag = tag;
    }

    public Object getTag() {
        return mTag;
    }

    public ValueAnimator getAnimator() {
        return mAnimator;
    }
}
