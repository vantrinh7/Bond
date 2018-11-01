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
- Improving available templates and generate messages that are personally customized to each contact
- In-app text messaging and calling
- Collecting logs of conversations between the user and the contacts from different platforms (e.g. text messages, Facebook Messenger, Gmail)
- Collecting information of the contacts on social networking sites and other online sources
- Using this information to construct suggestions for conversations, emails and messages

<br>

![](misc/Bond%20Artificial%20Intelligence.png) ![](misc/Bond%20Profile%20iPhone.png) ![](misc/Bond%20Contact%20iPhone.png) ![](misc/Bond%20Template%20Menu%20iPhone%202.png)
<br>

## File Description
This app was created using Android Studio and implemented using Android build system.

* [SplashActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/SplashActivity.java) defines the starting screen with Bond logo.
* [MainContactScreen.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/MainContactScreen.java) creates the home screen. This class specifies methods to create contact list views, construct buttons and handle button click events. It contains onCreate() method which is called at the start of the program, and onActivityResult() method which establishes the relationship between parent and child activities (pages).
* [ItemListActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/ItemListActivity.java) creates a list of Items. This activity has different presentations for handset and tablet-size devices. On handsets, the activity presents a list of items, which when touched, lead to a page representing item details. On tablets, the activity presents the list of items and item details side-by-side using two vertical panes. This class is used to form a list on the main contaxt screen.
* [IndividualContactActivity.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/IndividualContactActivity.java) denotes a page for each individual contact in the contact list. This class has methods to add profile picture, name, email, phone number and notes. It also creates an alarm for the next time to connect with the person.
* [AlarmBroadcastReceiver.java](https://github.com/vantrinh7/Bond/blob/master/app/src/main/java/com/example/demouser/bond/AlarmBroadcastReceiver.java) 

