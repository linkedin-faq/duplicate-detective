package us.linkedinfaq.duplicatedetective;

/**
 * This class loads the questions that have already been established as unique.
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

}
