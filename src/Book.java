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

    List<String> records;
    // Track current record index
    int currentRecordIndex;

    Book() {
        frame = new Frame("Book");
        frame.setLayout(new FlowLayout());
        panel = new Panel();


        records = new ArrayList<>();
        currentRecordIndex = 0;

        nameLabel = new Label("Name:");
        addressLabel = new Label("Address:");
        phoneLabel = new Label("Phone Number:");
        emailLabel = new Label("Email:");

        textField1 = new TextField(20);
        textField2 = new TextField(20);
        textField3 = new TextField(20);
        textField4 = new TextField(20);

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

        gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(textField1, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(addressLabel, gbc);

        gbc.gridx = 1;
        panel.add(textField2, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        panel.add(textField3, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        panel.add(textField4, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        panel.add(createButtonPanel(), gbc);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        displayRecord();
    }

    private Panel createButtonPanel() {
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(3, 3, 5, 5));

        buttonPanel.add(buttonSave);
        buttonPanel.add(buttonDelete);
        buttonPanel.add(buttonUpdate);

        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonSearch);
        buttonPanel.add(buttonRight);

        buttonPanel.add(buttonClear);
        buttonPanel.add(buttonExit);

        return buttonPanel;
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
                records.remove(currentRecordIndex);
                System.out.println("Record deleted.");
                if (currentRecordIndex >= records.size()) {
                    currentRecordIndex--;
                }
                displayRecord();
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
            // Left button search
            if (currentRecordIndex > 0) {
                currentRecordIndex--;
                displayRecord();
            }
        } else if (e.getSource() == buttonSearch) {
            // Search button
        } else if (e.getSource() == buttonRight) {
            // Right button search
            if (currentRecordIndex < records.size() - 1) {
                currentRecordIndex++;
                displayRecord();
            }
        } else if (e.getSource() == buttonClear) {
            // Clear all fields button
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            System.out.println("Cleared all fields.");
        } else if (e.getSource() == buttonExit) {
            // Exit the application button
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Book book = new Book();
    }
}
