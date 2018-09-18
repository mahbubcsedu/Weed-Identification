# Weed Infestation Identification
This is an android application which is used in weed identification project to submit captured image and getting notification of identified weed
General User Features 5


### 1. Introduction 
### 2. Common Interface
  * 2.1. Sing in to the system 
  * 2.2. Sign up for the system 
  * 2.3. Weed database 
  * 2.4. User profile 
  * 2.5. User instruction 
  * 2.6. Image Zoom 
###3. Farmer Specific Features 
  * 3.1. Add a request 
  * 3.2. Review previous requests (list) 
  * 3.3. Review previous requests (map) 
  * 3.4. Detail response 
  * 3.5. Sign out 
### 4. Expert Specific Features 
  * 4.1. Farmer's requests (list & map view) 
  * 4.2. Request diagnosis 
  * 4.3. Add new weed information 
  * 4.4. Review the response 
### 5. App admin Specific Features 
### 6. Conclusion 


## 1. Introduction

Weed Identification System-a farmer/consultant and expert based agricultural information exchanging system where the experts are the agricultural expert persons. Farmers having problem in identifying some weeds that naturally grow to the fields asked for help from the expert who are well known and fully concerned about the weeds. But the problem exchanging information manually costs for time, money and resources. Experts are very busy and farmers are in need of immediate help and they are scattered to all over the region which extends the cost of solve the problem.

In the mobile based weed identification system-it mimics the work of exchanging information among farmers/consultants and expert reside on hand hold mobile phone. This leads to the savings of money, time and labor. The system works in two ways: one from farmer side and the other from expert side.

 

  ### Weed Identification System
  
        ![alt text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/main.png "LogIn")

The farmers/consultant sends the requests to the system where background manipulation is done and stored the requests making accessible to both farmers and experts. The experts get the requests made by the farmers and consultants and give the feedback which is stored in the back-end system. The farmers or consultants get the response of their requests from the back-end system.

This purpose of this report is to present information on the features of the Weed Identification android app both front end app and back end server, and provided screenshots showing the application in use. The first part of the report constitutes of the usage and features of the android app and second part of the report is about the back end server and the intelligence in identifying weeds.

The application currently has two user types, Farmer and Expert, and there are another type of user who is known here as admin who can modify the weed database from the remote system. The features of both as well as general user features will be discussed below.

## 2. General User Features
Though there are two different types of users for the weed application, but there are some common features which is interesting and necessary both for the farmers and for the experts. Such features are registration and login to the system, keep personal profile and the facilities of change password. There are also having some very important common features like weed database and information facilities, image zoom view and the notification from the servers. These features are described in the following sections-

## 2.1 Sign in to the system
This is the introduction screen that everyone sees when loading the app. This is where users can first register with the service and log in to their respective accounts. A user will also be able to view information about the application.

## Sign In screen: 

![alt text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/Picture1.png "LogIn")


As of this version, a user can only reset their passwords via their profile once they have already logged in, so until further development, this Password

Reset button is non-functional because of the lack of email configuration in the back end server. Log-in is handled by retrieving information entered into the username and password text fields, and calling a PHP scripts checking the validity of the entered data. If valid, the correct home screen for the user's type is displayed. Otherwise, an error message is displayed indicating, for example, an incorrectly entered password or lack of a connection to the internet.

Another important information regarding log in to the system is that currently the user from the state of Arkansas can only log in to the system. Outside of this state will not be able to do that and will be displayed error message to the screen. For this purpose, the GPS should be kept on if there is no network connection or WI-FI connections to the phone.

## 2.2 Sign up for the system
In addition to logging in, this view is also where both types of users can register with this service. When the Sign Up button is pressed, a view is presented allowing the user to enter in the relevant data, including a specific map view for location data that checks to ensure the users are located in Arkansas.

## Registration

![alt-text-1](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/info1.png "title-1") ![alt-text-2](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/info2.png "title-2")


An additional user type of student is currently in development. The student's user interface will be the same as the expert's. Additionally, the image of the map view screen displays a valid location selection. If a user were to select a location outside of Arkansas, an error message stating that only locations in Arkansas are valid and prompts the user to make another selection. In order to make a more accurate location selection, the user has the option to zoom in, both with figure gestures on the screen and the zoom button. The map also cycles through three different types, a standard line map, a satellite map and the line map superimposed on the satellite map as shown above.

After the user enters all of the requested data and passes the error checking, for example there are no spaces in the password, a PHP script is called that either inserts the user into the database or returns an error message if the selected user name is already taken. If the user name is already taken, a prompt is displayed asking for a different username. Upon successfully registering, each user account is assigned a unique identification number that is used through the application to keep track of the specific user's data.

## 2.3 Weed database
Other general features available regardless of user type are the ability to view a database of weeds common to the area.

![alt-text-1](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/info1.png "title-1") ![alt-text-2](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/info2.png "title-2")

Figure 4: Weed database

The first time a user of either type runs the application, a PHP script is called which downloads the contents of the weed database. The information provided includes a photograph of the mature weed, its common name, Latin name, type, lifecycle, season, site, and recommendations for professional and home control. This data is then saved onto the android phone itself so that the user does not have to download it multiple times. The above images display the table view presenting the weed database, and the detailed weed information displayed when a weed in the database is selected.

## 2.4 User profile

Other general features available regardless of user type are the ability to allow the user to view his or her profile. It merely uses a PHP script to query the database for the information the user input during registration and displays it to the screen, as well as providing a button allowing the user to change his or her password.

![alt text](http://url/to/img.png)
Figure 5: User profile and change password

## 2.5 User instruction
User Instructions is another feature for both farmers and experts of this system. When the users use the system for the first time, the instruction window opens. If the user thinks that they are well aware of the process, and then can dismiss the instructions.

     ![alt text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/testweb/images/header.jpg)
     
Figure 6: Instruction for the user

## 2.6 Image Zoom
Another general feature of the app is to use the zoom feature for the image. For the both the farmer and the expert, when it needed, the image can be zoomed in and out to better understand and identify the weeds.

## 3. Farmer Specific Features
The farmer user type can currently do the following: browse a database of common weeds, submit a diagnosis request to the expert users, review the responses from the expert users, and review their profile/ update their password.

As stated above, when a user signs in, a PHP script is called to verify the information provided with the users in the database. If the information is valid, the call returns the user's unique identification number. This number is used by many of the subsequently called PHP scripts to keep track of things such as which farmer made which specific request, and which expert has answered which request. Since this piece of data is vital to effective use of the app, care is taken to ensure that this number is always available when the user attempts to perform a feature requiring a user id number.



Figure 7: Farmer home screen

Almost all of the new views in the application are presented modally, and in order to keep memory usage low and consistent with android's memory use standards, each calling screen is dismissed when a new one is called. This requires that a user's identification number be specifically passed back and forth between views when they are called and dismissed.

## 3.1 Add a request
When the Add Request button is pressed, a view is presented allowing the farmer to take a photograph of the weed, record a brief audio description and type some comments.



## Add a new reqeust
![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/req1.png "add new request")

This information is then collected and necessary conversions done. Once complete, a PHP script which handles inserting the request into the database is called. In addition, if the farmer has allowed location data to be used, a final check is done before the actual submission to verify that the user is currently in Arkansas. This check, unlike the one performed during registration, is based on location data provided by the phone, not input by the user in order to prevent people submitting requests from out of the state.

## 3.2 View previous requests (list)
The Request Result button's features are more in depth. Immediately upon selecting the button, the farmer is presented with a table view of all of his or her previous diagnosis requests. This view also includes an indicator that changes color based on whether the request has received a response. When a request is selected, a screen showing the farmer the details of the request is presented, along with an option to view the responses to that request.



##  View previous requests(list)

![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/req_st1.png "show previous request") |
![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/req_st2.png "show previous request")

If that button is selected, another table view appears with a list of the responses received, and finally, if a response is selected, a final screen displaying the details of the response is displayed. The above screenshots of the table view listing out all of the previous requests the farmer has made, and the detail screen allowing the farmer to view the information he or she submitted in the request before moving on to the table view of responses.

## 3.3 View previous requests (map)
When a farmer selects the View Previous Requests button, a map view is presented. This map view contains pins in the locations of all of the farmer's previous requests. These pins are selectable, and can be used to access the same response detail screen shown below, along with all of its sub-screens, such as the expert request table view and the expert's response details.



## View previous request (map)
![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/search1.png "show previous request map") |
![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/search2.png "show previous request map")



## 3.4 Detail response

In the Response Detail Screen, the farmer is presented with up to three possible results of diagnosis that he or she can browse. The information provided is displayed in the same manner as the weed database information, with some additions. A rank, assigned by the expert to indicate the likelihood of any of the three weeds is included, as well as some text comments and possibly an audio recording for the farmer to listen to.



Figure 11: View responses for the request

## 3.5 Sign out
The Sign Out button functions by severing the ability of the android phone to communication with the server. If the user signs out, the identification number mentioned above is deleted in order to render the program unable to call the PHP scripts until the user signs in again, and the number is again sent to the user's phone.

## 4. Expert Specific Features

The expert home screen is similar to the farmer's home screen. Like in the farmer's version above, all action taken by the expert user is kept track of via the specific user identification number assigned to the account, and so it is too passed between calling and dismissing views as the user navigates the application. Also like the farmer, they can also view the database of weeds as a standalone feature. Unlike the farmer's version, this database is also available to the expert whenever he or she is reviewing a request.



## Expert home screen

![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/exp1.png "expert window")

## 4.1 Farmer's requests (list & map view)
The Diagnose List View button calls a view that displays an interactive table of all of the requests the farmer users have submitted. Like in the farmer's version of the app, there is a color coded indicator showing which requests have already been answered. For request map view, when the user presses the button with Diagnose Map View, the interactive map view with the requests is open. The user can tap the marker to select for diagnosis. The red marker defines that there is at least one response for that requests and red indicate that there is no response for that particular request.

    ![alt text](http://url/to/img.png)
Figure 13: Diagnose farmer's requests (list&map)

## 4.2 Request diagnosis
When an expert selects a specific request to diagnose, another view is presented showing the details of the request, which include the photograph the farmer took, and buttons to display the text input from the farmer or to play the audio recorded. In addition, a side-scrolling sub-view is displays the weed database so the expert can compare the images directly. This weed database is navigated via swiping the screen to scroll through all the images. The application stores potential matches in a mutable array that can hold up to three matches, each assigned a rank of their likelihood of being the correct match. From this screen, the expert can also select to view an interactive image of the weed from the database if he or she wants to zoom in and get a better look.



## Figure 14: Diagnose selected requests

![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/diag1.png "Diagnosis review") | ![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/diag2.png "Diagnosis review")

Like in the farmer's version, all of this data is received from PHP scripts called by the app, parsed and presented to the user as he or she navigates through the application without being saved onto the android phone itself.

The above images show the view displayed when an expert is reviewing a request. In the first, the image displayed on top, while currently merely a test image sent earlier to verify submission works, will be the weed the farmer has photographed. The expert can swipe the weed database image to navigate through the entire database. The expert can view the image in zoom mood if they tap the image quickly. If they tab the image from the database and hold, then the popup menu will appear which will makes the facilities of selecting the image with ranking range from 1 to 3. Then the expert can select another image and can do so. If they think that the response is complete they can go for previewing before submitting it to the system.

## 4.3 Add new weed information

When an expert selects a specific request to diagnose, another view is presented showing the details of the request, which include the photograph the farmer took, and buttons to display the text input from the farmer or to play the audio recorded. In addition, a side-scrolling sub-view is displays the weed database so the expert can compare the images directly. The expert can select images from the gallery for response or they can add the weed as the new one. They will only do this if they think that the image which is included in the request is a new one and there is no reference in the database. If they think like this, they can tab the image and hold for few moments. Then a pop up screen will appear where it will ask the expert for the confirmation.

 

Figure 15: Add new weed information

If the expert confirmed about saving this to the database, a new window will open where there will be the view with the query image and the field related to the weed information. The experts can fill up the field which is optional and then press the submit button. If submit button pressed , a PHP script will be called which will store all the information of this weed in a database and will also place the new weed's image to a temporary folder which will then be processed by the admin to make it available for future reference of the system.

## 4.4  Review the response
The images below are what the expert sees after adding the weed to the response and selecting the Preview Response button, from left to right. The left image shows the action sheet that is called prompting the expert to rank the weed being added. As is shown in the right image, the expert can view the weed added to the database, and optionally enter more information.



## Review the response
![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/res1.png "Review response window") | 
![alt-text](https://github.com/mahbubcsedu/Weed-Identification/blob/master/docimages/res2.png "Review response window") | 


Once the expert makes his or her selections from the weed database, he or she can preview the selections, and provide text and audio supplements to the weeds selected from the database. When finished, the provided information is gathered, and a PHP script is called, sending all of the information to the server. The farmer can view the response on their version immediately.

## 5. App admin Specific Features

The expert who works on the weed id app may select the farmer's requested image as new weed and thus can be used to future references and collecting information. This process is explained in the new weed add section. But different expert can send different information regarding the new weeds. The admin of the system who is also a weed expert can collect the accurate information. Thus this app provides a desktop based facility of viewing the expert's response and from there provide the actual information. For this the user need to sing in to the system from the internet browser and then go to add new weeds. There they can find the information provided by the experts and then can fill up the text boxes with the accurate information for the weeds. If they submit all the information will be stored to the database and will be available to the app for future references.

          
 

Figure 17: Image, responses from the expert and fields for actual information

## 6. Conclusion

As shown, this system and its related applications provide a convenient and powerful tool for farmers and experts to diagnoses weeds affecting crops. The features of the application described above are straightforward and simple to use in both the farmer and the expert versions.
