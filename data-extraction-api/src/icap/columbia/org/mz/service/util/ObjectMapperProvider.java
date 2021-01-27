/**
 * 
 */
package icap.columbia.org.mz.service.util;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author simone
 *
 */
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;

	public ObjectMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return defaultObjectMapper;
	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.configure(SerializationFeature.INDENT_OUTPUT, true);
		result.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		result.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		// result.registerModule(hibernateModule(false));

		return result;
	}

	// private static Hibernate5Module hibernateModule(boolean forceLazyLoading) {
	// Hibernate5Module mod = new Hibernate5Module();
	// mod.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, forceLazyLoading);
	//
	// // Mappeia tambem os atributos Transient
	// // mod.enable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
	// return mod;
	// }
	
	
}
