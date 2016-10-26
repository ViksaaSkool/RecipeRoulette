package com.recipe.roulette.app.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerDetailsViewComponent;
import com.recipe.roulette.app.injection.module.DetailsViewModule;
import com.recipe.roulette.app.presenter.DetailsPresenter;
import com.recipe.roulette.app.presenter.ParentPresenterOwner;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.view.DetailsView;
import com.recipe.roulette.app.view.impl.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by varsovski on 25-Oct-16.
 */

public class DetailsActivity extends BaseActivity<DetailsPresenter, DetailsView> implements DetailsView, ParentPresenterOwner<DetailsPresenter> {

    @Inject
    PresenterFactory<DetailsPresenter> mPresenterFactory;
    @BindView(R.id.gif_loading_progress_bar)
    ContentLoadingProgressBar mGifLoadingProgressBar;
    @BindView(R.id.gif_video_view)
    VideoView mGifVideoView;
    @BindView(R.id.root_layout)
    RelativeLayout mRootLayout;

    private boolean hasVideoLoaded = false;

    @NonNull
    @Override
    protected PresenterFactory<DetailsPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent appComponent) {
        DaggerDetailsViewComponent.builder()
                .appComponent(appComponent)
                .detailsViewModule(new DetailsViewModule())
                .build()
                .inject(this);

    }

    @Override
    public void onConnectionChange(boolean isConnected) {
        if (isConnected) {


        } else {
            if (mRootLayout != null)
                Snackbar.make(mRootLayout, R.string.notification_no_connection, Snackbar.LENGTH_SHORT).show();
        }

    }


    @Override
    public DetailsPresenter getParentPresenter() {
        return mPresenter;
    }
}
