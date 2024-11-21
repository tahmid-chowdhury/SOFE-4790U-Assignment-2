# Distributed Music Library Application
* SOFE 4790U: Distributed Systems Individual Programming Assignment 2
* Tahmid Chowdhury
* [tahmid.chowdhury1@ontariotechu.net](mailto:tahmid.chowdhury1@ontariotechu.net)
* SID: 100822671
* 2024-11-24

## Description
Welcome to the Distributed Music Library Application, a scalable and feature-rich system built using Java RMI. This innovative project transforms the traditional music library into a distributed, interactive platform that allows multiple clients to search for songs, stream them, contribute new tracks, view metadata, and rate their favorite music.

## Prerequisites
* Java Development Kit (JDK)
* Any Integrated Development Environment (IDE) and/or terminal

## Instructions to Run the Application
1. Clone the repository to your local machine:
   ```bash
   git clone tahmid-chowdhury/SOFE-4790U-Assignment-2
   cd SOFE-4790U-Assignment-2
   ```
2. Place your audio files inside of the Songs directory, in the Server folder
3. Open a terminal window and navigate to the project directory, then compile the Java files using the following command:
   ```bash
   javac *.java
   ```
4. Start the RMI registry using the following command:
   ```bash
   rmiregistry
   ```
5. In a new terminal, run the server application using the following command:
   ```bash
   java "-Djava.security.policy=policy.txt" Server
   ```
   You should see a message indicating that the server has started.
6. In another new terminal, run the client application using the following command:
   ```bash
   java "-Djava.security.policy=policy.txt" Client
   ```
   You may connect as many clients as you would like, as this is a multithreaded application.

Follow the on-screen instructions to search, stream, upload, or rate songs!

## Notes:
* Ensure that both the server and client are running on the same machine or within the same network.
