/*
 * $Id$
 *
 * JNode.org
 * Copyright (C) 2005 JNode.org
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 */
 
package org.jnode.driver.console.spi;

import java.util.ArrayList;
import java.util.Iterator;

import org.jnode.driver.console.Console;
import org.jnode.driver.console.ConsoleManager;
import org.jnode.driver.input.KeyboardEvent;
import org.jnode.driver.input.KeyboardListener;
import org.jnode.driver.input.PointerEvent;
import org.jnode.driver.input.PointerListener;
import org.jnode.system.event.FocusEvent;

/**
 * @author epr
 */
public class AbstractConsole implements Console {

    private final ArrayList keyboardListeners = new ArrayList();

    private final ArrayList pointerListeners = new ArrayList();

    private final String consoleName;

    private final ConsoleManager mgr;

    private int acceleratorKeyCode = 0;

    /**
     * Initialize this instance.
     * 
     * @param mgr
     * @param name
     */
    public AbstractConsole(ConsoleManager mgr, String name) {
        this.mgr = mgr;
        this.consoleName = name;
    }

    /**
     * Does this console has the focus?
     * 
     * @return True if this console has the focus, false otherwise
     */
    public boolean isFocused() {
        return (mgr.getFocus() == this);
    }

    /**
     * Add a PointerListener
     * 
     * @param l
     */
    public void addPointerListener(PointerListener l) {
        synchronized (pointerListeners) {
            if (!pointerListeners.contains(l)) {
                pointerListeners.add(l);
            }
        }
    }

    /**
     * Remove a PointerListener
     * 
     * @param l
     */
    public void removePointerListener(PointerListener l) {
        synchronized (pointerListeners) {
            pointerListeners.remove(l);
        }
    }

    /**
     * Send the PointerEvent to all the PointerListeners
     * 
     * @param event
     */
    protected void dispatchPointerEvent(PointerEvent event) {
        if (event.isConsumed()) {
            return;
        }
        synchronized (pointerListeners) {
            for (Iterator i = pointerListeners.iterator(); i.hasNext();) {
                final PointerListener l = (PointerListener) i.next();
                l.pointerStateChanged(event);
                if (event.isConsumed()) {
                    break;
                }
            }
        }
    }

    /**
     * @param l
     * @see org.jnode.driver.console.Console#addKeyboardListener(org.jnode.driver.input.KeyboardListener)
     */
    public void addKeyboardListener(KeyboardListener l) {
        synchronized (keyboardListeners) {
            if (!keyboardListeners.contains(l)) {
                keyboardListeners.add(l);
            }
        }
    }

    /**
     * @param l
     * @see org.jnode.driver.console.Console#removeKeyboardListener(org.jnode.driver.input.KeyboardListener)
     */
    public void removeKeyboardListener(KeyboardListener l) {
        synchronized (keyboardListeners) {
            keyboardListeners.remove(l);
        }
    }

    /**
     * @param event
     * @see org.jnode.driver.input.KeyboardListener#keyPressed(org.jnode.driver.input.KeyboardEvent)
     */
    public void keyPressed(KeyboardEvent event) {
        if (isFocused()) {
            dispatchKeyboardEvent(event);
        }
    }

    /**
     * @param event
     * @see org.jnode.driver.input.KeyboardListener#keyReleased(org.jnode.driver.input.KeyboardEvent)
     */
    public void keyReleased(KeyboardEvent event) {
        if (isFocused()) {
            dispatchKeyboardEvent(event);
        }
    }

    /**
     * @param event
     * @see org.jnode.system.event.FocusListener#focusGained(org.jnode.system.event.FocusEvent)
     */
    public void focusGained(FocusEvent event) {
    }

    /**
     * @param event
     * @see org.jnode.system.event.FocusListener#focusLost(org.jnode.system.event.FocusEvent)
     */
    public void focusLost(FocusEvent event) {
    }

    /**
     * Dispatch a given keyboard event to all known listeners.
     * 
     * @param event
     */
    protected void dispatchKeyboardEvent(KeyboardEvent event) {
        if (event.isConsumed()) {
            return;
        }

        synchronized (keyboardListeners) {
            for (Iterator i = keyboardListeners.iterator(); i.hasNext();) {
                final KeyboardListener l = (KeyboardListener) i.next();
                if (event.isKeyPressed()) {
                    l.keyPressed(event);
                } else if (event.isKeyReleased()) {
                    l.keyReleased(event);
                }
                if (event.isConsumed()) {
                    break;
                }
            }
        }
    }

    /**
     * Respond to scroll events from the mouse.
     * 
     * @param event
     * @see org.jnode.driver.input.PointerListener#pointerStateChanged(org.jnode.driver.input.PointerEvent)
     */
    public void pointerStateChanged(PointerEvent event) {
        if (isFocused()) {
            dispatchPointerEvent(event);
        }
    }

    /**
     * Close this console.
     * 
     * @see org.jnode.driver.console.Console#close()
     */
    public void close() {
        mgr.unregisterConsole(this);
    }

    public void setAcceleratorKeyCode(int keyCode) {
        this.acceleratorKeyCode = keyCode;
    }

    public int getAcceleratorKeyCode() {
        return acceleratorKeyCode;
    }

    /**
     * @return Returns the consoleName.
     */
    public String getConsoleName() {
        return consoleName;
    }
    /**
     * @see org.jnode.driver.console.Console#getManager()
     */
    public final ConsoleManager getManager() {
        return mgr;
    }
}
