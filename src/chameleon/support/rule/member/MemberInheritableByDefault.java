package chameleon.support.rule.member;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.member.Member;
import chameleon.core.property.ChameleonProperty;
import chameleon.core.property.PropertyRule;
import chameleon.oo.language.ObjectOrientedLanguage;

public class MemberInheritableByDefault extends PropertyRule {

	@Override
	public boolean appliesTo(Element element) {
		return element instanceof Member;
	}

	@Override
	public PropertySet<Element,ChameleonProperty> suggestedProperties(Element element) {
		return createSet(language(ObjectOrientedLanguage.class).INHERITABLE);
	}

}
