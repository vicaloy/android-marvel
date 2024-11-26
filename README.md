# 🦹‍♀️ Marvel App Android

This is an Android app built with a multi-module architecture to display Marvel characters, including their details and comics. The app uses DeepLinks to navigate between modules, ViewModel for managing UI-related data, Paging3 for efficient pagination of large data sets, and a combination of XML and Compose for the UI.

## 🛠️ Technologies Used

- Kotlin: Primary language for Android development.
- Jetpack Compose: For building modern, declarative UIs.
- Paging3: For handling data pagination in the list of characters and comics.
- DeepLinks: To allow navigation from external sources to specific content within the app.
- ViewModels: For managing and persisting UI data in a lifecycle-conscious way.
- Retrofit: For making network requests to Marvel’s API.

## 🔄 Pros of Multi-Module Architecture

- Separation of Concerns: Separates UI, business logic, and data management into different modules.
- Scalability: Easy to scale the app as you can add more features without disturbing the existing functionality.
- Maintainability: Helps in managing large codebases by breaking the app into smaller, more manageable modules.
- Testability: Each module can be tested independently, making unit testing easier.

## ⚠️ Cons of Multi-Module Architecture

- Complexity: Multi-module setup can be complex, especially for smaller projects.
- Initial Setup Overhead: Configuring dependencies between modules requires extra setup time.

# 🐒 Monkey Testing

The Monkey tool is a stress-testing tool used to simulate random user interactions on an Android app. It is often used for testing the robustness of an app by simulating random touches, key events, and gestures.

- ./adb: The adb command is used to interact with Android devices from the command line. Here it refers to the location of the adb tool on your system.
- shell: This allows you to run commands on the device or emulator.
- monkey: This is the testing tool that generates random input events to simulate user interactions.
- -p com.example.marvel: Specifies the package name of the app you want to test. In this case, it's the app with the package com.example.marvel.
- -v: This flag sets the verbosity level of the output. It makes the Monkey tool output more detailed information about the events it's generating.
- 500: The number of random events (e.g., screen taps, key presses, gestures) the Monkey should perform on the app. Here it will simulate 500 random events on the Marvel app.

### What It Does:
It simulates 500 random user actions on the app com.example.marvel and outputs detailed logs about the events. This helps identify potential crashes, performance issues, or unexpected behavior in the app.

### Commands
- /Users/victoriaaloy/Library/Android/sdk/platform-tools
- ./adb shell monkey -p com.example.marvel -v 500
