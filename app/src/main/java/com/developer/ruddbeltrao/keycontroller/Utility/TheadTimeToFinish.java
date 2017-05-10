package com.developer.ruddbeltrao.keycontroller.Utility;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;

/**
 * Criado por Rudda Beltrao em 24/07/2015 as 03:20.
 */
public class TheadTimeToFinish extends CountDownTimer {

    private Activity activityToFinish;



    public TheadTimeToFinish(long millisInFuture, long countDownInterval, Activity activityToFinish) {
        super(millisInFuture, countDownInterval);
        this.activityToFinish = activityToFinish;

    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

        activityToFinish.finish();

    }
}
