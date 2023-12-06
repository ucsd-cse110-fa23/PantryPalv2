# PantryPal - Group 25

Welcome to PantryPal, the latest solution to your meal prep desires!

## Table of Contents

- [Introduction](#introduction)
- [How To Run](#how-to-run)
- [Instructions](#instructions)

## Introduction

PantryPal is the latest innovation in the recipe-creation tech space. Our application is powered with OpenAI's GPT and Whisper APIs to provide top recipes with the ingredients in _your_ pantry!

Our application's features include:

- Recipe Creation
- Meal Type Selection
- Detailed Recipe Display
- Recipe Saving
- View List of Recipes
- Recipe Editing
- Recipe Deletion
- Scalable to work on multiple platforms

## How To Run

To run our application, feel free to clone the repo with the following command:

```
git clone https://github.com/ucsd-cse110-fa23/cse-110-project-team-25.git
```

and then cd into the designated project directory.

updating the java version in `frontend/build.gradle` and `middleware/build.gradle` may be required based on the respective java version. 

### For Mac

Then, as our project is gradle-based, build the project using the following command:

```
./gradlew build
```

And finally, run the project using the following command:

to run the server - 
```
./gradlew :middleware:run 
```

to run the UI - 
```
./gradlew :frontend:run
```

### For Windows

Then, as our project is gradle-based, build the project using the following command:

```
gradlew build
```

And finally, run the project using the following command:

to run the server - 
```
gradlew :middleware:run 
```

to run the UI - 
```
gradlew :frontend:run
```

### and in case that doesn't work on windows...try powershell!

Then, as our project is gradle-based, build the project using the following command:

```
./gradlew.bat run
```

And finally, run the project using the following command:

```
./gradlew.bat build
```

**Please note** that sometimes macs have permission errors with gradle, and may require the following type of command to be run prior to building and running the application:

```
chmod +x gradlew
```

## Instructions

To use our application all you need to do is follow a few simple steps!

1. Create a recipe by pressing the green microphone icon and say the meal type you would like to create a recipe for!
2. Press the red no-microphone icon to stop the meal type recording
3. You will then be prompted to press the green microphone icon again to verbally enter your recipes (ie. "I have chicken and rice")
4. Press the red no-microphone icon to stop the ingredients recording
5. Your recipe will then be generated! Feel free to save the recipe to your recipe list by clicking the "save" button, or edit the recipe with the "edit" button
6. At any point you can cancel your recipe or go back to a previous page
7. To view your recipes list, click the "View Recipe List" button
8. In your recipes list, you can view and delete past recipes!

## PantryPal Updates 🥳🤑

We are so excited to share with you our newly updated PantryPal 2 🥳🥳 enjoy these new features -

1. Don't like a recipe? Simply press the "regenerate recipe" button on the bottom when you have created a new recipe to get a new one with your same specs.
2. Want to know what your recipe will look like? in each detailed recipe view, it now shows a generated image of what your dish could look like! Yummy!
3. Now you can sort and filter your recipes! In the "View Recipe List" tab, you can click the "Sort By" dropdown to sort recipes based on your needs. You can also toggle the "Breakfast", "Lunch", and "Dinner" buttons on the top-right to filter the recipe list.
4. Recipes in the list of recipes now display a tag of the type of recipe it is!
5. To share a link of a recipe, press the "Share recipe" button on the bottom of any detailed recipe view, and a shareable link to a website with all that recipe's info will be generated!
6. Now you can access your recipes anywhere with a login service! Upon opening the app, users will be prompted to log in or create an account. Now recipes can be associated with a user! Furthermore, to expedite log in services, simply press the "remember me" checkbox upon log in in the bottom corner to auto-save login during a session. 

**Thank you for choosing Team 25's PantryPal for your pantry needs! 🧑‍🍳🍳💅**
