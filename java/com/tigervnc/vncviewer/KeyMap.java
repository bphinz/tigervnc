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
import java.util.*;
import javax.swing.*;

import com.tigervnc.rfb.*;

import static java.awt.event.KeyEvent.*;
import static com.tigervnc.rfb.Keysymdef.*;

public class KeyMap {

  public final static int NoSymbol = 0;

  public static final HashMap<Integer, Integer[]> code_map_java_to_qnum;
  static {
    /*                        KEYCODE                                    LOCATION                   */
    /*                                                       UNKNOWN  STANDARD  LEFT  RIGHT  NUMPAD */
    code_map_java_to_qnum = new HashMap<Integer, Integer[]>();
    code_map_java_to_qnum.put(VK_ENTER,                     new Integer[]{0x00,    0x1c,     0x00, 0x00,  0x9c});
    code_map_java_to_qnum.put(VK_BACK_SPACE,                new Integer[]{0x00,    0x0e,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_TAB,                       new Integer[]{0x00,    0x0f,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CANCEL,                    new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CLEAR,                     new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_SHIFT,                     new Integer[]{0x00,    0x00,     0x2a, 0x36,  0x00});
    code_map_java_to_qnum.put(VK_CONTROL,                   new Integer[]{0x00,    0x00,     0x1d, 0x9d,  0x00});
    code_map_java_to_qnum.put(VK_ALT,                       new Integer[]{0x00,    0x00,     0x38, 0xb8,  0x00});
    code_map_java_to_qnum.put(VK_PAUSE,                     new Integer[]{0x00,    0xc6,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CAPS_LOCK,                 new Integer[]{0x00,    0x3a,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_ESCAPE,                    new Integer[]{0x00,    0x01,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_SPACE,                     new Integer[]{0x00,    0x39,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PAGE_UP,                   new Integer[]{0x00,    0xc9,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PAGE_DOWN,                 new Integer[]{0x00,    0xd1,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_END,                       new Integer[]{0x00,    0xcf,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_HOME,                      new Integer[]{0x00,    0xc7,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_LEFT,                      new Integer[]{0x00,    0xcb,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_UP,                        new Integer[]{0x00,    0xc8,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_RIGHT,                     new Integer[]{0x00,    0xcd,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DOWN,                      new Integer[]{0x00,    0xd0,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_COMMA,                     new Integer[]{0x00,    0x33,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_MINUS,                     new Integer[]{0x00,    0x0c,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PERIOD,                    new Integer[]{0x00,    0x34,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_SLASH,                     new Integer[]{0x00,    0x35,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_0,                         new Integer[]{0x00,    0x0b,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_1,                         new Integer[]{0x00,    0x02,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_2,                         new Integer[]{0x00,    0x03,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_3,                         new Integer[]{0x00,    0x04,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_4,                         new Integer[]{0x00,    0x05,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_5,                         new Integer[]{0x00,    0x06,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_6,                         new Integer[]{0x00,    0x07,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_7,                         new Integer[]{0x00,    0x08,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_8,                         new Integer[]{0x00,    0x09,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_9,                         new Integer[]{0x00,    0x0a,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_SEMICOLON,                 new Integer[]{0x00,    0x27,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_EQUALS,                    new Integer[]{0x00,    0x0d,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_A,                         new Integer[]{0x00,    0x1e,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_B,                         new Integer[]{0x00,    0x30,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_C,                         new Integer[]{0x00,    0x2e,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_D,                         new Integer[]{0x00,    0x2d,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_E,                         new Integer[]{0x00,    0x12,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F,                         new Integer[]{0x00,    0x21,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_G,                         new Integer[]{0x00,    0x22,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_H,                         new Integer[]{0x00,    0x23,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_I,                         new Integer[]{0x00,    0x17,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_J,                         new Integer[]{0x00,    0x24,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_K,                         new Integer[]{0x00,    0x25,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_L,                         new Integer[]{0x00,    0x26,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_M,                         new Integer[]{0x00,    0x32,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_N,                         new Integer[]{0x00,    0x31,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_O,                         new Integer[]{0x00,    0x18,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_P,                         new Integer[]{0x00,    0x19,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_Q,                         new Integer[]{0x00,    0x10,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_R,                         new Integer[]{0x00,    0x13,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_S,                         new Integer[]{0x00,    0x1f,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_T,                         new Integer[]{0x00,    0x14,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_U,                         new Integer[]{0x00,    0x16,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_V,                         new Integer[]{0x00,    0x2f,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_W,                         new Integer[]{0x00,    0x11,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_X,                         new Integer[]{0x00,    0x2d,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_Y,                         new Integer[]{0x00,    0x15,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_Z,                         new Integer[]{0x00,    0x2c,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_OPEN_BRACKET,              new Integer[]{0x00,    0x1a,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_BACK_SLASH,                new Integer[]{0x00,    0x2b,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CLOSE_BRACKET,             new Integer[]{0x00,    0x1b,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_NUMPAD0,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x52});
    code_map_java_to_qnum.put(VK_NUMPAD1,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x4f});
    code_map_java_to_qnum.put(VK_NUMPAD2,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x50});
    code_map_java_to_qnum.put(VK_NUMPAD3,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x51});
    code_map_java_to_qnum.put(VK_NUMPAD4,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x4b});
    code_map_java_to_qnum.put(VK_NUMPAD5,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x4c});
    code_map_java_to_qnum.put(VK_NUMPAD6,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x4d});
    code_map_java_to_qnum.put(VK_NUMPAD7,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x47});
    code_map_java_to_qnum.put(VK_NUMPAD8,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x48});
    code_map_java_to_qnum.put(VK_NUMPAD9,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x49});
    code_map_java_to_qnum.put(VK_MULTIPLY,                  new Integer[]{0x00,    0x00,     0x00, 0x00,  0x37});
    code_map_java_to_qnum.put(VK_ADD,                       new Integer[]{0x00,    0x00,     0x00, 0x00,  0x4e});
    code_map_java_to_qnum.put(VK_SEPARATER,                 new Integer[]{0x00,    0x53,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_SEPARATOR,                 new Integer[]{0x00,    0x53,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_SUBTRACT,                  new Integer[]{0x00,    0x00,     0x00, 0x00,  0x4a});
    code_map_java_to_qnum.put(VK_DECIMAL,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x53});
    code_map_java_to_qnum.put(VK_DIVIDE,                    new Integer[]{0x00,    0x00,     0x00, 0x00,  0xb5});
    code_map_java_to_qnum.put(VK_DELETE,                    new Integer[]{0x00,    0xd3,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_NUM_LOCK,                  new Integer[]{0x00,    0x45,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_SCROLL_LOCK,               new Integer[]{0x00,    0x46,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F1,                        new Integer[]{0x00,    0x3b,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F2,                        new Integer[]{0x00,    0x3c,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F3,                        new Integer[]{0x00,    0x3d,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F4,                        new Integer[]{0x00,    0x3e,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F5,                        new Integer[]{0x00,    0x3f,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F6,                        new Integer[]{0x00,    0x40,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F7,                        new Integer[]{0x00,    0x41,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F8,                        new Integer[]{0x00,    0x42,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F9,                        new Integer[]{0x00,    0x43,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F10,                       new Integer[]{0x00,    0x44,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F11,                       new Integer[]{0x00,    0x57,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F12,                       new Integer[]{0x00,    0x58,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F13,                       new Integer[]{0x00,    0x5d,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F14,                       new Integer[]{0x00,    0x5e,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F15,                       new Integer[]{0x00,    0x5f,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F16,                       new Integer[]{0x00,    0x55,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F17,                       new Integer[]{0x00,    0x83,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F18,                       new Integer[]{0x00,    0xf7,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F19,                       new Integer[]{0x00,    0x84,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F20,                       new Integer[]{0x00,    0x5a,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F21,                       new Integer[]{0x00,    0x74,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F22,                       new Integer[]{0x00,    0xf9,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F23,                       new Integer[]{0x00,    0x6d,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_F24,                       new Integer[]{0x00,    0x6f,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PRINTSCREEN,               new Integer[]{0x00,    0x54,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_INSERT,                    new Integer[]{0x00,    0xd2,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_HELP,                      new Integer[]{0x00,    0xf5,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_META,                      new Integer[]{0x00,    0x00,     0xdb, 0xdc,  0x00});
    code_map_java_to_qnum.put(VK_BACK_QUOTE,                new Integer[]{0x00,    0x29,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_QUOTE,                     new Integer[]{0x00,    0x28,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KP_UP,                     new Integer[]{0x00,    0xc8,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KP_DOWN,                   new Integer[]{0x00,    0xd0,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KP_LEFT,                   new Integer[]{0x00,    0xcb,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KP_RIGHT,                  new Integer[]{0x00,    0xcd,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_GRAVE,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_ACUTE,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_CIRCUMFLEX,           new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_TILDE,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_MACRON,               new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_BREVE,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_ABOVEDOT,             new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_DIAERESIS,            new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_ABOVERING,            new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_DOUBLEACUTE,          new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_CARON,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_CEDILLA,              new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_OGONEK,               new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_IOTA,                 new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_VOICED_SOUND,         new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DEAD_SEMIVOICED_SOUND,     new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_AMPERSAND,                 new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_ASTERISK,                  new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_QUOTEDBL,                  new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_LESS,                      new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_GREATER,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_BRACELEFT,                 new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_BRACERIGHT,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_AT,                        new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_COLON,                     new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CIRCUMFLEX,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_DOLLAR,                    new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_EURO_SIGN,                 new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_EXCLAMATION_MARK,          new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_INVERTED_EXCLAMATION_MARK, new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_LEFT_PARENTHESIS,          new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_NUMBER_SIGN,               new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PLUS,                      new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_RIGHT_PARENTHESIS,         new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_UNDERSCORE,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_WINDOWS,                   new Integer[]{0x00,    0x00,     0xdb, 0xdc,  0x00});
    code_map_java_to_qnum.put(VK_CONTEXT_MENU,              new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_FINAL,                     new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CONVERT,                   new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_NONCONVERT,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_ACCEPT,                    new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_MODECHANGE,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KANA,                      new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KANJI,                     new Integer[]{0x00,    0x79,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_ALPHANUMERIC,              new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KATAKANA,                  new Integer[]{0x00,    0x70,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_HIRAGANA,                  new Integer[]{0x00,    0x7b,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_FULL_WIDTH,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_HALF_WIDTH,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_ROMAN_CHARACTERS,          new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_ALL_CANDIDATES,            new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PREVIOUS_CANDIDATE,        new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CODE_INPUT,                new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_JAPANESE_KATAKANA,         new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_JAPANESE_HIRAGANA,         new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_JAPANESE_ROMAN,            new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_KANA_LOCK,                 new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_INPUT_METHOD_ON_OFF,       new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_CUT,                       new Integer[]{0x00,    0xbc,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_COPY,                      new Integer[]{0x00,    0xf8,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PASTE,                     new Integer[]{0x00,    0x65,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_UNDO,                      new Integer[]{0x00,    0x87,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_AGAIN,                     new Integer[]{0x00,    0x85,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_FIND,                      new Integer[]{0x00,    0xc1,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_PROPS,                     new Integer[]{0x00,    0x86,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_STOP,                      new Integer[]{0x00,    0xe8,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_COMPOSE,                   new Integer[]{0x00,    0xdd,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_ALT_GRAPH,                 new Integer[]{0x00,    0xb8,     0x00, 0x00,  0x00});
    code_map_java_to_qnum.put(VK_BEGIN,                     new Integer[]{0x00,    0x00,     0x00, 0x00,  0x00});
  }

  private static int[][] vkey_map = {
    /* KEYCODE                                                LOCATION                                      */
    /*                          UNKNOWN       STANDARD                  LEFT          RIGHT         NUMPAD  */
    { VK_BACK_SPACE,            NoSymbol,     XK_BackSpace,             NoSymbol,     NoSymbol,     NoSymbol },
    { VK_TAB,                   NoSymbol,     XK_Tab,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_CANCEL,                NoSymbol,     XK_Cancel,                NoSymbol,     NoSymbol,     NoSymbol },
    { VK_ENTER,                 NoSymbol,     XK_Return,                NoSymbol,     NoSymbol,     XK_KP_Enter },
    { VK_SHIFT,                 NoSymbol,     XK_Shift_L,               XK_Shift_L,   XK_Shift_R,   NoSymbol },
    { VK_CONTROL,               NoSymbol,     XK_Control_L,             XK_Control_L, XK_Control_R, NoSymbol },
    { VK_ALT,                   NoSymbol,     XK_Alt_L,                 XK_Alt_L,     XK_Alt_R,     NoSymbol },
    /* VK_PAUSE left out on purpose because interpretation depends on state of CTRL. See further down. */
    { VK_CAPS_LOCK,             NoSymbol,     XK_Caps_Lock,             NoSymbol,     NoSymbol,     NoSymbol },
    { VK_ESCAPE,                NoSymbol,     XK_Escape,                NoSymbol,     NoSymbol,     NoSymbol },
    { VK_END,                   NoSymbol,     XK_End,                   NoSymbol,     NoSymbol,     XK_KP_End },
    { VK_HOME,                  NoSymbol,     XK_Home,                  NoSymbol,     NoSymbol,     XK_KP_Home },
    { VK_LEFT,                  NoSymbol,     XK_Left,                  NoSymbol,     NoSymbol,     XK_KP_Left },
    { VK_UP,                    NoSymbol,     XK_Up,                    NoSymbol,     NoSymbol,     XK_KP_Up },
    { VK_RIGHT,                 NoSymbol,     XK_Right,                 NoSymbol,     NoSymbol,     XK_KP_Right },
    { VK_DOWN,                  NoSymbol,     XK_Down,                  NoSymbol,     NoSymbol,     XK_KP_Down },
    /* VK_PRINTSCREEN left out on purpose because interpretation depends on state of CTRL. See further down. */
    { VK_PAGE_UP,               NoSymbol,     XK_Page_Up,               NoSymbol,     NoSymbol,     XK_KP_Page_Up },
    { VK_PAGE_DOWN,             NoSymbol,     XK_Page_Down,             NoSymbol,     NoSymbol,     XK_KP_Page_Down },
    { VK_BEGIN,                 NoSymbol,     XK_Begin,                 NoSymbol,     NoSymbol,     XK_KP_Begin },
    { VK_KP_LEFT,               NoSymbol,     XK_KP_Left,               NoSymbol,     NoSymbol,     XK_KP_Left },
    { VK_KP_UP,                 NoSymbol,     XK_KP_Up,                 NoSymbol,     NoSymbol,     XK_KP_Up },
    { VK_KP_RIGHT,              NoSymbol,     XK_KP_Right,              NoSymbol,     NoSymbol,     XK_KP_Right },
    { VK_KP_DOWN,               NoSymbol,     XK_KP_Down,               NoSymbol,     NoSymbol,     XK_KP_Down },
    { VK_INSERT,                NoSymbol,     XK_Insert,                NoSymbol,     NoSymbol,     XK_KP_Insert },
    { VK_DELETE,                NoSymbol,     XK_Delete,                NoSymbol,     NoSymbol,     XK_KP_Delete },
    { VK_WINDOWS,               NoSymbol,     NoSymbol,                 XK_Super_L,   XK_Super_R,   NoSymbol },
    { VK_CONTEXT_MENU,          NoSymbol,     XK_Menu,                  NoSymbol,     NoSymbol,     NoSymbol },
    { VK_NUMPAD0,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_0 },
    { VK_NUMPAD1,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_1 },
    { VK_NUMPAD2,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_2 },
    { VK_NUMPAD3,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_3 },
    { VK_NUMPAD4,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_4 },
    { VK_NUMPAD5,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_5 },
    { VK_NUMPAD6,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_6 },
    { VK_NUMPAD7,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_7 },
    { VK_NUMPAD8,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_8 },
    { VK_NUMPAD9,               NoSymbol,     NoSymbol,                 NoSymbol,     NoSymbol,     XK_KP_9 },
    { VK_MULTIPLY,              NoSymbol,     XK_KP_Multiply,           NoSymbol,     NoSymbol,     XK_KP_Multiply },
    { VK_ADD,                   NoSymbol,     XK_KP_Add,                NoSymbol,     NoSymbol,     XK_KP_Add },
    { VK_SUBTRACT,              NoSymbol,     XK_KP_Subtract,           NoSymbol,     NoSymbol,     XK_KP_Subtract },
    { VK_DIVIDE,                NoSymbol,     XK_KP_Divide,             NoSymbol,     NoSymbol,     XK_KP_Divide },
    { VK_SEPARATER,             NoSymbol,     XK_KP_Separator,          NoSymbol,     NoSymbol,     XK_KP_Separator },
    { VK_DECIMAL,               NoSymbol,     XK_KP_Decimal,            NoSymbol,     NoSymbol,     XK_KP_Decimal },
    { VK_F1,                    NoSymbol,     XK_F1,                    XK_L1,        XK_R1,        NoSymbol },
    { VK_F2,                    NoSymbol,     XK_F2,                    XK_L2,        XK_R2,        NoSymbol },
    { VK_F3,                    NoSymbol,     XK_F3,                    XK_L3,        XK_R3,        NoSymbol },
    { VK_F4,                    NoSymbol,     XK_F4,                    XK_L4,        XK_R4,        NoSymbol },
    { VK_F5,                    NoSymbol,     XK_F5,                    XK_L5,        XK_R5,        NoSymbol },
    { VK_F6,                    NoSymbol,     XK_F6,                    XK_L6,        XK_R6,        NoSymbol },
    { VK_F7,                    NoSymbol,     XK_F7,                    XK_L7,        XK_R7,        NoSymbol },
    { VK_F8,                    NoSymbol,     XK_F8,                    XK_L8,        XK_R8,        NoSymbol },
    { VK_F9,                    NoSymbol,     XK_F9,                    XK_L9,        XK_R9,        NoSymbol },
    { VK_F10,                   NoSymbol,     XK_F10,                   XK_L10,       XK_R10,       NoSymbol },
    { VK_F11,                   NoSymbol,     XK_F11,                   NoSymbol,     XK_R11,       NoSymbol },
    { VK_F12,                   NoSymbol,     XK_F12,                   NoSymbol,     XK_R12,       NoSymbol },
    { VK_F13,                   NoSymbol,     XK_F13,                   NoSymbol,     XK_R13,       NoSymbol },
    { VK_F14,                   NoSymbol,     XK_F14,                   NoSymbol,     XK_R14,       NoSymbol },
    { VK_F15,                   NoSymbol,     XK_F15,                   NoSymbol,     XK_R15,       NoSymbol },
    { VK_F16,                   NoSymbol,     XK_F16,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F17,                   NoSymbol,     XK_F17,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F18,                   NoSymbol,     XK_F18,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F19,                   NoSymbol,     XK_F19,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F20,                   NoSymbol,     XK_F20,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F21,                   NoSymbol,     XK_F21,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F22,                   NoSymbol,     XK_F22,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F23,                   NoSymbol,     XK_F23,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_F24,                   NoSymbol,     XK_F24,                   NoSymbol,     NoSymbol,     NoSymbol },
    { VK_NUM_LOCK,              NoSymbol,     XK_Num_Lock,              NoSymbol,     NoSymbol,     XK_Num_Lock },
    { VK_SCROLL_LOCK,           NoSymbol,     XK_Scroll_Lock,           NoSymbol,     NoSymbol,     NoSymbol },
    { VK_ALT_GRAPH,             NoSymbol,     XK_ISO_Level3_Shift,      NoSymbol,     NoSymbol,     NoSymbol },
    { VK_META,                  NoSymbol,     NoSymbol,                 XK_Meta_L,    XK_Meta_R,    NoSymbol },
    { VK_MODECHANGE,            NoSymbol,     XK_Mode_switch,           NoSymbol,     NoSymbol,     NoSymbol },
    { VK_CLEAR,                 NoSymbol,     XK_Clear,                 NoSymbol,     NoSymbol,     XK_KP_Begin },
    { VK_AGAIN,                 NoSymbol,     XK_Redo,                  NoSymbol,     NoSymbol,     NoSymbol },
    { VK_UNDO,                  NoSymbol,     XK_Undo,                  NoSymbol,     NoSymbol,     NoSymbol },
    { VK_FIND,                  NoSymbol,     XK_Find,                  NoSymbol,     NoSymbol,     NoSymbol },
    { VK_STOP,                  NoSymbol,     XK_Cancel,                NoSymbol,     NoSymbol,     NoSymbol },
    { VK_HELP,                  NoSymbol,     XK_Help,                  NoSymbol,     NoSymbol,     NoSymbol },
    { VK_KANJI,                 NoSymbol,     XK_Kanji,                 NoSymbol,     NoSymbol,     NoSymbol },
    { VK_KATAKANA,              NoSymbol,     XK_Katakana,              NoSymbol,     NoSymbol,     NoSymbol },
    { VK_HIRAGANA,              NoSymbol,     XK_Hiragana,              NoSymbol,     NoSymbol,     NoSymbol },
    { VK_PREVIOUS_CANDIDATE,    NoSymbol,     XK_PreviousCandidate,     NoSymbol,     NoSymbol,     NoSymbol },
    { VK_CODE_INPUT,            NoSymbol,     XK_Codeinput,             NoSymbol,     NoSymbol,     NoSymbol },
    { VK_JAPANESE_ROMAN,        NoSymbol,     XK_Romaji,                NoSymbol,     NoSymbol,     NoSymbol },
    { VK_KANA_LOCK,             NoSymbol,     XK_Kana_Lock,             NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_ABOVEDOT,         NoSymbol,     XK_dead_abovedot,         NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_ABOVERING,        NoSymbol,     XK_dead_abovering,        NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_ACUTE,            NoSymbol,     XK_dead_acute,            NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_BREVE,            NoSymbol,     XK_dead_breve,            NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_CARON,            NoSymbol,     XK_dead_caron,            NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_CEDILLA,          NoSymbol,     XK_dead_cedilla,          NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_CIRCUMFLEX,       NoSymbol,     XK_dead_circumflex,       NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_DIAERESIS,        NoSymbol,     XK_dead_diaeresis,        NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_DOUBLEACUTE,      NoSymbol,     XK_dead_doubleacute,      NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_GRAVE,            NoSymbol,     XK_dead_grave,            NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_IOTA,             NoSymbol,     XK_dead_iota,             NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_MACRON,           NoSymbol,     XK_dead_macron,           NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_OGONEK,           NoSymbol,     XK_dead_ogonek,           NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_SEMIVOICED_SOUND, NoSymbol,     XK_dead_semivoiced_sound, NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_TILDE,            NoSymbol,     XK_dead_tilde,            NoSymbol,     NoSymbol,     NoSymbol },
    { VK_DEAD_VOICED_SOUND,     NoSymbol,     XK_dead_voiced_sound,     NoSymbol,     NoSymbol,     NoSymbol },
    { VK_ALPHANUMERIC,          NoSymbol,     XK_Eisu_Shift,            NoSymbol,     NoSymbol,     NoSymbol },
    { VK_ALL_CANDIDATES,        NoSymbol,     XK_MultipleCandidate,     NoSymbol,     NoSymbol,     NoSymbol },
    { VK_KANA,                  NoSymbol,     XK_Kana_Shift,            NoSymbol,     NoSymbol,     NoSymbol },
    { VK_JAPANESE_KATAKANA,     NoSymbol,     XK_Katakana,              NoSymbol,     NoSymbol,     NoSymbol },
    { VK_JAPANESE_HIRAGANA,     NoSymbol,     XK_Hiragana,              NoSymbol,     NoSymbol,     NoSymbol },
    { VK_COMPOSE,               NoSymbol,     XK_Multi_key,             NoSymbol,     NoSymbol,     NoSymbol },
  };

  public static int vkey_to_keysym(KeyEvent ev)
  {
    int keyCode = ev.getKeyCode();

    if (VncViewer.os.startsWith("mac os x")) {
      // Alt on OS X behaves more like AltGr on other systems, and to get
      // sane behaviour we should translate things in that manner for the
      // remote VNC server. However that means we lose the ability to use
      // Alt as a shortcut modifier. Do what RealVNC does and hijack the
      // left command key as an Alt replacement.
      switch (keyCode) {
      case KeyEvent.VK_META:
        if (ev.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
          return XK_Alt_L;
        else
          return XK_Super_L;
      case KeyEvent.VK_ALT:
        if (ev.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
          return XK_Alt_L;
        else
          return XK_ISO_Level3_Shift;
      }
    }

    // Start with keys that either don't generate a symbol, or
    // generate the same symbol as some other key.
    if (keyCode == KeyEvent.VK_PAUSE)
      return (ev.isControlDown() ? XK_Break : XK_Pause);
    else if (keyCode == KeyEvent.VK_PRINTSCREEN)
      return (ev.isControlDown() ? XK_Sys_Req : XK_Print);
    else
      for(int i = 0; i < vkey_map.length; i++)
        if (keyCode == vkey_map[i][0])
          return vkey_map[i][ev.getKeyLocation()+1];

    // Unknown special key?
    if (KeyEvent.getKeyText(keyCode).isEmpty()) {
      vlog.error("Unknown key code: 0x%04x", keyCode);
      return NoSymbol;
    }

    // Pressing Ctrl wreaks havoc with the symbol lookup, so turn if off.
    int ucs = (int)ev.getKeyChar();
    if (ev.isControlDown()) {
      // For CTRL-<letter>, CTRL is sent separately, so just send <letter>.
      if ((ucs >= 1 && ucs <= 26 && !ev.isShiftDown()) ||
          // CTRL-{, CTRL-|, CTRL-} also map to ASCII 96-127
          (ucs >= 27 && ucs <= 29 && ev.isShiftDown()))
        ucs += 96;
      // For CTRL-SHIFT-<letter>, send capital <letter> to emulate behavior
      // of Linux.  For CTRL-@, send @.  For CTRL-_, send _.  For CTRL-^,
      // send ^.
      else if (ucs < 32)
        ucs += 64;
      // Windows and Mac sometimes return CHAR_UNDEFINED with CTRL-SHIFT
      // combinations, so best we can do is send the key code if it is
      // a valid ASCII symbol.
      else if (ev.getKeyChar() == CHAR_UNDEFINED && keyCode >= 0 &&
               keyCode <= 127)
        ucs = keyCode;
    }

    // Dead keys are represented by their spacing equivalent
    // (or something similar depending on the layout)
    if (Character.getType(ucs) == Character.COMBINING_SPACING_MARK)
      return Keysym2ucs.ucs2keysym(Keysym2ucs.ucs2combining(ucs));

    if (Character.isDefined(ucs))
      return Keysym2ucs.ucs2keysym(ucs);

    return NoSymbol;
  }

  static LogWriter vlog = new LogWriter("KeyMap");
}
