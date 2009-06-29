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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chameleon.core.context.LookupException;
import chameleon.core.element.ChameleonProgrammerException;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.type.Type;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ConditionalExpression extends TernaryExpression {

  /**
   * @param first
   * @param second
   */
  public ConditionalExpression(Expression condition, Expression first, Expression second) {
    super(first, second,condition);
  }

  public Expression<? extends Expression> getCondition() {
  	return getThird();
  }
  
  public void setCondition(Expression condition) {
  	setThird(condition);
  }

  public Type getType() throws LookupException {
  	//GENERALIZE PROMOTIONS
    Type firstType = getFirst().getType();
    Type secondType = getSecond().getType();
    if (firstType.equals(secondType)) {
      return firstType;
    }
    
    //TODO I think this code is redundant since NullType is assignable to anything.
    
//    else if (firstType instanceof NullType) {
//      return secondType;
//    }
//    else if (secondType instanceof NullType) {
//      return firstType;
//    }
    
    else if (firstType.assignableTo(secondType)) {
      return secondType;
    }
    else if (secondType.assignableTo(firstType)) {
      return firstType;
    }
//    else if ((firstType.getName().equals("short") && secondType.getName().equals("byte")) || (firstType.getName().equals("byte") && secondType.getName().equals("short"))) {
//      return getNamespace().getDefaultNamespace().findType("short");
//    }

    else {
      throw new ChameleonProgrammerException("Numerical promotion in conditional expression not yet complete");
    }
  }

  public boolean superOf(InvocationTarget target) throws LookupException {
    return (target instanceof ConditionalExpression) &&
           (getCondition().compatibleWith(((ConditionalExpression)target).getCondition())) &&
           (getFirst().compatibleWith(((ConditionalExpression)target).getFirst())) &&
           (getSecond().compatibleWith(((ConditionalExpression)target).getSecond()));
  }

  public ConditionalExpression clone() {
    return new ConditionalExpression(getCondition().clone(), getFirst().clone(), getSecond().clone());
  }

  public List children() {
    List result = Util.createNonNullList(getCondition());
    Util.addNonNull(getFirst(), result);
    Util.addNonNull(getSecond(), result);
    return result;
  }

  public Set getDirectExceptions() throws LookupException {
    return new HashSet();
  }

//  public AccessibilityDomain getAccessibilityDomain() throws LookupException {
//    return getCondition().getAccessibilityDomain().intersect(
//             getFirst().getAccessibilityDomain().intersect(
//                 getSecond().getAccessibilityDomain())
//           ); 
//  }

  
}
