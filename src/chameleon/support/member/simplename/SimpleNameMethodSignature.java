package chameleon.support.member.simplename;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.OrderedMultiAssociation;

import chameleon.core.declaration.Signature;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.MethodSignature;
import chameleon.core.namespace.NamespaceElement;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;

public class SimpleNameMethodSignature extends MethodSignature<SimpleNameMethodSignature, NamespaceElement>{

  public SimpleNameMethodSignature(String name) {
    setName(name);
  }
  
  public String name() {
    return _name;
  }
  
  public void setName(String name) {
    _name = name;
    _nameHash = _name.hashCode();
  }
  
  public int nameHash() {
  	return _nameHash;
  }
  
  private int _nameHash;
  
  private String _name;

///*********************
//* FORMAL PARAMETERS *
//*********************/
//
public List<TypeReference> typeReferences() {
 return _parameters.getOtherEnds();
}


public void add(TypeReference arg) {
 _parameters.add(arg.parentLink());
}

public int getNbTypeReferences() {
 return _parameters.size();
}

private OrderedMultiAssociation<SimpleNameMethodSignature,TypeReference> _parameters = new OrderedMultiAssociation<SimpleNameMethodSignature,TypeReference>(this);

  
  @Override
  public SimpleNameMethodSignature clone() {
  	SimpleNameMethodSignature result = new SimpleNameMethodSignature(name());
  	for(TypeReference ref: typeReferences()) {
  		result.add(ref.clone());
  	}
  	return result;
  }

	public boolean sameAs(Signature other) throws LookupException {
		boolean result = false;
		if(other instanceof SimpleNameMethodSignature) {
			SimpleNameMethodSignature sig = (SimpleNameMethodSignature) other;
			result = name().equals(sig.name()) && sameParameterTypesAs(sig);
		}
		return result;
	}

	@Override
	public List<Type> parameterTypes() throws LookupException {
		List<Type> result = new ArrayList<Type>();
  	for(TypeReference ref: typeReferences()) {
  		result.add(ref.getType());
  	}
		return result;
	}

	public List<? extends Element> children() {
		return typeReferences();
	}

	@Override
	public VerificationResult verifySelf() {
		VerificationResult result = Valid.create();
		if(name() == null) {
			result = result.and(new BasicProblem(this, "The signature has no name."));
		}
		return result;
	}


}
