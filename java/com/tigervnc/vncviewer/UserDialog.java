/* Copyright (C) 2017 Brian P. Hinz
 *
 * This is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,
 * USA.
 */

package com.tigervnc.vncviewer;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.LayerUI;

import com.tigervnc.rfb.*;
import java.awt.Point;
import com.tigervnc.rfb.Exception;

import static com.tigervnc.vncviewer.Parameters.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.JOptionPane.*;

public class UserDialog implements UserPasswdGetter, UserMsgBox
{
  static URL info_icon = UserDialog.class.getResource("info-circle.png");
  static URL secure_icon = UserDialog.class.getResource("secure.png");
  static URL insecure_icon = UserDialog.class.getResource("insecure.png");
  static URL warning_icon = UserDialog.class.getResource("secure-warning.png");

  private class MyLayerUI extends LayerUI {
    // Using a JButton for the "?" icon yields the best look, but there
    // does not seem to be any reasonable way to disable a JButton without
    // also changing the color.  This wrapper just intercepts any mouse
    // click events so that the button just looks like an icon.
    @Override
    public void eventDispatched(AWTEvent e, JLayer l) {
      if (e instanceof InputEvent)
        ((InputEvent) e).consume();
    }

    @Override
    public void installUI(JComponent c) {
      super.installUI(c);
      if (c instanceof JLayer) {
        JLayer<?> layer = (JLayer<?>)c;
        layer.setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK);
      }
    }

    @Override
    protected void processMouseEvent(MouseEvent e, JLayer l) {
      super.processMouseEvent(e, l);
    }
  }

  public final void getUserPasswd(boolean secure,
                                  String[] warnings,
                                  StringBuffer user,
                                  StringBuffer password)
  {
    String passwordFileStr = passwordFile.getValue();

    if ((password == null) && sendLocalUsername.getValue()) {
      user.append((String)System.getProperties().get("user.name"));
      return;
    }

    if (user == null && !passwordFileStr.equals("")) {
      InputStream fp = null;
      try {
        fp = new FileInputStream(passwordFileStr);
      } catch(FileNotFoundException e) {
        throw new Exception("Opening password file failed");
      }
      byte[] obfPwd = new byte[256];
      try {
        fp.read(obfPwd);
        fp.close();
      } catch(IOException e) {
        throw new Exception("Failed to read VncPasswd file");
      }
      String PlainPasswd = VncAuth.unobfuscatePasswd(obfPwd);
      password.append(PlainPasswd);
      password.setLength(PlainPasswd.length());
      return;
    }

    JDialog win;
    Box banner;
    JTextField username = null;
    JPasswordField passwd = null;
    JLayer icon;

    int y;

    JPanel msg = new JPanel(null);
    msg.setSize(410, 145);

    banner = Box.createHorizontalBox();
    banner.setBackground(Color.WHITE);
    banner.setOpaque(true);
    banner.setBounds(0, 0, msg.getPreferredSize().width, 20);

    ToolTipManager.sharedInstance().setEnabled(false);
    JPanel info = new JPanel() {
      @Override
      public JToolTip createToolTip() {
        JToolTip tip = super.createToolTip();
        tip.setBorder(new EmptyBorder(15, 5, 5, 5) {
          @Override
          public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            super.paintBorder(c, g, x, y, w, h);
            g.setColor((Color)UIManager.getDefaults().get("Panel.background"));
            g.fillRect(x, y, w, 10);
            g.setColor(Color.WHITE);
            g.fillPolygon(new int[] {5, 15, 25}, new int[] {10, 0, 10}, 3);
          }
        });
        UIDefaults overrides = UIManager.getDefaults();
        overrides.put("info", Color.WHITE);
        tip.putClientProperty("Nimbus.Overrides", overrides);
        return tip;
      }
      @Override
      public Point getToolTipLocation(MouseEvent e) {
        return new Point(0, 20);
      }
      @Override
      protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
          ToolTipManager ttm = ToolTipManager.sharedInstance();
          ttm.setInitialDelay(0);
          ttm.setDismissDelay((int)1e9);
          ttm.setEnabled(!ttm.isEnabled());
          ttm.mouseMoved(e);
          requestFocusInWindow();
        }
      }
    };
    info.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        ToolTipManager.sharedInstance().setEnabled(false);
      }
    });

    info.setBackground(Color.WHITE);
    info.setPreferredSize(new Dimension(47, 20));
    info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));
    info.add(Box.createHorizontalStrut(5));
    info.add(new JLabel(new ImageIcon(info_icon)));
    info.add(Box.createHorizontalStrut(2));

    String tmpl = 
      new String("<html><font color=%s size=5>%s<pre>%s</pre></html>");
    String warn = new String();
    for (int i=0; i < warnings.length; i++)
      warn += warnings[i]+"\n";
    if (secure) {
      info.setToolTipText(String.format(tmpl, "#058B00", "Connection is secure", warn));
      if (warnings.length == 0)
        info.add(new JLabel(new ImageIcon(secure_icon)));
      else
        info.add(new JLabel(new ImageIcon(warning_icon)));
    } else {
      info.setToolTipText(String.format(tmpl, "#d74345", "Connection is not secure", warn));
      info.add(new JLabel(new ImageIcon(insecure_icon)));
    }
    banner.add(info);
    banner.add(Box.createHorizontalStrut(2));
    JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    // prevent BoxLayout from stretching the width of the JSeparator
    separator.setMaximumSize(new Dimension(1, Integer.MAX_VALUE));
    banner.add(separator);
    banner.add(Box.createHorizontalStrut(3));
    banner.add(new JLabel(VncViewer.cc.getServerName(), JLabel.LEFT));
    banner.add(Box.createHorizontalGlue());

    msg.add(banner);

    y = 20 + 10;

    JButton iconb = new JButton("?");
    iconb.setVerticalAlignment(JLabel.CENTER);
    iconb.setFont(new Font("Times", Font.BOLD, 34));
    iconb.setForeground(Color.BLUE);
    LayerUI ui = new MyLayerUI();
    icon = new JLayer(iconb, ui);
    icon.setBounds(10, y, 50, 50);
    msg.add(icon);

    y += 5;

    if (user != null && !sendLocalUsername.getValue()) {
      JLabel userLabel = new JLabel("Username:");
      userLabel.setBounds(70, y, msg.getSize().width-70-10, 20);
      msg.add(userLabel);
      y += 20 + 5;
      username = new JTextField(30);
      username.setBounds(70, y, msg.getSize().width-70-10, 25);
      msg.add(username);
      y += 25 + 5;
    }

    JLabel passwdLabel = new JLabel("Password:");
    passwdLabel.setBounds(70, y, msg.getSize().width-70-10, 20);
    msg.add(passwdLabel);
    y += 20 + 5;
    passwd = new JPasswordField(30);
    passwd.setBounds(70, y, msg.getSize().width-70-10, 25);
    msg.add(passwd);
    y += 25 + 5;

    msg.setPreferredSize(new Dimension(410, y));

    Object[] options = {"OK  \u21B5", "Cancel"};
    JOptionPane pane = new JOptionPane(msg,
                                      PLAIN_MESSAGE,
                                      OK_CANCEL_OPTION,
                                      null,       //do not use a custom Icon
                                      options,    //the titles of buttons
                                      options[0]);//default button title
    pane.setBorder(new EmptyBorder(0,0,0,0));
    Component c = pane.getComponent(pane.getComponentCount()-1);
    ((JComponent)c).setBorder(new EmptyBorder(0,0,10,10));
    win = pane.createDialog("VNC Authentication");
    win.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        ToolTipManager.sharedInstance().setEnabled(false);
      }
    });
    // don't display the default java window icon
    win.setIconImage(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE));

    win.setVisible(true);

    if (pane.getValue() == null || pane.getValue().equals("Cancel"))
      throw new Exception("Authentication cancelled");

    if (user != null)
      if (sendLocalUsername.getValue())
         user.append((String)System.getProperties().get("user.name"));
      else
         user.append(username.getText());
    if (password != null)
      password.append(new String(passwd.getPassword()));
  }

  public boolean showMsgBox(int flags, String title, String text)
  {
    // don't display the default java window icon
    switch (flags & 0xf) {
    case OK_CANCEL_OPTION:
      return (showConfirmDialog(null, text, title, OK_CANCEL_OPTION) == OK_OPTION);
    case YES_NO_OPTION:
      return (showConfirmDialog(null, text, title, YES_NO_OPTION) == YES_OPTION);
    default:
      if (((flags & 0xf0) == ERROR_MESSAGE) ||
          ((flags & 0xf0) == WARNING_MESSAGE))
        showMessageDialog(null, text, title, (flags & 0xf0));
      else
        showMessageDialog(null, text, title, PLAIN_MESSAGE);
      return true;
    }
  }
}
