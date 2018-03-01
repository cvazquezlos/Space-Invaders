Feature: Button activity

  Scenario: Show option menu
    Given I wait for the "MainActivity" screen to appear
    When I press "opcion_boton"
    Then I go back
    
  Scenario: Iniciate the game
    Given I wait for the "MainActivity" screen to appear
    When I press "play_boton"
    Then I go back
    
  Scenario: Move the ship to the left
    Given I wait for the "MainActivity" screen to appear
    When I press "play_boton"
    And I press "control_derecha"
    Then I go back
    
  Scenario: Move the ship to the right
    Given I wait for the "MainActivity" screen to appear
    When I press "play_boton"
    And I press "control_izquierda"
    Then I go back
    
  Scenario: Ship's shoot
    Given I wait for the "MainActivity" screen to appear
    When I press "play_boton"
    And I press "disparo"
    Then I go back