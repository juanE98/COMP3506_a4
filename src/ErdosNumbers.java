import java.lang.reflect.Array;
import java.util.*;

public class ErdosNumbers {
    /**
     * String representing Paul Erdos's name to check against
     */
    public static final String ERDOS = "Paul Erdös";

    /** Adjacency List representation of papers and authors */
    private HashMap<String, List<String>> paperAuthors;

    /** Adjacency List representation of <Author, [list of papers author
     * wrote]> */
    private HashMap<String, ArrayList<String>> authorPapers;

    /** Adjacency List representation of graph
     * Nodes: Authors
     * Edges: paper co-authorship between authors
     **/
    private HashMap<String, HashMap<String,Integer>> graphErdos;

    /** DFS variables */
    private int nodesNumber; //number of nodes in graph.
    private HashMap<String,Boolean> visited;
    private int erdosNeighbours;


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
        this.paperAuthors = new HashMap<>();
        this.authorPapers = new HashMap<>();

        //Populate hashmap representation for the constructor.
        for (int i = 0; i < papers.size(); i++) {
            String[] parts = papers.get(i).split(":");
            String paperName = parts[0];
            List<String> authorNames =
                    new ArrayList<>(Arrays.asList(parts[1].split(
                    "[|]")));
            paperAuthors.put(paperName, authorNames);
            //Populate papersOfAuthors with Author as key, and Papers as values
            authorPapersFill(authorNames, paperName);
        }
        createGraph(authorPapers);
        this.nodesNumber = graphErdos.size();

        //DFS traversal from ERDOS node.
        visited = new HashMap<>();
        for (String author : graphErdos.keySet()) {
            visited.put(author,false);
        }
        erdosNeighbours = 0;
        DFS(ERDOS);



    }

    /**
     * Helper method to populate papersOfAuthors in the constructor.
     * @param authorNames List of author names
     * @param paperName name of paper the authors have contributed.
     */
    private void authorPapersFill(List<String> authorNames, String paperName){
        ArrayList<String> thePapers;
        for (String author : authorNames) {
            if (authorPapers.containsKey(author)) {
                thePapers = authorPapers.get(author);
                thePapers.add(paperName);
            }
            else {
                thePapers = new ArrayList<>();
                thePapers.add(paperName);
                authorPapers.put(author,thePapers);
            }
        }
    }

    /**
     * Helper function to create graph:
     * Nodes: Authors
     * Edges: paper co-authorship between authors
     * @param authorPapers
     */
    private void createGraph(HashMap<String, ArrayList<String>> authorPapers) {
        this.graphErdos = new HashMap<>();
        for (String authorName : authorPapers.keySet()) {
            ArrayList collab = new ArrayList(getCollaborators(authorName));
            for (Object partner : collab) {
                HashMap<String, Integer> partnerPapers = new HashMap<>();
                Set partnerCollab = getPapers(partner.toString());
                Set authorCollab = getPapers(authorName);
                authorCollab.retainAll(partnerCollab);
                int coAuthorship = authorCollab.size();
                partnerPapers.put(partner.toString(),coAuthorship);

                //Add neighbouring nodes and their coAuthorship value to graph.
                if (graphErdos.containsKey(authorName)) {
                    HashMap existing = graphErdos.get(authorName);
                    existing.putAll(partnerPapers);
                    graphErdos.put(authorName,existing);
                }
                else {
                    graphErdos.put(authorName,partnerPapers);
                }
            }
            //Author doesn't have co-authorship with other authors.
            if (!graphErdos.containsKey(authorName)) {
                graphErdos.put(authorName,null);
            }
        }
    }

    /**
     * DFS traversal algorithm for graph.
     * @param node starting node to traverse from on graph
     */
    private void DFS(String node) {
        if (visited.get(node)) {
            return;
        }
        visited.put(node,true);
        erdosNeighbours++;
        ArrayList neighbours = new ArrayList(graphErdos.get(node).keySet());
        for (Object next : neighbours ) {
            DFS(next.toString());
        }
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
        papers.addAll(authorPapers.get(author));
        return papers;
    }


    /**
     * Gets all the unique co-authors the author has written a paper with.
     * @param author to get collaborators for
     * @return the unique co-authors the author has written with.
     */
    public Set<String> getCollaborators(String author) {
        HashSet<String> coAuthors = new HashSet<>();
        HashSet<String> papersOf = new HashSet<>(getPapers(author));
        for (String papers : papersOf) {
            coAuthors.addAll(paperAuthors.get(papers));
        }
        coAuthors.remove(author);
        return coAuthors;
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
        if (erdosNeighbours == (graphErdos.size())) {
            return true;
        }
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
        int erdosNumber = 0;
        if (author == ERDOS) {
            return 0;
        }


        return erdosNumber;
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
