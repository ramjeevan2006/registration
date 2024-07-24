package com.some.ics.api.core.domain;

import com.some.ics.core.security.authentication.oauth2.server.configure.web.ConcreteUserPrincipal;
import com.some.ics.core.security.authentication.oauth2.server.configure.web.UserPrincipal;
import com.some.ics.core.security.authentication.oauth2.server.configure.web.UserRole;
import com.some.ics.core.security.authentication.oauth2.server.configure.web.UserClaim;

import java.util.List;
import java.util.stream.Collectors;

public class EntitlementMapper {
    
    public UserPrincipal mapToUserPrincipal(GetEntitlementsResponse response) {
        AccountMemberDetails details = response.getData();
        
        UserRole role = new UserRole() {
            @Override
            public String getValue() {
                return details.getPolicyAbbrv();
            }
        };
        
        List<UserClaim> claims = details.getEntitlements().stream().map(entitlement -> new UserClaim() {
            @Override
            public String getValue() {
                return entitlement.getEntitlementName();
            }
        }).collect(Collectors.toList());
        
        return new ConcreteUserPrincipal(role, claims);
    }
}
package com.some.ics.core.security.authentication.oauth2.server.configure.web;

import java.util.List;

public class ConcreteUserPrincipal extends UserPrincipal {

    public ConcreteUserPrincipal(UserRole role, List<UserClaim> claims) {
        super(role, claims);
    }
}
