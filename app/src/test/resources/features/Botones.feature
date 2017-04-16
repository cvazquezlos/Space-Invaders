Feature: Actividad sobre botones
    Debo encontrarme en la aplicación
    
    Scenario: Mostrar menú de opciones
        Given la pantalla principal de la aplicación
        When pincho sobre el botón de opciones
        Then se abre el menú de opciones

    Scenario: Iniciar el juego
        Given la pantalla principal de la aplicación
        When pincho sobre el botón de jugar
        Then se cambia de pantalla a la pantalla de juego

    Scenario: Desplazar la nave hacia la izquierda
        Given me encuentro en la pantalla de juego
        When pulso en el botón de la derecha de la pantalla
        Then la nave se desplaza hacia la izquierda en 5 unidades

    Scenario: Desplazar la nave hacia la derecha
        Given me encuentro en la pantalla de juego
        When pulso en el botón de la izquierda de la pantalla
        Then la nave se desplaza hacia la derecha en 5 unidades

    Scenario: Disparar proyectil
        Given me encuentro en la pantalla de juego
        When pulso en el botón del centro de la pantalla
        Then la nave dispara un proyectil que se desplaza verticalmente