import jdk.jfr.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventUI extends JFrame {

    public EventUI() {
        setTitle("Mehedi Shooting Club Convention");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon(getClass().getResource("front.png"));
        JLabel backgroundlabel = new JLabel(img);
        backgroundlabel.setBounds(10, 80, 2111, 1100);
        add(backgroundlabel);

        // Top Navigation Bar with BorderLayout
        JPanel navBar = new JPanel();
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(new Color(240, 230, 220));

        // Contact Label aligned to the left
        JLabel contactLabel = new JLabel("                     Phone: 901522  |  WhatsApp: +880191-2548444");
        contactLabel.setForeground(Color.BLACK);
        contactLabel.setFont(new Font("Arial", Font.BOLD, 30));
        navBar.add(contactLabel, BorderLayout.WEST);

        // Panel for Buttons, aligned to the right
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 20));
        buttonPanel.setBackground(new Color(240, 230, 220)); // Same background color

        // Navigation Buttons (Removed "Contact" button)
        String[] navItems = {"Home", "Offers"};
        for (String item : navItems) {
            JButton button = createStyledButton(item);
            buttonPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (item.equals("Offers")) {
                        JFrame offersFrame = new JFrame("Offers");
                        offersFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen
                        offersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame when it's closed

                        ImageIcon img1 = new ImageIcon(getClass().getResource("offer.png"));
                        JLabel backgroundlabel1 = new JLabel(img1);
                        backgroundlabel1.setBounds(10, 80, 2111, 1100);
                        offersFrame.add(backgroundlabel1);
                        offersFrame.setVisible(true);
                    } else if (item.equals("Home")) {
                        dispose(); // Close the current window
                        new EventUI(); // Open the FrontUI window
                    }
                }
            });
        }






        // "Book Now" Button (Styled)
        JButton bookNow = createStyledButton("📌 Book Now");
        bookNow.setBackground(new Color(189, 135, 80)); // Golden-brown color
        bookNow.setForeground(Color.WHITE);
        buttonPanel.add(bookNow);
        bookNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactForm c = new ContactForm();
            }
        });

        // Admin Button for Admin Login
        JButton adminButton = createStyledButton("Admin Login");
        adminButton.setBackground(new Color(255, 99, 71)); // Red color
        adminButton.setForeground(Color.WHITE);
        buttonPanel.add(adminButton);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show Admin Login Frame
                showAdminLoginFrame();
            }
        });

        // Add the buttonPanel to the navBar (aligned to the right)
        navBar.add(buttonPanel, BorderLayout.EAST);

        // Title Section
        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        titlePanel.setBackground(Color.WHITE);
        JLabel title = new JLabel("Gulshan Shooting Club Convention", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 38));
        JLabel subtitle = new JLabel("Best Convention Hall in Gulshan", SwingConstants.CENTER);
        subtitle.setFont(new Font("Serif", Font.PLAIN, 22));
        titlePanel.add(title);
        titlePanel.add(subtitle);

        add(navBar, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24)); // Bigger font
        button.setPreferredSize(new Dimension(200, 60)); // Bigger buttons
        button.setBackground(new Color(100, 149, 237)); // Stylish blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); // Darker blue
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237));
            }
        });

        return button;
    }

    // Method to show the Admin Login Frame
    private void showAdminLoginFrame() {
        JFrame loginFrame = new JFrame("Admin Login");
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        loginFrame.add(loginPanel, BorderLayout.CENTER);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check for the hardcoded admin credentials (you can modify this)
                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                    loginFrame.dispose();  // Close the login window

                    // Open Admin Dashboard after successful login
                    openAdminDashboard();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid Credentials! Try Again.");
                }
            }
        });

        loginFrame.setVisible(true);
    }

    // Method to open the Admin Dashboard after successful login
    private void openAdminDashboard() {
        JFrame dashboardFrame = new JFrame("Admin Dashboard");
        dashboardFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 32));

        dashboardFrame.add(welcomeLabel, BorderLayout.CENTER);
        dashboardFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventUI::new);
    }
}
