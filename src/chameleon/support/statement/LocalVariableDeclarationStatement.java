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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.java.collections.Visitor;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationSelector;
import chameleon.core.element.Element;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.statement.VariableDeclaringStatement;
import chameleon.core.variable.VariableContainer;
import chameleon.support.variable.LocalVariable;

/**
 * @author Marko van Dooren
 */
public class LocalVariableDeclarationStatement extends Statement<LocalVariableDeclarationStatement> 
       implements VariableContainer<LocalVariableDeclarationStatement,StatementContainer>, ForInit<LocalVariableDeclarationStatement,StatementContainer>, VariableDeclaringStatement {

  public LocalVariableDeclarationStatement() {
	  
  }
  
	 /************
	  * VARIABLE *
	  ************/
	    
		private OrderedReferenceSet<LocalVariableDeclarationStatement,LocalVariable> _variables = new OrderedReferenceSet<LocalVariableDeclarationStatement,LocalVariable>(this);

		public List<LocalVariable> getVariables() {
	    return _variables.getOtherEnds();
	  }
	    
	  public void addVariable(LocalVariable v) {
	    _variables.add(v.parentLink());
	  }
	    
	  public void removeVariable(LocalVariable v) {
	    _variables.remove(v.parentLink());
	  }
	    
	  public OrderedReferenceSet<LocalVariableDeclarationStatement,LocalVariable> getVariableLink() {
	  	return _variables;
	  }
  
  public LocalVariableDeclarationStatement clone() {
    final LocalVariableDeclarationStatement result = new LocalVariableDeclarationStatement();
    new Visitor() {
      public void visit(Object element) {
        result.addVariable((LocalVariable)((LocalVariable)element).clone());
      }
    }.applyTo(getVariables());
    return result;
  }

  public List<? extends Element> children() {
    return getVariables();
  }

  public int getIndexOf(Statement statement) {
    return (equals(statement) ? 1 : 0);
  }

  public int getNbStatements() {
    return 1;
  }
  
  public Set<Declaration> declarations() {
    Set<Declaration> result = new HashSet<Declaration>();
    result.addAll(getVariables());
    return result;
  }
  
  //COPIED FROM chameleon.core.type.Type
  public <T extends Declaration> Set<T> declarations(DeclarationSelector<T> selector) throws MetamodelException {
    Set<Declaration> tmp = declarations();
    Set<T> result = selector.selection(tmp);
    return result;
  }

}
