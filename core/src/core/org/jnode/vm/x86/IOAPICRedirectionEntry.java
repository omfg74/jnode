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
 
package org.jnode.vm.x86;

/**
 * @author Ewout Prangsma (epr@users.sourceforge.net)
 */
final class IOAPICRedirectionEntry {

    public static final int DELMOD_FIXED = 0x00;

    public static final int DELMOD_LOWPRIORITY = 0x01;

    public static final int DELMOD_SMI = 0x02;

    public static final int DELMOD_NMI = 0x04;

    public static final int DELMOD_INIT = 0x05;

    public static final int DELMOD_EXTINT = 0x07;

    private final int offset;

    private final IOAPIC apic;

    private int low;

    private int high;
    
    private static final int DESTMOD = 1 << 11;
    private static final int IRR = 1 << 14;
    private static final int TRIGGERMODE = 1 << 15;
    private static final int MASK = 1 << 16;

    /**
     * Initialize this instance.
     * 
     * @param offset
     */
    public IOAPICRedirectionEntry(IOAPIC apic, int offset) {
        this.apic = apic;
        this.offset = offset;
        read();
    }

    /**
     * Gets the interrupt vector
     * 
     * @return
     */
    public int getVector() {
        return low & 0xFF;
    }

    /**
     * Sets the interrupt vector
     */
    public synchronized void setVector(int vector) {
        low &= 0xFFFFFF00;
        low |= vector & 0xFF;
        writeLow();
    }

    /**
     * Gets the delivery mode.
     * 
     * @return
     */
    public int getDeliveryMode() {
        return (low >> 8) & 0x07;
    }

    /**
     * Sets the delivery mode
     */
    public synchronized void setDeliveryMode(int mode) {
        low &= ~(0x7 << 8);
        low |= (mode & 0x7) << 8;
        writeLow();
    }

    /**
     * Is this entry set for physical destination.
     * 
     * @return
     */
    public boolean isPhysicalDestination() {
        return ((low & DESTMOD) == 0);
    }

    /**
     * Is the type signal that triggers an interrupt level sensative.
     * 
     * @return
     */
    public boolean isLevelTriggerMode() {
        return ((low & TRIGGERMODE) != 0);
    }

    /**
     * Is the type signal that triggers an interrupt edge sensative.
     * 
     * @return
     */
    public boolean isEdgeTriggerMode() {
        return ((low & TRIGGERMODE) == 0);
    }

    /**
     * Is this interrupt signal masked.
     * 
     * @return
     */
    public boolean isMasked() {
        return ((low & MASK) != 0);
    }
    
    /**
     * Initialize this entry to a masked, cleared state.
     */
    public void clear() {
        low = MASK;
        high = 0;
        writeLow();
        writeHigh();
    }

    /**
     * Convert to a string representation
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "VECT" + getVector() + ", DELMOD " + getDeliveryMode()
                + ", MASKED " + isMasked() + ", "
                + (isLevelTriggerMode() ? "LEVEL" : "EDGE");
    }

    private final void read() {
        low = apic.getReg(offset);
        high = apic.getReg(offset + 1);
    }

    private final void writeLow() {
        apic.setReg(offset, low);
    }

    private final void writeHigh() {
        apic.setReg(offset + 1, high);
    }
}
