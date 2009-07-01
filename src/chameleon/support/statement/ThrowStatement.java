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

import chameleon.core.expression.Expression;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.exception.TypeExceptionDeclaration;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionPair;
import chameleon.core.statement.ExpressionContainingStatement;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ThrowStatement extends ExpressionContainingStatement<ThrowStatement> {

 /*@
   @ public behavior
   @
   @ post getExpression() == expression;
   @*/
  public ThrowStatement(Expression expression) {
    super(expression);
  }

  public ThrowStatement clone() {
    return new ThrowStatement(getExpression().clone());
  }

 /*@
   @ also public behavior
   @
   @ post \result.contains(getExpression());
   @ post \result.size() == 1;
   @*/
  public List children() {
    return Util.createNonNullList(getExpression());
  }

  public CheckedExceptionList getDirectCEL() throws LookupException {
	    CheckedExceptionList cel = new CheckedExceptionList(language());
	    Type type = getExpression().getType();
	    TypeReference tr = new TypeReference(null, type.getFullyQualifiedName());
	    TypeExceptionDeclaration ted = new TypeExceptionDeclaration(tr);
	    cel.add(new ExceptionPair(type, ted, this));
	    return cel;
	  }
    
}
