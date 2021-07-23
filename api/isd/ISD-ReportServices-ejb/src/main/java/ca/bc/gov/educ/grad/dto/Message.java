package ca.bc.gov.educ.grad.dto;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private String path;

	private String message;

	private String[] messageArguments;

	public Message()
	{
		super();
	}

	public Message(String message)
	{
		this.message = message;
	}

	public Message(String path, String message, String[] messageArguments)
	{
		this.path = path;
		this.message = message;
		this.messageArguments = messageArguments;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String[] getMessageArguments()
	{
		return messageArguments;
	}

	public void setMessageArguments(String[] messageArguments)
	{
		this.messageArguments = messageArguments;
	}
}