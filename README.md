# Duplicate Detective
A program that finds and removes duplicates and renumbers questions in the LInkedIn Skill Assessments Quizzes Repo.

This program searches for markdown files containing quizes starting from the current project directory and traversing through one folder layer,
and then reaches the quiz file.

The program will prompt the user about duplicates to make sure the two questions are duplicates.
You can either hit 'y' for yes or 'n' for no.
If you wish to specify which question should be deleted,
the question number to be removed can be stated after the 'y' character, eg "y64".

There are a couple other things I wish to do with this tool:
TODO
- Better exception handling and repeating questions when receiving bad input.
- Saving in a file which questions are not duplicates so the user is not prompted about them every time the tool is run
- Create a `pom.xml` file to automate builds and other files writing.