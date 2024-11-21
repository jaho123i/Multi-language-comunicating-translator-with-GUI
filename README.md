Overview

This project is a multi-component dictionary translation system that allows users to translate words between languages using a client-server architecture. It includes a main server, language-specific dictionary servers, and a client application with a graphical user interface (GUI).
Components

    MainServer.java:
        Acts as a central hub to manage translation requests.
        Redirects user queries to the appropriate DictionaryServer based on the target language code.
        Listens for client requests on port 8888.

    DictionaryServer.java:
        Handles translations for a specific language.
        Maintains a dictionary of words and their translations.
        Listens for requests from the MainServer and returns the appropriate translation.
        Each language requires its own instance of DictionaryServer.

    ClientServerGUI.java:
        A client application with a simple GUI.
        Allows users to input a word and a target language code.
        Displays the translation received from the main server.

How to Run

    Main Server:
        Compile and run MainServer.java.
        Ensure the main server is listening on the correct port and has access to the DictionaryServer addresses.

    Dictionary Servers:
        Compile and run DictionaryServer.java with the following arguments:
            languageCode: The code for the target language (e.g., PL for Polish, ES for Spanish).
            port: The port number on which the server should listen.
        Example: java DictionaryServer PL 8887

    Client Application:
        Compile and run ClientServerGUI.java.
        Enter a word and target language code (e.g., "hello" and "ES") to get a translation.

Prerequisites

    Java Development Kit (JDK) installed.
    Basic understanding of Java networking and GUI development.

Example Usage

    Start a DictionaryServer for Spanish on port 8887:

java DictionaryServer ES 8887

Run the MainServer:

    java MainServer

    Launch the client GUI and enter:
        Word: hello
        Language Code: ES
        Click "Translate" to see the translation: hola.

Features

    Supports multiple languages by running different instances of DictionaryServer.
    User-friendly GUI for easy interaction.
    Easily extensible by adding more language servers with the desired translations.

Future Improvements

    Add support for dynamic addition of DictionaryServer instances.
    Implement error handling for server connections and invalid inputs.
    Enhance the dictionary with more words and languages.
  
