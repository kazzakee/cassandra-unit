package org.cassandraunit;

import static org.junit.Assert.assertEquals;

import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.Rule;
import org.junit.Test;

import com.datastax.driver.core.ResultSet;

/**
 * 
 * @author Jeremy Sevellec
 *
 */
public class CQLDataLoadTestWithReadTimeout {

	private static final int READ_TIMEOUT_VALUE = 15000;

	@Rule
	public CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("cql/simple.cql",
			"mykeyspace"), READ_TIMEOUT_VALUE);

	@Test
	public void testCQLDataAreInPlace() throws Exception {
		test();

	}

	@Test
	public void sameTestToMakeSureMultipleTestsAreFine() throws Exception {
		test();

	}

	private void test() {
		ResultSet result = cassandraCQLUnit.session
				.execute("select * from testCQLTable WHERE id=1690e8da-5bf8-49e8-9583-4dff8a570737");

		String val = result.iterator().next().getString("value");
		assertEquals("Cql loaded string", val);
	}

}