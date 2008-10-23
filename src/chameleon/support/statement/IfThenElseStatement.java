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

import org.rejuse.association.Reference;

import chameleon.core.expression.Expression;
import chameleon.core.statement.ExpressionContainingStatement;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class IfThenElseStatement extends ExpressionContainingStatement<IfThenElseStatement> implements StatementContainer<IfThenElseStatement,StatementContainer> {

  public IfThenElseStatement(Expression expression, Statement ifStatement, Statement elseStatement) {
    super(expression);
    setIfStatement(ifStatement);
    setElseStatement(elseStatement);
  }

	/**
	 * IF STATEMENT
	 */
	private Reference<IfThenElseStatement,Statement> _ifStatement = new Reference<IfThenElseStatement,Statement>(this);


  public void setIfStatement(Statement statement) {
    _ifStatement.connectTo(statement.getParentLink());
  }

  public Statement getIfStatement() {
    return _ifStatement.getOtherEnd();
  }

	/**
	 * ELSE STATEMENT
	 */
	private Reference<IfThenElseStatement,Statement> _elseStatement = new Reference<IfThenElseStatement,Statement>(this);

  public void setElseStatement(Statement statement) {
    if (statement != null) {
      _elseStatement.connectTo(statement.getParentLink());
    }
    else {
      _elseStatement.connectTo(null);
    }
  }

  public Statement getElseStatement() {
    return _elseStatement.getOtherEnd();
  }


  public IfThenElseStatement clone() {
    Statement ifStatement = null;
    if(getIfStatement() != null) {
      ifStatement = getIfStatement().clone();
    }
    Statement elseStatement = null;
    if(getElseStatement() != null) {
      elseStatement = getElseStatement().clone();
    }
    return new IfThenElseStatement(getExpression().clone(), ifStatement, elseStatement);
  }

  public List getChildren() {
    List result = Util.createNonNullList(getExpression());
    Util.addNonNull(getIfStatement(), result);
    Util.addNonNull(getElseStatement(), result);
    return result;
  }
}
