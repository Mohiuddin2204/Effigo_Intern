import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [authState, setAuthState] = useState({
        isAuthenticated: false,
        token: null,
        roles: "",
        username: ""  // Initialize as an empty string
    });

    useEffect(() => {
        const token = localStorage.getItem('jwtToken');
        const roles = localStorage.getItem('roles');
        const username = localStorage.getItem('username');  // Retrieve username from localStorage

        if (token && roles && username) {
            setAuthState({ isAuthenticated: true, token, roles, username });  // Set username along with other values
        }
    }, []);

    return (
        <AuthContext.Provider value={{ authState, setAuthState }}>
            {children}
        </AuthContext.Provider>
    );
};
