import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate, Link } from "react-router-dom";
import { Box, Container, Card, CardContent, Typography, Button, CircularProgress, Alert, Grid } from "@mui/material";
import { MdPerson, MdEmail } from "react-icons/md";

const UserList = () => {
  const { authState, logout } = useContext(AuthContext);
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    if (!authState.token) {
      setError("Unauthorized access. Redirecting to login...");
      setTimeout(() => navigate("/login"), 2000);
      return;
    }
    fetchUsers();
  }, [authState.token]);

  const fetchUsers = async () => {
    try {
      const response = await axios.get("http://localhost:8082/auth/admin/api/users", {
        headers: { Authorization: `Bearer ${authState.token}` },
      });
      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching users:", error);
      if (error.response?.status === 403) {
        setError("Access Denied: You do not have permission.");
      } else {
        setError("Failed to load users. Please try again later.");
      }
      if (error.response?.status === 401) {
        logout();
        navigate("/login");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" gutterBottom align="center">
        All Users
      </Typography>

      {error && <Alert severity="error">{error}</Alert>}

      {loading ? (
        <Box display="flex" justifyContent="center" mt={3}>
          <CircularProgress color="primary" />
        </Box>
      ) : (
        <Grid container spacing={3}>
          {users.length > 0 ? (
            users.map((user) => (
              <Grid item xs={12} sm={6} md={4} key={user.userId}>
                <Card sx={{ borderLeft: "5px solid #3f51b5", boxShadow: 3 }}>
                  <CardContent>
                    <Typography variant="h6" gutterBottom>
                      <MdPerson size={22} /> {user.userName}
                    </Typography>
                    <Typography>
                      <strong>ID:</strong> {user.userId}
                    </Typography>
                    <Typography>
                      <MdEmail size={18} /> <strong>Email:</strong> {user.userEmail}
                    </Typography>
                  </CardContent>
                </Card>
              </Grid>
            ))
          ) : (
            <Typography variant="body1" align="center" sx={{ width: "100%", color: "gray", mt: 2 }}>
              No users found.
            </Typography>
          )}
        </Grid>
      )}

      <Box display="flex" justifyContent="center" mt={3}>
        <Button variant="contained" color="secondary" component={Link} to="/AdminDashboard">
          Back to Dashboard
        </Button>
      </Box>
    </Container>
  );
};

export default UserList;
