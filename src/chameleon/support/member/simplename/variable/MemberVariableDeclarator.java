package chameleon.support.member.simplename.variable;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.association.Reference;

import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
import chameleon.core.modifier.Modifier;
import chameleon.core.type.Type;
import chameleon.core.type.TypeElement;
import chameleon.core.type.TypeElementImpl;
import chameleon.core.type.TypeReference;
import chameleon.core.variable.MemberVariable;
import chameleon.core.variable.RegularMemberVariable;
import chameleon.support.variable.VariableDeclaration;
import chameleon.support.variable.VariableDeclarator;

public class MemberVariableDeclarator extends TypeElementImpl<MemberVariableDeclarator,Element> implements TypeElement<MemberVariableDeclarator, Element>, VariableDeclarator<MemberVariableDeclarator,MemberVariable,Element> {

	public MemberVariableDeclarator() {
		
	}
	
	public MemberVariableDeclarator(TypeReference tref) {
		setTypeReference(tref);
	}
	
	public List<Element> children() {
		List<Element> result = new ArrayList<Element>();
		result.addAll(variableDeclarations());
		result.add(typeReference());
		return result;
	}
	
	public List<VariableDeclaration<MemberVariable>> variableDeclarations() {
		return _declarations.getOtherEnds();
	}
	
	public void add(VariableDeclaration<MemberVariable> declaration) {
		if(declaration != null) {
			_declarations.add(declaration.parentLink());
		}
	}
	
	public void remove(VariableDeclaration declaration) {
		if(declaration != null) {
			_declarations.remove(declaration.parentLink());
		}
	}
	
	private OrderedReferenceSet<MemberVariableDeclarator, VariableDeclaration<MemberVariable>> _declarations = new OrderedReferenceSet<MemberVariableDeclarator, VariableDeclaration<MemberVariable>>(this);

	public MemberVariable createVariable(SimpleNameSignature signature, Expression expression) {
		MemberVariable result = new RegularMemberVariable(signature, typeReference().clone(),expression);
		for(Modifier mod: modifiers()) {
			result.addModifier(mod);
		}
		return result;
	}

	@Override
	public MemberVariableDeclarator clone() {
		MemberVariableDeclarator result = new MemberVariableDeclarator(typeReference().clone());
		for(VariableDeclaration<MemberVariable> declaration: variableDeclarations()) {
			result.add(declaration.clone());
		}
		return result;
	}

	public List<MemberVariable> getIntroducedMembers() {
		List<MemberVariable> result = new ArrayList<MemberVariable>();
		for(VariableDeclaration<MemberVariable> declaration: variableDeclarations()) {
			result.add(declaration.variable());
		}
		return result;
	}

	/**
	 * TYPE
	 */
	private Reference<MemberVariableDeclarator,TypeReference> _typeReference = new Reference<MemberVariableDeclarator,TypeReference>(this);

  public Type type() throws LookupException {
  	return typeReference().getType();
  }
	
  public TypeReference typeReference() {
    return _typeReference.getOtherEnd();
  }

  public void setTypeReference(TypeReference type) {
    _typeReference.connectTo(type.parentLink());
  }

	public List<? extends Declaration> declarations() throws LookupException {
		return getIntroducedMembers();
	}

	public <D extends Declaration> List<D> declarations(DeclarationSelector<D> selector) throws LookupException {
		return selector.selection(declarations());
	}

}
