package dcgmsn.web;

import org.zkoss.zkplus.spring.DelegatingVariableResolver;

public class DCBeanFactory {
	private static DelegatingVariableResolver resolver;
	
	/**
	 * Get the bean managed by the spring
	 * @param name the bean name
	 * @return
	 */
	public static Object getBean(String name){
		if(resolver==null){
			resolver = new DelegatingVariableResolver();
		}
		//return resolver.getVariable(name);
		return resolver.resolveVariable(name);
	}
}
