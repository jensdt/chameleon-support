package chameleon.support.member.simplename;

import java.util.ArrayList;
import java.util.List;

import org.rejuse.association.OrderedReferenceSet;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Signature;
import chameleon.core.element.Element;
import chameleon.core.method.Method;
import chameleon.core.method.MethodSignature;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;

public class SimpleNameMethodSignature extends MethodSignature<SimpleNameMethodSignature, Method>{

  public SimpleNameMethodSignature(String name) {
    setName(name);
  }
  
  public String getName() {
    return _name;
  }
  
  public void setName(String name) {
    _name = name;
  }
  
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

private OrderedReferenceSet<SimpleNameMethodSignature,TypeReference> _parameters = new OrderedReferenceSet<SimpleNameMethodSignature,TypeReference>(this);

  
  @Override
  public SimpleNameMethodSignature clone() {
  	SimpleNameMethodSignature result = new SimpleNameMethodSignature(getName());
  	for(TypeReference ref: typeReferences()) {
  		result.add(ref.clone());
  	}
  	return result;
  }

	public boolean sameAs(Signature other) throws MetamodelException {
		boolean result = false;
		if(other instanceof SimpleNameMethodSignature) {
			SimpleNameMethodSignature sig = (SimpleNameMethodSignature) other;
			result = getName().equals(sig.getName()) && sameParameterTypesAs(sig);
		}
		return result;
	}

	@Override
	public List<Type> parameterTypes() throws MetamodelException {
		List<Type> result = new ArrayList<Type>();
  	for(TypeReference ref: typeReferences()) {
  		result.add(ref.getType());
  	}
		return result;
	}

	public List<? extends Element> children() {
		return typeReferences();
	}


}
