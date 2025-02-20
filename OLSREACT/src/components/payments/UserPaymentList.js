import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import {
  Container,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  TableContainer,
  Paper,
  Typography,
  CircularProgress,
  Alert,
  Button,
} from "@mui/material";
import { MdPayment, MdArrowBack, MdErrorOutline } from "react-icons/md";

const UserPaymentList = () => {
  const { authState } = useContext(AuthContext);
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [user, setUser] = useState(null);
  const [orders, setOrders] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!authState.token) {
      setError("Unauthorized access. Redirecting to login...");
      setTimeout(() => navigate("/login"), 2000);
      return;
    }
    fetchUserDetails();
  }, [authState.token]);

  const fetchUserDetails = async () => {
    try {
      const token = localStorage.getItem("jwtToken");
      if (!token) throw new Error("Token is missing. Please log in again.");

      const response = await axios.get(
        `http://localhost:8082/auth/user/api/users/username/${authState.username}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );

      const fetchedUser = response.data;
      if (!fetchedUser.userId) throw new Error("User ID not found.");

      setUser(fetchedUser);
      fetchUserPayments(fetchedUser.userId);
    } catch (error) {
      setError("Failed to retrieve user details. Please try again.");
      setLoading(false);
    }
  };

  const fetchUserPayments = async (userId) => {
    try {
      const token = localStorage.getItem("jwtToken");
      const response = await axios.get(
        `http://localhost:8082/auth/user/api/users/${userId}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );

      const userOrders = response.data.orders || [];
      const userPayments = userOrders
        .map((order) => order.payment)
        .filter((payment) => payment !== null);

      setOrders(userOrders);
      setPayments(userPayments);
    } catch (error) {
      setError("Failed to load payments. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container sx={{ mt: 5 }}>
      <Typography
        variant="h4"
        align="center"
        fontWeight="bold"
        sx={{ mb: 3, color: "#C08080" }} // Light Maroon Title
      >
        <MdPayment size={32} style={{ marginRight: 8 }} />
        Your Payments
      </Typography>

      {error && (
        <Alert severity="error" sx={{ mb: 3, display: "flex", alignItems: "center" }}>
          <MdErrorOutline size={20} style={{ marginRight: 8 }} />
          {error}
        </Alert>
      )}

      {loading ? (
        <div style={{ textAlign: "center", marginTop: "20px" }}>
          <CircularProgress sx={{ color: "#C08080" }} /> {/* Light Maroon Loader */}
        </div>
      ) : (
        <TableContainer component={Paper} sx={{ backgroundColor: "#F5F5F5" }}> {/* Light Gray Background */}
          <Table>
            <TableHead>
              <TableRow sx={{ backgroundColor: "#C08080" }}> {/* Light Maroon Header */}
                <TableCell sx={{ fontWeight: "bold", color: "#FFFFFF" }}>Payment ID</TableCell>
                <TableCell sx={{ fontWeight: "bold", color: "#FFFFFF" }}>Method</TableCell>
                <TableCell sx={{ fontWeight: "bold", color: "#FFFFFF" }}>Amount</TableCell>
                <TableCell sx={{ fontWeight: "bold", color: "#FFFFFF" }}>Order ID</TableCell>
                <TableCell sx={{ fontWeight: "bold", color: "#FFFFFF" }}>Status</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {payments.length > 0 ? (
                orders.map((order) => (
                  <TableRow
                    key={order.payment.payId}
                    sx={{
                      "&:hover": { backgroundColor: "#E0BFBF" }, // Lighter Maroon Hover Effect
                      transition: "background-color 0.3s ease",
                    }}
                  >
                    <TableCell>{order.payment.payId}</TableCell>
                    <TableCell>{order.payment.payMethod}</TableCell>
                    <TableCell>${order.payment.payAmount}</TableCell>
                    <TableCell>{order.orderId}</TableCell>
                    <TableCell>{order.payment.payStatus || "Pending"}</TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={5} align="center" sx={{ color: "gray" }}>
                    No payments found.
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      )}

      <div style={{ textAlign: "center", marginTop: "20px" }}>
        <Button
          variant="contained"
          sx={{
            backgroundColor: "#C08080", // Light Maroon Button
            color: "#FFF",
            "&:hover": { backgroundColor: "#A06060" }, // Slightly Darker Maroon Hover
          }}
          onClick={() => navigate("/UserDashboard")}
          startIcon={<MdArrowBack />}
        >
          Back to Dashboard
        </Button>
      </div>
    </Container>
  );
};

export default UserPaymentList;
