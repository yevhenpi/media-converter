package ua.pidopryhora.mediaconverter.requestmanager.model;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Map;

@Service
public class UserLimitsProvider {

    private static final long GUEST_MAX_FILE_SIZE = 1_000_000L;
    private static final long BASIC_MAX_FILE_SIZE = 5_000_000L;
    private static final long PREMIUM_MAX_FILE_SIZE = 10_000_000L;


    private final Map<UserRole, UserLimits> limitsMap = Map.of(
            UserRole.GUEST, new UserLimits(GUEST_MAX_FILE_SIZE),
            UserRole.BASIC, new UserLimits(BASIC_MAX_FILE_SIZE),
            UserRole.PREMIUM, new UserLimits(PREMIUM_MAX_FILE_SIZE),
            UserRole.ADMIN, new UserLimits(Integer.MAX_VALUE)
    );

    public UserLimits getLimitsByRole(UserRole role){
        return limitsMap.get(role);
    }
}
