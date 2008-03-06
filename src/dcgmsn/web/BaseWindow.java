package dcgmsn.web;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Window;

/**
 * Base Windows' methods
 */
public class BaseWindow extends Window {

	private static final long serialVersionUID = 8916030599854399395L;

	/**
	 * Find the component identified by the Path
	 */
	protected Component getComponent(String path){
		return Path.getComponent(path);
	}
	
	/**
	 * Create a new component
	 * @param path the new component's path
	 * @param parent the parent component
	 * @param args the args passed to the new component
	 * @return the new component
	 */
	@SuppressWarnings("unchecked")
	protected Component createComponents(String path, Component parent,Map args){
		return getDesktop().getExecution().createComponents(path, parent, args);
	}
	
}
