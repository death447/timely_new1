package com.timely.common;

import java.util.Map;
import java.util.Map.Entry;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author death447
 * @category 拦截器过滤字符串数据前后空格
 */
public class TrimInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
		for (Entry<String, Object> entry : parameters.entrySet()) {
			if (entry.getValue() instanceof String[]) {

				String[] valueArray = (String[]) entry.getValue();

				for (int i = 0; i < valueArray.length; i++) {
					valueArray[i] = valueArray[i].trim();
				}

				parameters.put(entry.getKey(), valueArray);
			}
		}
		invocation.invoke();
		return null;
	}
}
