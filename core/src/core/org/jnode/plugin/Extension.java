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
 
package org.jnode.plugin;

/**
 * Descriptor of an "implementation" of an ExtensionPoint.
 * 
 * @see org.jnode.plugin.ExtensionPoint
 * @author Ewout Prangsma (epr@users.sourceforge.net)
 */
public interface Extension {

	/**
	 * Returns the simple identifier of this extension, or null if this 
	 * extension does not have an identifier. 
	 * This identifier is specified in the plug-in manifest (plugin.xml) 
	 * file as a non-empty string containing no period characters ('.') 
	 * and must be unique within the defining plug-in. 
	 * @return The simple identifier
	 */
	public abstract String getSimpleIdentifier();
	
	/**
	 * Returns the unique identifier of this extension, or null if this 
	 * extension does not have an identifier. 
	 * If available, this identifier is unique within the plug-in registry, 
	 * and is composed of the identifier of the plug-in that declared this 
	 * extension and this extension's simple identifier. 
	 * @return The unique identifier
	 */
	public abstract String getUniqueIdentifier();
	
	/**
	 * Gets all child elements
	 * @return List&lt;Element&gt;
	 */
	public abstract ConfigurationElement[] getConfigurationElements();

	/**
	 * Gets the name of the extension-point this extension connects to.
	 * @return The unique id of the extension-point
	 */
	public String getExtensionPointUniqueIdentifier();

	/**
	 * Gets the descriptor of the plugin in which this element was declared.
	 * @return The descriptor
	 */
	public PluginDescriptor getDeclaringPluginDescriptor();
	
	public String getExtensionPointPluginId();

}
