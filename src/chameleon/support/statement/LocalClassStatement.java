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

import org.rejuse.association.Reference;

import chameleon.core.context.ContextFactory;
import chameleon.core.context.DeclarationSelector;
import chameleon.core.context.LookupException;
import chameleon.core.declaration.Declaration;
import chameleon.core.scope.Scope;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.statement.StatementListContainer;
import chameleon.core.statement.StatementListScope;
import chameleon.core.statement.TypeDeclaringStatement;
import chameleon.core.type.Type;
import chameleon.core.type.TypeContainer;
import chameleon.support.property.accessibility.HierarchyScope;
import chameleon.util.Util;

/**
 * @author marko
 */
public class LocalClassStatement extends Statement<LocalClassStatement> 
             implements TypeContainer<LocalClassStatement,StatementContainer>, TypeDeclaringStatement {

	public LocalClassStatement() {
	}

	public LocalClassStatement(Type type) {
		setType(type);
	}

  /*********
   * CLASS *
   *********/

	private Reference<LocalClassStatement,Type> _type = new Reference<LocalClassStatement,Type>(this);

	public void setType(Type type) {
    _type.connectTo(type.parentLink());
  }

  public Type getType() {
    return _type.getOtherEnd();
  }

  public Reference<LocalClassStatement,Type> getTypesLink() {
    return _type;
  }

	public LocalClassStatement clone() {
		return new LocalClassStatement(getType().clone());
	}

	/*@
	 @ also public behavior
	 @
	 @ post getType() != null ==> \result.contains(getType());
	 @*/
	public List children() {
		return Util.createNonNullList(getType());
	}

	public Scope getTypeAccessibilityDomain() throws LookupException {
		if (parent() instanceof StatementListContainer) {
			return new StatementListScope((StatementListContainer) parent(), this);
		} else {
			return new HierarchyScope(getType());
		}
	}

	public Type getTopLevelType() {
		return null;
	}

	public ContextFactory getContextFactory() {
		return language().contextFactory();
	}
  public Set<Declaration> declarations() {
    Set<Declaration> result = new HashSet<Declaration>();
    result.add(getType());
    return result;
  }
  
  //COPIED FROM chameleon.core.type.Type
  public <T extends Declaration> Set<T> declarations(DeclarationSelector<T> selector) throws LookupException {
    Set<Declaration> tmp = declarations();
    Set<T> result = selector.selection(tmp);
    return result;
  }

}
