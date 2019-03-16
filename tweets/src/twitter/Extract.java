/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	Instant first = null,last = null;
    	if(!tweets.isEmpty()) {   		
        	first=tweets.get(0).getTimestamp();
        	last=tweets.get(tweets.size()-1).getTimestamp();
        	Timespan result=new Timespan(first,last);
        	return result;
    	}
    	else if(tweets.size()==1){
    		first=tweets.get(0).getTimestamp();
        	last=tweets.get(0).getTimestamp();
        	Timespan result=new Timespan(first,last);
        	return result;
    	}
    	else {
    		return null;
    	}
    	
    	
    }
      
    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
   
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	Set<String> username = new HashSet<String>(); 
    	String mention_characters="\\B@[a-zA-Z0-9_-]+\\b";
    	Pattern pattern=Pattern.compile(mention_characters);
    	for (Tweet tweet: tweets) {
            String text = tweet.getText();
            Matcher matcher = pattern.matcher(text);
            
            while (matcher.find()) {
                String myuser = matcher.group().substring(1).toLowerCase();
                username.add(myuser);
            }
    	}
    	return username;
    }
    
}

    


