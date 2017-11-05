package software_engineering;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Lab1_PairTest
{
	public static Graph graph = new Graph("data.txt");
	public static int num;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println("@BeforeClass1");
		num = graph.buildGraph();
		System.out.println("@BeforeClass2");
	}

	@Test
	public void testqueryBridgeWords1()
	{
		System.out.println("Path");
		Lab1_Pair.head = graph.head;
		Lab1_Pair.vertexNum = num;
		String word1 = "in";
		String word2 = "big";
		String ans = "the ";
		String result = Lab1_Pair.queryBridgeWords(word1, word2);
		assertEquals(ans,result);
	}
	@Test
	public void testqueryBridgeWords2()
	{
		System.out.println("Path");
		Lab1_Pair.head = graph.head;
		Lab1_Pair.vertexNum = num;
		String word1 = "the";
		String word2 = "of";
		String ans = "meaning study format ";
		String result = Lab1_Pair.queryBridgeWords(word1, word2);
		assertEquals(ans,result);
	}
	@Test
	public void testqueryBridgeWords3()
	{
		System.out.println("Path");
		Lab1_Pair.head = graph.head;
		Lab1_Pair.vertexNum = num;
		String word1 = "asd";
		String word2 = "of";
		String ans = "-1";
		String result = Lab1_Pair.queryBridgeWords(word1, word2);
		assertEquals(ans,result);
	}
	@Test
	public void testqueryBridgeWords4()
	{
		System.out.println("Path");
		Lab1_Pair.head = graph.head;
		Lab1_Pair.vertexNum = num;
		String word1 = "de";
		String word2 = "fffff";
		String ans = "-1";
		String result = Lab1_Pair.queryBridgeWords(word1, word2);
		assertEquals(ans,result);
	}
	@Test
	public void testqueryBridgeWords5()
	{
		System.out.println("Path");
		Lab1_Pair.head = graph.head;
		Lab1_Pair.vertexNum = num;
		String word1 = "the";
		String word2 = "format";
		String ans = "0";
		String result = Lab1_Pair.queryBridgeWords(word1, word2);
		assertEquals(ans,result);
	}
	@Test
	public void testqueryBridgeWords6()
	{
		System.out.println("Path");
		Lab1_Pair.head = graph.head;
		Lab1_Pair.vertexNum = num;
		String word1 = "the";
		String word2 = "time";
		String ans = "0";
		String result = Lab1_Pair.queryBridgeWords(word1, word2);
		assertEquals(ans,result);
	}
}
