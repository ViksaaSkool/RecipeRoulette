package com.recipe.roulette.app.constants;

/**
 * Created by varsovski on 21-Aug-16.
 */
public class Constants {

    //FOOD2FORK API
    public static final String BASE_F2F_URL = "http://food2fork.com/api/";
    public static final String SEARCH_RECIPES = "search";
    public static final String GET_RECIPE = "get";
    public static final String KEY_PARAM = "key";
    public static final String QUERY_PARAM = "q";
    public static final int F2F_RESULTS_ERROR = -1;
    public static final int F2F_NO_RESULTS = 0;

    //REDDIT API
    public static final String BASE_REDDIT_URL = "https://www.reddit.com/";
    public static final String BASE_OAUTH_REDDIT_URL = "https://oauth.reddit.com";
    public static final String SEARCH_SUBREDDIT = "r/{subreddit}/search.json";
    public static final String REDDIT_OATH2_PATH = "/api/v1/access_token";
    public static final String SUBREDDIT_PATH = "subreddit";
    public static final String RESTRICT_PARAM = "restrict_sr";
    public static final String SORT_PARAM = "sort";
    public static final String SCOPE_PARAM = "t";
    public static final String TITLE_SEARCH_VALUE = "title:";
    public static final String RESTRICT_VALUE = "on";
    public static final String SORT_VALUE = "relevance";
    public static final String SCOPE_VALUE = "all";
    public static final String BODY_PARAMS_GET_TOKEN = "grant_type=https://oauth.reddit.com/grants/installed_client&device_id=";
    public static final String BODY_PARAMS_REFRESH_TOKEN = "grant_type=refresh_token";
    public static final String SUBREDDIT_RECIPE_GIFS = "recipegifs";
    public static final String SUBREDDIT_GIF_RECIPES = "gifrecipes";
    public static final String SUBREDDIT_COOKING_VIDEOS = "cookingvideos";

    public static final int VIDEO = 1;
    public static final int GIF = 2;
    //https://www.reddit.com/r/recipegifs/search.json?q=title%3Aapple&restrict_sr=on&sort=relevance&t=all

    //LOG TAGS
    public static final String API_TAG = "APITAG";
    public static final String UI_TAG = "UITAG";
    public static final String APP_TAG = "APPTAG";
    public static final String ADPR_TAG = "ADPRTAG";

    //CACHE KEYS
    public static final String LAYOUT_KEY = "layout_key";

    //VALUES
    public static final boolean LIST_VIEW = true;
    public static final boolean SWIPE_VIEW = false;

    //UI
    public static final String SWIPE_CARD_INDEX_KEY = "swipe_card_index_key";
    public static final String COUNT_KEY = "count_key";
}
