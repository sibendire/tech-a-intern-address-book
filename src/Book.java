import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Book implements ActionListener {
    String name;
    String address;
    int phoneNumber;
    String email;

    Button buttonSave, buttonDelete, buttonUpdate, buttonLeft, buttonSearch, buttonRight, buttonClear, buttonExit;
    TextField textField1, textField2, textField3, textField4;

    Panel panel;
    Label nameLabel, addressLabel, phoneLabel, emailLabel;
    GridBagLayout gridBagLayout;
    GridBagConstraints gbc;

    Frame frame;

    List<String> records; // Store records
    int currentRecordIndex; // Track current record index

    Book() {
        frame = new Frame("Book");
        frame.setLayout(new FlowLayout());
        panel = new Panel();

        // Initialize records list and current record index
        records = new ArrayList<>();
        currentRecordIndex = 0;

        // Initialize buttons and text fields
        buttonSave = new Button("Save");
        buttonSave.addActionListener(this);

        buttonDelete = new Button("Delete");
        buttonDelete.addActionListener(this);

        buttonUpdate = new Button("Update");
        buttonUpdate.addActionListener(this);

        buttonLeft = new Button("<<");
        buttonLeft.addActionListener(this);

        buttonSearch = new Button("Search");
        buttonSearch.addActionListener(this);

        buttonRight = new Button(">>");
        buttonRight.addActionListener(this);

        buttonClear = new Button("Clear");
        buttonClear.addActionListener(this);

        buttonExit = new Button("Exit");
        buttonExit.addActionListener(this);

        nameLabel = new Label("Name:");
        addressLabel = new Label("Address:");
        phoneLabel = new Label("Phone Number:");
        emailLabel = new Label("Email:");

        textField1 = new TextField(20);
        textField2 = new TextField(20);
        textField3 = new TextField(20);
        textField4 = new TextField(20);

        gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(nameLabel, gbc);

        gbc.gridy = 1;
        panel.add(addressLabel, gbc);

        gbc.gridy = 2;
        panel.add(phoneLabel, gbc);

        gbc.gridy = 3;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;

        panel.add(textField1, gbc);

        gbc.gridy = 1;
        panel.add(textField2, gbc);

        gbc.gridy = 2;
        panel.add(textField3, gbc);

        gbc.gridy = 3;
        panel.add(textField4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(buttonSave, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 5, 10); // Adjusted gap between Delete and Update

        panel.add(buttonDelete, gbc);

        gbc.gridx = 2;
        panel.add(buttonUpdate, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 5, 5);

        panel.add(buttonLeft, gbc);

        gbc.gridx = 1;
        panel.add(buttonSearch, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 5, 5);

        panel.add(buttonRight, gbc);

        gbc.gridx = 1;
        panel.add(buttonClear, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 5, 10); // Adjusted gap between Clear and Exit

        panel.add(buttonExit, gbc);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        displayRecord(); // Display the first record initially
    }

    private void displayRecord() {
        if (!records.isEmpty() && currentRecordIndex >= 0 && currentRecordIndex < records.size()) {
            String[] record = records.get(currentRecordIndex).split(",");
            textField1.setText(record[0]);
            textField2.setText(record[1]);
            textField3.setText(record[2]);
            textField4.setText(record[3]);
        } else {
            // Clear text fields if no records or invalid index
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSave) {
            // Save record to list
            name = textField1.getText();
            address = textField2.getText();
            phoneNumber = Integer.parseInt(textField3.getText());
            email = textField4.getText();
            String record = name + "," + address + "," + phoneNumber + "," + email;
            records.add(record);
            System.out.println("Saved: " + record);
            displayRecord();
        } else if (e.getSource() == buttonDelete) {
            // Delete the current record
            if (!records.isEmpty() && currentRecordIndex >= 0 && currentRecordIndex < records.size()) {
                records.remove(currentRecordIndex); // Remove current record
                System.out.println("Record deleted.");
                if (currentRecordIndex >= records.size()) {
                    currentRecordIndex--; // Adjust index if at the end
                }
                displayRecord(); // Display next record
            }
        } else if (e.getSource() == buttonUpdate) {
            // Update button logic
            if (!records.isEmpty() && currentRecordIndex >= 0 && currentRecordIndex < records.size()) {
                // Update the current record with new data
                name = textField1.getText();
                address = textField2.getText();
                phoneNumber = Integer.parseInt(textField3.getText());
                email = textField4.getText();
                String updatedRecord = name + "," + address + "," + phoneNumber + "," + email;
                records.set(currentRecordIndex, updatedRecord);
                System.out.println("Record updated: " + updatedRecord);
                displayRecord();
            }
        } else if (e.getSource() == buttonLeft) {
            // Left button logic
            if (currentRecordIndex > 0) {
                currentRecordIndex--;
                displayRecord();
            }
        } else if (e.getSource() == buttonSearch) {
            // Search button logic
        } else if (e.getSource() == buttonRight) {
            // Right button logic
            if (currentRecordIndex < records.size() - 1) {
                currentRecordIndex++;
                displayRecord();
            }
        } else if (e.getSource() == buttonClear) {
            // Clear all fields
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            System.out.println("Cleared all fields.");
        } else if (e.getSource() == buttonExit) {
            // Exit the application
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Book book = new Book();
    }
}
