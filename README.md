

![Alt text](https://github.com/yuvraj24/ArchComponents/blob/master/image/kotlin-arch_android.png)

Check out the Demo app from the Play Store link below.

<a href='https://play.google.com/store/apps/details?id=com.arch.components'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="80px"/></a>

A typical Android app is constructed out of multiple app components, including activities, fragments, services, content providers and broadcast receivers. Android app needs to be much more flexible as the user weaves their way through the different apps on their device, constantly switching flows and tasks. This app-hopping behavior is common, so your app must handle these flows correctly.

Keep in mind that mobile devices are resource constrained, so at any time, the operating system may need to kill some apps to make room for new ones. The point of all this is that your app components can be launched individually and out-of-order, and can be destroyed at anytime by the user or the system. Because app components are ephemeral and their lifecycle (when they are created and destroyed) are not under your control, you should not store any app data or state in your app components and your app components should not depend on each other.

The new architecture has following components that make it easy. They are also designed to fit together as building blocks.

<img src='https://cdn-images-1.medium.com/max/1600/1*KnYBBZIDDeg4zVDDEcLw2A.png'/>

# Room

In new approach, lets tackle the database using Room which a new SQLite object mapping library. To setup a table in room you can define a Plain Old Java Object with annotation @Entity and @PrimaryKey.

```html
@Entity(tableName = “comments”, foreignKeys = {@ForeignKey(entity = ProductEntity.class, parentColumns = “id”, childColumns = “productId”,onDelete = ForeignKey.CASCADE)}, indices = {@Index(value = “productId”)})
public class CommentEntity implements Comment{ @PrimaryKey(autoGenerate = true)
   private int id;
   private int productId;
   private String text;
   private Date postedAt;
}
```

For each POJO you need to define a Database Access Object (DAO). The annotated method represent the SQLite commands to interact with POJO data.

```html
@Dao
public interface CommentDao {
   @Query(“SELECT * FROM comments where productId = :productId”)
   List loadComments(int productId);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertAll(List products);
}
```

In above example Room automatically converts POJO object into corresponding database tables and back again.

## LiveData
LiveData is a data holder class that keeps a value and allows this value to be observed. Unlike a regular observable, LiveData respects the lifecycle of app components, such that the Observer can specify a Lifecycle in which it should observe.

LiveData considers an Observer to be in an active state if the Observer’s Lifecycle is in STARTED or RESUMED state. If the Lifecycle is not in an active state (STARTED or RESUMED), the observer isn’t called even if the value changes. If the Lifecycle is destroyed, the observer is removed automatically. There might be multiple fragments and activities that are observing LiveData instance, and LiveData gracefully manages them such that it connects to the system service only if any of them is visible (that is, active).

The LiveData class provides the following advantages:

* No memory leaks: Since the Observers are bound to their own Lifecycle objects, they are automatically cleaned when their Lifecycle is destroyed.
* No crashes due to stopped activities: If the Observer’s Lifecycle is inactive (like an activity in the back stack), they won’t receive change events.
* Always up to date data: If a Lifecycle starts again (like an activity going back to started state from the back stack) it receives the latest location data (if it didn’t already).
* Proper configuration change: If an activity or fragment is re-created due to a configuration change (like device rotation), it instantly receives the last available Location data.
* Sharing Resources: Now we can keep a single instance of class, connect to the system service just once, and properly support all observers in the app.
* No more manual lifecycle handling: Fragment can observe the data when it wants to, does not worry about being stopped or start observing after being stopped. LiveData automatically manages all of this since the fragment provided its Lifecycle while observing.

## Lifecycle Owners and Lifecycle Observers
LifecycleOwners are objects with lifecycle like Activity and Fragments. LifecycleObservers observes LifecycleOwners and are notified of lifecycle changes.

```html
class TestObserver implements LifecycleObserver{
   @OnLifecycleEvent(ON_CREATE)
   void startUp(LifecycleOwner source) {
   }

   @OnLifecycleEvent(ON_ANY)
   void onAny(LifecycleOwner source, Event event) {
   }
  
   @OnLifecycleEvent(ON_STOP)
   void cleanUp(LifecycleOwner source) {
   }
}
```

Lifecycle uses two main enumerations to track the lifecycle status for its associated component.

### Event

The lifecycle events that are dispatched from the framework and the Lifecycle class. These events map to the callback events in activities and fragments.

### State

The current state of the component tracked by the Lifecycle object.

# ViewModel

The ViewModel class is designed to store and manage UI-related data so that the data survives configuration changes such as screen rotations.

It is easier and more efficient to separate out view data ownership from UI controller logic. Lifecycles provides a new class called ViewModel, a helper class for the UI controller which is responsible for preparing the data for the UI. The ViewModel is automatically retained during configuration changes so that the data it holds is immediately available to the next activity or fragment instance.

If the activity is re-created, it receives the same ViewModel instance that was created by the previous activity. When the owner activity is finished, the Framework calls ViewModel’s onCleared() method so that it can clean up resources.

To create a ViewModel class, you extend the ViewModel class and then put all the data that is necessary for you UI into this class.

```html 
public class MyViewModel extends ViewModel {
private MutableLiveData users;
 public LiveData getUsers() {
   if (users == null) {
       users = new MutableLiveData();
       loadUsers();
   }
  return users;
 }
  private void loadUsers() {
   // do async operation to fetch users
 }
}
```

Setup this view model in your activity or fragment like this:

```html 
viewModel.getComments().observe(this,new Observer() {
   @Override public void onChanged (@Nullable List comments){
   if (comments != null) {
       mCommentsAdapter.setCommentsList(comments);
   }
 }
}
```
