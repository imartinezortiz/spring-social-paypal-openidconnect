package org.springframework.social.openidconnect;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Builder is used as there are minimum set of parameters needed and optional parameters also present. Perfect case to
 * use builder pattern.
 * 
 * @author abprabhakar
 * 
 */
public class PayPalConnectionFactoryBuilder {

    /**
     * AppId given by devportal while registration.
     */
    private String appId;

    /**
     * AppSecret given by devportal while registration.
     */
    private String appSecret;

    /**
     * Scope determines what values are shared by PayPal Access via UserInfo endpoint.
     */
    private String scope;

    /**
     * Authorization url if you do not want to use default.
     */
    private String authUrl;

    /**
     * Token Url if you do not want to use default.
     */
    private String tokenUrl;

    /**
     * User Info url if you do not want to use default.
     */
    private String userInfoUrl;

    /**
     * Default constructor for builder.
     * 
     * @return {@code PayPalConnectionFactoryBuilder}
     */
    public static PayPalConnectionFactoryBuilder builder() {
        return new PayPalConnectionFactoryBuilder();
    }

    /**
     * Builds a {@link PayPalConnectionFactory}. AppId, AppSecret and scope are mandatory values, hence asserts for the
     * same. Other properteis are optional, but you need to set all 3 urls together. Assertion for that requirement is
     * done, only if one of the 3 urls are not null.
     * 
     * @return {@link PayPalConnectionFactory}
     */
    public PayPalConnectionFactory build() {
        Assert.hasText(appId, "AppId is required");
        Assert.hasText(appSecret, "appSecret is required");
        Assert.hasText(scope, "Minimum scope is requried");
        PayPalConnectionFactory factory;
        if (StringUtils.hasText(authUrl) || StringUtils.hasText(tokenUrl) || StringUtils.hasText(userInfoUrl)) {
            Assert.hasText(authUrl, "AuthUrl, tokenUrl and userInfo all are required");
            Assert.hasText(tokenUrl, "AuthUrl, tokenUrl and userInfo all are required");
            Assert.hasText(userInfoUrl, "AuthUrl, tokenUrl and userInfo all are required");
            factory = new PayPalConnectionFactory(appId, appSecret, scope, authUrl, tokenUrl, userInfoUrl);
        } else {
            factory = new PayPalConnectionFactory(appId, appSecret, scope);
        }
        return factory;
    }

    /**
     * Sets App Id.
     * 
     * @param appId - App Id
     * @return - {@link PayPalConnectionFactoryBuilder}
     */
    public PayPalConnectionFactoryBuilder withAppId(String appId) {
        this.appId = appId;
        return this;
    }

    /**
     * Sets App Secret.
     * 
     * @param appSecret - App Secret
     * @return - {@link PayPalConnectionFactoryBuilder}
     */
    public PayPalConnectionFactoryBuilder withAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    /**
     * Sets scope. Please make sure you provide valid scopes otherwise it will error out.
     * 
     * @param scope - scope
     * @return - {@link PayPalConnectionFactoryBuilder}
     */
    public PayPalConnectionFactoryBuilder withScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * Sets Auth url. Only set this if you want to override default.
     * 
     * @param authUrl - Authorize URL for PP Access.
     * @return - {@link PayPalConnectionFactoryBuilder}
     */
    public PayPalConnectionFactoryBuilder withAuthUrl(String authUrl) {
        this.authUrl = authUrl;
        return this;
    }

    /**
     * Sets Token url. Only set this if you want to override default.
     * 
     * @param tokenUrl - Token URL for PP Access.
     * @return - {@link PayPalConnectionFactoryBuilder}
     */
    public PayPalConnectionFactoryBuilder withTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
        return this;
    }

    /**
     * Sets User Info url. Only set this if you want to override default.
     * 
     * @param userInfoUrl - User Info URL for PP Access.
     * @return - {@link PayPalConnectionFactoryBuilder}
     */
    public PayPalConnectionFactoryBuilder withUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
        return this;
    }

}
