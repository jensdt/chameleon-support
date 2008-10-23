/*
 * Copyright 2000-2004 the Jnome development team.
 *
 * @author Marko van Dooren
 * @author Nele Smeets
 * @author Kristof Mertens
 * @author Jan Dockx
 *
 * This file is part of Jnome.
 *
 * Jnome is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * Jnome is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Jnome; if not, write to the Free Software Foundation, Inc., 59 Temple Place,
 * Suite 330, Boston, MA 02111-1307 USA
 */
package chameleon.support.statement;

import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.element.Element;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;

/**
 * @author Marko van Dooren
 */

public interface ForInit<E extends ForInit, P extends Element> extends ExceptionSource<E,P> {

	/**
	 * 
	 * @uml.property name="parentLink"
	 * @uml.associationEnd 
	 * @uml.property name="parentLink" multiplicity="(0 1)"
	 */
	public Reference getParentLink();

  /**
   * @param statement
   * @return
   */
  public int getIndexOf(Statement statement);

  /**
   * @return
   */
  public int getNbStatements();
    
}
