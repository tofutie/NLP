package org.andrew.nlp.parser;

@SuppressWarnings("serial")
public class ModelNotLoadedException extends RuntimeException
{

	// Parameterless Constructor
	public ModelNotLoadedException()
	{
	}

	// Constructor that accepts a message
	public ModelNotLoadedException(String message)
	{
		super(message);
	}

}
