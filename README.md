# Trivadelphia

This is an Android trivia app whose questions are based on the TV show 'It's Always Sunny in Philadelphia'.  This is a more recent project I build to test some of the concepts I have learned recently, such as SQLite and handling Intents and Bundles in Android.

Below are links to the folders which hold most of the relevant code.

https://github.com/adh636/Trivadelphia/tree/master/app/src/main/java/com/example/android/trivadelphia

This folder includes the .java files.  MainActivity launches the app.  QuestionAnswer creates the class that is used to pass content into the UI.  DbHelper is used to create the SQLite database and fill it with questions.  ResultActivty handles the result screen when someone finishes the quiz (score display).

https://github.com/adh636/Trivadelphia/tree/master/app/src/main/res

This folder contains the XML and .png files used in the UI.  The layout folder has the two activity XML files, and the drawable folder has one other XML file used to handle the custom radio buttons as well as hold the different .png files.
