package chameleon.support.rule.member;

import org.rejuse.property.PropertySet;

import chameleon.core.element.Element;
import chameleon.core.language.Language;
import chameleon.core.member.Member;
import chameleon.core.property.PropertyRule;

public class MemberOverridableByDefault extends PropertyRule {

	public PropertySet<Element> suggestedProperties(Element element) {
		return createSet(language().OVERRIDABLE);
	}
	
	public boolean appliesTo(Element element) {
		return element instanceof Member;
	}

}
