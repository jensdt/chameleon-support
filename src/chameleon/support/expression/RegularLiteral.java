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
import chameleon.core.type.TypeReference;
import chameleon.support.property.accessibility.All;

/**
 * @author Marko van Dooren
 */
public class RegularLiteral extends LiteralWithTypeReference<RegularLiteral> {

  public RegularLiteral(TypeReference type, String value) {
    super(value);
    setTypeReference(type);
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
    return (target instanceof RegularLiteral) && 
           (getValue().equals(((RegularLiteral)target).getValue())) &&
    	   (getType().equals(((RegularLiteral)target).getType()));
    
  }

  public RegularLiteral clone() {
    return new RegularLiteral((TypeReference)getTypeReference().clone(), getValue());
  }

  public AccessibilityDomain getAccessibilityDomain() throws MetamodelException {
    return new All();
  }
  
}
