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
 
package org.jnode.net.command;

import org.jnode.shell.help.Argument;
import org.jnode.shell.help.ParsedArguments;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Martin Hartvig
 */

public class HostNameArgument extends Argument
{

	public HostNameArgument(String _name, String _description, boolean _multi)
  {
		super(_name, _description, _multi);
	}

	public HostNameArgument(String _name, String _description)
  {
		super(_name, _description);
	}


	public InetAddress getAddress(ParsedArguments _parsedArguments) throws UnknownHostException
  {
		String value = getValue(_parsedArguments);

		if (value == null)
			return null;

    return InetAddress.getByName(value);
	}
}
