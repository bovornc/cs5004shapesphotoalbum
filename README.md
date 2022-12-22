Design Notes:
I didn't have any prior experience using Graphics in Java, so I decided to implement my Graphical View using a split pane, with the snapshots/timestamps/and descriptions taking up the bulk of the window, and the buttons on the right side. Drawing the snapshots and their respective timestamps and descriptions was just done with the paint() method, while repaint() was used to generate a new drawing. I decided to simply paint a filled-in white rectangle at the start of my paint() method, essentially generating a blank slate for the snapshot to be drawn on.
The command line was parsed within the main(String args) method, printing out error messages and exiting if any invalid or missing inputs were found, then calling their respective View based on what followed "-v" or "-view" in the command line.
The input file was parsed through using a FileReader, which would split the file into lines, then split each line into words. Various methods from PhotoAlbumModel would be called based on the first word of each line. 

Changes to the Model from Homework 8:
- Added new protected variables Xposition, Yposition, R, G, and B to AbstractShape
- Added relevant "getter" methods for the new variables to AbstractShape
- Removed the now irrelevant variables double[] position and Color color and their respective methods.
- Refactored methods that previously used double[] position and Color color to work with Xposition and Yposition, and R G and B respectively.
- Changed createShape() to accommodate different inputs:
	- Reordered parameters based on command line input order
	- Positional inputs now use two doubles instead of a double[]
	- Color inputs now use three doubles (R G B values) instead of a Color enum
- Changed the Snapshot class to accommodate blank input Strings for the Description
- Changed the snapshot method in PhotoAlbumModel to accommodate blank input Strings for the Description
- Changed Shapelist and Snapshots in PhotoAlbumModel from HashMap to LinkedHashMap since the order in which shapes/snapshots were added matters for painting.

Bugs:
- "Next", "Select", and "Previous" buttons on the Graphical view behave inconsistently. Sometimes all 3 are invisible but clickable, sometimes the "Next" button is visible while the other are invisible, and sometimes all 3 are visible.
- Couldn't figure out how to change the displayed JComboBox item on the "Select" button to reflect the current Snapshot.