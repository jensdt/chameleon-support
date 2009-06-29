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

import java.util.Iterator;
import java.util.List;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.association.Reference;
import org.rejuse.java.collections.Visitor;
import org.rejuse.predicate.PrimitivePredicate;

import chameleon.core.context.LookupException;
import chameleon.core.element.ChameleonProgrammerException;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class TryStatement extends StatementContainingStatement<TryStatement> {

  public TryStatement(Statement statement) {
    super(statement);
  }

	/**
	 * CATCH CLAUSES
	 */
	private OrderedReferenceSet<TryStatement,CatchClause> _catchClausesLink = new OrderedReferenceSet<TryStatement,CatchClause>(this);


  public OrderedReferenceSet<TryStatement,CatchClause> getCatchClausesLink() {
    return _catchClausesLink;
  }

  public void addCatchClause(CatchClause catchClause) {
  	if(catchClause != null) {
      _catchClausesLink.add(catchClause.parentLink());
  	} else {
  		throw new ChameleonProgrammerException("Trying to add a null catch clause to a try statement");
  	}
  }
  
  public void addAllCatchClauses(List<CatchClause> catchClauses) {
  	for(CatchClause clause : catchClauses) {
  		addCatchClause(clause);
  	}
  }

  public void removeCatchClause(CatchClause catchClause) {
    _catchClausesLink.remove(catchClause.parentLink());
  }

  public List<CatchClause> getCatchClauses() {
    return _catchClausesLink.getOtherEnds();
  }

	/**
	 * FINALLY
	 */
	private Reference _finally = new Reference(this);

  public FinallyClause getFinallyClause() {
    return (FinallyClause)_finally.getOtherEnd();
  }

  public void setFinallyClause(FinallyClause clause) {
    _finally.connectTo(clause.parentLink());
  }

  public TryStatement clone() {
    final TryStatement result = new TryStatement(getStatement().clone());
    new Visitor() {
      public void visit(Object element) {
        result.addCatchClause(((CatchClause)element).clone());
      }
    }.applyTo(getCatchClauses());
    if(getFinallyClause() != null) {
      result.setFinallyClause(getFinallyClause().clone());
    }
    return result;
  }
  
  public List children() {
    List result = Util.createNonNullList(getStatement());
    result.addAll(getCatchClauses());
    Util.addNonNull(getFinallyClause(), result);
    return result;
  }
  
  /**
   * Check whether or not all catch clauses of this try statement are valid.
   */
 /*@
   @ public behavior
   @
   @ post \result == (\forall CatchClause cc; getCatchClauses().contains(cc);
   @                    cc.isValid());
   @*/
  public boolean hasValidCatchClauses() throws LookupException {
    try {
      return new PrimitivePredicate() {
        public boolean eval(Object o) throws LookupException {
          return ((CatchClause)o).isValid();
        }
      }.forAll(getCatchClauses());
    }
    catch (LookupException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Error();
    }
  }
  
  public CheckedExceptionList getCEL() throws LookupException {
    final CheckedExceptionList cel = getStatement().getCEL();

    Iterator iter = getCatchClauses().iterator();
    // remove all handled exceptions
    while(iter.hasNext()) {
      CatchClause cc = (CatchClause)iter.next();
      cel.handleType(cc.getExceptionParameter().getType());
    }
    iter = getCatchClauses().iterator();
    while(iter.hasNext()) {
      CatchClause cc = (CatchClause)iter.next();
      cel.absorb(cc.getCEL());
    }
    if(getFinallyClause() != null) {
      cel.absorb(getFinallyClause().getCEL());
    }
    return cel;
  }
  
  public CheckedExceptionList getAbsCEL() throws LookupException {
    final CheckedExceptionList cel = getStatement().getAbsCEL();

    Iterator iter = getCatchClauses().iterator();
    // remove all handled exceptions
    while(iter.hasNext()) {
      CatchClause cc = (CatchClause)iter.next();
      cel.handleType(cc.getExceptionParameter().getType());
    }
    iter = getCatchClauses().iterator();
    while(iter.hasNext()) {
      CatchClause cc = (CatchClause)iter.next();
      cel.absorb(cc.getAbsCEL());
    }
    if(getFinallyClause() != null) {
      cel.absorb(getFinallyClause().getAbsCEL());
    }
    return cel;
  }
  
}
