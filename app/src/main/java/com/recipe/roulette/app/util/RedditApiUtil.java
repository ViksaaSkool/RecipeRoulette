package com.recipe.roulette.app.util;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.model.reddit.ChildData;

import java.util.UUID;

/**
 * Created by varsovski on 10-Oct-16.
 */

public class RedditApiUtil {

    private static final String YOUTUBE = "youtube";
    private static final String GIF = ".gif";
    private static final String GIFV = ".gifv";

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static int getRecipeType(ChildData dataItem) {
        if (dataItem.getDomain() != null && dataItem.getDomain().contains(YOUTUBE))
            return Constants.VIDEO;
        else if (dataItem.getUrl() != null
                && (dataItem.getUrl().contains(GIF) || dataItem.getUrl().contains(GIFV)))
            return Constants.GIF;
        else
            return -1;
    }


    public static boolean isVideoOrGif(ChildData dataItem) {
        return getRecipeType(dataItem) != -1;
    }
}
