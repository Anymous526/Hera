package com.justinmobile.security.intercept.support;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;
import org.springframework.security.AuthenticationServiceException;
import org.springframework.security.providers.dao.salt.ReflectionSaltSource;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.util.ReflectionUtils;

import com.justinmobile.security.userdetails.UserWithSalt;

public class ReflectionSaltSourceEx extends ReflectionSaltSource {
	
	 public Object getSalt(UserDetails ud) {
		 
		   	UserWithSalt user = (UserWithSalt)ud;
	        Method saltMethod = findSaltMethod(user);

	        try {
	            return saltMethod.invoke(user, new Object[] {});
	        } catch (Exception exception) {
	            throw new AuthenticationServiceException(exception.getMessage(), exception);
	        }
	    }

	    private Method findSaltMethod(UserDetails ud) {
	    	UserWithSalt user = (UserWithSalt)ud;
	        Method saltMethod = ReflectionUtils.findMethod(user.getClass(), getUserPropertyToUse(), new Class[0]);

	        if (saltMethod == null) {
	            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(user.getClass(), getUserPropertyToUse());

	            if (pd != null) {
	                saltMethod = pd.getReadMethod();
	            }

	            if (saltMethod == null) {
	                throw new AuthenticationServiceException("Unable to find salt method on user Object. Does the class '" +
	                    user.getClass().getName() + "' have a method or getter named '" + getUserPropertyToUse() + "' ?");
	            }
	        }

	        return saltMethod;
	    }
}
