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

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.Reference;

import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.element.Element;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.LookupStrategy;
import chameleon.core.lookup.LookupStrategyFactory;
import chameleon.core.scope.Scope;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.statement.StatementListContainer;
import chameleon.core.statement.StatementListScope;
import chameleon.core.statement.TypeDeclaringStatement;
import chameleon.core.type.Type;
import chameleon.support.property.accessibility.HierarchyScope;
import chameleon.util.Util;

/**
 * @author marko
 */
public class LocalClassStatement extends Statement<LocalClassStatement> 
             implements DeclarationContainer<LocalClassStatement,StatementContainer>, TypeDeclaringStatement {

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

	public LookupStrategyFactory getContextFactory() {
		return language().lookupFactory();
	}
  public List<Declaration> declarations() {
    List<Declaration> result = new ArrayList<Declaration>();
    result.add(getType());
    return result;
  }
  
//  //COPIED FROM chameleon.core.type.Type
//  @SuppressWarnings("unchecked")
//  public <T extends Declaration> Set<T> declarations(DeclarationSelector<T> selector) throws LookupException {
//    List<Declaration> tmp = declarations();
//    List<T> result = selector.selection(tmp);
//    return result;
//  }

  public LookupStrategy lexicalContext(Element element) {
  	return language().lookupFactory().createLexicalContext(this, language().lookupFactory().createTargetContext(this));
  }
  
  public LookupStrategy linearContext() {
  	return lexicalContext(getType());
  }

	public <D extends Declaration> List<D> declarations(DeclarationSelector<D> selector) throws LookupException {
    List<D> result = new ArrayList<D>();
    D element = selector.selection(getType());
    if(element != null) {
      result.add(element);
    }
    return result;
	}
}
