package org.recap.security;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dharmendrag on 2/2/17.
 */
public class UserServiceImplUT extends BaseTestCase{

    @Autowired
    public UserService userService;

    @Test
    public void testPermissions(){
        Map<Integer,String> permissionsMap=userService.getPermissions();
        assertTrue(permissionsMap.containsKey(1));
        assertTrue(permissionsMap.containsValue("Create User"));
        assertEquals(permissionsMap.get(1),"Create User");
    }
}
