# Multi-Language Dictionary System

## Introduction
This project is a simple multi-language dictionary system using a client-server architecture. It enables clients to request translations of words from English to various target languages. The system consists of three main components:
- **Main Server**: Handles client requests and routes them to the appropriate language-specific dictionary servers.
- **Dictionary Server**: Maintains translations for a specific language and responds to requests from the Main Server.
- **Client GUI**: A graphical user interface that allows users to input a word and select a target language for translation.

## Table of Contents
- [Introduction](#introduction)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Dependencies](#dependencies)
- [Configuration](#configuration)

## Architecture
The system consists of three core components:
1. **Main Server** (`MainServer.java`):  
   - Listens for client requests on a specified port.
   - Routes translation requests to the appropriate `DictionaryServer` based on the language code.
   - Communicates with the `DictionaryServer` over a network socket.

2. **Dictionary Server** (`DictionaryServer.java`):  
   - Hosts translations for a specific language (e.g., Spanish, Polish).
   - Receives words from the `MainServer`, looks them up in its dictionary, and returns the translation.
   - Supports multiple languages through individual server instances.

3. **Client GUI** (`ClientServerGUI.java`):  
   - A Swing-based graphical interface where users can input a word and specify a target language code.
   - Sends requests to the `MainServer` and displays the translated word.

## Installation
1. **Java Setup**: Ensure Java Development Kit (JDK) is installed on your system.
   - [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
   - Set up environment variables (e.g., `JAVA_HOME`).
   
2. **Compile the Code**:
   ```bash
   javac zad1/MainServer.java
   javac zad1/DictionaryServer.java
   javac zad1/ClientServerGUI.java

Run the Servers:
   Start the DictionaryServer for each desired language:

    java zad1.DictionaryServer <language_code> <port>

## Example:

java zad1.DictionaryServer PL 8887
java zad1.DictionaryServer ES 8888

1. **Start the MainServer**:

    java zad1.MainServer

2. **Run the Client**:

    java zad1.ClientServerGUI

## Usage

Open the client application using the ClientServerGUI.
Enter a word in English that you want to translate.
Provide a target language code (e.g., PL for Polish, ES for Spanish).
Click the "Translate" button to get the translation.

## Features

- Multi-language support through individual DictionaryServer instances.
- Real-time translation via client-server communication.
- Simple GUI for easy interaction.
- Extensible dictionary: Add more languages by deploying additional DictionaryServer instances.

## Dependencies

Java: The project requires Java 8 or higher.
Swing: Java's built-in library for GUI development is used (no additional libraries needed).

## Configuration

Main Server: Modify the languageServers map in MainServer.java to add or update IP addresses for language servers.

Dictionary Server: Customize the dictionary contents in DictionaryServer.java by modifying the initialization of the dictionary map for each language code.

Example:

    if (languageCode.equals("FR")) {
        dictionary.put("hello", "bonjour");
        dictionary.put("cat", "chat");
    }
