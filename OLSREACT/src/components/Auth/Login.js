import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { AuthContext } from '../../context/AuthContext';
import { jwtDecode } from 'jwt-decode';
import {
    Box, Button, Card, CardContent, Container, TextField, Typography
} from '@mui/material';
import '../../styles/Login.css';


const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { setAuthState } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8082/auth/generateToken', { username, password });

            if (response.data) {
                const { token } = response.data;
                const decodedToken = jwtDecode(token);
                const usernameFromToken = decodedToken.sub;
                const rolesFromToken = decodedToken.roles || [];

                localStorage.setItem('jwtToken', token);
                localStorage.setItem('roles', JSON.stringify(rolesFromToken));
                localStorage.setItem('username', usernameFromToken);

                setAuthState({ isAuthenticated: true, token, roles: rolesFromToken, username: usernameFromToken });

                navigate(usernameFromToken === "Mohi" ? '/AdminDashboard' : '/UserDashboard');
            }
        } catch (err) {
            console.error("Login failed:", err);
            toast.error("Invalid credentials. Please try again.");
        }
    };

    return (
        <Box className="login-container">
            <Container maxWidth="sm">
                <Card className="login-box" elevation={10}>
                    <CardContent>
                        <Typography variant="h4" component="h1" gutterBottom align="center">
                            Login
                        </Typography>
                        <form onSubmit={handleSubmit}>
                            <TextField
                                label="Username"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                            <TextField
                                label="Password"
                                type="password"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                            <Button type="submit" variant="contained" color="primary" fullWidth size="large" sx={{ mt: 2 }}>
                                Login
                            </Button>
                        </form>
                        <Button 
                            color="secondary"
                            fullWidth
                            sx={{ mt: 2 }}
                            onClick={() => navigate('/register')}
                        >
                            Register
                        </Button>
                    </CardContent>
                </Card>
            </Container>
            <ToastContainer />
        </Box>
    );
};

export default Login;
