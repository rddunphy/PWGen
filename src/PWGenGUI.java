import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PWGenGUI {

	// Static fields
	public static final String NAME = "PWGen";
	public static final String VERSION = "1.0";
	public static final String AUTHOR = "David Dunphy";
	public static final String YEAR = "2015";
	public static final String NL = "\n";

	// Private fields
	private JFrame frame;
	private KeyGenerator gen;
	private JSpinner minField;
	private JSpinner maxField;
	private JCheckBox startCB;
	private JCheckBox lowerCB;
	private JCheckBox upperCB;
	private JCheckBox numCB;
	private JCheckBox specialCB;
	private JTextField pwField;
	private JRadioButtonMenuItem customRadio;

	/**
	 * Create a new PWGen GUI
	 */
	public PWGenGUI() {
		gen = new KeyGenerator();
		makeFrame();
	}

	private void quit() {
		gen.saveState();
		System.exit(0);
	}

	private void setInputs() {
		checkInputs();
		gen.setMinLength((Integer) minField.getValue());
		gen.setMaxLength((Integer) maxField.getValue());
		gen.setStartAlpha(startCB.isSelected());
		gen.setLower(lowerCB.isSelected());
		gen.setUpper(upperCB.isSelected());
		gen.setNumeric(numCB.isSelected());
		gen.setSpecial(specialCB.isSelected());
	}

	private void checkInputs() {
		int absMin = 0;
		if (lowerCB.isSelected()) {
			absMin++;
		}
		if (upperCB.isSelected()) {
			absMin++;
		}
		if (numCB.isSelected()) {
			absMin++;
		}
		if (specialCB.isSelected()) {
			absMin++;
		}
		int min = (Integer) minField.getValue();
		int max = (Integer) maxField.getValue();
		if (min < absMin) {
			minField.setValue(absMin);
		}
		if (min > max) {
			maxField.setValue(min);
		}
		if (!lowerCB.isSelected() && !upperCB.isSelected()
				&& !numCB.isSelected() && !specialCB.isSelected()) {
			lowerCB.setSelected(true);
		}
		if (!lowerCB.isSelected() && !upperCB.isSelected()) {
			startCB.setEnabled(false);
		} else if (!numCB.isSelected() && !specialCB.isSelected()) {
			startCB.setEnabled(false);
		} else {
			startCB.setEnabled(true);
		}
	}
	
	private void setType(int type) {
		gen.setDefaultType(type);
		reset();
	}

	private void reset() {
		gen.setToDefault();
		int min = gen.getMinLength();
		int max = gen.getMaxLength();
		boolean start = gen.isStartAlpha();
		boolean lower = gen.isLower();
		boolean upper = gen.isUpper();
		boolean num = gen.isNumeric();
		boolean special = gen.isSpecial();
		minField.setValue(min);
		maxField.setValue(max);
		startCB.setSelected(start);
		lowerCB.setSelected(lower);
		upperCB.setSelected(upper);
		numCB.setSelected(num);
		specialCB.setSelected(special);
		setInputs();
	}

	private void generate() {
		pwField.setText(gen.generate());
	}

	private void copy() {
		String pw = pwField.getText();
		StringSelection strsel = new StringSelection(pw);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(strsel, null);
	}

	private void setSpecial() {
		String special = (String) JOptionPane.showInputDialog(frame,
				"You can change which special characters to use here:",
				"Set special characters", JOptionPane.PLAIN_MESSAGE, null,
				null, gen.getSpecialChars());
		if (special == null || special.length() == 0) {
			return;
		}
		String spaceRemoved = special.replaceAll("\\s", "");
		if (special != spaceRemoved) {
			int remove = JOptionPane
					.showConfirmDialog(
							frame,
							"Your input contains whitespace characters.\nWould you like to remove whitespace?",
							"Whitespace detected",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE);
			if (remove == JOptionPane.CANCEL_OPTION) {
				return;
			}
			if (remove == JOptionPane.YES_OPTION) {
				special = spaceRemoved;
			}
		}
		gen.setSpecialChars(special);
	}
	
	private void saveAsCustom() {
		gen.setAsCustom();
		customRadio.setSelected(true);
	}

	private void about() {
		String text = NAME + " version " + VERSION + NL;
		text += "Copyright © " + YEAR + " " + AUTHOR;
		String title = "About " + NAME;
		JOptionPane.showMessageDialog(frame, text, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void makeFrame() {
		frame = new JFrame(NAME);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel label = new JLabel("Min. length:");
		c.insets = new Insets(5, 5, 0, 5);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		contentPane.add(label, c);

		SpinnerNumberModel model = new SpinnerNumberModel(
				gen.getMinLength(), 1, 25, 1);
		minField = new JSpinner(model);
		minField.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setInputs();
			}
		});
		c.gridx = 1;
		contentPane.add(minField, c);

		label = new JLabel("Max. length:");
		c.gridx = 2;
		contentPane.add(label, c);

		model = new SpinnerNumberModel(gen.getMaxLength(), 1, 25, 1);
		maxField = new JSpinner(model);
		maxField.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setInputs();
			}
		});
		c.gridx = 3;
		contentPane.add(maxField, c);

		startCB = new JCheckBox("Start with letter");
		startCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInputs();
			}
		});
		startCB.setSelected(gen.isStartAlpha());
		c.insets = new Insets(5, 5, 8, 5);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		contentPane.add(startCB, c);

		label = new JLabel("Included character sets:");
		c.insets = new Insets(5, 5, 0, 5);
		c.gridy = 2;
		contentPane.add(label, c);

		lowerCB = new JCheckBox("Lower case");
		lowerCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInputs();
			}
		});
		lowerCB.setSelected(gen.isLower());
		c.gridy = 3;
		c.gridwidth = 2;
		contentPane.add(lowerCB, c);

		upperCB = new JCheckBox("Upper case");
		upperCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInputs();
			}
		});
		upperCB.setSelected(gen.isUpper());
		c.insets = new Insets(5, 5, 8, 5);
		c.gridy = 4;
		contentPane.add(upperCB, c);

		numCB = new JCheckBox("Numeric");
		numCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInputs();
			}
		});
		numCB.setSelected(gen.isNumeric());
		c.insets = new Insets(5, 5, 0, 5);
		c.gridx = 2;
		c.gridy = 3;
		contentPane.add(numCB, c);

		specialCB = new JCheckBox("Special");
		specialCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInputs();
			}
		});
		specialCB.setSelected(gen.isSpecial());
		c.insets = new Insets(5, 5, 8, 5);
		c.gridy = 4;
		contentPane.add(specialCB, c);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout(5, 0));

		JButton button = new JButton("Reset");
		button.setMnemonic('R');
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		buttonPanel.add(button, BorderLayout.CENTER);

		JButton genButton = new JButton("Generate");
		genButton.setMnemonic('G');
		genButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate();
			}
		});
		buttonPanel.add(genButton, BorderLayout.EAST);

		c.anchor = GridBagConstraints.EAST;
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 4;
		contentPane.add(buttonPanel, c);

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout(5, 0));

		pwField = new JTextField();
		pwField.setEditable(false);
		outputPanel.add(pwField, BorderLayout.CENTER);

		button = new JButton("Copy");
		button.setMnemonic('C');
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
		outputPanel.add(button, BorderLayout.EAST);

		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridy = 6;
		contentPane.add(outputPanel, c);

		setInputs();
		generate();

		frame.setContentPane(contentPane);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pwicon.png"));
		frame.setJMenuBar(makeMenuBar());
		// On frame close, exit application:
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				quit();
			}
		});
		frame.getRootPane().setDefaultButton(genButton);
		genButton.requestFocus();
		frame.pack();
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
				/ 2 - frame.getSize().height / 2);
		frame.setVisible(true);
	}

	private JMenuBar makeMenuBar() {
		JMenuBar mb = new JMenuBar();

		JMenu menu = new JMenu("Tools");
		menu.setMnemonic('T');
		
		JMenu submenu = new JMenu("Type");
		submenu.setMnemonic('T');
		
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem radio = new JRadioButtonMenuItem("Password");
		radio.setMnemonic('P');
		radio.setAccelerator(KeyStroke.getKeyStroke("ctrl 1"));
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setType(KeyGenerator.PASSWORD);
			}
		});
		if (gen.getDefaultType() == KeyGenerator.PASSWORD) {
			radio.setSelected(true);
		}
		group.add(radio);
		submenu.add(radio);
		
		radio = new JRadioButtonMenuItem("Simple");
		radio.setMnemonic('M');
		radio.setAccelerator(KeyStroke.getKeyStroke("ctrl 2"));
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setType(KeyGenerator.SIMPLE);
			}
		});
		if (gen.getDefaultType() == KeyGenerator.SIMPLE) {
			radio.setSelected(true);
		}
		group.add(radio);
		submenu.add(radio);
		
		radio = new JRadioButtonMenuItem("Secure");
		radio.setMnemonic('S');
		radio.setAccelerator(KeyStroke.getKeyStroke("ctrl 3"));
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setType(KeyGenerator.SECURE);
			}
		});
		if (gen.getDefaultType() == KeyGenerator.SECURE) {
			radio.setSelected(true);
		}
		group.add(radio);
		submenu.add(radio);

		radio = new JRadioButtonMenuItem("Extra secure");
		radio.setMnemonic('X');
		radio.setAccelerator(KeyStroke.getKeyStroke("ctrl 4"));
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setType(KeyGenerator.EXTRA);
			}
		});
		if (gen.getDefaultType() == KeyGenerator.EXTRA) {
			radio.setSelected(true);
		}
		group.add(radio);
		submenu.add(radio);
		
		radio = new JRadioButtonMenuItem("PIN");
		radio.setMnemonic('N');
		radio.setAccelerator(KeyStroke.getKeyStroke("ctrl 5"));
		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setType(KeyGenerator.PIN);
			}
		});
		if (gen.getDefaultType() == KeyGenerator.PIN) {
			radio.setSelected(true);
		}
		group.add(radio);
		submenu.add(radio);

		customRadio = new JRadioButtonMenuItem("Custom");
		customRadio.setMnemonic('C');
		customRadio.setAccelerator(KeyStroke.getKeyStroke("ctrl 6"));
		customRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setType(KeyGenerator.CUSTOM);
			}
		});
		if (gen.getDefaultType() == KeyGenerator.CUSTOM) {
			customRadio.setSelected(true);
		}
		group.add(customRadio);
		submenu.add(customRadio);
		
		menu.add(submenu);

		JMenuItem item = new JMenuItem("Generate key");
		item.setMnemonic('G');
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate();
			}
		});
		menu.add(item);

		item = new JMenuItem("Reset parameters");
		item.setMnemonic('R');
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		menu.add(item);

		item = new JMenuItem("Copy key");
		item.setMnemonic('C');
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
		menu.add(item);

		item = new JMenuItem("Set special characters...");
		item.setMnemonic('E');
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSpecial();
			}
		});
		menu.add(item);
		
		item = new JMenuItem("Save as custom");
		item.setMnemonic('S');
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAsCustom();
			}
		});
		menu.add(item);

		item = new JMenuItem("Quit");
		item.setMnemonic('Q');
		item.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		menu.add(item);

		mb.add(menu);

		menu = new JMenu("?");

		item = new JMenuItem("About");
		item.setMnemonic('A');
		item.setAccelerator(KeyStroke.getKeyStroke("F1"));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		menu.add(item);

		mb.add(menu);

		return mb;
	}
}
