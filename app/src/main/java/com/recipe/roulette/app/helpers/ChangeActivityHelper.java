package com.recipe.roulette.app.helpers;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by varsovski on 18-Mar-15.
 */
public class ChangeActivityHelper implements OnClickListener {

    private AppCompatActivity _source;
    private Class<?> _destination;
    private boolean _finishActivity;

    public ChangeActivityHelper(AppCompatActivity source, Class<?> destination) {
        super();
        _source = source;
        _destination = destination;
        _finishActivity = false;
    }

    public ChangeActivityHelper(AppCompatActivity source, Class<?> destination, boolean finishActivity) {
        this(source, destination);
        _finishActivity = finishActivity;
    }

    @Override
    public void onClick(View v) {
        changeActivity(_source, _destination, _finishActivity);
    }

    public static void changeActivity(AppCompatActivity source, Class<?> destination) {
        changeActivity(source, destination, false);
    }

    public static void changeActivity(AppCompatActivity source, Class<?> destination, Boolean finishContext) {
        if (finishContext)
            source.finish();

        Intent iTransition = new Intent(source, destination);
        source.startActivity(iTransition);
    }


    public static void changeActivityExtra(Activity source, Class<?> destination, Bundle b, boolean finishContext) {
        if (finishContext)
            source.finish();

        Intent iTransition = new Intent(source, destination);
        iTransition.putExtras(b);
        source.startActivity(iTransition);
    }


}
