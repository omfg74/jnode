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
 
package org.jnode.vm.compiler;

import org.jnode.vm.bytecode.BytecodeFlags;
import org.jnode.vm.bytecode.BytecodeParser;
import org.jnode.vm.bytecode.ControlFlowGraph;
import org.jnode.vm.classmgr.VmByteCode;

/**
 * @author Ewout Prangsma (epr@users.sourceforge.net)
 */
public class CompilerBytecodeParser extends BytecodeParser implements BytecodeFlags {

	private final ControlFlowGraph cfg;
	private final CompilerBytecodeVisitor handler;
	
	/**
	 * @param bc
	 * @param handler
	 */
	protected CompilerBytecodeParser(VmByteCode bc, ControlFlowGraph cfg, CompilerBytecodeVisitor handler) {
		super(bc, handler);
		this.cfg = cfg;
		this.handler = handler;
	}
	
	/**
	 * @see org.jnode.vm.bytecode.BytecodeParser#fireStartInstruction(int)
	 */
	protected void fireStartInstruction(int address) {
		final int flags = cfg.getOpcodeFlags(address);
		if ((flags & F_START_OF_TRYBLOCK) != 0) {
		    handler.startTryBlock();
		}
		if ((flags & F_START_OF_TRYBLOCKEND) != 0) {
		    handler.endTryBlock();
		}
		super.fireStartInstruction(address);
		if ((flags & F_YIELDPOINT) != 0) {
			handler.yieldPoint();
		}
	}

}
