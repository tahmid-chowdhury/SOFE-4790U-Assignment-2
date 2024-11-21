/* SOFE 4790U: Distributed Systems
 * Individual Programming Assignment 1
 * Tahmid Chowdhury
 * Faculty of Engineering and Applied Science
 * Ontario Tech University
 * Oshawa, Ontario
 * tahmid.chowdhury1@ontariotechu.net
 * SID: 100822671
 * 2024-10-25
 */

import java.rmi.Naming;

public class Server {
    public static void main(String[] args) {
        try {
            LibraryImpl musicLibrary = new LibraryImpl();
            Naming.rebind("//localhost/Library", musicLibrary);
            System.out.println("Music Library Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
