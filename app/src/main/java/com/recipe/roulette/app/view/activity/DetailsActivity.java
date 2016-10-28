package com.recipe.roulette.app.view.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.recipe.roulette.app.R;
import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.injection.component.AppComponent;
import com.recipe.roulette.app.injection.component.DaggerDetailsViewComponent;
import com.recipe.roulette.app.injection.module.DetailsViewModule;
import com.recipe.roulette.app.presenter.DetailsPresenter;
import com.recipe.roulette.app.presenter.ParentPresenterOwner;
import com.recipe.roulette.app.presenter.loader.PresenterFactory;
import com.recipe.roulette.app.util.LogUtil;
import com.recipe.roulette.app.view.DetailsView;
import com.recipe.roulette.app.view.impl.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.top_relative_layout)
    RelativeLayout mTopRelativeLayout;
    @BindView(R.id.bottom_relative_layout)
    RelativeLayout mBottomRelativeLayout;
    @BindView(R.id.root_layout)
    LinearLayout mRootLayout;

    private boolean hasGifVideoLoaded = false;
    private String mGifVideoUrl = "";

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

        mGifVideoUrl = getExtras(Constants.GIF_VIDEO_URL_KEY);
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
    protected void onStart() {
        super.onStart();
        loadGifVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGifVideoView != null)
            mGifVideoView.stopPlayback();
    }

    @Override
    public void onConnectionChange(boolean isConnected) {
        if (isConnected && !hasGifVideoLoaded) {
            loadGifVideo();
        } else {
            if (mRootLayout != null)
                Snackbar.make(mRootLayout, R.string.notification_no_connection, Snackbar.LENGTH_SHORT).show();
            loading(false);
        }

    }

    @Override
    public void loadGifVideo() {
        if (mGifVideoView != null) {
            loading(true);
            LogUtil.d(Constants.UI_TAG, "loadGifVideo() | mGifVideoUrl = " + mGifVideoUrl);
            mGifVideoView.setVideoURI(Uri.parse(mGifVideoUrl));
            mGifVideoView.requestFocus();
            mGifVideoView.start();
            //when the video is prepared stop loading
            mGifVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    hasGifVideoLoaded = true;
                    loading(false);
                    mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                        @Override
                        public void onBufferingUpdate(MediaPlayer mp, int percent) {
                            LogUtil.d(Constants.UI_TAG, "loadGifVideo() | buffered = " + percent + "%");
                        }
                    });
                }
            });
            //loop video
            mGifVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mGifVideoView.start();
                }
            });
        }
    }

    @Override
    public void loading(boolean showLoading) {
        if (mGifLoadingProgressBar != null) {
            if (showLoading)
                mGifLoadingProgressBar.show();
            else
                mGifLoadingProgressBar.hide();
        }
    }

    @Override
    public DetailsPresenter getParentPresenter() {
        return mPresenter;
    }

    @OnClick({R.id.top_relative_layout, R.id.bottom_relative_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_relative_layout:
                finish();
                break;
            case R.id.bottom_relative_layout:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
