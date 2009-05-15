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
package chameleon.support.variable;

import java.util.List;

import org.rejuse.predicate.TypePredicate;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.expression.Expression;
import chameleon.core.scope.Scope;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementListContainer;
import chameleon.core.statement.StatementListScope;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.core.variable.RegularVariable;
import chameleon.support.statement.LocalVariableDeclarationStatement;

/**
 * @author Marko van Dooren
 */
public class LocalVariable extends RegularVariable<LocalVariable,LocalVariableDeclarationStatement> {

  public LocalVariable(SimpleNameSignature sig, TypeReference type, Expression init) {
    super(sig, type, init);
  }

  public Type getNearestType() {
    return parent().getNearestType();
  }

  protected LocalVariable cloneThis() {
    Expression expr = null;
    if(getInitialization() != null) {
      expr = getInitialization().clone();
    }
    return new LocalVariable(signature().clone(), (TypeReference)getTypeReference().clone(), expr);
  }

  public Scope getAccessibilityDomain() throws MetamodelException {
    List ancestors = ancestors();
    new TypePredicate(StatementListContainer.class).filter(ancestors);
    return new StatementListScope((StatementListContainer)ancestors.get(ancestors.size() - 1), (Statement)parent());
  }
	
}
