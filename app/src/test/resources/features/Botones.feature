Feature: button activity
    I must be in the app
    
    Scenario: Show option menu
        Given principal screen in app
        When i tap on the option menu
        Then option menu opens

    Scenario: Iniciate the game
        Given principal screen in app
        When i tap the game button
        Then appears the game screen

    Scenario: Move the ship to the left
        Given gameplay screen
        When i tap the right button in the screen
        Then the spaceship moves to the left 5 units

    Scenario: Move the ship to the right
        Given gameplay screen
        When i tap the left button in the screen
        Then the spaceship moves to the right 5 units

    Scenario: Spaceship's shoot
        Given gameplay screen
        When i tap the button in the middle of the screen
        Then the spaceship shoots a missile that moves vertically in the screen

