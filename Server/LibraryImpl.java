import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.util.*;

public class LibraryImpl extends UnicastRemoteObject implements Library {
    private Map<String, File> songs = new HashMap<>();
    private Map<String, List<Integer>> ratings = new HashMap<>();
    private Map<String, String> metadata = new HashMap<>();

    public LibraryImpl() throws RemoteException {
        super();
        loadSongs();
    }

    private void loadSongs() {
        // Pre-load songs and metadata from the server directory
        File songDir = new File("Songs");
        if (!songDir.exists()) songDir.mkdir();

        for (File song : songDir.listFiles()) {
            songs.put(song.getName(), song);
            metadata.put(song.getName(), "Artist: Daft Punk, Album: Around the World, Duration: 7:10");
        }
    }

    @Override
    public List<String> searchSongs(String query) throws RemoteException {
        List<String> results = new ArrayList<>();
        for (String song : songs.keySet()) {
            if (song.toLowerCase().contains(query.toLowerCase())) {
                results.add(song);
            }
        }
        return results;
    }

    @Override
    public byte[] streamSong(String songName) throws RemoteException {
        try {
            File song = songs.get(songName);
            if (song == null) throw new FileNotFoundException("Song not found!");

            byte[] data = new byte[(int) song.length()];
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(song))) {
                bis.read(data);
            }
            return data;
        } catch (IOException e) {
            throw new RemoteException("Error streaming song: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean uploadSong(String songName, byte[] data) throws RemoteException {
        try {
            File song = new File("Songs/" + songName);

            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(song))) {
                bos.write(data);
            }
            songs.put(songName, song);
            metadata.put(songName, "Uploaded by user, Artist: Daft Punk, Album: One More Time, Duration: 5:20");
            ratings.put(songName, new ArrayList<>());
            return true;
        } catch (IOException e) {
            throw new RemoteException("Error uploading song: " + e.getMessage(), e);
        }
    }

    @Override
    public String getSongMetadata(String songName) throws RemoteException {
        return metadata.getOrDefault(songName, "Metadata not found!");
    }

    @Override
    public boolean rateSong(String songName, int rating) throws RemoteException {
        if (!songs.containsKey(songName)) {
            throw new RemoteException("Song not found!");
        }
        ratings.putIfAbsent(songName, new ArrayList<>());
        ratings.get(songName).add(rating);

        // Calculate the average rating
        List<Integer> songRatings = ratings.get(songName);
        double average = songRatings.stream().mapToInt(Integer::intValue).average().orElse(0);
        metadata.put(songName, metadata.get(songName) + ", Average Rating: " + String.format("%.2f", average));

        return true;
    }
}
