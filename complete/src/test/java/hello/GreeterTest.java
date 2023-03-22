package hello;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class GreeterTest {
	
	private Greeter greeter = new Greeter();
	String timeZone = "Asia/Singapore";
	private static final String PATTERN = "E yyyy-MM-dd HH:mm:ss a";
	@Test
	public void greeterSaysHello() {
		assertThat(greeter.sayHello(), containsString("Hello"));
	}

	@org.junit.Test
	public void givenDateWithoutTimeZone_WhenSetTimeZoneUsingJava8_ThenTimeZoneIsSetSuccessfully() {
		Instant nowUtc = Instant.now();
		ZoneId asiaSingapore = ZoneId.of(timeZone);
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("America/Chicago"));
//		localDateTime.at

		ZonedDateTime nowAsiaSingaporeLDT = ZonedDateTime.of(localDateTime, asiaSingapore);

		System.out.println(String.format("Java8 nowAsiaSingaporeLDT : Time now in '%s' is '%s'", nowAsiaSingaporeLDT.getZone(),
				localDateTime.format(DateTimeFormatter.ofPattern(PATTERN))));



		ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);

		System.out.println(String.format("Java8: Time now in '%s' is '%s'", nowAsiaSingapore.getZone(),
				nowAsiaSingapore.format(DateTimeFormatter.ofPattern(PATTERN))));

		Assert.assertEquals(nowUtc.getEpochSecond(), nowAsiaSingapore.toEpochSecond());
		Assert.assertEquals(nowUtc.getEpochSecond(), localDateTime.toEpochSecond(ZoneOffset.ofTotalSeconds(0)));
		Assert.assertEquals(asiaSingapore, nowAsiaSingapore.getZone());
	}

}
