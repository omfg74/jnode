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
 
package org.jnode.test.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author epr
 */
public class UDPTest {
	
	public static void main(String[] args) 
	throws Exception {
		
		// Listen to netbios messages
		final DatagramSocket socket = new DatagramSocket(2237);
		try {
			final byte[] buf = new byte[256];
			final DatagramPacket dp = new DatagramPacket(buf, buf.length);
		
			System.out.println("Starting to listen for netbios messages now...");
		
			for (int i = 0; i < 5; i++) {
				socket.receive(dp);
				System.out.println("Received datagram packet from " + dp.getAddress() + ":" + dp.getPort());
			}
		
			System.out.println("I'm stopping now");
		} finally {
			socket.close();
		}
	}

}
