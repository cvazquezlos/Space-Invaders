Feature: Ranking de puntuación
    Debo haber finalizado la partida
    
    Scenario: Mostrar el ranking al terminar
        Given la puntuación actual de la partida
        When finalizo la partida
        Then se muestra el ranking

    Scenario: Si bato mi record de puntuación
        Given una puntuación de mi partida
        When supero mi puntuación máxima
        Then la aplicación me avisará de que he superado mi puntuación máxima

    Scenario: Añadir opción de visualizar ranking en las opciones del menú principal
        Given me encuentro en el menú principal de la aplicación
        And pulso la opción de ver ranking
        Then se me mostrará el ranking de puntuación

    Scenario: Botón de resetear ranking
        Given me encuentro visualizando el ranking
        When pulso el botón de 'borrar puntuaciones'
        Then se borrarán todas las puntuaciones del ranking

    Scenario: Compartir ranking
        Given me encuentro visualizando el ranking
        When pulso el botón 'compartir puntuaciones'
        And selecciono en que red social quiero compartiarlas
        Then se mostrará en el perfil de la red social seleccionada las puntuaciones compartidas