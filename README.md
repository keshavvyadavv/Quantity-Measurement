# Quantity-Measurement

A simple Java application to measure and compare quantities in **Feet** using clean design and unit testing.

---

## Features

- Immutable `Feet` class  
- Factory method `fromString()`  
- Custom exception `InvalidFeetException`  
- Proper `equals()` and `hashCode()` implementation  
- Unit testing using JUnit 5  

---

```
## 📂 Folder Structure

Quantity-Measurement
│
├── .mvn/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── apps/
│   │               └── quantitymeasurement/
│   │                   ├── app/
│   │                   │   └── QuantityMeasurementApp.java
│   │                   │
│   │                   └── domain/
│   │                       ├── Feet.java
│   │                       └── InvalidFeetException.java
│   │                       └── Inches.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── apps/
│                   └── quantitymeasurement/
│                       └── domain/
│                           └── FeetTest.java
│                           └── InchesTest.java
│
├── .gitignore
├── pom.xml
└── README.md

```

---

## Test Cases Covered

- Same value comparison  
- Different value comparison  
- Null comparison  
- Different type comparison  
- Valid string input  
- Invalid string input (Exception case)  

---

## Example Output

---

## Technologies Used

- Java  
- JUnit 5

🔗 *Code Link:*  
👉 [UC-1](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC1-FeetEquality)

---

# UC2 - Equality Comparison

This use case implements equality comparison for:

- Feet
- Inches

Objects are created using:
Feet.fromString("1.0");
Inches.fromString("1.0");

Invalid numeric input throws a custom exception.

## Equality Rules
- Same value → true
- Different value → false
- Null → false
- Same reference → true
- Different class → false

## Tech
Java, JUnit 5

🔗 *Code Link:*  
👉 [UC-2](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC2-InchEquality)
