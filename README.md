# Reading List

### Notes
- Architecture
 - Store IDs as CSV string
 - Retreive books with ID as another CSV String
 - Constructors to parse string
 - Title, read (bool), notes, ID (stored in first value)
- Process
  - pull ids
  - use ids to pull and parse each book
  - add books to unread/read arraylist
  - pass book id in intent to next screen
  - retreive book again and display
  - have add book button in bottom right (perhaps use basic activity template)

### iOS Version

A student that completes this project shows that they can:

- understand and explain the role of model objects and model controllers in MVC
- make a class or struct conform to Codable
- use FileManager to find system directories (e.g. documents directory)
- use PropertyListEncoder to save Codable objects to a file

## Introduction

Reading List is an application that lets you save a list of books and allow you to mark whether you have or have not read them. Since this application will use persistence, the books you create and check as read or not will be saved between launches of the application.

Please look at the screen recording below to know what the finished project should look like:

![](https://user-images.githubusercontent.com/16965587/43476440-d31883ac-94b5-11e8-89f9-c83f30099e69.gif)

## Instructions

Please fork and clone this repository. This repository comes with a started Xcode project that includes images that you will need.

### Part 1 - Book and BookController

#### Book

1. Create a "Book.swift" file. Create a struct called `Book`
2. This struct should have the following properties:
    - A `title` String
    - A `reasonToRead` String
    - A `hasBeenRead` Bool
3. Create an initializer for the struct so that you can give a default value of `false` to the `hasBeenRead` parameter.
4. Adopt both the `Equatable`, and `Codable` protocols.

#### BookController

Up until now, the model controllers you've made have been more or less the same, where they just have CRUD methods, and an array of your model object. Now however since you will be implementing a form of persistence, the model controller gets another responsibility which is to make sure that when a `Book` changes (whether by creating a new one, deleting once, or changing one), those changes are saved and persisted. It would be weird if the user deletes a `Book`, then the next time they open the app, the book is magically there again.

 1. Create a "BookController.swift" file, and create a `BookController` class inside of it.
 2. Add a variable called `books` and set its value to an empty array of `Book` objects.
 
Let's prepare the `BookController` to handle saving and loading from the persistent store. First of all, `Codable` is the main driving force behind this form of persistence. It allows us to convert `Book` objects to Property-List form and back to `Book`s. We need a place to save this property list on the device's hard drive. 

3. Create a computed property called `readingListURL: URL?`. Inside of the computed property, you should:
    - Get the user's document directory using the `FileManager` class.
    - Create a filename string for the plist, such as "ReadingList.plist"
    - Return a url that appends the filename string to the document directory. In doing this, you will create a full path wherein the `Book`s in plist form will be stored on the user's device.
4. Create a function called `saveToPersistentStore()`. This function will be responsible for saving any changes to any `Book` object so that the changes will still be there when the user comes back into the application. You can implement this by doing the following:
    - Create an instance of `PropertyListEncoder`. 
    - Inside of a do-try-catch block create a constant called `booksData`. Using the `encode(value: ...)` function of the property list encoder, encode the `books` array into `Data`.
    - Call the `write(to: URL)` function on the data you encoded computed property. The url you pass in should be an unwrapped version of the `readingListURL` property.
5. Create a function called `loadFromPersistentStore()`. This function will be responsible for grabbing the property list stored on the device, and converting the information in it back into an array of `Book` objects so the application may display them on the table view, etc. To implement this:
    - Inside of a do-try-catch- block, unwrap the `readingListURL` property.
    - Still inside of the block, use the `Data(contentsOf: URL)` initializer to get access to the property list form of the books. Assign this data you initialize to a constant.
    - Initialize a `PropertyListDecoder` and assign it to a constant.
    - Create a constant called `decodedBooks`. Set its value by calling the `decode` method on the property list decoder you just created, and passing in the type it should be decoded as, and the data constant you made a couple steps ago. (Hint: the type parameter to this function should be `[Book].self`)
    - Set the value of the `books` property in the `BookController` to the `decodedBooks` you just made.
    - In the catch block, you should make an error message that is descriptive of what happened

6. Like always, we will need to make CRUD methods. As we're using a form of persistence, **be sure to call `saveToPersistentStore()` at the end of each of these or the changes will not persist**:
    - Add a "Create" method that initializes a new `Book` object. In order to persist the newly created `Book`, call the `saveToPersistentStore()` method at the end of this function.
    - Add a "Delete" method that passes in a `Book` object as a parameter, and removes it from the `books` array.
    - We'll need two "Update" methods: 
      -  One is to update a `Book` object's `hasBeenRead` property. Call it `updateHasBeenRead(for book: Book)`. It should simply swap the `hasBeenRead` value from `false` to `true` and vice-versa. 
      - The other is to edit the `Book`'s `title` and/or `reasonToRead` properties.

You may have noticed from the screen recording that the table view is going to have more than one section. In order to facilitate the implementation of this multi-section table view, we're going to add a couple computed properties to the `BookController`:

7. Create a computed property called `readBooks: [Book]`. Inside of the closure of the computed property, you will need to return an array of all of the `Book` objects from the `books` array whose `hasBeenRead` property is true. The easiest way to do that is by using the `.filter` higher-order function. If you are unfamiliar with this function, read the part of [this article](https://useyourloaf.com/blog/swift-guide-to-map-filter-reduce/) titled "Filter". If you still have questions, please ask in the help channel for your cohort, and a PM will explain it to you in more depth.
8. Create a similar computed property called `unreadBooks: [Book]` that does the same thing, except it returns an array of `Book`s whose `hasBeenRead` property is `false`.

### Part 2 - Storyboard Scenes

The layout of this application uses a simple master-detail interface.

1. Drag out a `UITableViewController` scene, and embed it in a navigation controller. Set the navigation controller as the initial view controller. Then, drag out a `UIViewController` scene that will serve as the detail view controller.
2. Set the table view controller's title to be "Reading List"
3. In the table view's cell: 
    - Set its style to "Custom" if it isn't already.
    - Add a label that will show the title of the book.
    - Add a button that will be used to show images of a checked or unchecked checkbox, indicating whether the book has been read or not. Remove the button's title.
    - Constrain these UI elements to the cell's content view. You may use a stack view, or constrain them individually. The checkbox button should have a 1:1 aspect ratio. 
      - **NOTE:** After adding the constraints, a way to make sure the constraints on the button are correct is to set its image property to the checked or unchecked images using the Attributes Inspector. If the constraints are correct, the image should be square, and also not expand off the button.
      - Create a show segue from the cell to the detail view controller. Give the segue an identifier
4. Add a bar button item in the top-right corner of the table view controller. Set its "System Item" to "Add". Create a segue from it to the detail view controller. Give the segue an identifier.
5. Create a Cocoa Touch subclass for both the table view controller and the custom `UITableViewCell`. Call the `UITableViewController` subclass `ReadingListTableViewController`, and the `UITableViewCell` subclass `BookTableViewCell`. Set the table view controller and cell's class in the Identity inspector. 
6. In the custom cell class, create outlets from the label and button, and an action from the button as well.

Now we'll set up the detail view controller. This view controller will serve two purposes. First, it will be used to add new books to the reading list. The second is to view an existing book's information and potentially edit it.

5. Add a text field and a text view to the detail view controller scene. They will be used to show, create, and edit a `Book`'s `title` and `reasonToRead` strings respectively. Constrain them with the keyboard in mind so that they won't get covered by it when editing the fields.
6. Add a navigation item to the detail view controller, then add a bar button item to the top-right corner of the view controller. Set its "System Item" to "Save"
7. Create a Cocoa Touch Subclass of `UIViewController` called `BookDetailViewController` and set this view controller's class to it in the Identity Inspector.
8. Add outlets from the text field and text view, and an action from the save button.

### Part 3 - Wiring Everything Up

#### BookTableViewCell

1. Add a `var book: Book?` variable to the `BookTableViewCell` class.
2. If you haven't done so already, create outlets from the label and button, and an action from the button from the storyboard.
3. Create an `updateViews()` function that takes the values in the `book` property and sets its `title` in the label, and set the button's image to either the checked or unchecked image that are in the assets folder. 
    - **NOTE:** You may either use image literals or the `UIImage(named: String)` initializer to get access to the checkbox images from the assets folder.

This custom cell will follow the delegate pattern in order to let the table view controller know that the user just tapped the checkbox button indicating they have or haven't read a book.

4. Create a new Swift file called "BookTableViewCellDelegate.swift", and create a protocol with the same name (minus the ".swift", of course).
5. Add a function called `func toggleHasBeenRead(for cell: BookTableViewCell)`
6. Back in the `BookTableViewCell` class, add a `weak var delegate: BookTableViewCellDelegate?` variable.
7. Call the `delegate` property's `toggleHasBeenRead(for cell: ...)` function in the action of the button.

#### ReadingListTableViewController

This table view controller is going to set the table view up to use two sections; one to group all of the read books, and one to group all of the unread books. This is potentially the first time you have used with multiple sections. As always when setting up a table view, we need to use the `numberOfRowsInSection` and `cellForRowAt` methods. However since we want a table view with more than one section, we also need to implement the `numberOfSections(in tableView: ...)` method. Another useful `UITableViewDataSource` method to use when your table view has more than one section is the `titleForHeaderInSection` method that allows us to give a title to each section in order to let the user know how the table view is grouping its cells.

1. Add a `bookController` constant, and set its value to a new instance of `BookController`.
2. Implement the `numberOfSections(in tableView: ...)`. It should already be in the table view subclass above the `numberOfRowsInSection`. If you've deleted it, simply call it again. This method is very similar to the `numberOfRowsInSection`. It simply wants you to return an `Int` indicating how many sections the table view should have. In this case, **we simply want to return two.**
3. Implement the `numberOfRowsInSection` next. This time however, since we are using multiple sections, we can't get away with simply returning the `count` of the `books` array, because that holds all of the books in the application. We need to now say, "For my first section (that will display only the **read** books), return the number of read books, and for the second section return the number of unread books." Since the section that the table view is trying to get the number of rows for is passed in as a parameter, we can easily check the section using a conditional statement, and return the correct amount. Use the `readBooks` and `unreadBooks` computed properties in the `bookController` to get the right amount of rows depending on the section. The first section (0) will show the read books, and the second section (1) will show the unread books.

Like any time we set up a table view, we need to get an instance of the model object in order to either set up a cell or delete it when the user uses swipe to delete to remove it from the array. If we had a single section in our table view, in order to get that instance of a `Book` we would do something like this:

``` Swift
let book = bookController.books[indexPath.row]
```

However since we have multiple sections, you need to implement some logic similar to that in step 3 where we check the `section` property on the `indexPath` to know which section is being either set up in the `cellForRowAt`, edited in the `commit editingStyle`, etc. **and then** we can use the `indexPath.row` to pull the correct instance of the model object from the correct array. For example, if the user swipes and deletes the second cell in the first section, we need to look in the `readBooks` array because that is the data source for that section. If the cell that was being deleted in the second section, we would use the `unreadBooks` array instead. 

Since you will need to use this logic in multiple places in this table view controller, make a function that will do that logic for you so you don't have to re-write this logic over and over throughout this class. Call the function `func bookFor(indexPath: IndexPath) -> Book`. Try to write it on your own, however you may use the the example function that is hidden right now below:

<details><summary>Example Function</summary>
<p>

``` Swift
    private func bookFor(indexPath: IndexPath) -> Book {
        if indexPath.section == 0 {
            return bookController.readBooks[indexPath.row]
        } else {
            return bookController.unreadBooks[indexPath.row]
        }
    }
```

</p>
</details>

This function will facilitate the process of getting the correct instance of `Book` every time, while also keeping your code clean by not repeating this same logic all over the table view controller subclass. **Every place you would use `bookController.books[indexPath.row]` to grab an instance of `Book`, simply use the `bookFor(indexPath: ...)` function you just wrote.**

4. Adopt the `BookTableViewCellDelegate` protocol, and add the `toggleHasBeenRead(for cell: ...)` function. The function should call the `BookController`'s `updateHasBeenRead(for: Book)` method. You will need an instance of `Book` to pass in to this function.  At the end of this function, reload the table view.
    - **HINT:** Think about how to get an `IndexPath` using the cell parameter of the `toggleHasBeenRead(for cell: ...)`. Once you have an `IndexPath`, you can get an instance of `Book` to pass into the update function.
5. Fill out the `cellForRowAt` function, making sure to set the cell's `delegate` property, or else the delegate relationship between the table view controller and the custom cell will not exist.

6. Fill out the `commit editingStyle` function. You only need to worry about the `.delete` case.

Another function that we can use to help the user know why we have split the data up into sections is the `titleForHeaderInSection` method. This is also a part of the `UITableViewDataSource` protocol. It allows you to return a string that will be put in the header of a section. Similar to the `numberOfRowsInSection` method, you will need to check the `section` parameter of the function, and return a string depending on its value. For example, the title of the first section (0) could be "Read Books" and the title of the second section could be "Unread Books". 

7. Fill out the `titleForHeaderInSection` function, giving each section a unique title.

#### BookDetailViewController

As stated before, the `BookDetailViewController` will function as a way to create a new `Book` or to view/update an existing one, depending on the segue that is triggered.

1. Add a `var bookController: BookController?` variable. Whether the user is creating a new `Book` or updating an existing one, this view controller will need access to the `BookController` to do so.
2. Add a `var book: Book?` variable. This is going to be nil if the user taps on the plus button to create a new `Book`, or it will actually hold a `Book` object if they are trying to view/update one by segueing when tapping on a cell in the table view.
3. Be sure that you've added outlets to the text field and text view, and the action from the save button if you haven't done so.
4. Create an `updateViews()` method. If the `book` variable has a value, it should take its `title` and put it in the text field, and put its `reasonToRead` in the textView. It should also set the title of the view controller to the `book`'s title if there is one, or it should set the title to "Add a new book" if a new book is going to be created.
5. In the action of the button, it should either call the `createBook` method in the `bookController` if the `book` property is `nil` or call the `update` method in the `bookController` if the `book` property is not nil.

#### Back to the ReadingListTableViewController

Finally, we need to set up the `prepare(for segue: ...)` in order to pass the `bookController` and potentially a `Book` to the `BookDetailViewController`.

1. Check the segue's `identifier` property using a conditional statement. If it's the one from the "Add" bar button item, then this means the user wants to create a new `Book`. Pass the `bookController` variable in the table view controller to the segue's destination view controller. (You will need to cast the destination as the correctly typed view controller)
2. If the segue's identifier matches the cell's segue identifier, do the same thing as step 1, and also pass a `Book` object that was selected in the table view to the destination view controller's `book` property as well.

### Go Further

- Sort the cells in each section alphabetically
- Change the `Book` model to hold a `UIImage`. Modify the detail view controller to allow the user to select an image from their photo library as the book's cover (Look at `UIImagePickerController`). Modify the cell to display the book's cover.
