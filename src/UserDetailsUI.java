import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDetailsUI extends JFrame {

    private JLabel nameLabel, addressLabel, mobileLabel;
    private JTextField nameField, addressField, mobileField;
    private JButton saveButton;

    public UserDetailsUI() {
        setTitle("User Details");
        setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 25);
        add(nameField);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 50, 80, 25);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(100, 50, 200, 25);
        add(addressField);

        mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setBounds(20, 80, 100, 25);
        add(mobileLabel);

        mobileField = new JTextField();
        mobileField.setBounds(130, 80, 170, 25);
        add(mobileField);

        saveButton = new JButton("Save");
        saveButton.setBounds(120, 120, 80, 25);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveUserDetails();
            }
        });

        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void saveUserDetails() {
        String name = nameField.getText();
        String address = addressField.getText();
        String mobileNumber = mobileField.getText();

        String query = "INSERT INTO users (name, address, mobile) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, mobileNumber);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "User details saved successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new UserDetailsUI();
    }
}
