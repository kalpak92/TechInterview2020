package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 *
 * --- postTweet(userId, tweetId): Compose a new tweet.
 *
 * --- getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
 *      Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 *
 * --- follow(followerId, followeeId): Follower follows a followee.
 *
 * --- unfollow(followerId, followeeId): Follower unfollows a followee.
 *
 *
 * Example:
 *
 * Twitter twitter = new Twitter();
 *
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 *
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 *
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 *
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 *
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 */


public class DesignTwitter {
    static int timeStamp;
    int feedMaxNum;
    Map<Integer, Set<Integer>> followees;
    Map<Integer, List<Tweet>> tweets;

    /** Initialize your data structure here. */
    public DesignTwitter() {
        timeStamp = 0;
        feedMaxNum = 10;
        followees = new HashMap<>();
        tweets = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!tweets.containsKey(userId)) {
            tweets.put(userId, new LinkedList<Tweet>());
            follow(userId, userId);                                        //follow itself
        }

        tweets.get(userId).add(0, new Tweet(tweetId, timeStamp));    //add new tweet on the first place
        timeStamp++;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     * */
    public List<Integer> getNewsFeed(int userId) {
        //min heap that the earliest tweet is on the top
        PriorityQueue<Tweet> feedHeap = new PriorityQueue<>((a, b) -> a.timePosted - b.timePosted);
        List<Integer> myFeed = new LinkedList<>();

        //add tweets of the followees
        Set<Integer> myFollowees = followees.get(userId);           // Get the followers from the map

        if(myFollowees != null) {
            for(int followeeId : myFollowees) {
                List<Tweet> followeeTweets = tweets.get(followeeId);

                if(followeeTweets == null)
                    continue;

                for(Tweet t : followeeTweets) {
                    if(feedHeap.size() < feedMaxNum)
                        feedHeap.add(t);
                    else {
                        if(t.timePosted <= feedHeap.peek().timePosted)
                            break;
                        else {
                            feedHeap.add(t);
                            feedHeap.poll();                        //remove the oldest tweet
                        }
                    }
                }
            }
        }

        while(!feedHeap.isEmpty()) {
            myFeed.add(0, feedHeap.poll().tweetId);
        }

        return myFeed;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!followees.containsKey(followerId))
            followees.put(followerId, new HashSet<Integer>());

        followees.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!followees.containsKey(followerId) || followerId == followeeId)
            return;                                                 //cannot unfollow itself

        followees.get(followerId).remove(followeeId);
    }

    private static class Tweet{
        int tweetId;
        int timePosted;
        public Tweet(int tId, int time){
            tweetId = tId;
            timePosted = time;
        }
    }

    public static void main(String[] args) {
        DesignTwitter obj = new DesignTwitter();
        obj.postTweet(1, 5);
        System.out.println(obj.getNewsFeed(1));
        obj.follow(1, 2);
        obj.postTweet(2, 6);
        System.out.println(obj.getNewsFeed(1));
        obj.unfollow(1, 2);
        System.out.println(obj.getNewsFeed(1));
    }
}
