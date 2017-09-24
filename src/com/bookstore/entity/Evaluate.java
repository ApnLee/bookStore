package com.bookstore.entity;

public class Evaluate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String evaluateId;
	private String evaluateDesc; //评价详细
	private String uid;
	private String bid;
	private String evaluateTime;
	private User user;
	private Book book;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(String evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public String getEvaluateDesc() {
		return evaluateDesc;
	}

	public void setEvaluateDesc(String evaluateDesc) {
		this.evaluateDesc = evaluateDesc;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((evaluateId == null) ? 0 : evaluateId.hashCode());
		result = prime * result + ((evaluateDesc == null) ? 0 : evaluateDesc.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evaluate other = (Evaluate) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (evaluateId == null) {
			if (other.evaluateId != null)
				return false;
		} else if (!evaluateId.equals(other.evaluateId))
			return false;
		if (evaluateDesc == null) {
			if (other.evaluateDesc != null)
				return false;
		} else if (!evaluateDesc.equals(other.evaluateDesc))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Evaluate [evaluateId=" + evaluateId + ", evaluateDesc=" + evaluateDesc + 
				", uid=" + uid +", bid=" + bid +", user=" + user +", book=" + book + "]";
	}
	
	
	
}
