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
import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.statement.StatementListContainer;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ForStatement extends IterationStatement<ForStatement> implements StatementListContainer<ForStatement,StatementContainer> {

  /**
   * @param expression
   * @param statement
   */
  public ForStatement(ForInit forInit, Expression condition, StatementExprList forUpdate, Statement statement) {
    super(condition, statement);
    setForInit(forInit);
    setUpdate(forUpdate);
  }

  /**
	 * FOR INIT
	 */
	private Reference<ForStatement,ForInit> _forInit = new Reference<ForStatement,ForInit>(this);


  public Reference<ForStatement,ForInit> getInitLink() {
    return _forInit;
  }

  public void setForInit(ForInit forInit) {
    if (forInit != null) {
      _forInit.connectTo(forInit.getParentLink());
    }
    else {
      _forInit.connectTo(null);
    }
  }

  public ForInit getForInit() {
    return _forInit.getOtherEnd();
  }

	/**
	 * UPDATE
	 */

  private Reference<ForStatement,StatementExprList> _update = new Reference<ForStatement,StatementExprList>(this);

  public Reference<ForStatement,StatementExprList> getUpdateLink() {
    return _update;
  }

  public void setUpdate(StatementExprList update) {
    if (update != null) {
      _update.connectTo(update.getParentLink());
    }
    else {
      _update.connectTo(null);
    }
  }

  public StatementExprList getUpdate() {
    return _update.getOtherEnd();
  }

  public ForStatement clone() {
    Expression cond = null;
    if(getExpression() != null) {
      cond = getExpression().clone();
    }
    Statement statement = null;
    if(getStatement() != null) {
      statement = getStatement().clone();
    }
    ForInit init = null;
    if(getForInit() != null) {
      init = ((ForInit<? extends ForInit, ? extends Element>)getForInit()).clone();
    }
    StatementExprList update = null;
    if(getUpdate() != null) {
      update = getUpdate().cloneUpdate();
    }
    return new ForStatement(init, cond, update, statement);
  }

  public List<Element> getChildren() {
    List result = Util.createNonNullList(getForInit());
    Util.addNonNull(getUpdate(), result);
    Util.addNonNull(getCondition(), result);
    return result;
  }

  public int getIndexOf(Statement statement) {
    int index = getForInit().getIndexOf(statement);
    if(index >= 0) {
      return index + 1;
    }
    else if(getStatement().equals(statement)) {
      return getForInit().getNbStatements() + 1;
    }
    else {
      return 0;
    }
  }
}
