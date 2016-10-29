# RecipeRoulette


<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/logo.png" width="126" height="126"/>

Main goal of the Recipe Roulette app is to provide the user with quick recipe provided that the users enters keyword. It has simple and intuitive UI and it serves the purpose of displaying the MVP principles as well as the use of EventBus, Dagger 2, RxJava 2 (work in progress). In the process it utilizes [food2fork API](http://bit.ly/2dWKwIj) and [reddit API](http://bit.ly/2dWKBvJ).

Here's how the app looks in action:

There are 2 branches of the application that tackle the challenge using different approaches

1. [develop_mvp](http://bit.ly/2dzThKu) - Dagger2, MVP, Retrofit 2, Butterknife and EventBus (greenrobot)

<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_1.gif" width="280" height="500"/>
<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_2.gif" width="280" height="500"/>
<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_3.gif" width="280" height="500"/>



2. [develop_mvp_rxjava](http://bit.ly/2dWKy3f) - Dagger2, MVP, Retrofit 2, Butterknife and RxJava
<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_1.gif" width="280" height="500"/>
<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_rx_2.gif" width="280" height="500"/>
<img src="https://github.com/ViksaaSkool/RecipeRoulette/blob/master/art/preview_rx_3.gif" width="280" height="500"/>


#MVP
The very basic definitions for MVP from Wikipedia, go as follows:
Model–view–presenter (MVP) is a derivation of the model–view–controller (MVC) architectural pattern, and is used mostly for building user interfaces.
In MVP the presenter assumes the functionality of the "middle-man".

    - The model is an interface defining the data to be displayed or otherwise acted upon in the user 
    interface.
    
    - The presenter acts upon the model and the view. It retrieves data from repositories (the model), and 
    formats it for display in the view.
    
    - The view is a passive interface that displays data (the model) and routes user commands 
    (events) to the presenter to act upon that data.
    
<img src="http://bit.ly/2dWSZLL" width="320" height="250" align="middle"/>
<img src="http://bit.ly/2dWXnuc" width="320" height="200" align="middle"/> 

Before continuing, read more about [Android MVP Pattern](http://bit.ly/2dWW12H).

If you understand MVC and are familiar to Android (SDK), that doesn't mean that you'll understand MVP "on the fly".
I'd stretch a bit and say it's almost as learning new language, not to scare you off, but MVP requires paradigm shift in the way
you think of building Android app. So, if you're novice to it, prepare for a ride filled with bumps of the trial-and-error road.
It's in general sense, unconventional approach of doing conventional things - events and service calls. The main thing you need to grasp is thatthe view (Activity/Fragment) is passive. It lets the Presenter (and Interactor) do all the work (business logic) and waits for the results in the callbacks. Summed up: 

The View (Activity or Fragment) is responsible for:

    Instantiating the Presenter, and its binding/unbinding mechanism
    Informing the Presenter of relevant lifecycle events
    Informing the Presenter of input events
    Laying out the views and binding data to them
    Animations
    Event tracking
    Navigating to other screens


The Presenter is responsible for:

    Loading models
    Keeping a reference to the model, and the state of the view
    Formatting what should be displayed, and instructing the View to display it
    Interacting with the repositories (database, network, etc.)
    Deciding the actions to take when input events are received

read more [here](http://bit.ly/2dWPPI6) and also [here](http://bit.ly/2dWRJs4). Last, not least - one of the most 
informative articles on the subject, read it [here] (http://bit.ly/2dWRNbl). 

MVP massive improvement in sense that <b>makes testing, debugging adding new features way easier than other approaches</b>.


#Dagger 2

As it says in the official documentation: "Dagger is a fully static, compile-time dependency injection framework for both Java and Android. It is an adaptation of an earlier version created by Square and now maintained by Google." Dependency injection is a software design pattern that implements inversion of control for resolving dependencies.

Everything you need to know about Dagger (2) is contained in this two images (taken from [this](http://bit.ly/2cQKOCu) presentation):

<img src="http://bit.ly/2dX1uXd" width="350" height="200" align="middle"/> 
<img src="http://bit.ly/2dX2eeM" width="450" height="260" align="middle"/> 

To get started with Dagger 2 just go trough [this](http://bit.ly/2cQL9Fr). Also read this [presentation](http://bit.ly/2dWXZjB)

Read more on Dagger 2 [here] (http://bit.ly/2dWRY6v).


#Android Studio MVP Template

Recipe Roullete utilizes [Android Studio MVP Template](http://bit.ly/2dWWkKZ) as way of implementing MVP (and Dagger 2). 
It provides you with basic templates of the components you need and it's quite the head start you need. It might seem complex at first and take you time to understand what you've imported and how generics work. Make sure you go trough [this](http://bit.ly/2dWS4eo) article before you decide to take a lunge.

<img src="http://bit.ly/2dWXQMR" width="250" height="400" align="middle"/> 

After a while you can play around with the structure to suit your needs.


#Miscellaneous

When writing clean code is important to obey guidelines, coding style and naming conventions. There are more than a few guidelines out there, the one I follow is here ([ribot/android-guidelines](http://bit.ly/2e8dVo9)).

Write build scripts and define build properties in [build.gradle](http://bit.ly/2dK5KM9) file. Note: It's time efficient to build the app from terminal. To create Signed APK Build in root of the project run. After the task is finished ZIP archive with .apk and mapping.txt will be moved in /apk-build.

Windows: gradlew buildSignedAPK

Linux: ./gradlew buildSignedAPK (if file not executable run chmod +x gradlew)

 


Also, when running in debug mode, version your app with suffix to suit your project needs, for Recipe Roulette is 
                  
                  versionNameSuffix "_" + gitBranch() + "_" + getTimeStamp() 

The end result is something like

<img src="http://bit.ly/2dK7PHR" width="400" height="300" align="middle"/> 

Add constants, keys and sensitive info in build.gradle and put their values in local.properites.

