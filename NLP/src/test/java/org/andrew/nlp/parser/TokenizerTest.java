package org.andrew.nlp.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TokenizerTest
{

	Parser parser;

	@Before
	public void setUp() throws Exception
	{
		parser = new Parser.Builder().addSentenceParser("C:/apache-opennlp/en-sent.bin")
				.addTokenizerParser("C:/apache-opennlp/en-token.bin").build();
	}

	@Test
	public void testSingleSentenceTokenize()
	{
		String[] correctParsedResult =
		{ "I went to school ." };
		String testString = "I went to school.";
		assertArrayEquals(correctParsedResult, parser.parse(testString));
	}

	@Test
	public void testMultipleSentenceTokenize()
	{
		String[] correctParsedResult =
		{ "I went to school .", "I like to eat food .",
				"Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 ." };
		String testString = "I went to school. I like to eat food. Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29.";
		assertArrayEquals(correctParsedResult, parser.parse(testString));
	}
	
	@Test
	public void testEmptySentenceDetection()
	{
		String testString = "";
		assertTrue(parser.parse(testString).length == 0);
	}
	
	@Test(expected = ModelNotLoadedException.class)
	public void testModelNotLoaded()
	{
		Parser exceptionParser = new Parser.Builder()
				.addSentenceParser("C:/apache-opennlp/en-sent.bin")
				.addTokenizerParser("C:/not-a-file-that-exists.bin").build();
	}
	
	
	@Test
	public void testNoTokenizerLoaded()
	{
		Parser noTokenParser = new Parser.Builder().addSentenceParser(
				"C:/apache-opennlp/en-sent.bin").build();
		String[] correctParsedResult =
		{ "I went to the play on Dec. 25th.", "I went with Mr. Dude.",
				"I want to go again!" };
		String testString = "I went to the play on Dec. 25th. I went with Mr. Dude. I want to go again!";
		assertArrayEquals(correctParsedResult, noTokenParser.parse(testString));
	}

}
