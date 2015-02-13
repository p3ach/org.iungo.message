package org.iungo.message.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.iungo.id.api.ID;
import org.iungo.result.api.Result;

public class DefaultMessage implements Message {

	private static final long serialVersionUID = 1L;
	
	public static final ID UNDEFINED_KEY_ID = new ID(DefaultMessage.class.getName(), "Key", "Undefined");
	
	public static DefaultMessage valueOf(final byte[] data) {
		final ByteArrayInputStream baos = new ByteArrayInputStream(data);
		try {
			final ObjectInputStream ois = new ObjectInputStream(baos);
			DefaultMessage message = (DefaultMessage) ois.readObject();
			ois.close();
			return message;
		} catch (final Exception exception) {
			Result.valueOf(exception).print();
			return null;
		}
	}
	
	protected final Long timestamp = System.currentTimeMillis();

	protected final ID id;
	
	protected final ID from;
	
	protected final ID to;
	
	private final Integer priority;
	
	protected final ID key;
	
	public DefaultMessage(final ID id, final ID from, final ID to, final Integer priority, final ID key) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.priority = priority;
		this.key = key;
	}

	@Override
	public Long getTimestamp() {
		return timestamp;
	}

	@Override
	public ID getID() {
		return id;
	}

	@Override
	public ID getFrom() {
		return from;
	}

	@Override
	public ID getTo() {
		return to;
	}

	@Override
	public Integer getPriority() {
		return priority;
	}
	
	@Override
	public ID getKey() {
		return key;
	}
	
	@Override
	public int compareTo(final Message o) {
		int r = getPriority().compareTo(o.getPriority());
		if (r == 0) {
			return getTimestamp().compareTo(o.getTimestamp());
		}
		return r;
	}

	public byte[] asMD5Hash() {
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(asByteArray());
			return messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public byte[] asByteArray() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream(2<<16);
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.close();
			return baos.toByteArray();
		} catch (final Exception exception) {
			return null;
		}
	}

	@Override
	public String toString() {
		return String.format("id [%s]\nfrom [%s]\nto [%s]\ntimestamp [%d]", getID(), getFrom(), getTo(), getTimestamp());
	}
}
