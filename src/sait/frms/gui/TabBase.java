package sait.frms.gui;

import javax.swing.*;

/**
 * Abstract class for a tab in the JFrame.
 * 
 */
public abstract class TabBase 
{
	/**
	 * Tab panel attribute.
	 */
	protected JPanel panel;
	
	/**
	 * Default constructor.
	 */
	protected TabBase() {
		this.panel = new JPanel();
	}
	
	/**
	 * Gets the panel containing the tab components.
	 * @return JPanel with components.
	 */
	public JPanel getPanel() {
		return this.panel;
	}
}
