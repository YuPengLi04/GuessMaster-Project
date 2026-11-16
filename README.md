# GuessMaster3

**GuessMaster3** is an interactive Android trivia game built in Java where players guess the birthdates or founding dates of notable people, politicians, singers, and countries to earn tickets.

---

## Features

* **Interactive Trivia Gameplay:** Displays a portrait or flag alongside the name of a target entity and challenges the player to guess the exact date (`mm/dd/yyyy`).
* **Smart Date Hints:** Evaluates guesses using custom `Date` comparison logic and displays `AlertDialog` prompts guiding the player to try an **earlier** or **later** date.
* **Ticket Reward System:** Awards difficulty-scaled tickets upon each correct guess and maintains a running total on screen.
* **Skip / Change Entity:** Allows players to cycle randomly to a different entity at any time.
* **Robust Input Validation:** Guards against empty submissions, arbitrary strings, malformed date formats, and out-of-range calendar dates before parsing.

---

## Project Architecture

```text
app/src/main/
├── AndroidManifest.xml                  # App configuration & launcher activity declaration
├── java/com/example/guessmaster3/
│   ├── GuessMaster.java                 # Main game Activity, UI bindings, and game loop
│   ├── Entity.java                      # Base class defining shared entity attributes & ticket logic
│   ├── Person.java                      # Subclass representing a general person
│   ├── Politician.java                  # Subclass of Person adding political party metadata
│   ├── Singer.java                      # Subclass of Person adding debut album & release date
│   ├── Country.java                     # Subclass of Entity adding capital city metadata
│   ├── Date.java                        # Custom date representation and chronological comparison
│   └── MainActivity.java                # Secondary/template Activity
└── res/
    ├── layout/
    │   └── activity_guess_activity.xml  # ConstraintLayout UI for the main game screen
    ├── drawable/                        # Entity images (justint, celidion, my_creater, usaflag)
    └── values/                          # App strings, colors, and Material 3 theme definitions
```

---

## Included Entities

| Entity | Category | Target Date (`mm/dd/yyyy`) | Difficulty |
| :--- | :--- | :--- | :--- |
| **Justin Trudeau** | Politician | `12/25/1971` | `0.25` |
| **Celine Dion** | Singer | `03/30/1961` | `0.50` |
| **myCreator** | Person | `09/01/2000` | `1.00` |
| **United States** | Country | `07/04/1776` | `0.10` |

---

## Tech Stack & Requirements

* **Language:** Java 8 (`JavaVersion.VERSION_1_8`)
* **UI Toolkit:** AndroidX AppCompat, Material 3, ConstraintLayout
* **SDK Versions:**
  * `minSdk`: 26 (Android 8.0 Oreo)
  * `targetSdk`: 34 (Android 14)
  * `compileSdk`: 34
* **IDE:** Android Studio (Jellyfish or newer recommended)

---

## Getting Started

1. **Clone or Open the Project:**
   * Open Android Studio and select **Open**, then navigate to the `GuessMaster3` root directory.
2. **Sync Gradle:**
   * Allow Android Studio to finish syncing `build.gradle.kts` and indexing project resources.
3. **Verify Run Configuration:**
   * Ensure **`app`** is selected in the top toolbar's Run Configuration dropdown (next to the green **Run ▶** button) and that an Android Emulator (API 26+) or physical device is connected.
4. **Build & Run:**
   * Click **Run (▶)** to install and launch **GuessMaster3**.

---

## How to Play

1. Tap **Start Game** on the welcome dialog.
2. Look at the displayed image and entity name.
3. Enter your date guess in the text field using the **`mm/dd/yyyy`** format (e.g., `07/04/1776`).
4. Tap **Submit Guess** (left button) to check your answer:
   * If your guess is too early or too late, follow the hint dialog and try again.
   * If your guess is exact, collect your awarded tickets and tap **Continue Game** to load the next entity.
5. Tap **Change Entity** (right button) at any time to clear the input field and skip to another random entity.