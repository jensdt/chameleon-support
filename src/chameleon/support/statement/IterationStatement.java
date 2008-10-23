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

import java.util.Set;

import org.rejuse.java.collections.Visitor;

import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public abstract class IterationStatement<E extends IterationStatement> extends StatementExprStatement<E> {
  
  /**
   * @param expression
   */
  public IterationStatement(Expression expression, Statement statement) {
    super(statement, expression);
  }

  public Expression getCondition() {
    return getExpression();
  }

}
