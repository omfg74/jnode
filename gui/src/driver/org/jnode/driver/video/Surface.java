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
 
package org.jnode.driver.video;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.awt.image.Raster;

/**
 * A Surface is an area (of the screen or of an imagebuffer) that can
 * be painted on.
 * 
 * @author epr
 */
public interface Surface {

	/** Paint (opaque mode) */
	public static final int PAINT_MODE = 0x00;
	/** XOR mode */
	public static final int XOR_MODE = 0x01;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param dx
	 * @param dy
	 * @see java.awt.Graphics#copyArea(int, int, int, int, int, int)
	 */
	public void copyArea(int x, int y, int width, int height, int dx, int dy);

	/**
	 * Draw the given shape
	 * @param shape The shape to draw
	 * @param clip The clipping area, can be null
	 * @param tx The transformation to be applied to shape &amp; clip.
	 * @param color
	 * @param mode
	 */
	public void draw(Shape shape, Shape clip, AffineTransform tx, Color color, int mode);

	/**
	 * Fill the given shape with the given color
	 * @param shape The shape to fill
	 * @param clip The clipping area, can be null
	 * @param tx The transformation to be applied to shape &amp; clip.
	 * @param color
	 * @param mode
	 */
	public void fill(Shape shape, Shape clip, AffineTransform tx, Color color, int mode);

	/**
	 * Draw an raster to this surface.
	 * The given raster is compatible with the color model of this surface.
	 * 
	 * @param raster
	 * @param srcX The upper left x coordinate within the raster
	 * @param srcY The upper left y coordinate within the raster
	 * @param dstX The upper left destination x coordinate 
	 * @param dstY The upper left destination y coordinate
	 * @param width
	 * @param height
	 * @param bgColor The background color to use for transparent pixels. If null, no transparent pixels are unmodified on the destination
	 */
	public void drawCompatibleRaster(Raster raster, int srcX, int srcY, int dstX, int dstY, int width, int height, Color bgColor);

	/**
	 * Draw an raster of alpha values using a given color onto to this surface.
	 * The given raster is a 1 band gray type raster.
	 * 
	 * @param raster
	 * @param srcX The upper left x coordinate within the raster
	 * @param srcY The upper left y coordinate within the raster
	 * @param dstX The upper left destination x coordinate 
	 * @param dstY The upper left destination y coordinate
	 * @param width
	 * @param height
	 * @param color The color to use. 
	 */
	public void drawAlphaRaster(Raster raster, AffineTransform tx, int srcX, int srcY, int dstX, int dstY, int width, int height, Color color);

	/**
	 * Gets the color model of this surface
	 */
	public ColorModel getColorModel();

	/**
	 * Close this surface
	 */
	public void close();
}
