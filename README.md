# TO DO LIST


## Description:

The To-Do List Android App is a simple task management application that allows users to create, edit, and delete tasks. Users can set priorities, deadlines, and categories for their tasks, helping them stay organized and manage their time efficiently.

## Setup and Run:

Follow these instructions to set up and run the To-Do List Android App using Android Studio.

## Dependencies

'implementation "androidx.recyclerview:recyclerview-selection:1.1.0"'
 
    
RecyclerView Selection library for handling item selection in RecyclerView.

' implementation "androidx.room:room-runtime:2.6.1" '


' annotationProcessor "androidx.room:room-compiler:2.6.1" 

 
 Android Room library for local database persistence.


## Prerequisites

Android Studio installed on your machine.


Android device or emulator.

## Installation

1.Clone the repository: git clone https://github.com/your-username/todo-list-android.git


2.Open Android Studio.


3.Click on "Open an existing Android Studio project."


4.Navigate to the cloned repository and select the project folder.

## Usage

1.Connect your Android device or start an emulator.




2.Click on the "Run" button in Android Studio to build and install the app.


3.Open the app on your device/emulator to start managing your tasks.

## Working

The To-Do List application allows users to add tasks, which are then displayed on the tasks page. The Create Task page features spinners to set the priority, status, and category. Additionally, the Datepicker feature presents a calendar to set the task deadline. The Create Task page enforces the requirement of filling in all fields (the Description field is optional), and it prevents the addition of tasks with duplicate names.

When a task is saved, it is immediately visible on the tasks page. The tasks on the tasks page are differentiated using different text colors for new, completed, and in-progress statuses. Clicking on a task redirects the user to the Edit Task page, where they can modify the task details, update it, or delete it. The changes are then reflected on the tasks page.

Furthermore, there is a spinner at the top right corner that, when clicked, displays different categories. Clicking on a specific category filters and displays only the tasks belonging to that category.

This comprehensive functionality enhances the user experience, providing a robust and organized task management system.



### SCREENSHOTS

## Fisrt page

![1](https://github.com/YashaSriAluvala/To-Do/assets/117937166/d56d8ce9-3c80-4483-b1a4-57a03584a7a5)

## Tasks page-Saved tasks will be displayed in tasks page

![2](https://github.com/YashaSriAluvala/To-Do/assets/117937166/6becb20c-dbdb-4d32-8f5d-2ee3a685fc55)

## Spinner to categorize the tasks(when user selects a particular category only that category elements are viewed)

![3](https://github.com/YashaSriAluvala/To-Do/assets/117937166/68bc9bcc-443b-40c3-967f-5648eec28e59)

## Create task:The user can add the tasks by filling all the fields

![4](https://github.com/YashaSriAluvala/To-Do/assets/117937166/c5fbf96c-03f7-4219-aa03-ea11ac840512)

## Spinner to set the Priority

![5](https://github.com/YashaSriAluvala/To-Do/assets/117937166/01d4d54f-c7e1-41a8-be9c-fc09c90c320b)

## Spinner to set the status

![6](https://github.com/YashaSriAluvala/To-Do/assets/117937166/c1e02c7b-44af-4a15-8629-866cc0705b04)

## Datepicker to set the deadline

![7](https://github.com/YashaSriAluvala/To-Do/assets/117937166/fc95e99b-6131-4180-8109-cc53e6692ac8)

## Spinner to select the category

![8](https://github.com/YashaSriAluvala/To-Do/assets/117937166/aeae4b80-5a72-4917-b9ac-2ca4031f623c)

## The tasks saved will be viewed on the tasks page

![9](https://github.com/YashaSriAluvala/To-Do/assets/117937166/3f731eae-c724-4f37-a39e-8c546de1f415)

## Edit task page to edit or delete the task

![10](https://github.com/YashaSriAluvala/To-Do/assets/117937166/469d58c5-68d4-4656-8c6c-235e21761deb)


