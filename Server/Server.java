/* SOFE 4790U: Distributed Systems
 * Individual Programming Assignment 2
 * Tahmid Chowdhury
 * Faculty of Engineering and Applied Science
 * Ontario Tech University
 * Oshawa, Ontario
 * tahmid.chowdhury1@ontariotechu.net
 * SID: 100822671
 * 2024-11-24
 */

import java.rmi.Naming;

// Server class to run the music library server
public class Server {
    public static void main(String[] args) {
        try {
            // Create the music library server
            LibraryImpl musicLibrary = new LibraryImpl();

            // Bind the server to the RMI registry
            Naming.rebind("//localhost/Library", musicLibrary);

            // Display a message to indicate the server is running
            System.out.println("Music Library Server is running...");
        } catch (Exception e) {
            // Handle any exceptions that occur
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
