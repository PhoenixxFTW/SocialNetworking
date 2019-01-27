# Social Networking Platform for Students
Basic Social Networking platform made for students using JavaFX for the frontend, Kryonet for the networking, and MySQL for data storage.

This was my final project for my computer science class at GFSS (ICS4U0). The entire thing is extrememly buggy, as its just a proof of concept
which brings everything I've learnt together to see what I could achieve. 

## Installation

The installation is extremely simple due to the simple fact that I use gradle as a build tool, so all the libraries will be automatically installed.

Just download the source and or clone it and import the project as a gradle project

IntelliJ Setup:
1) Open IntelliJ IDEA
2) Click  ```Import project```
3) Locate the workspace and click on the ```build.gradle``` file, and your done :)

Eclipse Setup (If you have the gradle plugin):
1) Follw this because I'm too lazy to create a proper tutorial for this
http://makble.com/how-to-import-gradle-project-into-eclipse

If for some reason, you don't want to use gradle, I've also added all the libraries used in this project under the directory ```AllLibs``` which you can use to import into any IDE of your choice.

## Usage

In order for you to successfully launch the client, you first need to start the management server which handles all the networking, data storage, etc. 

1) Launch```ServerNetworkMain.java```, you can find it under the directory ```com.phoenixx.server```
once you get this message in your console / terminal window, proceed to step 2
![Launch Confirmation](https://i.ibb.co/jhh6zMv/Screenshot-2.png)
2) Once the Management Server is running, you can run as many clients (```ClientMain.java```) as you like, this class can be found under ```com.phoenixx.client.application```. Once launched you'll reach the login screen, and you're ready to go.
![Login Screen](https://i.ibb.co/wcwds0x/Screenshot-3.png)

If you wish to not create an account, you can use the test account located below. Otherwise, feel free to create an account (Just **DO NOT use a personal password**, as I currently don't have an encryption system setup for the passwords yet)

__Testing User__  
Username: TestUser  
Password: Test  

### MySQL Database setup
This project uses MySQL to store all the user information, as well as posts, to learn how to connect to it [go here](https://www.gearhost.com/documentation/connect-to-a-mysql-database)

## How does it work? What does it do? Extra Information

All the front-end / UI is made with JavaFX using [SceneBuilder 2.0](https://gluonhq.com/products/scene-builder/). All the FXML files (Which is essentially the UI) can be found in the resources folder. Now each of these are connected to a "Controller" which handles all the backend work (Buttons, packets, you name it). Every time you click a button on the client, a request (packet) gets sent to the Management server asking for whatever information you need. A lot of the requests / responses are not optimized, so the entire application may be slow when it comes to getting you your data.


**Login Screen**
![Login Screen](https://i.ibb.co/wcwds0x/Screenshot-3.png)

**Home Screen**  
![Home Screen](https://i.ibb.co/y41FjWZ/Screenshot-4.png)

**Post Screen**  
![Post Screen](https://i.ibb.co/LhgkHWD/Screenshot-5.png)

**Profile Screen**  
![Profile Screen](https://i.ibb.co/fCN8QGg/Screenshot-6.png)

**Create Post Screen**  
![Create Post Screen](https://i.ibb.co/jf4d155/Screenshot-7.png)

## Known Bugs

- Scaling of the UI is a bit off on some computers
- Some icons may not appear, for example the back arrow (Its in the top left corner if you need to click it)
- Loading posts / logging in is slow (Like I said, not fully optimized)
- Management Server may disconnect from the MySQL server after not being in use for some time (Just restart the entire server and you should be good)

## Planned Features

- Friends list
- Messaging System
- Notification System
- User ranks (Student, Teacher, etc)
- Profile customization (Setting up the mood, editing profile pic, etc)
- Post search / filter (Using tags, titles, by user, date)
- Post editing 
- Post commenting / likes
- Management server console UI (Global notifications, auto updates, etc)
- Latest school news menu

## License
[MIT](https://choosealicense.com/licenses/mit/)
