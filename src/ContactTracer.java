import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ContactTracer {
    HashMap<String, HashMap<String, Integer>> adjMap;

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
            String vertex = trace.getPerson1();
            String neighbour = trace.getPerson2();
            int time = trace.getTime();
            HashMap<String, Integer> neighbours;
            if (adjMap.containsKey(trace.getPerson1())) {
                adjMap.get(vertex).put(neighbour, time);
            }
            else {
                neighbours = new HashMap<>();
                neighbours.put(neighbour,time);
                adjMap.put(vertex,neighbours);
            }

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
        HashMap<String,Integer> compareNode = new HashMap<>();
        compareNode.put(person2,time);
        HashMap<String,Integer> compareOtherNode = new HashMap<>();
        compareOtherNode.put(person1,time);

        if (adjMap.containsKey(person1) && adjMap.containsValue(compareNode)) {
            return;
        }
        else if (adjMap.containsKey(person2) && adjMap.containsValue(compareOtherNode)){
            return;
        }
        else {
            HashMap<String, Integer> newNeighbour = new HashMap<>();
            newNeighbour.put(person2,time);
            adjMap.put(person1,newNeighbour);
        }
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
