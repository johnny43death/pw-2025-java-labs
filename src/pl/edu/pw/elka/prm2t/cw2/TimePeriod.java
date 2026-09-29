package pl.edu.pw.elka.prm2t.cw2;

import java.util.Arrays;
import java.util.Objects;

import static pl.edu.pw.elka.prm2t.GeneralUtil.prn;

/**
 * To store immutable time periods, as hours:minutes.
 *
 * Polecenia:
 * 1. Uzupełnij klasę o wszelkie potrzebne elementy tak, aby program zapisany w funkcji main działał
 *    zgodnie z oczekiwaniami.
 * 2. Uzupełnij brakujące komentarze dokumentujące.
 * 3. Zaproponuj modyfikację metody equals, korzystającą z faktu niezmienności obiektów (napisz tę metodę
 *    lub opisz jak mogłaby działać).
 * 4. Zaproponuj konstruktor parsujący dane z napisu, np. new TimePeriod("2:30"); albo new TimePeriod("2h 30m");
 *
 * @author Michał Sergiel, Kajetan Rosik
 * @version 1.0.0
 */
public class TimePeriod {
	private static final int MINUTES_IN_HOUR = 60;

    /**
     * Hash value for speed up computing.
     */
    private int hash = 0;

    /**
     * Has hash already been computed?
     */
    private boolean hashComputed = false;

    /**
     * Number of hours of this time period.
     */
    private final int hours;

    /**
     * Number of minutes of this time period.
     */
    private final int minutes;

    /**
     * Creates time period from given hours:minutes.
     * @param hours number of hours in this time period.
     * @param minutes number of minutes in this time period.
     */
    TimePeriod(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Creates a TimePeriod object from a string in the format "H:M".
     *
     * @param timeString the string representing the time period
     */
    public TimePeriod(String timeString) {
        String[] parts = timeString.split(":");
        int parsedHours = Integer.parseInt(parts[0]);
        int parsedMinutes = Integer.parseInt(parts[1]);

        this.hours = parsedHours;
        this.minutes = parsedMinutes;
    }

    /**
     * Creates time period from given hours and zero minutes.
     * @param hours number of hours in this time period.
     */
    TimePeriod(int hours) {
        this(hours, 0);
    }

    /**
     * Creates time period of 0 hours, 0 minutes.
     */
    TimePeriod() {
        this(0, 0);
    }

    /**
     * Creates time period as a copy of given time period.
     * @param tp time period to copy.
     */
    TimePeriod(TimePeriod tp) {
    	hours = tp.hours;
    	minutes = tp.minutes;
    }

    /**
     * Creates new time period normalizing minutes to less than 60.
     * @param hours
     * @param someMinutes
     * @return new object created
     */
    private static TimePeriod computeNormalizedTimePeriod(final int hours, final int someMinutes) {
        assert hours >= 0 : "Hours less than 0";

    	if (hours < 0 ) {
            throw new IllegalArgumentException("Hours less than 0");
        }
        if (someMinutes < 0) {
            throw new IllegalArgumentException("Minutes less than 0");
        }

        int normalizedMinutes = someMinutes % MINUTES_IN_HOUR;
    	int normalizedHours = hours + someMinutes / MINUTES_IN_HOUR;
    	return new TimePeriod(normalizedHours, normalizedMinutes);
    }

    /**
     * Returns new TimePeriod as the sum of this and the given period.
     * @param tp given time period.
     * @return the sum of this and given periods.
     */
    TimePeriod sum(TimePeriod tp) {
    	int h = hours + tp.hours;
    	int m = minutes + tp.minutes;
    	return computeNormalizedTimePeriod(h, m);
    }

    /**
     * Return new TimePeriod as the sum of all periods from the array.
     * @param tps array of time periods.
     * @return the sum of all periods.
     */
    static TimePeriod sumV1(TimePeriod[] tps) {
    	int h = 0, m = 0;
    	for (int i = 0; i < tps.length; i++) {
    		m += tps[i].minutes;
        	h += tps[i].hours;
    	}
    	return computeNormalizedTimePeriod(h, m);
    }

    static TimePeriod sumV2(TimePeriod[] tps) {
    	int h = 0, m = 0;
    	for (TimePeriod tp : tps) {
        	m += tp.minutes;
        	h += tp.hours;
        }
    	return computeNormalizedTimePeriod(h, m);
    }

    static TimePeriod sumV3(TimePeriod[] tps) {
    	TimePeriod returnValue = new TimePeriod();
    	Arrays.asList(tps).forEach(tp -> returnValue.sum(tp) );
    	return returnValue;
    }

    static TimePeriod sumV4(TimePeriod[] tps) {
        TimePeriod returnValue = new TimePeriod();
        Arrays.asList(tps).forEach(returnValue::sum);
        return returnValue;
    }

    /**
     * Returns new TimePeriod as: this - given period. The resulting
     * period must be positive or equal zero.
     * @param tp given time period.
     * @return the sum of this and given periods.
     */
    TimePeriod sub(TimePeriod tp) {
        int totalMinutesThis = this.hours * MINUTES_IN_HOUR + this.minutes;
        int totalMinutesOther = tp.hours * MINUTES_IN_HOUR + tp.minutes;

        if (totalMinutesOther > totalMinutesThis) {
            throw new IllegalArgumentException("Resulting TimePeriod cannot be negative");
        }

        int diffMinutes = totalMinutesThis - totalMinutesOther;
        return new TimePeriod(diffMinutes / MINUTES_IN_HOUR, diffMinutes % MINUTES_IN_HOUR);
    }


    /**
     * Returns new TimePeriod as multiplication of this object by
     * the given integer.
     * @param times
     * @return ...
     */
    TimePeriod mul(int times) {
    	int h = hours * times;
    	int m = minutes * times;
    	return computeNormalizedTimePeriod(h, m);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(hours).append("h ").append(minutes).append("m");
        return sb.toString();
        //v1 return hours + "h " + minutes + "m";
        //v2 String.format("%dh %dm", hours, minutes);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof TimePeriod) {
            TimePeriod c = (TimePeriod) o;
            return c.hours == hours && c.minutes == minutes;
        }
        return false;
    }
    /** PROPOWANA ZMIANA equals()
    * Ponieważ obiekty TimePeriod są niemutowalne (niezmienne), możemy zoptymalizować metodę equals,
     * najpierw sprawdzając równość referencyjną (==), zanim przeprowadzimy porównanie pól. Dzięki
     * temu unikniemy zbędnych operacji, gdy porównujemy ten sam obiekt z samym sobą.
     */

    @Override
    public int hashCode() {
        if (hashComputed) {
            return hash;
        }
        hash = Objects.hash(hours, minutes);
        hashComputed = true;
        return hash;

        /*if (!hashComputed) {
            hash = Objects.hash(hours, minutes);
            hashComputed = true;
            return hash;
        }
        return hash;
        */
    }

    /**
     * To test the class.
     * @param args not used.
     */
    public static void main(String[] args) {
        //computeNormalizedTimePeriod(-1, 5);

    	prn(computeNormalizedTimePeriod(0, 59).toString());
    	prn(computeNormalizedTimePeriod(0, 60).toString());
    	prn(computeNormalizedTimePeriod(0, 61).toString());
    	prn(computeNormalizedTimePeriod(0, 599).toString());
    	prn(computeNormalizedTimePeriod(0, 600).toString());
    	prn(computeNormalizedTimePeriod(0, 601).toString());


        TimePeriod c1 = new TimePeriod(3, 45);
        prn("c1=" + c1);
        TimePeriod c2 = new TimePeriod(1, 45);
        prn("c2=" + c2);
        TimePeriod c3 = c1.sum(c2);
        prn("%s + %s = %s", c1, c2, c3); // 5h 30m
        c3 = c1.sub(c2);
        prn("%s - %s = %s", c1, c2, c3); // 2h 0m

        c3 = c1.sub(c2);
        System.out.println("c3=" + c3); // 0h 15m
        TimePeriod[] t = { new TimePeriod(1,20),
        		new TimePeriod(2,40),
        		new TimePeriod(4,50) };
        c3 = TimePeriod.sumV1(t);
        prn("%s", Arrays.asList(t));
        System.out.println("c3=" + c3); // 8h 50m

        c3 = c2.mul(6);
        System.out.println("c3=" + c3); // 10h 30m

        TimePeriod c4 = new TimePeriod("2:30"); //Example use of new constructor
        System.out.println("c4="+c4); // Output: 2h 30m
    }
}