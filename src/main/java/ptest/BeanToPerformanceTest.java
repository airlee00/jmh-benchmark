package ptest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Benchmark                                             (size)  Mode  Cnt  Score   Error  Units
BeanToPerformanceTest.testApacheCommonsBeanUtils          10  avgt    2  0.066          ms/op
BeanToPerformanceTest.testApacheCommonsPropertyUtils      10  avgt    2  0.007          ms/op
BeanToPerformanceTest.testByHand                          10  avgt    2  0.001          ms/op
BeanToPerformanceTest.testJodd                            10  avgt    2  0.040          ms/op
BeanToPerformanceTest.testOpenSymphony                    10  avgt    2  0.209          ms/op
BeanToPerformanceTest.testSpring                          10  avgt    2  0.003          ms/op
 * @author kichaelee
 *
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 1)
@Measurement(iterations = 2)
public class BeanToPerformanceTest {

    @Param({"10"})
    private int size;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BeanToPerformanceTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
	public void testApacheCommonsBeanUtils() {
		BeanConverter converter = new ApacheCommonsBeanUtilsBeanConverter();
		executeIncrementally(converter);
	}

    @Benchmark
	public void testApacheCommonsPropertyUtils() {
		BeanConverter converter = new ApacheCommonsPropertyUtilsBeanConverter();
		executeIncrementally(converter);
	}

//    @Benchmark
//	public void testOpenSymphony() {
//		BeanConverter converter = new OpenSymphonyBeanConverter();
//		executeIncrementally(converter);
//	}

    @Benchmark
	public void testSpring() {
		BeanConverter converter = new SpringBeanConverter();
		executeIncrementally(converter);
	}

    @Benchmark
	public void testJodd() {
		BeanConverter converter = new JoddBeanConverter();
		executeIncrementally(converter);
	}

    @Benchmark
	public void testByHand() {
		BeanConverter converter = new UserConverter();
		executeIncrementally(converter);
	}

	private void excuecteBeanConverter(BeanConverter converter, int iterations) {
		List<Map<String, Object>> testList = createMapListForTest(iterations);
		long start = System.currentTimeMillis();
		List<User> beanList = converter.convertMapToBean(testList, User.class);
		//long end = System.currentTimeMillis();
		//System.out.printf("%s,%d times, %d milliseconds \r\n", converter//
		//		.getClass().getSimpleName(), iterations, (end - start));
	}

	private void executeIncrementally(BeanConverter converter) {
		for (int i = 0; i <= size; i += size) {
			excuecteBeanConverter(converter, i);
		}
	}

	private List<Map<String, Object>> createMapListForTest(int iterations) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("id", 1);
		user.put("age", 1);
		user.put("name", "내이름");
		user.put("name1", "내이름");
		user.put("name2", "내이름");
		user.put("name3", "내이름");
		user.put("name4", "내이름");
		user.put("name5", "내이름");
		user.put("name6", "내이름");
		user.put("name7", "내이름");
		user.put("name8", "내이름");
		user.put("name9", "내이름");
		user.put("name10", "내이름");
		user.put("income", new BigDecimal("1000100100"));
		user.put("address", "오늘 아침 내가 행복한 이유는 이런거지 오늘아침 내가 서러운 이유는 그런거야 ");
		user.put("introduce", "오늘 아침 내가 행복한 이유는 이런거지 오늘아침 내가 서러운 이유는 그런거야 ");
		user.put("married", true);
		user.put("nickName", "뻐꾸기");
		for (int i = 0; i < iterations; i++) {
			list.add(user);
		}
		return list;
	}
}
