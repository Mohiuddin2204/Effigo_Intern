import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {
    Box, Button, Card, CardContent, Container, TextField, Typography, MenuItem
} from '@mui/material';
import '../../styles/Register.css';

const Register = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [roles, setRoles] = useState('ROLE_USER');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const user = { username, email, password, roles };

        try {
            await axios.post('http://localhost:8082/auth/addNewUser', user);
            toast.success("Registration successful!");
            navigate('/login');
        } catch (err) {
            toast.error("Registration failed! " + err.response?.data);
        }
    };

    return (
        <Box className="register-container">
            <Container maxWidth="sm">
                <Card className="register-box" elevation={10}>
                    <CardContent>
                        <Typography variant="h4" component="h1" gutterBottom align="center">
                            Register
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
                                label="Email"
                                type="email"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
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
                            <TextField
                                select
                                label="Role"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={roles}
                                onChange={(e) => setRoles(e.target.value)}
                            >
                                <MenuItem value="ROLE_USER">User</MenuItem>
                                <MenuItem value="ROLE_ADMIN">Admin</MenuItem>
                            </TextField>
                            <Button type="submit" variant="contained" color="primary" fullWidth size="large" sx={{ mt: 2 }}>
                                Register
                            </Button>
                        </form>
                        <Button
                            color="secondary"
                            fullWidth
                            sx={{ mt: 2 }}
                            onClick={() => navigate('/login')}
                        >
                            Back to Login
                        </Button>
                    </CardContent>
                </Card>
            </Container>
            <ToastContainer />
        </Box>
    );
};

export default Register;
