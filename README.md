# Bond ![logo](misc/Bond%20Logo%202.png)

Bond is an application that helps remind users to keep in touch with others, thus establishing the bonds between people and enhacing personal relationships. This app was originally created for Android, and has so far contained several features:
- Recording and saving detailed information of a contact (e.g. name, phone number, birthday, hobbies, first impression, etc.)
- Setting up reminders that notify user of the next time to connect with someone
- Viewing upcoming schedule in a calendar overview
- Providing ideas and templates for users' conversation (e.g. greetings, wishes, conversation starters, self-introductions, follow-up emails, etc.)
<br>

![](misc/Bond%20Calendar.png) ![](misc/Bond%20Contact%20List.png) ![](misc/Bond%20Contact.png) ![](misc/Bond%20Template%20Menu.png) 

<br>

In the future, an iOS version for this app will be created. Additional features will also be implemented, such as:
- Personally customizing templates and messages to each contact
- In-app text messaging and calling
- Collecting logs of conversations between the user and the contacts from different platforms (e.g. text messages, Facebook Messenger, Gmail)
- Collecting information of the contacts on social networking sites and other online sources
- Using this information to construct suggestions for conversations, emails and messages

<br>

![](misc/Bond%20Artificial%20Intelligence.png) ![](misc/Bond%20Profile%20iPhone.png) ![](misc/Bond%20Contact%20iPhone.png) ![](misc/Bond%20Template%20Menu%20iPhone%202.png)
<br>

## File Description
This app was created in Android Studio and implemented the [Android build system](https://developer.android.com/studio/build/).

* Android Studio utilizes [Gradle](https://gradle.com/), an open-source build toolkit that automates the software build process. The Gradle files for this program include: [settings.gradle](https://github.com/vantrinh7/Bond/blob/master/settings.gradle) tells Gradle which modules to include when building the app, [build.gradle](https://github.com/vantrinh7/Bond/blob/master/build.gradle) (project level) defines build configurations (repositories and dependencies) that apply to all modules in the app, [build.gradle](https://github.com/vantrinh7/Bond/blob/master/app/build.gradle) (module level) defines build settings for the specific module it is located in, [gradle.properties](https://github.com/vantrinh7/Bond/blob/master/gradle.properties) define project wide settings for the Gradle build toolkit itself, and [proguard-rules.pro] (https://github.com/vantrinh7/Bond/blob/master/app/proguard-rules.pro) defines the rules for ProGuard, a tool to detects and removes unused code in the program.
* (AndroidManifest.xml)[https://github.com/vantrinh7/Bond/blob/master/app/src/main/AndroidManifest.xml] file describes the app package name, app ID, permissions, device compatibility features, and certain app components (activities, services, broadcast receivers and content providers).
* [res](https://github.com/vantrinh7/Bond/tree/master/app/src/main/res) folder contains XML files that construct the user interface of the app, such as the layout, color, dimension, or drawable objects in the app.
* [SplashActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/SplashActivity.java) defines the starting screen with Bond logo.
* [MainContactScreen.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/MainContactScreen.java) creates the home screen. This class specifies methods to create contact list views, construct buttons and handle button click events. It contains onCreate() method which is called at the start of the program, and onActivityResult() method which establishes the relationship between parent and child activities (pages).
* [ItemListActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/ItemListActivity.java) creates a list of Items. This activity has different presentations for handset and tablet-size devices. On handsets, the activity presents a list of items, which when touched, lead to a [ItemDetailActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/ItemDetailActivity.java) representing item details. On tablets, the activity presents the list of items and item details side-by-side using two vertical panes. This class is used to form a list on the main contaxt screen.
* [ItemDetailActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/ItemDetailActivity.java) is a class representing a single Item detail screen. This class is only used on narrow width devices. On tablet-size devices, item details are presented side-by-side with a list of items in a [ItemListActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/ItemListActivity.java).
* [GridAdapter.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/CustomListAdapter.java) is an adapter that returns a view for each object in a collection of data objects. This class is used to help showing profile pictures list.
* [IndividualContactActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/IndividualContactActivity.java) denotes a page for each individual contact in the contact list. This class has methods to add profile picture, name, email, phone number and notes. It also creates an alarm for the next time to connect with the person.
* [DatePickerFragment.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/DatePickerFragment.java) generates a date picker fragment, a pop-up with options for day, year and month. This class allows user to choose what date to save and display on the screen when setting a reminder.
* [TimePickerFragment.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/TimePickerFragment.java) generates a time picker fragment, a pop-up for the hour and minute of a day. This class specifies events when user sets the time and allows user to choose a time when setting a reminder. It also has an AlarmManager that calls AlarmBroadcastReceiver.java to invoke an alarm service.
* [AlarmBroadcastReceiver.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/AlarmBroadcastReceiver.java) is used in conjunction with AlarmManager to perform time-based operations outside the lifetime of the application. It triggers the alarm and generates notifications inside notification center when the alarm goes off.
* [CalendarMainActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/CalendarMainActivity.java) constructs the calendar view and has method to navigate back to the home screen.
* [CalendarCustomView.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/CalendarCustomView.java) creates a calendar view using GridAdapter.java. This class also specifies what happens when there are click events on each grid cell.
* [GridAdapter.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/GridAdapter.java) extends ArrayAdapter and constructs a calendar grid view. It also adds and displays current date and events on the calendar.
* [EventObjects.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/EventObjects.java) defines an event with the date and content of the event.
* [TemplateMainActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/TemplateMainActivity.java) creates the Template screen. This class has methods to create image buttons representing available templates. Each button is a TemplateMainItemObject. This class also uses a GridAdapter to generate a grid view.
* [TemplateMainItemObject.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/TemplateMainItemObjects.java) specifies an object on the template screen. It has methods to get and set content of the object.
* [TemplateGridAdapter.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/TemplateGridAdapter.java) extends ArrayAdapter and contructs a grid view on the temmplate screen. 
