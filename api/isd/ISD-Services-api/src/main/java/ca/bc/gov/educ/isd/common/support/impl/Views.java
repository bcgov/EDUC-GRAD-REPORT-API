/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 * 
 *  Revision Control Information
 *  File:                Views.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common.support.impl;

/**
 * A centralized class to contain any views that need to be created in the
 * database.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class Views {

    /**
     * View for the Login Module to determine the SSO GUID for a given
     * principal.
     */
    public static final String ISD_LOGIN_SSO_GUID_VW =
            " CREATE OR REPLACE FORCE VIEW ISD_LOGIN_SSO_GUID_VW(PrincipalID, SSOGUID) AS "
            + " SELECT uup.displayname as PrincipalID, sso.accountguid as SSOGUID "
            + " FROM "
            + " ISD_USER_SSO_IDENTIFIER sso, "
            + " ISD_USER_USERPROFILE uup "
            + " WHERE "
            + " uup.user_userprofile_id = sso.user_userprofile_id"
            + " UNION " // MODIFIED 29-JUL-2016 
            + " SELECT DISPLAYNAME as PrincipalID, accountguid as SSOGUID " // MODIFIED 29-JUL-2016
            + " FROM ISD_TESTUSERS; "; // MODIFIED 29-JUL-2016 

    /**
     * View for the Login Module Common Logon Page.
     */
    public static final String ISD_LOGIN_PRINCIPALS_VW =
            " CREATE OR REPLACE FORCE VIEW ISD_LOGIN_PRINCIPALS_VW(PrincipalID, PASSWORD) AS "
            + " SELECT displayname as PrincipalID, '!k8h47#R$' as PASSWORD "
            + " FROM ISD_USER_USERPROFILE " // MODIFIED 07-JUN-2016 
            + " UNION " // MODIFIED 07-JUN-2016
            + " SELECT displayname as PrincipalID, PASSWORD " // MODIFIED 07-JUN-2016
            + " FROM ISD_TESTUSERS; "; // MODIFIED 07-JUN-2016

    /**
     * View for the Login Module to determine the Roles for a given principal.
     */
    public static final String ISD_LOGIN_ROLES_VW =
            " CREATE OR REPLACE FORCE VIEW ISD_LOGIN_ROLES_VW(PrincipalID, Role, RoleGroup) AS "
            + " SELECT uup.displayname as PrincipalID, ur.name as Role, urg.name as RoleGroup "
            + " FROM ISD_USER_USERPROFILE uup, ISD_USER_ROLE ur, ISD_USER_ROLE_ROLEGROUP urrg, "
            + " ISD_USER_ROLEGROUP urg, "
            + " ISD_USER_USERPROFILE_ROLEGROUP uuprg "
            + " WHERE "
            + " uup.user_userprofile_id = uuprg.user_userprofile_id "
            + " AND uuprg.user_rolegroup_id = urrg.rolegroups_user_rolegroup_id "
            + " AND ur.user_role_id = urrg.roles_user_role_id "
            + " AND urg.user_rolegroup_id = urrg.rolegroups_user_rolegroup_id "
            + " UNION " // for testuser in local/dev environments
            + " SELECT DISPLAYNAME as PrincipalID, ur2.name as Role, RoleGroup "
            + " FROM ISD_TESTUSERS itu, ISD_USER_ROLE ur2, ISD_USER_ROLE_ROLEGROUP urrg2, "
            + " ISD_USER_ROLEGROUP urg2, "
            + " ISD_USER_USERPROFILE_ROLEGROUP uuprg2 "
            + " WHERE "
            + " itu.rolegroup = urg2.name "
            + " AND ur2.user_role_id = urrg2.roles_user_role_id "
			+ " AND urg2.user_rolegroup_id = urrg2.rolegroups_user_rolegroup_id; ";

    /**
     * Views required for the Login Module and Common Logon Page.
     */
    public static final String[] LM_VIEWS = {ISD_LOGIN_SSO_GUID_VW, ISD_LOGIN_PRINCIPALS_VW, ISD_LOGIN_ROLES_VW};
}
