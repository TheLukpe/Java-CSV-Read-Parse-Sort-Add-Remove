
/**
 * @author 2512860
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class DOLBGUI extends JFrame implements ActionListener {

    /**
     * Frame layout constants
     */
    private static final int HOME_FRAME_WIDTH = 950;
    private static final int HOME_FRAME_HEIGHT = 750;

    /**
     * JLabel Setup
     */
    private JLabel titleLabel = new JLabel(
            "                                                   Directory Of Listed Buildings                                                          ");
    private JLabel referenceLabel = new JLabel("            Reference ");
    private JLabel addressLabel = new JLabel(" Address ");
    private JLabel categoryLabel = new JLabel(" Category ");
    private JLabel councilLabel = new JLabel("Council ");
    private JLabel parishLabel = new JLabel(" Parish");
    private JLabel dayLabel = new JLabel("Day");
    private JLabel monthLabel = new JLabel("Month");
    private JLabel yearLabel = new JLabel("Year");
    private JLabel sortByLabel = new JLabel("             Sort By:");

    /**
     * Creating JTextFields
     */
    private JTextField refField = new JTextField(5);
    private JTextField addField = new JTextField(7);
    private JTextField catField = new JTextField(1);
    private JTextField dayField = new JTextField(2);
    private JTextField monthField = new JTextField(2);
    private JTextField yearField = new JTextField(4);
    private JTextField councilField = new JTextField(7);
    private JTextField parishField = new JTextField(7);
    private JTextField sortField = new JTextField(10);

    /**
     * Creating JButtons
     */
    private JButton addBtn = new JButton("Add Record");
    private JButton refreshBtn = new JButton("Refresh / Display All Results");
    private JButton sortBtn = new JButton("Sort!");

    /**
     * Setting up J menu bar
     */
    private JMenuBar menuBar = new JMenuBar();
    // private JMenu addMenu = new JMenu("Add");
    // private JMenu sortByMenu = new JMenu("Sort By");
    private JMenu saveMenu = new JMenu("Save");

    /**
     * Adding items to the J menu
     */
    // private JMenuItem recordMI = new JMenuItem("Record");
    // private JMenuItem addressMI = new JMenuItem("Address");
    // private JMenuItem dateMI = new JMenuItem("Date");
    private JMenuItem saveMI = new JMenuItem("Save File");


    /**
     * JText Area Set up and Scroll Pane
     */
    protected JTextArea outputArea = new JTextArea(35, 75);
    JScrollPane scrollPane = new JScrollPane(outputArea);

    private Directory_Of_Listed_Buildings myRecords = new Directory_Of_Listed_Buildings();
    Font titleFont = new Font("Serif", Font.BOLD, 20); // creates a font for the title label

    private String[] sortByArr = { "Address", "Date" };
    private JComboBox sortByBox = new JComboBox(sortByArr);
    private String saveFileName;
    /**
     * Main , Launches the Directory of listed buildings program
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        DOLBGUI frame = new DOLBGUI(); // creates frame referenced as DOLBGUI
        // DOLBGUI dolbgui = new DOLBGUI();
        frame.readFile(args[0]);
        frame.setSize(HOME_FRAME_WIDTH, HOME_FRAME_HEIGHT); // sets frame size
        frame.createGUI(); // calls create GUI method
        
        

    }
    
    public void readFile(String fileName) throws FileNotFoundException
    {
        saveFileName = fileName;
        File inputFile = new File(fileName);
        Scanner in = new Scanner(inputFile);
        in.nextLine(); // Skips first line of Headings
        while (in.hasNextLine()) // While there is a line to be read
        {
            String currentLine = in.nextLine(); // set the string currentLine to the line in the file
            addRecordUsingLine(currentLine); // using that line create a record
        }
        in.close();
    }
        
    /**
     * Sets up the graphical user interface
     * 
     * @throws FileNotFoundException
     */
    public void createGUI() throws FileNotFoundException {
        setVisible(true); // sets frame to visible
        setTitle("Directory Of Listed Buildings"); // sets title of frame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        menuBar.add(saveMenu); // adds the "Save" option to the menu bar
        saveMenu.add(saveMI);
        saveMI.addActionListener(this);
        add(menuBar); // adds the whole menu bar to the frame

        // MISC ELEMENTS
        titleLabel.setFont(titleFont); // sets labels font
        add(titleLabel); // adds label
        add(refreshBtn);
        refreshBtn.addActionListener(this);
        outputArea.setEditable(false);
        add(scrollPane);

        // ADDING RECORD ELEMENTS
        add(referenceLabel); // Adds reference label
        add(refField); // adds reference text field
        add(addressLabel); // Adds address label
        add(addField); // adds address field
        add(categoryLabel); // Adds category label
        add(catField); // adds category field
        add(dayLabel); // adds day label
        add(dayField);
        add(monthLabel); // adds month label
        add(monthField); // adds month field
        add(yearLabel); // adds year label
        add(yearField); // adds year field
        add(councilLabel); // Adds Labels
        add(councilField);
        add(parishLabel); // Adds Labels
        add(parishField);
        addBtn.addActionListener(this); // adds action listner to the add button
        add(addBtn); // adds add button to jframe

        // SORTING ELEMENTS
        add(sortByLabel);
        add(sortByBox);
        add(sortField);
        sortBtn.addActionListener(this);
        add(sortBtn);

        displayEntries();

    } // END OF CREATE GUI

    /**
     * Splits the current line in the file  into an array in which every other index contains a category
     * 
     * @param currentLine
     *            in the file that is to be split
     */
    public void addRecordUsingLine(String currentLine) {
        
            /**
     * Creating Variables for records
     */

     int reference;
    String address;
     char category;
     int day;
     int month;
     int year;
     String council;
     String parish;
    
        String lineSplit[] = currentLine.split("\"|\",|,\""); // Splits the curernt line into an array. Breaking the line up at each "\","\",",""
        String dateSplit[] = lineSplit[7].split("/"); // splits the date part of the string into an array of day month year. breaking at "/" each time
        reference = Integer.parseInt(lineSplit[1]); // reference number is set to index 1 in the lineSplit array
        address = lineSplit[3]; // address is set to index three in the lineSplit array
        category = lineSplit[5].charAt(0); // category is set to the character at index 5 in the lineSplit array 
        day = Integer.parseInt(dateSplit[0]); // day is set to the integer from index 0 in the date split array 
        month = Integer.parseInt(dateSplit[1]); // month is set to the integer from index 1 in the date split array 
        year = Integer.parseInt(dateSplit[2]); // year is set to the integer from index 2 in the date split array.
        council = lineSplit[9]; // council is set to index 9 in the lineSplit array 
        parish = lineSplit[11]; // parish is set to index 11 in the lineSplit array
        
        addRecord(category, address, council, parish, day, month, year, reference);
        
    } // END OF SPLIT RECORD

    /**
     * Decides record needed to be added
     */
    protected void addRecord(char category, String address, String council, String parish, int day, int month, int year, int reference) {
        if (Character.toUpperCase(category) == 'A') { // if the character in upper case is = A
            addRecordA(category, address, council, parish, day, month, year, reference); // call add record A
        } else if (Character.toUpperCase(category) == 'B') { // if the character in upper case is = B
            addRecordB(category, address, council, parish, day, month, year, reference); // call add record B
        } else if (Character.toUpperCase(category)== 'C'){ //else which means C 
            addRecordC(category, address, day, month, year, reference); // call add record C
        }
        else{
            JOptionPane.showMessageDialog(null, "Error Please Input Category as either : A, B or C");
            catField.setText("");
        }
    } // END OF ADD RECORD

    /**
     * Adds records Type A
     */
    private void addRecordA(char category, String address, String council, String parish, int day, int month, int year, int reference) {
        category = 'A'; // set the category to Captial A
        address = address.toUpperCase(); // make address capitals
        council = council.toUpperCase(); // make council capitals
        parish = parish.toUpperCase(); // make parish capitals
        Category_A ca = new Category_A(reference, address, category, day, month, year, council, parish); // create new entry
        myRecords.addEntry(ca); // add the entry to the array list
    }

    /**
     * Adds records Type B
     */
    private void addRecordB(char category, String address, String council, String parish, int day, int month, int year, int reference) {
        category = 'b'; // make category lower case
        address = address.toLowerCase(); // make address lower case
        council = council.toLowerCase(); // make council lower case
        parish = parish.toLowerCase(); // make parish lower case
        Category_B cb = new Category_B(reference, address, category, day, month, year, council, parish);// create new entry
        myRecords.addEntry(cb); // add the entry to the array list
    }

    /**
     * Adds records Type C
     */
    private void addRecordC(char category, String address, int day, int month, int year, int reference) {
        category = 'c'; // make category lower case
        address = address.toLowerCase(); // make address lower case
         Category_C cc = new Category_C(reference, address, category, day, month, year); // create new entry
        myRecords.addEntry(cc); // add the entry to the array list
    }

    /** 
     * Sorting method. Decides to sort by date or address and calls required methods
     * @param sortSelection
     */
    private void sortRecords(String sortSelection) {
        if (sortSelection.equals("Address")) { // if the selection in combo box is address Execute... 
            String result = myRecords.sortByAddress(sortField.getText()); // set result as the sort by address method call from directory of listed buildings with the user input in the text field
            outputArea.setText(result); // output the results
        } else {
            String userInput = sortField.getText(); // grab user input
            String[] partsOfDate = userInput.split("/"); // split date into day month year
            int d = Integer.parseInt(partsOfDate[0]); // set day
            int m = Integer.parseInt(partsOfDate[1]); // set month
            int y = Integer.parseInt(partsOfDate[2]); // set year
            String result = myRecords.sortByDate(d, m, y); // call sort by date method in Directory of listed buildings
            outputArea.setText(result); // output the results
        }
    }

    /** 
     * Sets the user text fields to empty
     */
    private void clearFields() {
        refField.setText("");
        addField.setText("");
        catField.setText("");
        dayField.setText("");
        monthField.setText("");
        yearField.setText("");
        councilField.setText("");
        parishField.setText("");
    }

    /**
     * Saves the file (including any additions made) with the same name as it was loaded with 
     * @throws FileNotFoundException
     */
    private void saveFile() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(saveFileName); // creates new print writer telling what file to print to / create new one
        out.println("\"REFERENCE\",\"ADDRESS\",\"CATEGORY\",\"LISTED\",\"COUNCIL\",\"PARISH\"\r\n"); // input headings
        int count = 0;
        while (count <= (myRecords.getSize())) { // loop through myRecords until at end of array list
            out.println(myRecords.getCurrentLine(count)); // print out line at count
            count++; // increase count and  start loop again
        }
        out.close(); // close print writer to stop leaks
        outputArea.setText("File has been saved, using the same file name as specified on the command line.");
    }

    /**
     * Displays all records in the text area
     */
    protected void displayEntries() {
        String result = myRecords.displayAllEntries(); // sets result as the return from the display All Entries method call
        outputArea.setText(result); // sets the output area as the result
    }

    /**
     * Actions that are performed on button clicks
     */

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == saveMI) { // if the save menu button is clicked
            try { // try to execute the next line
                saveFile(); // call the save file method
            } catch (FileNotFoundException e) { // if an error occurs, catch and prevent program from crashing
                e.printStackTrace();
            }
        }

        if (event.getSource() == refreshBtn) {  // if the refresh button is pressed
            displayEntries(); // call the display all entries method
        }

        if (event.getSource() == sortBtn) { // if sort button clicked 
            try { // try execute following code
                String sortSelection = (String) sortByBox.getSelectedItem(); // set selection sort to type string of combo box
                sortRecords(sortSelection); // call sort records method with the sort string
            } catch (Exception e) { // this will only crash if date has been input wrong -- explain issue with j option pane1
                JOptionPane.showMessageDialog(null, "Please Enter Date as follows: \n Day/Month/Year \n Ex. 20/03/1998");
            }
        }

        if (event.getSource() == addBtn) { // if the add button is pressed
            try { // try to execute the following code
                
                int reference;
                String address;
                char category;
                int day;
                int month;
                int year;
                String council;
                String parish;
     
                reference = Integer.parseInt(refField.getText()); // grab reference number from user input
                boolean recExists = myRecords.ifReferenceExists(reference); //creates boolean rec exists and calls if reference exists from directory of listed buildings
                if (recExists) { // if the record does exist
                    JOptionPane.showMessageDialog(null,
                        "Entry already exists! \n Please use a different reference number"); // show error message
                    refField.setText(""); // set the reference field to empty to allow a new input
                } else { // if it doesn't exists, continue to add record
                    address = addField.getText();
                    category = catField.getText().charAt(0);
                    day = Integer.parseInt(dayField.getText());
                    month = Integer.parseInt(monthField.getText());
                    year = Integer.parseInt(yearField.getText());
                    council = councilField.getText();
                    parish = parishField.getText();
                    addRecord(category, address, council, parish, day, month, year, reference); // calls add record
                    clearFields(); // clears the fields
                    displayEntries(); // displays the tnries including new entry
                }
            } catch (NumberFormatException e) { // if the program encounters problem - Data has been input wrong, Explain entry
                JOptionPane.showMessageDialog(null,
                    "Please Enter Data as follows: \n Reference: 5 digit number - Ex. 12345 \n Category : Characters \"A\",\"B\" or \"C\" \n Day: Number 1-31 \n Month: Number 1-12 \n Year: A four digit number Ex. 2020 ");
            }
        }

    } // END OF ACTION LISTENERS
}
