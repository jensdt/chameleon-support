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
import java.util.Set;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.java.collections.Visitor;

import chameleon.core.expression.Expression;
import chameleon.core.statement.ExpressionContainingStatement;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class SwitchStatement extends ExpressionContainingStatement<SwitchStatement> implements StatementContainer<SwitchStatement,StatementContainer> {

  public SwitchStatement(Expression expression) {
    super(expression);
  }

	/**
	 * SWITCH CASES
	 */
	private OrderedReferenceSet<SwitchStatement,SwitchCase> _switchCases = new OrderedReferenceSet<SwitchStatement,SwitchCase>(this);

  public OrderedReferenceSet<SwitchStatement,SwitchCase> getCasesLink() {
    return _switchCases;
  }

  public void addCase(SwitchCase switchCase) {
    _switchCases.add(switchCase.parentLink());
  }

  public void removeCase(SwitchCase switchCase) {
    _switchCases.remove(switchCase.parentLink());
  }

  public List<SwitchCase> getSwitchCases() {
    return _switchCases.getOtherEnds();
  }

  public SwitchStatement clone() {
    final SwitchStatement result = new SwitchStatement(getExpression().clone());
    new Visitor() {
      public void visit(Object element) {
        result.addCase(((SwitchCase)element).clone());
      }
    }.applyTo(getSwitchCases());
    return result;
  }

  public List children() {
    List result = Util.createNonNullList(getExpression());
    result.addAll(getSwitchCases());
    return result;
  }
}
