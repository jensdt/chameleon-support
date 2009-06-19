package chameleon.support.variable;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.OrderedReferenceSet;
import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;

public class LocalVariableDeclarator extends  Statement<LocalVariableDeclarator> implements VariableDeclarator<LocalVariableDeclarator,LocalVariable,StatementContainer> {

	
	public LocalVariableDeclarator() {
		
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
		return new LocalVariable(signature, typeReference().clone(),expression);
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

}
