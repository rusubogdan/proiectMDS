package com.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDS")
public class Friend implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FriendPK friendPK;

	public Friend(User user, User friend) {
		friendPK = new FriendPK();
		setUser(user);
		setFriend(friend);
	}
	
	public Friend() {
	}

	public void setFriendPK(FriendPK friendPK) {
		this.friendPK = friendPK;
	}

	public FriendPK getFriendPK() {
		return friendPK;
	}

	public User getUser() {
		return friendPK.getUser();
	}
	
	public void setUser(User user) {
		friendPK.setUser(user);
	}
	
	public User getFriend() {
		return friendPK.getFriend();
	}
	
	public void setFriend(User friend) {
		friendPK.setFriend(friend);
	}
}
