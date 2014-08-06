/**
 * Waffle (https://github.com/dblock/waffle)
 *
 * Copyright (c) 2010 - 2014 Application Security, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Application Security, Inc.
 */
package waffle.windows.auth;

/**
 * Implements Windows authentication functions.
 * 
 * @author dblock[at]dblock[dot]org
 */
public interface IWindowsAuthProvider {

    /**
     * The LogonUser function attempts to log a user on to the local computer using a network logon type and the default
     * authentication provider.
     * 
     * @param username
     *            A string that specifies the name of the user in the UPN format.
     * @param password
     *            A string that specifies the plaintext password for the user account specified by username.
     * @return Windows identity.
     */
    IWindowsIdentity logonUser(final String username, final String password);

    /**
     * The LogonDomainUser function attempts to log a user on to the local computer using a network logon type and the
     * default authentication provider.
     * 
     * @param username
     *            A string that specifies the name of the user. This is the name of the user account to log on to. If
     *            you use the user principal name (UPN) format, user@DNS_domain_name, the domain parameter must be NULL.
     * @param domain
     *            A string that specifies the name of the domain or server whose account database contains the username
     *            account. If this parameter is NULL, the user name must be specified in UPN format. If this parameter
     *            is ".", the function validates the account by using only the local account database.
     * @param password
     *            A string that specifies the plaintext password for the user account specified by username.
     * @return Windows identity.
     */
    IWindowsIdentity logonDomainUser(final String username, final String domain, final String password);

    /**
     * The LogonDomainUserEx function attempts to log a user on to the local computer. The local computer is the
     * computer from which LogonUser was called. You cannot use LogonUser to log on to a remote computer. You specify
     * the user with a user name and domain and authenticate the user with a plaintext password.
     * 
     * @param username
     *            A string that specifies the name of the user. This is the name of the user account to log on to. If
     *            you use the user principal name (UPN) format, user@DNS_domain_name, the domain parameter must be NULL.
     * @param domain
     *            A string that specifies the name of the domain or server whose account database contains the username
     *            account. If this parameter is NULL, the user name must be specified in UPN format. If this parameter
     *            is ".", the function validates the account by using only the local account database.
     * @param password
     *            A string that specifies the plaintext password for the user account specified by username.
     * @param logonType
     *            The type of logon operation to perform.
     * @param logonProvider
     *            Specifies the logon provider.
     * @return Windows identity.
     */
    IWindowsIdentity logonDomainUserEx(final String username, final String domain, final String password,
            final int logonType, final int logonProvider);

    /**
     * Retrieve a security identifier (SID) for the account and the name of the domain or local computer on which the
     * account was found.
     * 
     * @param username
     *            Fully qualified or partial username.
     * @return Windows account.
     */
    IWindowsAccount lookupAccount(final String username);

    /**
     * Retrieve the current computer information.
     * 
     * @return Current computer information.
     */
    IWindowsComputer getCurrentComputer();

    /**
     * Retrieve a list of domains (Active Directory) on the local server.
     * 
     * @return A list of domains.
     */
    IWindowsDomain[] getDomains();

    /**
     * Attempts to validate the user using an SSPI token. This token is generated by the client via the
     * InitializeSecurityContext(package) method described in
     * http://msdn.microsoft.com/en-us/library/aa375509(VS.85).aspx
     * 
     * @param connectionId
     *            A unique connection id.
     * @param token
     *            The security token generated by the client wishing to logon.
     * @param securityPackage
     *            The name of the security package to use. Can be any security package supported by both the client and
     *            the server. This is usually set to "Negotiate" which will use SPNEGO to determine which security
     *            package to use. Other common values are "Kerberos" and "NTLM".
     * @return Windows account.
     */
    IWindowsSecurityContext acceptSecurityToken(final String connectionId, final byte[] token,
            final String securityPackage);

    /**
     * Reset a previously saved continuation security token for a given connection id.
     * 
     * @param connectionId
     *            Connection id.
     */
    void resetSecurityToken(final String connectionId);
}
