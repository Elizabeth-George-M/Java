import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

//Swing Entry Point & Theme Settings
public class MRMRS 
{
	private static int selectedRow;
    private static JFrame mainFrame;
    private static JFrame moodFrame;
    private static JFrame songFrame;
    private static JTable songTable;
    private static DefaultTableModel songTableModel;
    private static String selectedMood;
    //JDBC Credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    public static void main(String[] args) 
    {
    	try 
    	{
    		//Theme
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    	} 
    	catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) 
    	{
            e.printStackTrace();
    	}
        SwingUtilities.invokeLater(() -> 
        {
            createAndShowRegisterPage();
        });
    }
//Constructor for SongTableModel
private static class SongTableModel extends DefaultTableModel 
{
            public SongTableModel(Object[][] data, String[] columnNames) 
            {
                super(data, columnNames);
            }
}    
    
//Register Page (also home page) and it's components
private static void createAndShowRegisterPage() 
{
    mainFrame = new JFrame("Register Page");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Registration Page Components
    JPanel panel = new JPanel(new BorderLayout());
    JPanel formPanel = new JPanel(new GridLayout(4, 2));
    JPanel buttonPanel = new JPanel(new FlowLayout());
    
    JTextField nameField = createStyledTextField();
    String[] languages = {"Tamil", "Malayalam", "English", "Hindi", "Kannada"};
    JComboBox<String> languageComboBox = createStyledComboBox(languages);
    String[] genres = {"R&B", "Hip-hop", "Pop", "Classical", "Instrumental"};
    JComboBox<String> genreComboBox = createStyledComboBox(genres);
    JButton registerButton = createStyledButton("Register", new Color(52, 152, 219));
    JButton returningUserButton = createStyledButton("Login", new Color(52, 152, 219));
    //Validation for Registration Form
    registerButton.addActionListener(new ActionListener() 
    {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (nameField.getText().isEmpty() || languageComboBox.getSelectedItem() == null || genreComboBox.getSelectedItem() == null) 
                {
                    JOptionPane.showMessageDialog(mainFrame, "Please fill in all fields.");
                } 
                else 
                {
                    if (containsNumericCharacters(nameField.getText())) 
                    {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid name. Please enter a name without numeric characters.");
                    } 
                    else 
                    {
                        registerUser(nameField.getText(), (String) languageComboBox.getSelectedItem(), (String) genreComboBox.getSelectedItem());
                    }
                }
            }
            private boolean containsNumericCharacters(String name) 
            {
                return name.matches(".*\\d.*");
            }
    });
    //Existing User Login Functionality
    returningUserButton.addActionListener(new ActionListener() 
    {
    	@Override
        public void actionPerformed(ActionEvent e) 
        {
    		// Accepts the User Name of existing User
            JTextField usernameField = createStyledTextField();
            Object[] message = 
            {
                "Enter your username:", usernameField
            };
            int option = JOptionPane.showConfirmDialog(mainFrame, message, "Returning User", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) 
            {
            	String username = usernameField.getText();
            	//Validation of User Name
                if (username != null && !username.isEmpty()) 
                {
                	// Check if the User Name contains only non-numeric characters
                    if (containsNumericCharacters(username)) 
                    {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid username. Please enter a username without numeric characters.");
                    }
                    else 
                    {
                    	// Check if the User Name exists in the database
                    	if (isUsernameExists(username.toLowerCase())) 
                    	{
                            createAndShowMoodPage("Returning User: " + username, "", "");
                    	} 
                    	else 
                    	{
                            JOptionPane.showMessageDialog(mainFrame, "Username not found. Please try again.");
                    	}
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(mainFrame, "Please enter a username.");
                }
            }
        }
    	// Method to check if the User Name contains numeric characters
		private boolean containsNumericCharacters(String username) 
		{
		         return username.matches(".*\\d.*");
		         
		}
    });
    formPanel.add(createStyledLabel("Name:"));
    formPanel.add(nameField);
    formPanel.add(createStyledLabel("Language:"));
    formPanel.add(languageComboBox);
    formPanel.add(createStyledLabel("Genre:"));
    formPanel.add(genreComboBox);
    formPanel.add(registerButton);
    formPanel.add(returningUserButton);
    formPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 40));
    //Contact Us Button
    JButton contactUsButton = createStyledButton("Contact Us", new Color(0, 128, 0));
    contactUsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try 
            {
                // The URL of JSP page
                URI jspPageUri = new URI("http://localhost:8080/ContactForm/contact.jsp");
                Desktop.getDesktop().browse(jspPageUri);
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });

    	panel.add(formPanel, BorderLayout.CENTER);
    	buttonPanel.add(registerButton);
        buttonPanel.add(returningUserButton);
        buttonPanel.add(contactUsButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        mainFrame.getContentPane().add(panel);
        mainFrame.setSize(500, 200);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
}

//User Registration Method
private static void registerUser(String name, String language, String genre) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String query = "INSERT INTO Registrations (name, language, genre) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, language);
        ps.setString(3, genre);
        int i = ps.executeUpdate();
        JOptionPane.showMessageDialog(mainFrame, "User Registration was successful!");
        ps.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(mainFrame, "Error registering user. Please try again.");
    }
}

//User Name Validation
private static boolean isUsernameExists(String username) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String query = "SELECT * FROM Registrations WHERE name = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        rs.close();
        ps.close();
        con.close();
        return exists;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

//General Method to create Combo Box
private static JComboBox<String> createStyledComboBox(String[] items) 
{
	JComboBox<String> comboBox = new JComboBox<>(items);
    comboBox.setBackground(new Color(75, 75, 75));
    comboBox.setForeground(new Color(255, 255, 255));
    ListCellRenderer<? super String> renderer = new DefaultListCellRenderer() 
    {
    	@Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
    	{
    		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    		setBackground(new Color(44, 62, 80));
    		setForeground(new Color(255, 255, 255));
    		if (isSelected) 
    		{
    			setBackground(new Color(52, 152, 219)); 
    			setForeground(Color.WHITE);
    		}
            return this;
    	}
    };
    comboBox.setRenderer(renderer);
    return comboBox;
}

//Generate page prompting user to select the Mood 
private static void createAndShowMoodPage(String name, String language, String genre) 
{
    moodFrame = new JFrame("Mood Selection");
    moodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
    JButton happyButton = createStyledButtonWithBorder("Happy", new Color(52, 152, 219));
    JButton sadButton = createStyledButtonWithBorder("Sad", new Color(52, 152, 219));
    JButton calmButton = createStyledButtonWithBorder("Calm", new Color(52, 152, 219));
    JButton excitedButton = createStyledButtonWithBorder("Excited", new Color(52, 152, 219));
    JButton angryButton = createStyledButtonWithBorder("Angry", new Color(52, 152, 219));
    JButton relaxedButton = createStyledButtonWithBorder("Relaxed", new Color(52, 152, 219));
    
    happyButton.addActionListener(createMoodButtonListener("Happy"));
    sadButton.addActionListener(createMoodButtonListener("Sad"));
    calmButton.addActionListener(createMoodButtonListener("Calm"));
    excitedButton.addActionListener(createMoodButtonListener("Excited"));
    angryButton.addActionListener(createMoodButtonListener("Angry"));
    relaxedButton.addActionListener(createMoodButtonListener("Relaxed"));
    
    panel.add(happyButton);
    panel.add(sadButton);
    panel.add(calmButton);
    panel.add(excitedButton);
    panel.add(angryButton);
    panel.add(relaxedButton);
    
    moodFrame.getContentPane().add(panel);
    moodFrame.setSize(500, 250);
    moodFrame.setLocationRelativeTo(mainFrame);
    moodFrame.setVisible(true);
}

//Event Listener for Mood Selection Button
private static ActionListener createMoodButtonListener(String mood) 
{
    return new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            selectedMood = mood;
            createAndShowSongList(selectedMood);
        }
    };
}

//General Code to Create Buttons
private static JButton createStyledButton(String text, Color bgColor) 
{
    JButton button = new JButton(text);
    button.setBackground(bgColor);
    button.setForeground(new Color(50, 50, 50));
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    return button;
}
private static JButton createStyledButtonWithBorder(String text, Color bgColor) 
{
    JButton button = createStyledButton(text, bgColor);
    button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
    return button;
}

//General Code to Create Labels
private static JLabel createStyledLabel(String text) 
{
    JLabel label = new JLabel(text);
    label.setBorder(new EmptyBorder(0, 10, 0, 0));
    label.setForeground(new Color(0, 0, 0));
    return label;
}
//General Code to Create TextField
private static JTextField createStyledTextField() 
{
    JTextField textField = new JTextField();
    textField.setBackground(new Color(75, 75, 75));
    textField.setForeground(new Color(255, 255, 255));
    return textField;
}
//Code to generate Songs List Page based on the Mood Selection
private static void createAndShowSongList(String mood) {
    JButton playButton = createStyledButton("Play", new Color(46, 204, 113));
    JButton removeButton = createStyledButton("Remove", new Color(231, 76, 60));
    JButton addButton = createStyledButton("Add Song", new Color(52, 152, 219));
    JButton updateButton = createStyledButton("Update", new Color(52, 152, 219));

    songFrame = new JFrame("Song List - " + mood);
    songFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    Object[][] data = fetchSongDataFromDatabase(mood);
    String[] columnNames = {"Song Title", "Artist", "Duration", "Link"};

    songTableModel = new SongTableModel(data, columnNames); // Use the SongTableModel constructor
    songTable = new JTable(songTableModel);
    songTable.setBackground(new Color(100, 100, 100)); 
    songTable.getTableHeader().setBackground(new Color(25, 25, 25));
    songTable.getTableHeader().setForeground(Color.WHITE);
    songTable.setRowHeight(30);

    JScrollPane scrollPane = new JScrollPane(songTable);
    songFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

    playButton.addActionListener(createPlayButtonListener());
    removeButton.addActionListener(createRemoveButtonListener());
    addButton.addActionListener(new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            showAddSongDialog();
        }
    });
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedRow = songTable.getSelectedRow();
            if (selectedRow != -1) {
                showUpdateSongDialog(selectedRow);
            } else {
                JOptionPane.showMessageDialog(songFrame, "Select a song to update.");
            }
        }
    });
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(playButton);
    buttonPanel.add(removeButton);
    buttonPanel.add(addButton);
    buttonPanel.add(updateButton); 

    songFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    songFrame.setSize(600, 400);
    songFrame.setLocationRelativeTo(moodFrame);
    songFrame.setVisible(true);
}
    
//crUd - Update Functionality
private static void showUpdateSongDialog(int selectedRow) 
{
    JDialog updateSongDialog = new JDialog(songFrame, "Update Song", true);
    updateSongDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    JTextField titleField = createStyledTextField();
    JTextField artistField = createStyledTextField();
    JTextField durationField = createStyledTextField();
    JTextField linkField = createStyledTextField();
    titleField.setText((String) songTableModel.getValueAt(selectedRow, 0));
    artistField.setText((String) songTableModel.getValueAt(selectedRow, 1));
    durationField.setText((String) songTableModel.getValueAt(selectedRow, 2));
    linkField.setText((String) songTableModel.getValueAt(selectedRow, 3));
    JButton updateButton = createStyledButton("Update", new Color(0, 0, 0));
    JButton cancelButton = createStyledButton("Cancel", new Color(0, 0, 0));
    String oldtitle=(String) songTableModel.getValueAt(selectedRow, 0);
    String oldartist=(String) songTableModel.getValueAt(selectedRow, 1);
    String oldduration=(String) songTableModel.getValueAt(selectedRow, 2);
    String oldlink=(String) songTableModel.getValueAt(selectedRow, 3);
    updateButton.addActionListener(new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateSongInTable(selectedRow, titleField.getText(), artistField.getText(), durationField.getText(), linkField.getText());
            updateSongInDatabase(selectedRow, titleField.getText(), artistField.getText(), durationField.getText(), linkField.getText(), oldtitle, oldartist, oldduration, oldlink);
            updateSongDialog.dispose();
        }
    });
    cancelButton.addActionListener(new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateSongDialog.dispose();
        }
    });
    JPanel updateSongPanel = new JPanel(new GridLayout(5, 2));
    updateSongPanel.add(createStyledLabel("Title:"));
    updateSongPanel.add(titleField);
    updateSongPanel.add(createStyledLabel("Artist:"));
    updateSongPanel.add(artistField);
    updateSongPanel.add(createStyledLabel("Duration:"));
    updateSongPanel.add(durationField);
    updateSongPanel.add(createStyledLabel("Link:"));
    updateSongPanel.add(linkField);
    updateSongPanel.add(updateButton);
    updateSongPanel.add(cancelButton);
    updateSongPanel.setBackground(new Color(100, 100, 100));
    updateSongDialog.getContentPane().add(updateSongPanel);
    updateSongDialog.setSize(300, 200);
    updateSongDialog.setLocationRelativeTo(songFrame);
    updateSongDialog.setVisible(true);
}

private static void updateSongInTable(int selectedRow, String title, String artist, String duration, String link) {
    songTableModel.setValueAt(title, selectedRow, 0);
    songTableModel.setValueAt(artist, selectedRow, 1);
    songTableModel.setValueAt(duration, selectedRow, 2);
    songTableModel.setValueAt(link, selectedRow, 3);
}

private static void updateSongInDatabase(int selectedRow, String title, String artist, String duration, String link, String oldtitle, String oldartist, String oldduration, String oldlink) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String query = "UPDATE songs SET title=?, artist=?, duration=?, link=? WHERE title=? AND artist=? AND duration=? AND link=?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, artist);
        ps.setString(3, duration);
        ps.setString(4, link);
        ps.setString(5, oldtitle);  
        ps.setString(6, oldartist); 
        ps.setString(7, oldduration);  
        ps.setString(8, oldlink);  
        int i = ps.executeUpdate();

        if (i > 0) {
            JOptionPane.showMessageDialog(songFrame, "Song has been updated successfully");
        } else {
            JOptionPane.showMessageDialog(songFrame, "Error updating song. Please try again.");
        }

        ps.close();
        con.close();
    } catch (Exception ex) 
    {
        ex.printStackTrace(); 
        JOptionPane.showMessageDialog(songFrame, "Error updating song. Please try again. Check console for details.");
    }
}


//cRud - Read Functionality
private static Object[][] fetchSongDataFromDatabase(String mood) 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT title, artist, duration, link, mood FROM songs WHERE mood=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, mood.toLowerCase());
            ResultSet rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            List<Object[]> dataList = new ArrayList<>();

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                dataList.add(row);
            }

            Object[][] data = new Object[dataList.size()][columnCount];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }

            rs.close();
            ps.close();
            con.close();

            return data;
            
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(songFrame, "Error fetching song data. Please try again.");
            return new Object[0][0];
        }
        
    }
    
//Dialog Box that opens up when Add Song Button is clicked
private static void showAddSongDialog() 
    {

        JDialog addSongDialog = new JDialog(songFrame, "Add Song", true);
        addSongDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JTextField titleField = createStyledTextField();
        JTextField artistField = createStyledTextField();
        JTextField durationField = createStyledTextField();
        JTextField linkField = createStyledTextField();
        
        JButton addButton = createStyledButton("Add", new Color(52, 152, 219));
        JButton cancelButton = createStyledButton("Cancel", new Color(231, 76, 60));

        addButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addSongToTableAndDatabase(titleField.getText(), artistField.getText(), durationField.getText(), linkField.getText(),selectedMood);
                addSongDialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSongDialog.dispose();
            }
        });

        JPanel addSongPanel = new JPanel(new GridLayout(5, 2));
        addSongPanel.add(createStyledLabel("Title:"));
        addSongPanel.add(titleField);
        addSongPanel.add(createStyledLabel("Artist:"));
        addSongPanel.add(artistField);
        addSongPanel.add(createStyledLabel("Duration:"));
        addSongPanel.add(durationField);
        addSongPanel.add(createStyledLabel("Link:"));
        addSongPanel.add(linkField);
        addSongPanel.add(addButton);
        addSongPanel.add(cancelButton);

        addSongPanel.setBackground(new Color(100, 100, 100));;

        addSongDialog.getContentPane().add(addSongPanel);
        addSongDialog.setSize(300, 200);
        addSongDialog.setLocationRelativeTo(songFrame);
        addSongDialog.setVisible(true);
    }
    
//Populate Table Method
    private static void addSongToTableAndDatabase(String title, String artist, String duration, String link,String mood) 
    {
        songTableModel.addRow(new Object[]{title, artist, duration, link,mood});
        addSongToDatabase(title, artist, duration, link,mood);
    }
    
//Play Button Functionality
    private static ActionListener createPlayButtonListener() 
    {
        return new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedRow = songTable.getSelectedRow();
                if (selectedRow != -1) 
                {
                    String songLink = (String) songTableModel.getValueAt(selectedRow, 3);
                    openWebPage(songLink);
                }
            }
        };
    }
    
    private static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
//Remove Button Functionality
    private static ActionListener createRemoveButtonListener() 
    {
        return new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = songTable.getSelectedRow();
                if (selectedRow != -1) 
                {
                    String title = (String) songTableModel.getValueAt(selectedRow, 0);
                    String artist = (String) songTableModel.getValueAt(selectedRow, 1);
                    String duration = (String) songTableModel.getValueAt(selectedRow, 2);
                    String link = (String) songTableModel.getValueAt(selectedRow, 3);

                    songTableModel.removeRow(selectedRow);

                    removeSongFromDatabase(title, artist, duration, link);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(songFrame, "Select a song to remove.");
                }
            }
        };
    }
    
//Delete Songs Functionality - cru'D'
    private static void removeSongFromDatabase(String title, String artist, String duration, String link) 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "DELETE FROM songs WHERE title=? AND artist=? AND duration=? AND link=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, artist);
            ps.setString(3, duration);
            ps.setString(4, link);

            int i = ps.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(songFrame, "Song has been removed successfully");
            } else {
                JOptionPane.showMessageDialog(songFrame, "Error removing song. Please try again.");
            }

            ps.close();
            con.close();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(songFrame, "Error removing song. Please try again.");
        }
    }
    
//Add Songs Functionality - 'C'rud
private static void addSongToDatabase(String title, String artist, String duration, String link, String mood) 
{
    	try 
    	{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "INSERT INTO songs (title, artist, duration, link, mood) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, artist);
            ps.setString(3, duration);
            ps.setString(4, link);
            ps.setString(5, mood);

            int i = ps.executeUpdate();

            if (i > 0) 
            {
                JOptionPane.showMessageDialog(songFrame, "Song has been added successfully");
            }
            else 
            {
                JOptionPane.showMessageDialog(songFrame, "Error adding song. Please try again.");
            }

            ps.close();
            con.close();
    	} 
    	catch (Exception ex) 
    	{
    		ex.printStackTrace();
    		JOptionPane.showMessageDialog(songFrame, "Error adding song. Please try again.");
    	}
    }
}