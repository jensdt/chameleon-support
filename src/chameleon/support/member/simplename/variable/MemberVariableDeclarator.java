package chameleon.support.member.simplename.variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.member.Member;
import chameleon.core.type.Type;
import chameleon.core.type.TypeElement;
import chameleon.core.type.TypeElementImpl;
import chameleon.core.type.TypeReference;
import chameleon.core.variable.MemberVariable;
import chameleon.core.variable.RegularMemberVariable;

public class MemberVariableDeclarator extends TypeElementImpl<MemberVariableDeclarator,Type> implements TypeElement<MemberVariableDeclarator, Type>, VariableDeclarator<MemberVariableDeclarator,MemberVariable> {

	public Type getNearestType() {
		return parent().getNearestType();
	}

	public List<Element> children() {
		List<Element> result = new ArrayList<Element>();
		return result;
	}
	
	public List<VariableDeclaration<MemberVariable>> declarations() {
		return _declarations.getOtherEnds();
	}
	
	public void add(VariableDeclaration<MemberVariable> declaration) {
		if(declaration != null) {
			_declarations.add(declaration.parentLink());
		}
	}
	
	public void remove(VariableDeclaration declaration) {
		
	}
	
	private OrderedReferenceSet<MemberVariableDeclarator, VariableDeclaration<MemberVariable>> _declarations = new OrderedReferenceSet<MemberVariableDeclarator, VariableDeclaration<MemberVariable>>(this);

	public MemberVariable createVariable(SimpleNameSignature signature, Expression expression) {
		return new RegularMemberVariable(signature, typeReference().clone(),expression);
	}

	@Override
	public MemberVariableDeclarator clone() {
		MemberVariableDeclarator result = new MemberVariableDeclarator();
		// TODO
		return result;
	}

	public Set<Member> getIntroducedMembers() {
		Set<Member> result = new HashSet();
		for(VariableDeclaration<MemberVariable> declaration: declarations()) {
			result.add(declaration.variable());
		}
		return result;
	}

	/**
	 * TYPE
	 */
	private Reference<MemberVariableDeclarator,TypeReference> _typeReference = new Reference<MemberVariableDeclarator,TypeReference>(this);

  public Type type() throws MetamodelException {
  	return typeReference().getType();
  }
	
  public TypeReference typeReference() {
    return _typeReference.getOtherEnd();
  }

  public void setTypeReference(TypeReference type) {
    _typeReference.connectTo(type.parentLink());
  }

}
