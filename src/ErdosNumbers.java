import java.lang.reflect.Array;
import java.util.*;

public class ErdosNumbers {
    /**
     * String representing Paul Erdos's name to check against
     */
    public static final String ERDOS = "Paul Erdös";

    /** Adjacency List representation of papers and authors */
    private HashMap<String, List<String>> adjList;

    private int n; //number nodes
    private boolean[] visited; //boolean array of size n

    /**
     * Initialises the class with a list of papers and authors.
     *
     * Each element in 'papers' corresponds to a String of the form:
     * 
     * [paper name]:[author1][|author2[|...]]]
     *
     * Note that for this constructor and the below methods, authors and papers
     * are unique (i.e. there can't be multiple authors or papers with the exact same name or title).
     * 
     * @param papers List of papers and their authors
     */
    public ErdosNumbers(List<String> papers) {
        this.adjList = new HashMap<>();
        for (int i = 0; i < papers.size(); i++) {
            String[] parts = papers.get(i).split(":");
            String paperName = parts[0];
            List<String> authorNames =
                    new ArrayList<>(Arrays.asList(parts[1].split(
                    "[|]")));
            adjList.put(paperName, authorNames);
        }

        //Depth-First Search
        this.n = papers.size();
        this.visited = new boolean[n];
        Arrays.fill(visited,Boolean.FALSE);

    }

    private void DFS(String node) {

    }

    /**
     * Gets all the unique papers the author has written (either solely or
     * as a co-author).
     * 
     * @param author to get the papers for.
     * @return the unique set of papers this author has written.
     */
    public Set<String> getPapers(String author) {
        HashSet<String> papers = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            for (String authorName : entry.getValue()) {
                if (Objects.equals(authorName,author)) {
                    papers.add(entry.getKey());
                }
            }
        }
        return papers;
    }

    /**
     * Gets all the unique co-authors the author has written a paper with.
     *
     * @param author to get collaborators for
     * @return the unique co-authors the author has written with.
     */
    public Set<String> getCollaborators(String author) {
        HashSet<String> coAuthors = new HashSet<>();
        ArrayList<String> tempCoAuthors = new ArrayList<>();
        HashSet<String> papersOf = new HashSet<>(getPapers(author));

        for (String papers : papersOf) {
            tempCoAuthors.addAll(adjList.get(papers));
        }

        for(int i = 0; i < tempCoAuthors.size(); i++) {
            if (!Objects.equals(tempCoAuthors.get(i), author)){
                coAuthors.add(tempCoAuthors.get(i));
            }
        }
        return coAuthors ;
    }

    /**
     * Checks if Erdos is connected to all other author's given as input to
     * the class constructor.
     * 
     * In other words, does every author in the dataset have an Erdos number?
     * 
     * @return the connectivity of Erdos to all other authors.
     */
    public boolean isErdosConnectedToAll() {
        //TODO

        return false;
    }

    /**
     * Calculate the Erdos number of an author. 
     * 
     * This is defined as the length of the shortest path on a graph of paper 
     * collaborations (as explained in the assignment specification).
     * 
     * If the author isn't connected to Erdos (and in other words, doesn't have
     * a defined Erdos number), returns Integer.MAX_VALUE.
     * 
     * Note: Erdos himself has an Erdos number of 0.
     * 
     * @param author to calculate the Erdos number of
     * @return authors' Erdos number or otherwise Integer.MAX_VALUE
     */
    public int calculateErdosNumber(String author) {
        // TODO: implement this
        
        return 0;
    }

    /**
     * Gets the average Erdos number of all the authors on a paper.
     * If a paper has just a single author, this is just the author's Erdos number.
     *
     * Note: Erdos himself has an Erdos number of 0.
     *
     * @param paper to calculate it for
     * @return average Erdos number of paper's authors
     */
    public double averageErdosNumber(String paper) {
        // TODO: implement this
        
        return 0;
    }

    /**
     * Calculates the "weighted Erdos number" of an author.
     * 
     * If the author isn't connected to Erdos (and in other words, doesn't have
     * an Erdos number), returns Double.MAX_VALUE.
     *
     * Note: Erdos himself has a weighted Erdos number of 0.
     * 
     * @param author to calculate it for
     * @return author's weighted Erdos number
     */
    public double calculateWeightedErdosNumber(String author) {
        // TODO: implement this

        return 0;
    }
}
