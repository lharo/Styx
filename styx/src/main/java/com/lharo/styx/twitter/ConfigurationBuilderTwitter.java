package com.lharo.styx.twitter;

import java.io.File;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class ConfigurationBuilderTwitter {

	private Twitter twitter;
	
	public ConfigurationBuilderTwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("BJfhzSzmjRCgAVNNLLxIHpjRG")
		  .setOAuthConsumerSecret("p3nsg6abx4NV8kniiKOLbFYtMKknu2OqfV2nrUVV6wf4JrJE2X")
		  .setOAuthAccessToken("1106316763162177538-1sWTZDsofv16MaDXUcTQXI1Prs8G3j")
		  .setOAuthAccessTokenSecret("PtyHSQSvfqG8TvefdIid5TihTERp9Hvwn5vBweOSbDPk2");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	
	public String createTweet(String tweet) throws TwitterException {
	    Twitter twitter = getTwitter();
	    Status status = twitter.updateStatus(tweet);
	    return status.getText();
	}
	
	public String createTweetWithImage(String tweet, File tweetPic) throws TwitterException {
	    Twitter twitter = getTwitter();
	    String statusMessage = tweet;
	    File imagefile1 = tweetPic;

	    /*long[] mediaIds = new long[1];
	    UploadedMedia media1 = twitter.uploadMedia(imagefile1);
	    mediaIds[0] = media1.getMediaId();*/

	    StatusUpdate statusUpdate = new StatusUpdate(statusMessage);
	    statusUpdate.setMedia(imagefile1);
	    Status status = twitter.updateStatus(statusUpdate);
	    return status.getText();
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
}
