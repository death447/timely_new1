package com.timely.common.filter;

import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import com.timely.common.utils.AES;

/**
 * @category 解密hibernate中的敏感信息, 采用AES加密;如连接地址,用户名和密码
 * @author death447
 * 
 */
public class EncryptAnnotationSessionFactoryBean extends AnnotationSessionFactoryBean {
	// 是否使用加密敏感信息
	private boolean encrypt;

	@Override
	protected void postProcessMappings(Configuration config) throws HibernateException {
		if (encrypt) {
			Properties properties = config.getProperties();

			// hibernate.connection.url
			properties.setProperty(Environment.URL, AES.decrypt(properties.getProperty(Environment.URL)));

			// hibernate.connection.username
			properties.setProperty(Environment.USER, AES.decrypt(properties.getProperty(Environment.USER)));

			// hibernate.connection.password
			properties.setProperty(Environment.PASS, AES.decrypt(properties.getProperty(Environment.PASS)));
		}
		super.postProcessMappings(config);
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}
}
