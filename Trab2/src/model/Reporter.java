package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author João Bolsson (jvmarques@inf.ufsm.br)
 * @version 2019, May 05.
 */
public class Reporter {

    /**
     * Keep the sum of time for each distance.
     */
    private final Map<Integer, Long> distanceTime = new HashMap<>();

    /**
     * Keep the number of samples for each distance.
     */
    private final Map<Integer, Integer> distanceSamplesNumber = new HashMap<>();

    /**
     * Default construct.
     */
    public Reporter() {
        // empty
    }

    /**
     * Add a time sample.
     *
     * @param distance Given distance.
     * @param time Time to find the distance.
     */
    public void addSample(final Integer distance, final Long time) {
        Long sumTime = time;
        int samples = 1;
        if (distanceTime.containsKey(distance)) {
            sumTime += distanceTime.get(distance);

            samples += distanceSamplesNumber.get(distance);
        }
        distanceTime.put(distance, sumTime); // update
        distanceSamplesNumber.put(distance, samples);
    }

    @Override
    public String toString() {
        Iterator<Map.Entry<Integer, Long>> it = distanceTime.entrySet().iterator();

        StringBuilder builder = new StringBuilder();

        while (it.hasNext()) {
            Map.Entry<Integer, Long> next = it.next();

            Integer distance = next.getKey();
            Integer totalTime = next.getKey();

            Integer samples = distanceSamplesNumber.get(distance);

            if (samples >= 50) {
                builder.append("------------------------------------\n");
                builder.append("distance: ").append(distance).append(" samples: ").append(samples).append("\n");
                builder.append("total time: ").append(totalTime).append("\n");
                builder.append("average time: ").append(((double) totalTime) / samples).append("\n");
            }
        }

        return builder.toString();
    }

}
