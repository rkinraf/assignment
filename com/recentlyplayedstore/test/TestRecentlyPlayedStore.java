package com.recentlyplayedstore.test;
import org.testng.annotations.Test;

import com.recentlyplayedstore.RecentlyPlayedStore;

import org.testng.Assert;

import java.util.Arrays;
import java.util.List;


public class TestRecentlyPlayedStore {

	

	    @Test
	    public void testSingleUserPlaylist() {
	        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

	        store.playSong("user1", "S1");
	        store.playSong("user1", "S2");
	        store.playSong("user1", "S3");

	        List<String> recentlyPlayed = store.getRecentlyPlayed("user1");
	        Assert.assertEquals(recentlyPlayed, Arrays.asList("S3", "S2", "S1"));
	    }

	    @Test
	    public void testMultipleUsers() {
	        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

	        store.playSong("user1", "S1");
	        store.playSong("user2", "S2");

	        List<String> user1RecentlyPlayed = store.getRecentlyPlayed("user1");
	        List<String> user2RecentlyPlayed = store.getRecentlyPlayed("user2");

	        Assert.assertTrue(user1RecentlyPlayed.isEmpty());
	        Assert.assertEquals(user2RecentlyPlayed, Arrays.asList("S2"));
	    }

	    @Test
	    public void testCapacityLimit() {
	        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

	        store.playSong("user1", "S1");
	        store.playSong("user1", "S2");
	        store.playSong("user1", "S3");
	        store.playSong("user1", "S4");

	        List<String> recentlyPlayed = store.getRecentlyPlayed("user1");
	        Assert.assertEquals(recentlyPlayed, Arrays.asList("S4", "S3", "S2"));
	    }

	    @Test
	    public void testRepeatedSongs() {
	        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

	        store.playSong("user1", "S1");
	        store.playSong("user1", "S2");
	        store.playSong("user1", "S3");
	        store.playSong("user1", "S2");

	        List<String> recentlyPlayed = store.getRecentlyPlayed("user1");
	        Assert.assertEquals(recentlyPlayed, Arrays.asList("S2", "S3", "S1"));
	    }

	    @Test
	    public void testNonExistentUser() {
	        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

	        List<String> recentlyPlayed = store.getRecentlyPlayed("nonexistent_user");
	        Assert.assertTrue(recentlyPlayed.isEmpty());
	    }
	}

}
