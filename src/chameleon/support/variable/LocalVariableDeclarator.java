package chameleon.support.variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.context.Context;
import chameleon.core.context.LexicalContext;
import chameleon.core.context.TargetContext;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.ChameleonProgrammerException;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.modifier.Modifier;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.support.statement.ForInit;

public class LocalVariableDeclarator extends  Statement<LocalVariableDeclarator> implements VariableDeclarator<LocalVariableDeclarator,LocalVariable,StatementContainer>, ForInit<LocalVariableDeclarator, StatementContainer> {

	
	public LocalVariableDeclarator() {
		
	}
	
	public Set<LocalVariable> variables() {
		Set<LocalVariable> result = new HashSet();
		for(VariableDeclaration<LocalVariable> declaration: variableDeclarations()) {
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
			result.addAll(variableDeclarations());
			result.add(typeReference());
			return result;
	}

	@Override
	public LocalVariableDeclarator clone() {
		LocalVariableDeclarator result = new LocalVariableDeclarator(typeReference().clone());
		for(VariableDeclaration<LocalVariable> declaration: variableDeclarations()) {
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

	public List<VariableDeclaration<LocalVariable>> variableDeclarations() {
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

  /**
   * The declarations of a local variable declaration are the declared variables.
   */
 /*@
   @ public behavior
   @ 
   @ post \result == variables();
   @*/
	public Set<? extends Declaration> declarations() throws MetamodelException {
		return variables();
	}
	
	/**
	 * The context of a local variable declarator takes the order of the declarations
	 * into account. For example in 'int a=..., b=..., c=...', the initialization of each variable can reference the 
	 * variables to its left.Thus, 'int a=3,b=a,c=b' is valid, while in 'int a=b, b=c,c=3' the intializations of 
	 * both a and b are invalid.
	 * 
	 * @throws MetamodelException 
	 */
 /*@
   @ public behavior
   @
   @ post declarations().indexOf(element) == 0) ==> \result == parent().lexicalContext(this);
   @ post declarations().indexOf(Element) > 0) ==> 
   @      \result == declarations().elementAt(declarations().indexOf(element) - 1).lexicalContext();
   @*/
	public Context lexicalContext(Element element) throws MetamodelException {
		List<VariableDeclaration<LocalVariable>> declarations = variableDeclarations();
		int index = declarations.indexOf(element);
		if(index == 0) {
			return parent().lexicalContext(this);
		} else if (index > 0) {
			return declarations.get(index-1).lexicalContext();
		} else {
		  throw new ChameleonProgrammerException("Invoking lexicalContext(element) with an element that is not a child.");
		}
	}

}
