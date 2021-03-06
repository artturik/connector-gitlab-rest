/**
 * Copyright (c) 2010-2017 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evolveum.polygon.connector.gitlab.rest;

/**
 * @author Lukas Skublik
 *
 */

import org.identityconnectors.common.StringUtil;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;
import org.identityconnectors.framework.spi.StatefulConfiguration;

public class GitlabRestConfiguration extends AbstractConfiguration implements StatefulConfiguration{


	private String loginUrl;
	private GuardedString privateToken;
	private static final Log LOGGER = Log.getLog(GitlabRestConnector.class);



	@ConfigurationProperty(order = 1, displayMessageKey = "privateToken.display", helpMessageKey = "privateToken.help", required = false, confidential = true)
	public GuardedString getPrivateToken() {
		return privateToken;
	}
	/**
	 * Setter method for the "privateToken" attribute.
	 *
	 * @param privateToken
	 * the privateToken string value.
	 */
	public void setPrivateToken(GuardedString privateToken) {
		this.privateToken = privateToken;
	}

	@ConfigurationProperty(order = 3, displayMessageKey = "loginUrl.display", helpMessageKey = "loginUrl.help", required = false, confidential = false)
	public String getLoginURL() {
		return loginUrl;
	}

	public void setLoginURL(String loginURL) {
		this.loginUrl = loginURL;
	}

	@Override
	public void validate() {
		LOGGER.info("Processing trough configuration validation procedure.");
		if (StringUtil.isBlank(loginUrl)) {
			throw new ConfigurationException("Login url cannot be empty.");
		}
		if ("".equals(privateToken)) {
			throw new ConfigurationException("Private Token cannot be empty.");
		}
		LOGGER.info("Configuration valid");
	}
	
	@Override
	public void release() {
		LOGGER.info("The release of configuration resources is being performed");
		this.loginUrl = null;
		this.privateToken.dispose();
	}

	@Override
	public String toString() {
		return "ScimConnectorConfiguration{" +
				", loginUrl='" + loginUrl + '\'' +
				'}';
	}
}