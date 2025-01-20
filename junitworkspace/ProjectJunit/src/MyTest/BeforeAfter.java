package MyTest;
import static org.junit.Assert.*;

import org.junit.*;
public class BeforeAfter {
	@Before
	public void before() {
		System.out.println("before");
	}
	@After
	public void after() {
		System.out.println("after");
	}
	@Test
	public void test() {
		System.out.println("Test");
	}
}