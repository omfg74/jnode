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
 
package org.jnode.vm;

import org.jnode.assembler.ObjectResolver;
import org.jnode.security.JNodePermission;
import org.jnode.vm.classmgr.VmMethod;
import org.jnode.vm.classmgr.VmType;
import org.vmmagic.pragma.UninterruptiblePragma;
import org.vmmagic.unboxed.Address;
import org.vmmagic.unboxed.Extent;
import org.vmmagic.unboxed.ObjectReference;
import org.vmmagic.unboxed.Offset;

/**
 * Class that allows directy hardware access.
 * 
 * @author epr
 */
public final class Unsafe {

	private static final JNodePermission GET_JUMP_TABLE_PERM = new JNodePermission(
			"getJumpTable");

	public static class UnsafeObjectResolver extends ObjectResolver {

		public UnsafeObjectResolver() {
		}

		/**
		 * @param object
		 * @see org.jnode.assembler.ObjectResolver#addressOf32(Object)
		 * @return int
		 */
		public int addressOf32(Object object) {
			return ObjectReference.fromObject(object).toAddress().toInt();
		}

		/**
		 * @param object
		 * @see org.jnode.assembler.ObjectResolver#addressOf64(Object)
		 * @return long
		 */
		public long addressOf64(Object object) {
			return ObjectReference.fromObject(object).toAddress().toLong();
		}
	}

	/**
	 * Gets the Super Classes Array of the given object.
	 * 
	 * @param object
	 * @return VmType[]
	 */
	protected static native VmType[] getSuperClasses(Object object);

	/**
	 * Sets a byte at a given memory address While count is greater then 1, the
	 * address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setBytes(Address memPtr, byte value, int count);

	/**
	 * Perform a bitwise AND of the byte at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void andByte(Address memPtr, byte value, int count);

	/**
	 * Perform a bitwise OR of the byte at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void orByte(Address memPtr, byte value, int count);

	/**
	 * Perform a bitwise XOR of the byte at the given address and the given
	 * value While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void xorByte(Address memPtr, byte value, int count);

	/**
	 * Sets a short at a given memory address While count is greater then 1, the
	 * address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setShorts(Address memPtr, short value,
			int count);

	/**
	 * Perform a bitwise AND of the short at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void andShort(Address memPtr, short value, int count);

	/**
	 * Perform a bitwise OR of the short at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void orShort(Address memPtr, short value, int count);

	/**
	 * Perform a bitwise XOR of the short at the given address and the given
	 * value While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void xorShort(Address memPtr, short value, int count);

	/**
	 * Sets a char at a given memory address While count is greater then 1, the
	 * address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setChars(Address memPtr, char value, int count);

	/**
	 * Perform a bitwise AND of the char at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void andChar(Address memPtr, char value, int count);

	/**
	 * Perform a bitwise OR of the char at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void orChar(Address memPtr, char value, int count);

	/**
	 * Perform a bitwise XOR of the char at the given address and the given
	 * value While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void xorChar(Address memPtr, char value, int count);

	/**
	 * Sets an int at a given memory address While count is greater then 1, the
	 * address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setInts(Address memPtr, int value, int count);

	/**
	 * Perform a bitwise AND of the int at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void andInt(Address memPtr, int value, int count);

	/**
	 * Perform a bitwise OR of the int at the given address and the given value.
	 * While count is greater then 1, the address is incremented and the process
	 * repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void orInt(Address memPtr, int value, int count);

	/**
	 * Perform a bitwise XOR of the int at the given address and the given value
	 * While count is greater then 1, the address is incremented and the process
	 * repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void xorInt(Address memPtr, int value, int count);

	/**
	 * Sets a 24-bit int at a given memory address While count is greater then
	 * 1, the address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setInts24(Address memPtr, int value, int count);

	/**
	 * Perform a bitwise AND of the 24-bit int at the given address and the
	 * given value. While count is greater then 1, the address is incremented
	 * and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void andInt24(Address memPtr, int value, int count);

	/**
	 * Perform a bitwise OR of the 24-bit int at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void orInt24(Address memPtr, int value, int count);

	/**
	 * Perform a bitwise XOR of the 24-bit int at the given address and the
	 * given value While count is greater then 1, the address is incremented and
	 * the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void xorInt24(Address memPtr, int value, int count);

	/**
	 * Sets a long at a given memory address While count is greater then 1, the
	 * address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setLongs(Address memPtr, long value, int count);

	/**
	 * Perform a bitwise AND of the long at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void andLong(Address memPtr, long value, int count);

	/**
	 * Perform a bitwise OR of the long at the given address and the given
	 * value. While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void orLong(Address memPtr, long value, int count);

	/**
	 * Perform a bitwise XOR of the long at the given address and the given
	 * value While count is greater then 1, the address is incremented and the
	 * process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 *            The number of times to repeat this operation
	 */
	protected static native void xorLong(Address memPtr, long value, int count);

	/**
	 * Sets a float at a given memory address While count is greater then 1, the
	 * address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setFloats(Address memPtr, float value,
			int count);

	/**
	 * Sets a double at a given memory address While count is greater then 1,
	 * the address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setDoubles(Address memPtr, double value,
			int count);

	/**
	 * Sets a Object at a given memory address While count is greater then 1,
	 * the address is incremented and the process repeats.
	 * 
	 * @param memPtr
	 * @param value
	 * @param count
	 */
	protected static native void setObjects(Address memPtr, Object value,
			int count);

	/**
	 * Fill the memory at the given memory address with size times 0 bytes.
	 * 
	 * memPtr must be VmObject.SLOT_SIZE aligned
	 * 
	 * size % VmObject.SLOT_SIZE must be 0
	 * 
	 * @param memPtr
	 * @param size
	 */
	protected static native void clear(Address memPtr, Extent size);

	/**
	 * Copy size bytes of memory from srcMemPtr to destMemPtr. The memory areas
	 * must not overlap.
	 * 
	 * @param srcMemPtr
	 * @param destMemPtr
	 * @param size
	 */
	protected static native void copy(Address srcMemPtr, Address destMemPtr,
			int size);

	/**
	 * Push an integer onto the execution stack
	 * 
	 * @param value
	 */
	protected static native void pushInt(int value);

	/**
	 * Push a long onto the execution stack
	 * 
	 * @param value
	 */
	protected static native void pushLong(long value);

	/**
	 * Push an Object onto the execution stack
	 * 
	 * @param value
	 */
	protected static native void pushObject(Object value);

	/**
	 * Invoke the given method without any parameters
	 * 
	 * @param method
	 */
	protected static native void invokeVoid(VmMethod method);

	/**
	 * Invoke the given method without any parameters
	 * 
	 * @param method
	 * @return int
	 */
	protected static native int invokeInt(VmMethod method);

	/**
	 * Invoke the given method without any parameters
	 * 
	 * @param method
	 * @return long
	 */
	protected static native long invokeLong(VmMethod method);

	/**
	 * Invoke the given method without any parameters
	 * 
	 * @param method
	 * @return Object
	 */
	protected static native Object invokeObject(VmMethod method);

	/**
	 * Halt the processor until the next interrupt arrives.
	 */
	protected static native void idle();

	/**
	 * Cause the system to stop TODO Protect me again
	 */
	public static void die(String msg) {
		debug("Real panic: ");
		if (msg != null) {
			debug(msg);
		}
		die();
	}

	/**
	 * Cause the system to stop TODO Protect me again
	 */
	private static native void die();

	/**
	 * Print the given string on the screen.
	 */
	public static native void debug(String str);

	/**
	 * Print the given value on the screen.
	 */
	public static native void debug(char value);

	/**
	 * Print the given value on the screen.
	 */
	public static native void debug(int value);

	/**
	 * Print the given value on the screen.
	 */
	public static native void debug(long value);

	/**
	 * Initialize the new Thread.
	 * 
	 * @param curThread
	 * @param newStack
	 * @param stackSize
	 */
	protected static native void initThread(VmThread curThread,
			Object newStack, int stackSize);

	/**
	 * Atomic compare and swap. Compares the int value addressed by the given
	 * address with the given old value. If they are equal, the value at the
	 * given address is replace by the new value and true is returned, otherwise
	 * nothing is changed and false is returned.
	 * 
	 * @param address
	 * @param oldValue
	 * @param newValue
	 * @return boolean true if the value at address is changed, false otherwise.
	 */
	protected static native boolean atomicCompareAndSwap(VmAddress address,
			int oldValue, int newValue);

	protected static native int inPortByte(int portNr);

	protected static native int inPortWord(int portNr);

	protected static native int inPortDword(int portNr);

	protected static native void outPortByte(int portNr, int value);

	protected static native void outPortWord(int portNr, int value);

	protected static native void outPortDword(int portNr, int value);

	public static native float intBitsToFloat(int value);

	public static native int floatToRawIntBits(float value);

	public static native double longBitsToDouble(long value);

	public static native long doubleToRawLongBits(double value);

	/**
	 * Gets the minimum valid address in the addressspace of the current
	 * architecture.
	 * 
	 * @return Address
	 */
	protected static native VmAddress getMinAddress();

	/**
	 * Gets the maximum valid address in the addressspace of the current
	 * architecture.
	 * 
	 * @return Address
	 */
	protected static native VmAddress getMaxAddress();

	/**
	 * Gets the (inclusive) start address of the available memory.
	 * 
	 * @return Address
	 */
	protected static native Address getMemoryStart();

	/**
	 * Gets the (exclusive) end address of the available memory.
	 * 
	 * @return Address
	 */
	protected static native Address getMemoryEnd();

	/**
	 * Gets the (inclusive) start address of the kernel.
	 * 
	 * @return Address
	 */
	protected static native Address getKernelStart();

	/**
	 * Gets the (exclusive) end address of the kernel.
	 * 
	 * @return Address
	 */
	protected static native Address getKernelEnd();

	/**
	 * Gets the (inclusive) start address of the initial jarfile.
	 * 
	 * @return Address
	 */
	protected static native Address getInitJarStart();

	/**
	 * Gets the (exclusive) end address of the initial jarfile.
	 * 
	 * @return Address
	 */
	protected static native Address getInitJarEnd();

	/**
	 * Gets the (inclusive) start address of the boot heap.
	 * 
	 * @return Address
	 */
	protected static native Address getBootHeapStart();

	/**
	 * Gets the (exclusive) end address of the boot heap.
	 * 
	 * @return Address
	 */
	protected static native Address getBootHeapEnd();

	public static native long getTimeStampCounter();

	/**
	 * Gets information of the JNode kernel command line.
	 * 
	 * @param destination
	 *            If non-null, the commandline is copied into this array.
	 * @return The maximum length of the command line
	 */
	protected static native int getCmdLine(byte[] destination);

	/**
	 * Gets the processor that currently runs the active thread.
	 * 
	 * @return The current processor.
	 * @throws UninterruptiblePragma
	 */
	public static native VmProcessor getCurrentProcessor()
			throws UninterruptiblePragma;

	/**
	 * Trigger a yieldpoint
	 */
	static native void yieldPoint();

	/**
	 * Gets the address of the system dependent jump table used for native
	 * method indirection.
	 * 
	 * @return The address of the system dependent jump table.
	 */
	private static native Address getJumpTable0();

	/**
	 * Gets the address of the system dependent jump table used for native
	 * method indirection.
	 * 
	 * @return The address of the system dependent jump table.
	 */
	public static final Address getJumpTable() {
		final SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(GET_JUMP_TABLE_PERM);
		}
		return getJumpTable0();
	}

	/**
	 * Gets a jumptable entry. This method can only be called at runtime.
	 * 
	 * @param offset
	 * @return The jumptable entry.
	 */
	public static final Address getJumpTableEntry(int offset) {
		final SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(GET_JUMP_TABLE_PERM);
		}
		return getJumpTable0().loadAddress(Offset.fromIntSignExtend(offset));
	}

	/**
	 * Read CPU identification data.
	 * 
	 * If id is null, this method will return the length of the id array that is
	 * required to fit all data. If id is not null and long enough, it is filled
	 * with all identification data.
	 * 
	 * @param id
	 * @return The required length of id.
	 */
	static native int getCPUID(int[] id);

	/**
	 * Force a breakpoint
	 */
	public static native void breakPoint();
}
