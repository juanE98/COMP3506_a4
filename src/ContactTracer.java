import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ContactTracer {

    //Adjacency map representation of graph.
    HashMap<String, HashMap<String, ArrayList<Integer>>> adjMap;

    /**
     * Initialises an empty ContactTracer with no populated contact traces.
     */
    public ContactTracer() {
        this.adjMap = new HashMap<>();
    }

    /**
     * Initialises the ContactTracer and populates the internal data structures
     * with the given list of contract traces.
     * 
     * @param traces to populate with
     * @require traces != null
     */
    public ContactTracer(List<Trace> traces) {
        this.adjMap = new HashMap<>();
        for (Trace trace : traces) {
            addTrace(trace);
        }
    }

    /**
     * Adds a new contact trace to 
     * 
     * If a contact trace involving the same two people at the exact same time is
     * already stored, do nothing.
     * 
     * @param trace to add
     * @require trace != null
     */
    public void addTrace(Trace trace) {
        String person1 = trace.getPerson1();
        String person2 = trace.getPerson2();
        int time = trace.getTime();

        if (adjMap.containsKey(person1) && adjMap.containsKey(person2)
                && !checkExisitingTrace(adjMap,trace)) {
            HashMap<String, ArrayList<Integer>> neighbour = new HashMap<>();
            ArrayList<Integer> times = new ArrayList<>();
            times.add(time);
            if (adjMap.get(person1).get(person2) == null) {
                neighbour.put(person2,times);
                adjMap.put(person1, neighbour);
                adjMap.put(person2,neighbour);
            }
            else if (adjMap.get(person2).get(person1) == null) {
                neighbour.put(person1,times);
                adjMap.put(person2, neighbour);
                adjMap.put(person1,neighbour);
            }
            else {
                adjMap.get(person1).get(person2).add(time);
                adjMap.get(person2).get(person1).add(time);
            }
        }

        else if (adjMap.containsKey(person1) && !adjMap.containsKey(person2)) {
            ArrayList<Integer> times = new ArrayList<Integer>();
            times.add(time);
            HashMap<String, ArrayList<Integer>> neighbour1 =
                    new HashMap<>();
            neighbour1.put(person1,times);
            adjMap.put(person2,neighbour1);
            adjMap.get(person1).put(person2,times);

        }
        else if (adjMap.containsKey(person2) && !adjMap.containsKey(person1)) {
            ArrayList<Integer> times = new ArrayList<Integer>();
            times.add(time);
            HashMap<String, ArrayList<Integer>> neighbour1 =
                    new HashMap<>();
            neighbour1.put(person2,times);
            adjMap.put(person1,neighbour1);
            adjMap.get(person2).put(person1,times);
        }

        else {
            ArrayList<Integer> times = new ArrayList<Integer>();
            times.add(time);
            HashMap<String, ArrayList<Integer>> neighbour1 =
                    new HashMap<>();
            neighbour1.put(person2,times);
            HashMap<String, ArrayList<Integer>> neighbour2 =
                    new HashMap<>();
            neighbour2.put(person1,times);
            adjMap.put(person1,neighbour1);
            adjMap.put(person2,neighbour2);
        }
    }

    private static void addOther (HashMap<String, HashMap<String,
            ArrayList<Integer>>> adjMap,int time, String person1,
                                  String person2) {
        ArrayList<Integer> times = new ArrayList<Integer>();
        times.add(time);
        HashMap<String, ArrayList<Integer>> neighbour1 =
                new HashMap<>();
        neighbour1.put(person1,times);
        adjMap.put(person2,neighbour1);
        adjMap.get(person1).put(person2,times);
    }

    /**
     * Helper method to check if a contact trace involving the same two people
     * at the exact same time is already stored.
     * @param adjMap
     * @param trace
     * @return true if time involving two people is stored, false otherwise.
     */
    private static boolean checkExisitingTrace (HashMap<String, HashMap<String,
            ArrayList<Integer>>> adjMap, Trace trace) {
        String person1 = trace.getPerson1();
        String person2 = trace.getPerson2();
        int timeCheck = trace.getTime();
        if (adjMap.get(person1).get(person2) != null
                && adjMap.get(person2).get(person1) != null) {
            if (adjMap.get(person1).get(person2).contains(timeCheck)
                    || adjMap.get(person2).get(person1).contains(timeCheck) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a list of times that person1 and person2 have come into direct 
     * contact (as per the tracing data).
     *
     * If the two people haven't come into contact before, an empty list is returned.
     * 
     * Otherwise the list should be sorted in ascending order.
     * 
     * @param person1 
     * @param person2
     * @return a list of contact times, in ascending order.
     * @require person1 != null && person2 != null
     */
    public List<Integer> getContactTimes(String person1, String person2) {
        // TODO: implement this!
        
        return null;
    }

    /**
     * Gets all the people that the given person has been in direct contact with
     * over the entire history of the tracing dataset.
     * 
     * @param person to list direct contacts of
     * @return set of the person's direct contacts
     */
    public Set<String> getContacts(String person) {
        // TODO: implement this!
        
        return null;
    }

    /**
     * Gets all the people that the given person has been in direct contact with
     * at OR after the given timestamp (i.e. inclusive).
     * 
     * @param person to list direct contacts of
     * @param timestamp to filter contacts being at or after
     * @return set of the person's direct contacts at or after the timestamp
     */
    public Set<String> getContactsAfter(String person, int timestamp) {
        // TODO: implement this!
        
        return null;
    }

    /**
     * Initiates a contact trace starting with the given person, who
     * became contagious at timeOfContagion.
     * 
     * Note that the return set shouldn't include the original person the trace started from.
     * 
     * @param person to start contact tracing from
     * @param timeOfContagion the exact time person became contagious
     * @return set of people who may have contracted the disease, originating from person
     */
    public Set<String> contactTrace(String person, int timeOfContagion) {
        // TODO: implement this!
        
        return null;
    }
}
