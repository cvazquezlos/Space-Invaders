Feature: button activity
    I must be in the app
    
    Scenario: Show option menu
        Given Principal screen in app
        When I tap on the option menu
        Then option menu opens

    Scenario: Iniciate the game
        Given Principal screen in app
        When I tap the game button
        Then appear the game screen 

    Scenario: Move the ship to the left
        Given Gameplay screen
        When I tap the right button in the screen
        Then the spaceship moves to the left 5 units

    Scenario: Move the ship to the right
        Given Gameplay screen
        When I tap the left button in the screen
        Then the spaceship moves to the right 5 units

    Scenario: Spaceship's shoot
        Given Gameplay screen
        When I tap the button in the middle of the screen
        Then the spaceship shoots a missile that moves vertically in the screen
