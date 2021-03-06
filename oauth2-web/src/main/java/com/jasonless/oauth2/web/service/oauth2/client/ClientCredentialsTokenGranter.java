package com.jasonless.oauth2.web.service.oauth2.client;

import com.jasonless.oauth2.api.entity.domain.Oauth2GrantType;
import com.jasonless.oauth2.common.core.exception.Oauth2ErrorType;
import com.jasonless.oauth2.web.entity.dto.TokenRequest;
import com.jasonless.oauth2.web.exception.Oauth2Exception;
import com.jasonless.oauth2.web.service.oauth2.AbstractOauth2TokenGranter;
import com.jasonless.oauth2.web.util.OAuth2Util;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author LiuShiZeng
 */
@Service
public class ClientCredentialsTokenGranter extends AbstractOauth2TokenGranter {

    public ClientCredentialsTokenGranter() {
        super(Oauth2GrantType.CLIENT_CREDENTIALS.getValue());
    }


    @Override
    protected void verifyInfo( TokenRequest tokenRequest) throws Oauth2Exception {
        super.verifyInfo(tokenRequest);
        String clientSecret = tokenRequest.getRequestParameters().get(OAuth2Util.CLIENT_SECRET);
        if(clientSecret!=null){
            if(!tokenRequest.getOauthClientDetailDTO().getSecretKey().equals(clientSecret)){
                throw new Oauth2Exception(Oauth2ErrorType.PARAMETER_ERROR,"传入client_secret不同");
            }
        }else{
            throw new Oauth2Exception(Oauth2ErrorType.MISSING_PARAMETER,"缺少client_secret");
        }

    }





}
