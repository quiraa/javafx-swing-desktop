
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddActivityPanel extends JPanel {

    private JTextField nameField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JCheckBox prioritizeCheckbox;
    private JButton saveButton;
    private ActivityManager activityManager;

    public AddActivityPanel(ActivityManager activityManager) {
        this.activityManager = activityManager;
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Nama Kegiatan:");
        nameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        JLabel startTimeLabel = new JLabel("Waktu Mulai (contoh: 31-12-2024 10:00):");
        startTimeField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(startTimeLabel, gbc);
        gbc.gridx = 1;
        add(startTimeField, gbc);

        JLabel endTimeLabel = new JLabel("Waktu Selesai (contoh: 31-12-2024 12:00):");
        endTimeField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(endTimeLabel, gbc);
        gbc.gridx = 1;
        add(endTimeField, gbc);

        JLabel prioritizeLabel = new JLabel("Tandai sebagai Prioritas?");
        prioritizeCheckbox = new JCheckBox();
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(prioritizeLabel, gbc);
        gbc.gridx = 1;
        add(prioritizeCheckbox, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Simpan Kegiatan");

        buttonPanel.add(saveButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(buttonPanel, gbc);

        saveButton.addActionListener(event -> {
            String name = nameField.getText();
            String startTimeStr = startTimeField.getText();
            String endTimeStr = endTimeField.getText();

            try {
                LocalDateTime startTime = TimeValidator.validateAndParse(startTimeStr);
                LocalDateTime endTime = TimeValidator.validateAndParse(endTimeStr);

                if (!TimeValidator.isEndTimeAfterStartTime(startTime, endTime)) {
                    JOptionPane.showMessageDialog(this, "Waktu selesai harus setelah waktu mulai.", "Kesalahan Waktu",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean prioritize = prioritizeCheckbox.isSelected();

                Activity activity = new Activity(name, startTime, endTime, prioritize);
                activityManager.addActivity(activity);

                JOptionPane.showMessageDialog(this, "Kegiatan berhasil ditambahkan!", "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear fields after saving
                nameField.setText("");
                startTimeField.setText("");
                endTimeField.setText("");
                prioritizeCheckbox.setSelected(false);

            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Format waktu tidak valid. Harap masukkan sesuai format (contoh: 31-12-2024 10:00)",
                        "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
