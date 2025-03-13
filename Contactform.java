import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

class ContactForm extends JFrame {

    private JTextField yourNameTextField;
    private JTextField contactNoTextField;
    private JTextField programDateTextField;
    private JTextField guestNumberTextField;
    private JTextField emailAddressTextField;
    private JComboBox<String> programTypeComboBox;
    private JTextArea messageTextArea;
    private JButton submitButton;
    private JButton backButton; // Back button
    private JLabel imageLabel;

    public ContactForm() {
        setTitle("Contact Form");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-Screen Mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left Panel for Image
        JPanel leftPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel(new ImageIcon("bride.jpg")); // Load Image
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        leftPanel.add(imageLabel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        // Right Panel for Form
        JPanel rightPanel = new JPanel(new GridLayout(10, 2, 20, 20)); // Adjusted grid for Back button
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Padding
        Font font = new Font("Arial", Font.PLAIN, 30); // Text Size 30

        // Input Fields
        rightPanel.add(createLabel("Your Name:"));
        yourNameTextField = createTextField();
        rightPanel.add(yourNameTextField);

        rightPanel.add(createLabel("Contact No.:"));
        contactNoTextField = createTextField();
        rightPanel.add(contactNoTextField);

        rightPanel.add(createLabel("Program Date:"));
        programDateTextField = createTextField();
        rightPanel.add(programDateTextField);

        rightPanel.add(createLabel("Guest Number:"));
        guestNumberTextField = createTextField();
        rightPanel.add(guestNumberTextField);

        rightPanel.add(createLabel("Email Address:"));
        emailAddressTextField = createTextField();
        rightPanel.add(emailAddressTextField);

        rightPanel.add(createLabel("Program Type:"));
        programTypeComboBox = new JComboBox<>(new String[]{"Holud", "Corporate Event", "Birthday", "Anniversary", "Wedding Reception"});
        programTypeComboBox.setFont(font);
        rightPanel.add(programTypeComboBox);

        rightPanel.add(createLabel("Message:"));
        messageTextArea = new JTextArea();
        messageTextArea.setFont(font);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        rightPanel.add(scrollPane);

        // Submit Button
        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 35));
        submitButton.setBackground(new Color(200, 150, 100)); // Soft Brown Color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        submitButton.addActionListener(this::handleSubmit);
        rightPanel.add(submitButton);


        // Back Button
        backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 35));
        backButton.setBackground(new Color(200, 150, 100)); // Soft Brown Color
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        backButton.addActionListener(this::handleBack); // Action for Back Button
        rightPanel.add(backButton);

        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        return textField;
    }

    private void handleSubmit(ActionEvent e) {
        // Collect User Input
        String yourName = yourNameTextField.getText();
        String contactNo = contactNoTextField.getText();
        String programDate = programDateTextField.getText();
        String guestNumber = guestNumberTextField.getText();
        String emailAddress = emailAddressTextField.getText();
        String programType = (String) programTypeComboBox.getSelectedItem();
        String message = messageTextArea.getText();

        // Save Data to Text File
        saveToFile(yourName, contactNo, programDate, guestNumber, emailAddress, programType, message);

        // Clear Form After Submission
        yourNameTextField.setText("");
        contactNoTextField.setText("");
        programDateTextField.setText("");
        guestNumberTextField.setText("");
        emailAddressTextField.setText("");
        programTypeComboBox.setSelectedIndex(0);
        messageTextArea.setText("");

        JOptionPane.showMessageDialog(this, "Form Submitted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToFile(String name, String contact, String date, String guests, String email, String type, String message) {
        try (FileWriter writer = new FileWriter("contact_form_data.txt", true)) {
            writer.write("Name: " + name + "\n");
            writer.write("Contact No: " + contact + "\n");
            writer.write("Program Date: " + date + "\n");
            writer.write("Guest Number: " + guests + "\n");
            writer.write("Email Address: " + email + "\n");
            writer.write("Program Type: " + type + "\n");
            writer.write("Message: " + message + "\n");
            writer.write("-------------------------------------\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleBack(ActionEvent e) {
        // Close the ContactForm and go back to the previous window
        this.dispose(); // Close the current form (ContactForm)
        new EventUI();  // Open the EventUI (Main Window) again
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactForm::new);
    }
}
