package com.recentlyplayedstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecentlyPlayedStore {
	 private int initialCapacity;
	    private Map<String, LinkedList<Song>> userPlaylists;

	    public RecentlyPlayedStore(int initialCapacity) {
	        this.initialCapacity = initialCapacity;
	        this.userPlaylists = new HashMap<>();
	    }

	    public void playSong(String user, String songName) {
	        userPlaylists.putIfAbsent(user, new LinkedList<>());
	        LinkedList<Song> playlist = userPlaylists.get(user);

	        Song song = new Song(songName);

	        if (playlist.contains(song)) {
	            playlist.remove(song);
	        }

	        playlist.addFirst(song);

	        if (playlist.size() > initialCapacity) {
	            playlist.removeLast();
	        }
	    }

	    public List<String> getRecentlyPlayed(String user) {
	        LinkedList<Song> playlist = userPlaylists.getOrDefault(user, new LinkedList<>());
	        List<String> songNames = new ArrayList<>();

	        for (Song song : playlist) {
	            songNames.add(song.getName());
	        }

	        return songNames;
	    }
	}

	
