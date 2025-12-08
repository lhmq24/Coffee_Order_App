# Coffee Shop Order App for Android 
This repo is with learning-purpose, create an Android app with Java for café staff use only.
## Functions:
- View tables in shop.
- Filter by Table number/ Table capacity/ Floor number/ Table Status
- Dynamic search for beverage name
- CRUD beverages of 1 table with real time update
- Table receipts review

## Prerequisite: 
- Java version >= 11
- Dependency management with Gradle Kotlin DTS
- Theme with MaterialComponent

# Conceptual Data Model
<img width="1224" height="654" alt="image" src="https://github.com/user-attachments/assets/c6187331-2302-49a5-9cc1-c5014240d65b" />

- Database designed to stores beverage price history to make orders be consistent.
- Also designed to manage staff sessions and role management but not implemented yet.
- DBMS: MySQL (XAMPP)

# For usage
- Download [Android Studio](https://developer.android.com/studio?hl=vi) for rich-supported environment.
- Clone repo
  ```
  git clone https://github.com/lhmq24/Coffee_Order_App.git
  cd https://github.com/lhmq24/Coffee_Order_App
  ```
- Open 'build.gradle.kts' (App level) and click "Sync Now" for dependencies downloading.
- Run the project.
  
## For debugging with real devices
- Enable "Developer Mode" on your phone"
- In "Developer Options" turn on USB Debugging.
- Use your phone cable connect your PC with your phone, accept the RSA key if the dialog appears.
- Now, click Run 'app' or `Shift + F10`
- The app Login Page will be opend on your screen.

# Improvements needed
- Only support staffs, can be develop to support admin.
- Need to update static IP address of localhost to demo, can furthermore use Server static IP.
- Basic UI, can design more.
