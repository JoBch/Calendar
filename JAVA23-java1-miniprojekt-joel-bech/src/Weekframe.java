import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static java.lang.Integer.parseInt;
public class Weekframe extends JFrame {
    private JTextArea[] arrayOutput;
    private JTextField[] arrayInput;
    private JButton[] arrayButton;
    private String [] buttonName = {"Submit"};
    private JLabel[] arrayHeader;
    private LocalDate today;
    private LocalDate findMonday;
    Weekframe() {
        //Constructing a frame to put everything in
        JFrame frame = new JFrame("Kalender");
        frame.setSize(1400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 7));

        //Arrays to make every label, button, textArea and textField is their own individual
        arrayHeader = new JLabel[7];
        arrayButton = new JButton[7];
        arrayInput = new JTextField[7];
        arrayOutput = new JTextArea[7];

        //Formatters for displaying my dates the way I want it
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");

        //Variables for being able to display the date of every day
        today = LocalDate.now();
        findMonday = (today.with(DayOfWeek.MONDAY));

        //For-loop creating my seven panels and putting them in the frame
        for (int i = 0; i <= 6; i++) {
            //Adding days from monday to display my dates right in the header
            LocalDate setDays = findMonday.plusDays(i);

            //Making a panel that will fit on my frame
            JPanel dayPanel = new JPanel();
            frame.add(dayPanel);
            dayPanel.setLayout(new BorderLayout());
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

            //Trying to add labels with different names for every dayPanel, so I can display my dates in them
            arrayHeader[i] = new JLabel(String.valueOf(findMonday));
            arrayHeader[i].setText(setDays.format(dayFormatter).toUpperCase() + " DEN " + setDays.format(dateFormatter).toUpperCase() + " " + setDays.format(monthFormatter).toUpperCase());
            dayPanel.add(arrayHeader[i], BorderLayout.NORTH);

            //A JPanel to store my button and textField for input of my activity
            JPanel dayPanelBottom = new JPanel();
            dayPanelBottom.setLayout(new GridLayout(2, 0));
            dayPanel.add(dayPanelBottom, BorderLayout.SOUTH);

            //TextArea to output my activities in
            arrayOutput[i] = new JTextArea();
            dayPanel.add(arrayOutput[i]);
            arrayOutput[i].setEditable(false);
            arrayOutput[i].setLineWrap(true);
            arrayOutput[i].setWrapStyleWord(true);

            //Inputting a text to display in arrayOutput
            arrayInput[i] = new JTextField("Lägg till en aktivitet");
            dayPanelBottom.add(arrayInput[i]);

            //Sending input to arrayOutput via a button with action listener
            for (String text : buttonName){
            arrayButton[i] = new JButton(text); //Submit
            arrayButton[i].addActionListener(listener);
            arrayButton[i].setActionCommand(i+"");
            dayPanelBottom.add(arrayButton[i]);}

            //If-statement to change the colour of today's JLabel
            if (setDays.isEqual(today)) {
                arrayHeader[i].setForeground (Color.red);
            }
            frame.setVisible(true);
        }
    }
    //ActionListener for taking my text from arrayInput and insert it to arrayOutput by the click of arraySubmit
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                String text = e.getActionCommand();
                int i = parseInt(text);
                arrayOutput[i].append(arrayInput[i].getText() + "\n");
            }
        }
    };
}

