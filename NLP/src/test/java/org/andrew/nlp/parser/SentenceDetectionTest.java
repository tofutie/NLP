package org.andrew.nlp.parser;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SentenceDetectionTest
{
	Parser parser;

	@Before
	public void setUp()
	{
		parser = new Parser.Builder().addSentenceParser("C:/apache-opennlp/en-sent.bin")
				.build();
	}

	@Test
	public void testSimpleDeclarativeSentenceDetection()
	{
		String[] correctParsedResult =
		{ "I went to school.", "I like food.", "I am gone.", "What a great day!",
				"Hello!" };

		String testString = "I went to school. I like food. I am gone. What a great day! Hello!";
		assertArrayEquals(correctParsedResult, parser.parse(testString));
	}

	@Test
	public void testSingleSentenceDetection()
	{
		String[] correctParsedResult =
		{ "I went to school." };

		String testString = "I went to school.";
		assertArrayEquals(correctParsedResult, parser.parse(testString));
	}

	@Test
	public void testEmptySentenceDetection()
	{
		String testString = "";
		assertTrue(parser.parse(testString).length == 0);
	}

	@Test
	public void testAbbreviatedMonthSentenceDetection()
	{
		String[] correctParsedResult =
		{ "I went to the play on Dec. 25th.", "I went with Mr. Dude.",
				"I want to go again!" };
		String testString = "I went to the play on Dec. 25th. I went with Mr. Dude. I want to go again!";
		assertArrayEquals(correctParsedResult, parser.parse(testString));
	}

	@Test(expected = ModelNotLoadedException.class)
	public void testModelNotLoaded()
	{
		Parser exceptionParser = new Parser.Builder().addSentenceParser(
				"C:/not-a-file-that-exists.bin").build();
	}

}
