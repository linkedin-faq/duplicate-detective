package duplicates;

import java.awt.color.ICC_Profile;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DuplicateDetective {
    static Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Assessment> assessments = loadAssessments();
        for (Assessment assessment : assessments) {
            assessment.findDuplicates();
            assessment.writeFile();
        }
        Report.getInstance().finish();
    }

    /**
     * Loads all quiz files and sends them to their own {@link Assessment} class.
     * The directory of this project is as follows:
     * <pre>
     *     duplicate-detective (root)
     *     |- linkedin-skill-assessments-quizzes
     *        |- [quiz directory]
     *           |- [quiz MD file(s)]
     *     |- src
     *        |- ...
     * </pre>
     *
     * @return Quiz files represented by {@link Assessment} objects
     */
    static ArrayList<Assessment> loadAssessments() {
        ArrayList<Assessment> assessments = new ArrayList<>();
        // linkedin-skill-assessments-quizzes
        File[] directories = new File("linkedin-skill-assessments-quizzes").listFiles();
        if (directories == null) {
            System.err.println("Could not find assessments directory! Make sure git submodule is configured in root directory of this project.");
            System.exit(1);
        }
        // [quiz directory]
        for (File quizDirectory : directories) {    // 2
            if (quizDirectory.isDirectory()) {
                File[] quizzes = quizDirectory.listFiles();
                if (quizzes != null) {
                    // [quiz MD file(s)]
                    for (File quiz : quizzes) {
                        if (quiz.getName().endsWith(".md")) {
                            assessments.add(new Assessment(quiz));
                        }
                    }
                } else {
                    System.out.println("Skipping " + quizDirectory.getName() + " - No quizzes found within.");
                }
            }
        }
        return assessments;
    }
}
