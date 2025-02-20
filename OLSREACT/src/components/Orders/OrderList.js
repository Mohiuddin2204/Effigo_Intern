import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate, Link } from "react-router-dom";
import { Box, Container, Card, CardContent, Typography, Button, CircularProgress, Alert, Grid } from "@mui/material";
import { MdOutlinePayment, MdOutlineShoppingCart, MdPerson, MdBook } from "react-icons/md";

const OrderList = () => {
  const { authState, logout } = useContext(AuthContext);
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    if (!authState.token) {
      setError("Unauthorized access. Redirecting to login...");
      setTimeout(() => navigate("/login"), 2000);
      return;
    }
    fetchOrders();
  }, [authState.token]);

  const fetchOrders = async () => {
    try {
      const response = await axios.get("http://localhost:8082/auth/admin/api/orders", {
        headers: { Authorization: `Bearer ${authState.token}` },
      });
      setOrders(response.data);
    } catch (error) {
      console.error("Error fetching orders:", error);
      if (error.response?.status === 403) {
        setError("Access Denied: You do not have permission.");
      } else {
        setError("Failed to load orders. Please try again later.");
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
        All Orders
      </Typography>

      {error && <Alert severity="error">{error}</Alert>}

      {loading ? (
        <Box display="flex" justifyContent="center" mt={3}>
          <CircularProgress color="primary" />
        </Box>
      ) : (
        <Grid container spacing={3}>
          {orders.length > 0 ? (
            orders.map((order) => (
              <Grid item xs={12} sm={6} md={4} key={order.orderId}>
                <Card sx={{ borderLeft: "5px solid #3f51b5", boxShadow: 3 }}>
                  <CardContent>
                    <Typography variant="h6" gutterBottom>
                      <MdOutlineShoppingCart size={22} /> Order ID: {order.orderId}
                    </Typography>
                    <Typography>
                      <strong>Status:</strong> {order.orderStatus}
                    </Typography>
                    <Typography>
                      <MdPerson size={18} /> <strong>User:</strong> {order.user?.userName || "N/A"}
                    </Typography>
                    <Typography>
                      <MdBook size={18} /> <strong>Course:</strong> {order.course?.courseName || "N/A"}
                    </Typography>
                    <Typography>
                      <strong>Total Amount:</strong> ${order.orderTotalAmount}
                    </Typography>
                    <Typography>
                      <MdOutlinePayment size={18} /> <strong>Payment Status:</strong> {order.payment ? order.payment.payStatus : "Pending"}
                    </Typography>
                  </CardContent>
                </Card>
              </Grid>
            ))
          ) : (
            <Typography variant="body1" align="center" sx={{ width: "100%", color: "gray", mt: 2 }}>
              No orders found.
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

export default OrderList;
