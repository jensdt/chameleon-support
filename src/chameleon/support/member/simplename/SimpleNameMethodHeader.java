package chameleon.support.member.simplename;

import java.util.List;

import chameleon.core.Config;
import chameleon.core.declaration.Signature;
import chameleon.core.method.MethodHeader;
import chameleon.core.namespace.NamespaceElement;
import chameleon.core.validation.BasicProblem;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
import chameleon.core.variable.FormalParameter;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.oo.type.TypeReference;

public class SimpleNameMethodHeader<E extends SimpleNameMethodHeader, P extends NamespaceElement, S extends SimpleNameMethodSignature> extends MethodHeader<E, P, S>{

  public SimpleNameMethodHeader(String name) {
    setName(name);
  }
  
  public String getName() {
    return _name;
  }
  
  public void setName(String name) {
    _name = name;
  }
  
  private String _name;

	@Override
	public E cloneThis() {
		return (E) new SimpleNameMethodHeader(getName());
	}

	private SimpleNameMethodSignature _signatureCache;
	
	@Override
	public S signature() {
		SimpleNameMethodSignature result;
		boolean cacheSignatures = Config.cacheSignatures();
		if(cacheSignatures) {
		  result = _signatureCache;
		} else {
			result = null;
		}
		if(result == null) {
			result = new SimpleNameMethodSignature(getName());
			result.setUniParent(parent());
			for(FormalParameter param: formalParameters()) {
				result.add(param.getTypeReference().clone());
			}
			if(cacheSignatures) {
				_signatureCache = result;
			}
		}
		return (S) result;
	}

	@Override
	public void flushLocalCache() {
		_signatureCache = null;
	}

	@Override
	public VerificationResult verifySelf() {
		VerificationResult result = Valid.create();
		if(getName() == null) {
			result = result.and(new BasicProblem(this, "The method has no name"));
		}
		return result;
	}

	@Override
	public E createFromSignature(Signature signature) {
		if(signature instanceof SimpleNameMethodSignature) {
			SimpleNameMethodSignature sig = (SimpleNameMethodSignature) signature;
			E result;
			List<TypeReference> typeReferences = sig.typeReferences();
			List<FormalParameter> params = formalParameters();
			int size = params.size();
			if(typeReferences.size() != size) {
				throw new ChameleonProgrammerException();
			} else {
				// clone and copy parameter types
				result = clone();
				result.setName(sig.name());
				params = result.formalParameters();
				for(int i=0; i <size; i++) {
					params.get(i).setTypeReference(typeReferences.get(i).clone());
				}
			}
			return result;
		} else {
  		throw new ChameleonProgrammerException("Setting wrong type of signature. Provided: "+(signature == null ? null :signature.getClass().getName())+" Expected SimpleNameSignature");
		}
	}

}
