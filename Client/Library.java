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

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Remote interface for the music library
public interface Library extends Remote {
    // Search for songs based on a query
    List<String> searchSongs(String query) throws RemoteException;

    // Stream a song from the library
    byte[] streamSong(String songName) throws RemoteException;

    // Upload a song to the library
    boolean uploadSong(String songName, byte[] data) throws RemoteException;

    // Get metadata for a song
    String getSongMetadata(String songName) throws RemoteException;

    // Rate a song in the library
    boolean rateSong(String songName, int rating) throws RemoteException;
}
