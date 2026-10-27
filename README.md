# Restaurant Management Console App

A Java console application for a restaurant. Customers can browse the menu and place orders, and managers can add new food items that are saved permanently to a file.

The project is split into small, single-purpose classes using object-oriented principles, so the code is easy to read, extend and maintain.

## Features

### Customer
- View the restaurant menu grouped by category (Starter, Main Course, Drinks)
- Place an order one item at a time by entering category, food ID and quantity
- Keep adding items until the customer chooses to stop
- View an itemized bill with the total amount
- Confirm or cancel the order

### Manager
- Add new food items by entering category, name and price
- Food ID is **auto-generated** (next available ID within the category)
- The new item appears in the menu immediately
- Added items are **saved permanently** to `menu.txt` and are available the next time the program runs

### General
- Input validation on every prompt, with a clear message and a retry on invalid input
- Default menu is created automatically on the first run
- Menu persists between runs using a plain text file

## Tech Stack

- **Language:** Java (JDK 8 or higher)
- **Storage:** Plain text file (`menu.txt`)
- **Interface:** Command line (console)
- **Dependencies:** None (Java standard library only)

## Project Structure

```
.
├── Main.java           # Entry point; asks whether the user is a customer or manager
├── Customer.java       # Customer flow: view menu, place order, confirm bill
├── Manager.java        # Manager flow: add new food items
├── Menu.java           # Holds the food list, finds foods, generates IDs, displays the menu
├── MenuStorage.java    # Saves the menu to a file and loads it back
├── Food.java           # Food model (category, id, name, price)
├── Category.java       # Enum: STARTER, MAIN_COURSE, DRINKS
├── Order.java          # A customer's order; calculates and prints the bill
├── OrderItem.java      # One order line (food + quantity)
├── InputHelper.java    # Reads and validates all console input
└── menu.txt            # Generated automatically on first run
```

### Class Responsibilities

| Class | Responsibility |
|---|---|
| `Main` | Starts the program and routes to `Customer` or `Manager` |
| `Customer` | Handles the complete customer journey |
| `Manager` | Handles adding new food items |
| `Menu` | In-memory list of foods; add, search, auto-ID and display |
| `MenuStorage` | File read/write; converts `Food` objects to text and back |
| `Food` | Data model for a menu item |
| `Category` | Enum for the fixed food categories, with code and display name |
| `Order` / `OrderItem` | Order data and bill calculation |
| `InputHelper` | Centralized input reading and validation |

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

Check your installation:

```bash
java -version
javac -version
```

### Installation

```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
```

### Compile and Run

```bash
javac *.java
java Main
```

> Run the program from the project folder. `menu.txt` is created in the directory the program is launched from.

## Usage

When the program starts, it asks who is using it:

```
Are you a customer or manager? (1 = Customer, 2 = Manager):
```

### Customer flow

1. Choose `1`
2. Answer `yes` to view the menu
3. For each item, enter the category (1-3), food ID and quantity
4. Answer `yes` to order more, or `no` to finish
5. Review the bill and confirm the order

### Manager flow

1. Choose `2`
2. Enter the category, food name and price
3. The ID is generated automatically, and the updated menu is shown
4. Answer `yes` to add another item, or `no` to finish

### Sample menu

```
================ MENU ================

[1] Starter
   ID    Name                           Price
   1     Chicken Soup                  150.00 Tk
   2     French Fries                  120.00 Tk

[2] Main Course
   ID    Name                           Price
   1     Chicken Biryani               280.00 Tk
   2     Beef Curry with Rice          320.00 Tk

[3] Drinks
   ID    Name                           Price
   1     Coca Cola                      50.00 Tk
   2     Mango Juice                    80.00 Tk

======================================
```

## Data Storage

Menu data is stored in `menu.txt`, one food item per line:

```
categoryCode,id,price,name
```

Example:

```
1,1,150.0,Chicken Soup
2,1,280.0,Chicken Biryani
3,2,80.0,Mango Juice
```

| Situation | Behavior |
|---|---|
| `menu.txt` does not exist (first run) | Default menu is loaded and written to `menu.txt` |
| `menu.txt` exists | Menu is loaded from the file; defaults are not applied |
| Manager adds a food | List is updated and the whole menu is saved to `menu.txt` |

To **reset the menu** to defaults, delete `menu.txt` and run the program again.

## Input Validation

Every prompt validates the input and asks again if it is invalid:

- Role and category must be a valid option number
- Food ID and quantity must be positive whole numbers
- Price must be a positive number
- Food name cannot be empty
- Yes/no questions accept `yes`, `y`, `no`, `n` (case-insensitive)
- An ID that does not exist in the chosen category shows an error message

## Design Notes

- **Enum for categories:** the three categories are fixed, so an enum provides type safety and removes magic numbers.
- **Separation of concerns:** input handling, data model, business logic and file storage each live in their own class.
- **Shared `Menu` object:** `Customer` and `Manager` receive the same `Menu` instance, so changes by the manager are instantly visible to customers.
- **Auto-generated IDs:** new ID = highest existing ID in the category + 1, which avoids duplicates.

## Future Improvements

- Edit and delete food items
- Manager authentication (password)
- Order history and daily sales report
- Dynamic categories added by the manager
- Database storage (SQLite / MySQL) instead of a text file
- Discount, tax and service charge calculation
- GUI or web interface

## Author

**Your Name**
GitHub: [@your-username](https://github.com/mazharulislamsajib01)

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
