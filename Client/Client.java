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
import java.util.Scanner;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Library musicLibrary = (Library) Naming.lookup("//localhost/Library");

            try (Scanner scanner = new Scanner(System.in)) {
                // Display the menu
                System.out.println("Welcome to the Music Library");
                System.out.println("1. Search Songs");
                System.out.println("2. Stream Song");
                System.out.println("3. Upload Song");
                System.out.println("4. Get Song Metadata");
                System.out.println("5. Rate Song");
                System.out.println("6. Exit");

                // Process user input
                while (true) {
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    // Perform the selected operation
                    switch (choice) {
                        case 1:
                            // Search for songs
                            System.out.print("Enter search query: ");
                            String query = scanner.nextLine();
                            System.out.println("Search results: " + musicLibrary.searchSongs(query));
                            break;

                        case 2:
                            // Stream a song
                            System.out.print("Enter song name: ");
                            String songName = scanner.nextLine();
                            byte[] data = musicLibrary.streamSong(songName);
                            try (FileOutputStream fos = new FileOutputStream("Downloads/" + songName)) {
                                fos.write(data);
                            }
                            System.out.println("Song streamed and saved: " + songName);
                            break;

                        case 3:
                            // Upload a song
                            System.out.print("Enter the path of the song to upload: ");
                            String filePath = scanner.nextLine();
                            File file = new File(filePath);
                            byte[] fileData = new byte[(int) file.length()];
                            try (FileInputStream fis = new FileInputStream("Downloads/" + file)) {
                                fis.read(fileData);
                            }
                            System.out.print("Enter the name for the uploaded song: ");
                            String uploadName = scanner.nextLine();
                            musicLibrary.uploadSong(uploadName, fileData);
                            System.out.println("Song uploaded successfully: " + uploadName);
                            break;

                        case 4:
                            // Get metadata for a song
                            System.out.print("Enter song name for metadata: ");
                            String metaName = scanner.nextLine();
                            System.out.println("Metadata: " + musicLibrary.getSongMetadata(metaName));
                            break;

                        case 5:
                            // Rate a song
                            System.out.print("Enter song name to rate: ");
                            String rateName = scanner.nextLine();
                            System.out.print("Enter your rating (1-5): ");
                            int rating = scanner.nextInt();
                            scanner.nextLine();
                            musicLibrary.rateSong(rateName, rating);
                            System.out.println("Rated successfully.");
                            break;

                        case 6:
                            // Exit the program
                            System.out.println("Exiting...");
                            return;

                        default:
                            // Invalid choice
                            System.out.println("Invalid choice!");
                    }
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
