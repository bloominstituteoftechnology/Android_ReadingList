# Reading List

## Introduction

Reading List is an application that lets you save a list of books and allow you to mark whether you have or have not read them. Since this application will use persistence, the books you create and check as read or not will be saved between launches of the application.

Please look at the screen recording below to know what the finished project could look like:


<img src="https://github.com/LambdaSchool/Android_ReadingList/blob/master/ReadingList_MVP.gif" width="300">

  * I used a basic activity to experiment with a floating action button, you can use a standard button.
  * I also changed the theme, you can have it your own. Only functionality must be the same.

## Instructions

### Part 1 - Front End

For this project, we'll be using a top-down coding process. The advantage of doing this is that we can have an app that a user can interact with and then you can add functionality behind it.  

#### Book  
1. Create a "Book.java" file. Create a class called `Book`  
2. This class should have the following data members:  
    - A `title` String  
    - A `reasonToRead` String  
    - A `hasBeenRead` boolean  
    - An `id` String  
3. Create two constructors.  
    a. One that accepts and assigns all data members  
    b. One that parses a CSV string to an object  
4. Write getters for each of the data members  
5. Write a `toCsvString` method to convert the object to a CSV string  
> Make sure your CSV constructor and CSV output methods match formatting  

#### MainActivity  
1. Build your `MainActivity` layout file with a `ScrollView` and a `Button`  
> Make sure the `ScrollView` has a `ViewGroup` child with an id for you to add views to   

2. Write a `buildItemView` method which will accept a `Book` object and return a custom view to display information about the book.  
> Typically these views will just show the title of the book. In the next couple weeks, we'll show you how to define custom layouts for lists  

3. Hardcode a list of `Book` objects to test your methods. Be sure to add all views returned from your `buildItemView` method to the `ScrollView`'s child using `.addView(View)`  
4. Test your app.  

#### EditBookActivity  
1. Add a new empty activity called `EditBookActivity`  
2. Add fields to edit information stored in the `Book` object  
3. Add attributes to the fields to make the layout look good.  
4. Go into the `AndroidManifext` file and move the `intent-filter` block to your new activity.  
5. Build your app and test the new Activity.  
6. Return the `intent-filter` block to your `MainActivity`  

#### Link the Activities
Now that you have built both activities. You'll need to link the two activities and pass data between them.  
1. In your `MainActivity` add an `onClickListener` to your button  
2. In the listener create a new `Intent` passing in the current `Context` and the `EditBookActivity.class`  
3. Call `startActivity()` with your new `Intent`  
4. Build and Run your app to test that the button now navigates to the `EditBookActivity`  

#### Pass Data Between Activities
Now that the activity can be launched, it needs data. We have two unique use cases that we'll have to deal with.  
A. New Entry  
​    1. Use `putExtra` to add a new id value to pass to the `EditBookActivity`  
   > For now, you can use the method `getChildCount` to use the item count as a unique id  

​    2. In the `onCreate` method of the `EditBookActivity` use `getIntent().getStringExtra()` to get the passed id  
B. Edit Entry  
​    1. In your `buildItemView`, give the created item an `onClickListener`  
​    2. This `onClickListener` must create an `Intent` just like the button, but this one will add the `Book` as a CSV string to the `Intent`  
C. Accept Data  
​    Now that we have data in the `Intent`, we need to get it from the `Intent`  
​    1. In the `onCreate` method of your `EditBookActivity` use `getIntent()` to get a handle on the `Intent`  
​    2. Use `getExtra` and pass it your tag for id to pull the id from the `Intent`
​    3. Use `getStringExtra` to get your `Book` which was encoded as a CSV string
​    4. If your `Book` string isn't null, use it to build a `Book` object and pull the components from it.
​    5. Add your data as text to your `EditText` fields.

#### Return Data
Once your `EditTextActivity` can accept and process data, it needs to return that data back to the `MainActivity`.
1. Write a method called `returnData` which will pull the information from all the edit fields, use it to build a `Book` object, and convert the `Book` to a CSV string.
2. Create a new `Intent` and use `setResult`with `RESULT_OK` to add the CSV string to the intent
3. Call `finish()` to complete the activity and return.
4. Override the `onBackPressed()` method to intercept when the user presses the back button and call your `returnData`
5. Add two buttons, one as a submit and one as a cancel.
6. In the submit button's `onClickListener` call your `returnData` method
7. For the cancel button write a new method that functions similarly to `returnData` but doesn't pull data from the UI and uses `setResult` with `RESULT_CANCELED` instead
9. In your `MainActivity`, change your calls to `startActivity` to `startActivityForResult` and pass in a constant tag representing which result you will be looking for.
10. Override the `onActivityResult` method to get the returned data
11. Check first that the result code is `RESULT_OK` then check for the result tag to match the one you passed in
12. Use `getStringExtra` to grab the string from the provided `Intent`
13. Parse the CSV string into a `Book` object
14. Add the book object to your list of books
> Check to see if the id of the book in question already exists, if so, update that entry instead of adding a new one  

15. Update the UI with the new list
16. Test your app

#### Write the Data Access Object for SharedPreferences
Now that we have a functioning app, we need to add our persistence to it. To do this using `SharedPreferences`, we'll store our objects as strings by their id. We will also store a list of active objects in a list of ids. Finally, we'll store a single id value representing the next id to be used
1. Create a `SharedPreferences` data member called `preferences` in your `MainActivity`
2. In the `onCreate` method of that activity, use `this.getSharedPreferences` to store a handle to the activity's `SharedPreferences` object in your `preferences` member.
> This isn't the safest way to access `SharedPreferences` but it works for a simple app like this. Be sure to check if `preferences` is null before trying to access it.

3. Pass in a `Constant String` value as the `SharedPreferences` name and `MODE_PRIVATE` as its access mode.
4. Create a class called `SharedPrefsDao`, all members of this class will be static
5. Create constant keys for id list retreival and next id retreival.
6. Create methods that use these keys to return `String`s which are retreived from `SharedPreferences`. These methods should be named `getAllBookIds` and `getNextId`
> These are methods which will simply return the value given when calling `MainActivity.preferences.getString()` and passing in the right key

7. Create a method that will accept a `Book`'s id and return the `Book` CSV string
8. Create a method to store a book called `updateBook`which accepts a single `Book` object
9. This method will check to see if the provided book is new or an updated version of the same book, if it is new, it will add the id to the list of active ids and increment the next id. The method will then store the book using its id as the key.
10. Build your app to make sure it compiles properly.

#### Write a model to interpret the raw data
A model, like a repository in MVVM architecture is in charge of managing the data sources and providing a consistent interface to the next layer in the app. In this app, since we only have one data source, it will merely act as a `Facade` that will parse the string data.
1. Create a new class called `BooksModel`
2. Write separate methods that will work with yout `SharedPrefsDao` to return the following
    a. an array of all `Book` objects, accepting no parameters
    b. a single `Book` object, accepting an id parameter
    c. the next id to be used, accepting no parameters
3. Write a method which will pass a `Book` parameter to the `upateBook` method
4. Build your app to make sure it compiles properly.

#### Write a controller to format the data and communicate with the model
1. Create a class called `BooksContoller`
2. Refactor your `buildItemView` method to move it to your new class.
> In order to call `startActivityForResult` in this method, you'll need to pass a handle to the activity you'll be building

3. Remove your list of `Book` objects, the loop to build their views, and the `LinearLayout`.
4. In your `BookController`write a method called `getBooksView`that accepts a `Context` object
5. In the new method, you'll get the list of book objects from the `BooksModel`, create a new `LinearLayout` object, use `buildItemView` to add view items to it, and return the `LinearLayout` view.
> use `imageView.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;` to set the with to match parent in your list items

6. Write a method called `handleEditActivityResult` which will accept an `Intent` object, pull the returned data from it and use it to update the model.
> This will replace some of the code in the `onActivityResult` method of your `MainActivity`

7. In the `onActivityResult` method, you will now check the requestCode and the resultCode and call the `handleEditActivityResult` method, passing it the `Intent` object.
8. In the `onCreate` method, you will now get a handle to the `ScrollView` and then call the `getBooksView` and pass that result to the `ScrollView`'s `addView` method.
9. Test the app

#### Challenge
In your testing, find bugs and small feature improvements that can improve the polish of your app.
