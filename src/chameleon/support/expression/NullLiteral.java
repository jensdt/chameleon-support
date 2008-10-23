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
package chameleon.support.expression;




import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.Literal;
import chameleon.core.type.Type;
import chameleon.support.property.accessibility.All;

/**
 * @author marko
 */
public class NullLiteral extends Literal<NullLiteral> {

  public NullLiteral(){
    super("null");
  }

  public Type getType() throws MetamodelException {
	  return getNamespace().getNullType();
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
    return target instanceof NullLiteral;
  }

  public NullLiteral clone() {
    return new NullLiteral();
  }

  public AccessibilityDomain getAccessibilityDomain() throws MetamodelException {
    return new All();
  }

}
