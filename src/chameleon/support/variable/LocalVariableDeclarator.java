package chameleon.support.variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.ChameleonProgrammerException;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.member.Member;
import chameleon.core.modifier.Modifier;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.core.type.TypeElement;
import chameleon.core.type.TypeReference;
import chameleon.core.variable.MemberVariable;

public class LocalVariableDeclarator extends  Statement<LocalVariableDeclarator> implements VariableDeclarator<LocalVariableDeclarator,LocalVariable,StatementContainer> {

	
	public LocalVariableDeclarator() {
		
	}
	
	public Set<LocalVariable> variables() {
		Set<LocalVariable> result = new HashSet();
		for(VariableDeclaration<LocalVariable> declaration: declarations()) {
			result.add(declaration.variable());
		}
		return result;
	}
	
	public LocalVariableDeclarator(TypeReference tref) {
		setTypeReference(tref);
	}

	@Override
	public List children() {
			List<Element> result = new ArrayList<Element>();
			result.addAll(declarations());
			result.add(typeReference());
			return result;
	}

	@Override
	public LocalVariableDeclarator clone() {
		LocalVariableDeclarator result = new LocalVariableDeclarator(typeReference().clone());
		for(VariableDeclaration<LocalVariable> declaration: declarations()) {
			result.add(declaration.clone());
		}
		return result;
	}

	public LocalVariable createVariable(SimpleNameSignature signature, Expression expression) {
		LocalVariable result = new LocalVariable(signature, typeReference().clone(),expression);
		for(Modifier mod: modifiers()) {
			result.addModifier(mod);
		}
    return result;
	}

	/**
	 * TYPE
	 */
	private Reference<LocalVariableDeclarator,TypeReference> _typeReference = new Reference<LocalVariableDeclarator,TypeReference>(this);

  public Type type() throws MetamodelException {
  	return typeReference().getType();
  }
	
  public TypeReference typeReference() {
    return _typeReference.getOtherEnd();
  }

  public void setTypeReference(TypeReference type) {
    _typeReference.connectTo(type.parentLink());
  }

	public List<VariableDeclaration<LocalVariable>> declarations() {
		return _declarations.getOtherEnds();
	}
	
	public void add(VariableDeclaration<LocalVariable> declaration) {
		if(declaration != null) {
			_declarations.add(declaration.parentLink());
		}
	}
	
	public void remove(VariableDeclaration declaration) {
		if(declaration != null) {
			_declarations.remove(declaration.parentLink());
		}
	}
	
	private OrderedReferenceSet<LocalVariableDeclarator, VariableDeclaration<LocalVariable>> _declarations = new OrderedReferenceSet<LocalVariableDeclarator, VariableDeclaration<LocalVariable>>(this);

	
	
	// COPIED FROM TypeElementImpl
	
	
  /*************
   * MODIFIERS *
   *************/
  private OrderedReferenceSet<LocalVariableDeclarator, Modifier> _modifiers = new OrderedReferenceSet<LocalVariableDeclarator, Modifier>(this);


  /**
   * Return the list of modifiers of this member.
   */
 /*@
   @ behavior
   @
   @ post \result != null;
   @*/
  public List<Modifier> modifiers() {
    return _modifiers.getOtherEnds();
  }

  public void addModifier(Modifier modifier) {
    if (modifier != null) {
    	if (!_modifiers.contains(modifier.parentLink())) {
    		_modifiers.add(modifier.parentLink());	
      }
    } else {
    	throw new ChameleonProgrammerException("Modifier passed to addModifier is null");
    }
  }
  
  public void addModifiers(List<Modifier> modifiers) {
  	if(modifiers == null) {
  		throw new ChameleonProgrammerException("List passed to addModifiers is null");
  	} else {
  		for(Modifier modifier: modifiers) {
  			addModifier(modifier);
  		}
  	}
  }

  public void removeModifier(Modifier modifier) {
  	if(modifier != null) {
      _modifiers.remove(modifier.parentLink());
  	} else {
  		throw new ChameleonProgrammerException("Modifier passed to removeModifier was null");
  	}
  }

  public boolean hasModifier(Modifier modifier) {
    return _modifiers.getOtherEnds().contains(modifier);
  }

}
