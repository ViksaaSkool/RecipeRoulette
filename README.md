# RecipeRoulette


<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/logo.png" width="126" height="126"/>


Here's how the app looks in action:

<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_1.gif" width="280" height="500"/>
<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_2.gif" width="280" height="500"/>
<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_3.gif" width="280" height="500"/>


[go to main Readme](http://bit.ly/2dKfgin)


# EventBus (greenrobot)

[EventBus](http://bit.ly/2dLqctF) is an open-source library for Android using the publisher/subscriber pattern for loose coupling. It's a great way of notifying components of the app when some event has finished ad pass result. ([usage in 3 easy steps](http://bit.ly/2e6fgwd))

<img src="http://bit.ly/2e6f1RP" width="550" height="220" />

One way that the EventBus is used in Recepie Roulette is to notify the BaseActivity that there has been change in the Internet connection. This way, you can address this issue on UI level if that's needed (displaying Snackbar notification). 

In [NetworkStateReceiver](http://bit.ly/2e6jzYb) (also [declared](http://bit.ly/2e6hhYX) in the AndroidManifes.xml):

 ```java  
   @Override
    public void onReceive(Context context, Intent intent) {
         if (intent.getExtras() != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) 
                EventBus.getDefault().post(new InternetConnectionEvent(activeNetwork.isConnected()));
             else
                EventBus.getDefault().post(new InternetConnectionEvent(false));
        }
    }
  ```

      
where InternetConnectionEvent is definied as:   

  ```java  
  public class InternetConnectionEvent {
    private final boolean hasConnection;
    //additionally types of connection!

    public InternetConnectionEvent(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public boolean isConnected() {
        return hasConnection;
    }
      }
  ```

In BaseActivity: 

 ```java  
  @Override
      protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        //...
      }
    
    
        @Override
       protected void onStop() {
        //...
        EventBus.getDefault().unregister(this);
        super.onStop();
      }
    
      @Subscribe
      public void handleConnectionChange(InternetConnectionEvent connectionEvent) {
        LogUtil.d(Constants.APP_TAG, "Connection change! isConnected == " + connectionEvent.isConnected());
        onConnectionChange(connectionEvent.isConnected());
      }
  ```
    

The same mechanism is used to notify the presenter that api call has finished, see [Foof2ForkApi](http://bit.ly/2e6hWdd) class. 
