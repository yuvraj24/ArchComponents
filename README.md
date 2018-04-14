# ArchComponents

# LiveData
To enhance this approach we can use a new architecture component called LiveData. LiveData is a data holder class that keeps a value and allows this value to be observed. Unlike a regular observable, LiveData respects the lifecycle of app components, such that the Observer can specify a Lifecycle in which it should observe.

LiveData considers an Observer to be in an active state if the Observer’s Lifecycle is in STARTED or RESUMED state. If the Lifecycle is not in an active state (STARTED or RESUMED), the observer isn’t called even if the value changes. If the Lifecycle is destroyed, the observer is removed automatically. There might be multiple fragments and activities that are observing LiveData instance, and LiveData gracefully manages them such that it connects to the system service only if any of them is visible (that is, active).

LiveData can easily be used with Room to monitor the changes in database. Room will create a the live data object observing the database.
 
The LiveData class provides the following advantages:

No memory leaks: Since the Observers are bound to their own Lifecycle objects, they are automatically cleaned when their Lifecycle is destroyed.
No crashes due to stopped activities: If the Observer’s Lifecycle is inactive (like an activity in the back stack), they won’t receive change events.
Always up to date data: If a Lifecycle starts again (like an activity going back to started state from the back stack) it receives the latest location data (if it didn’t already).
Proper configuration change: If an activity or fragment is re-created due to a configuration change (like device rotation), it instantly receives the last available Location data.
Sharing Resources: Now we can keep a single instance of class, connect to the system service just once, and properly support all observers in the app.
No more manual lifecycle handling: Fragment can observe the data when it wants to, does not worry about being stopped or start observing after being stopped. LiveData automatically manages all of this since the fragment provided its Lifecycle while observing.
