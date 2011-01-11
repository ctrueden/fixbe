//
// EditorFrame.java
//

package restless.fixbe.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import restless.fixbe.data.FormatDefinition;
import restless.fixbe.data.ListRule;
import restless.fixbe.data.SaveData;

public class EditorFrame extends JFrame {

	private MenuHandler menuHandler;
	private JPanel mainPanel;
	private ListWidget rulesPanel;
	private JFileChooser defChooser;
	private JFileChooser saveChooser;
	private JLabel statusBar;

	private FormatDefinition def;
	private SaveData saveData;

  public EditorFrame() {
    menuHandler = new MenuHandler(this);
  	buildMenu();
  	buildFrame();
    buildFileChoosers();
    saveData = new SaveData();
  }

	public void loadDef() {
    loadDef(chooseFile(defChooser, false));
	}

  public void loadDef(String path) {
  	if (path == null) return;
  	def = FormatDefinition.load(path);
  	saveData.clear();
  	configureDefChooser();
  	createGUI();
  }

	public void open() {
		open(chooseFile(saveChooser, false));
	}

	public void open(String path) {
		if (path == null) return;
		try {
			saveData.open(path, def.getFileLength());
			rulesPanel.populate(saveData.getData(), 0, 0, 0);
			updateTitle();
		}
		catch (IOException exc) {
			// TODO
			exc.printStackTrace();
		}
	}

	public void save() {
		saveAs(saveData.getActiveSave());
	}

	public void saveAs() {
		saveAs(chooseFile(saveChooser, true));
	}

	public void saveAs(String path) {
		if (path == null) return;
		// TODO
	}

	public void exit() {
		System.exit(0);
	}

	public void about() {
		JOptionPane.showMessageDialog(this,
			"TODO",
			"Save Editor", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setStatus(String status) {
		statusBar.setText(status);
	}

	// -- Helper methods --

	private void buildFrame() {
		final JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		final JScrollPane scrollPane = new JScrollPane(mainPanel);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		buildStatusBar();
		contentPane.add(statusBar, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		placeWindow();
	}

	private void buildStatusBar() {
		statusBar = new JLabel("Save Editor");
		statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
	}

	private void buildMenu() {
		JMenuBar menubar = new JMenuBar();
		menubar.add(buildFileMenu());
		menubar.add(buildHelpMenu());
		setJMenuBar(menubar);
	}

	private void buildFileChoosers() {
		defChooser = new JFileChooser();
		saveChooser = new JFileChooser();
	}

	private void configureDefChooser() {
		final String ext = def.getExtension();
		final String label = def.getName() + " " +
			def.getType() + " (*." + ext + ")";
		saveChooser.setFileFilter(new FileNameExtensionFilter(label, ext));
	}

	private void createGUI() {
		rulesPanel = new ListWidget(new ListRule(def.getRules()), true);
		mainPanel.removeAll();
		mainPanel.add(rulesPanel);
		tweakLabels();
		updateTitle();
		pack();
	}

	private String chooseFile(JFileChooser chooser, boolean save) {
		int rval = save ?
			chooser.showSaveDialog(this) :
			chooser.showOpenDialog(this);
		if (rval != JFileChooser.APPROVE_OPTION) return null;
		final File file = chooser.getSelectedFile();
		return file == null ? null : file.getAbsolutePath();
	}

	private JMenu buildFileMenu() {
		JMenu file = new JMenu("File");
		JMenuItem fileLoadDef = new JMenuItem("Load definition...");
    fileLoadDef.setActionCommand(MenuHandler.CMD_LOAD_DEF);
		fileLoadDef.addActionListener(menuHandler);
		file.add(fileLoadDef);
		file.addSeparator();
		JMenuItem fileOpen = new JMenuItem("Open...");
		fileOpen.setActionCommand(MenuHandler.CMD_OPEN);
		fileOpen.addActionListener(menuHandler);
		file.add(fileOpen);
		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.setActionCommand(MenuHandler.CMD_SAVE);
		fileSave.addActionListener(menuHandler);
		file.add(fileSave);
		JMenuItem fileSaveAs = new JMenuItem("Save as...");
		fileSaveAs.setActionCommand(MenuHandler.CMD_SAVE_AS);
		fileSaveAs.addActionListener(menuHandler);
		file.add(fileSaveAs);
		file.addSeparator();
		JMenuItem fileExit = new JMenuItem("Exit");
		fileExit.setActionCommand(MenuHandler.CMD_EXIT);
		fileExit.addActionListener(menuHandler);
		file.add(fileExit);
		return file;
	}

	private JMenu buildHelpMenu() {
		JMenu help = new JMenu("Help");
		JMenuItem helpAbout = new JMenuItem("About");
		helpAbout.setActionCommand(MenuHandler.CMD_ABOUT);
		helpAbout.addActionListener(menuHandler);
		help.add(helpAbout);
		return help;
	}

	private void placeWindow() {
		final Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		final int maxWidth = 4 * ss.width / 5;
		final int maxHeight = 4 * ss.height / 5;
		final Dimension ws = getSize();
		if (ws.width > maxWidth) ws.width = maxWidth;
		if (ws.height > maxHeight) ws.height = maxHeight;
		int x = 2 * (ss.width - ws.width) / 7;
		int y = (ss.height - ws.height) / 10;
		setBounds(new Rectangle(x, y, ws.width, ws.height));
	}

	/**
	 * A hack to set all labels to the same size. Makes the layout look nicer
	 * without needing to use the dreaded GridBagLayout or third party manager.
	 */
	private void tweakLabels() {
		final int maxWidth = findMaxLabelWidth(mainPanel);
		setLabelWidth(mainPanel, maxWidth);
	}

	private int findMaxLabelWidth(Container container) {
		final Component[] c = container.getComponents();
		int maxWidth = 0;
		for (int i = 0; i < c.length; i++) {
			final int value;
			if (c[i] instanceof JLabel) {
				value = c[i].getPreferredSize().width;
			}
			else if (c[i] instanceof Container) {
				value = findMaxLabelWidth((Container) c[i]);
			}
			else value = 0;
			if (value > maxWidth) maxWidth = value;
		}
		return maxWidth;
	}

	private void setLabelWidth(Container container, int width) {
		final Component[] c = container.getComponents();
		for (int i = 0; i < c.length; i++) {
			if (c[i] instanceof JLabel) {
				final int height = c[i].getPreferredSize().height;
				c[i].setPreferredSize(new Dimension(width, height));
			}
			else if (c[i] instanceof Container) {
				setLabelWidth((Container) c[i], width);
			}
		}
	}

	private void updateTitle() {
		StringBuilder sb = new StringBuilder();
		sb.append("FixBE");
		if (def != null) sb.append(" - " + def.getName());
		if (saveData != null) sb.append(" - " + saveData.getActiveSave());
		setTitle(sb.toString());
	}

}
