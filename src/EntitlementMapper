package com.some.ics.api.core.domain;

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
        
        return new UserPrincipal(role, claims);
    }
}
