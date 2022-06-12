package us.linkedinfaq.duplicatedetective;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class loads the questions that have already been established as unique.
 * Having this mechanism insures the user isn't prompted about questions that they've already answered for.
 * It will also pass them along to the appropriate Assessments that request them and writes any new changes.
 * This is easily manageable using the hashcode of the prompts per Assessment.
 * Using a question number would be easier but would not work as questions are regularly renumbered.
 * File format:
 *
 * <pre>
 *     [Quiz name]
 *     [prompt1 hash],[prompt2 hash]
 *     ...
 *     [Another quiz name]
 *     [prompt1 hash],[prompt 2 hash]
 * </pre>
 */
public class UniqueQuestionManager {

    // Contains all
    private static final ArrayList<UniqueQuestionManager> allManagers = new ArrayList<>();

    private String assessmentHeading;
    private final ArrayList<UniquePair> uniquePairs;

    UniqueQuestionManager() {
        uniquePairs = new ArrayList<>();
        allManagers.add(this);
    }

    void setAssessmentHeading(String assessmentHeading) {
        this.assessmentHeading = assessmentHeading;
    }

    void addPair(Question q1, Question q2) {
        uniquePairs.add(new UniquePair(q1.prompt().hashCode(), q2.prompt().hashCode()));
    }

    static void writeUniques() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("known-unique-questions.txt"));
            for (UniqueQuestionManager uniqueQuestionManager : allManagers) {
                writer.write(uniqueQuestionManager.assessmentHeading + "\n");
                for (UniquePair uniquePair : uniqueQuestionManager.uniquePairs) {
                    writer.write(uniquePair.hash1 + "," + uniquePair.hash2 + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A simple data structure to hold the hashes of unique questions.
     *
     * @param hash1 Hash of the first question that is unique from the second.
     * @param hash2 Hash of the second question that is unique form the first.
     */
    private record UniquePair(int hash1, int hash2) {
    }
}
