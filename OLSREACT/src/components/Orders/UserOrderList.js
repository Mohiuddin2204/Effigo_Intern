import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import {
  Container,
  Card,
  CardContent,
  Typography,
  Box,
  Button,
  CircularProgress,
  Alert,
  Divider,
} from "@mui/material";
import { MdShoppingCart, MdOutlinePayment, MdArrowBack } from "react-icons/md";

const UserOrderList = () => {
  const { authState } = useContext(AuthContext);
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
    fetchUserOrders();
  }, [authState.token]);

  const fetchUserOrders = async () => {
    try {
      const token = localStorage.getItem("jwtToken");
      const response = await axios.get(
        `http://localhost:8082/auth/user/api/users/username/${authState.username}`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      setOrders(response.data.orders || []);
    } catch (error) {
      console.error("Error fetching orders:", error);
      setError("Failed to load orders. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" align="center" fontWeight="bold" color="primary">
        🛒 Your Orders
      </Typography>

      {error && <Alert severity="error">{error}</Alert>}

      {loading ? (
        <Box display="flex" justifyContent="center" mt={4}>
          <CircularProgress color="primary" />
        </Box>
      ) : (
        <Box
          sx={{
            display: "flex",
            flexWrap: "wrap",
            gap: 2,
            justifyContent: "center",
            mt: 3,
          }}
        >
          {orders.length > 0 ? (
            orders.map((order) => (
              <Card
                key={order.orderId}
                sx={{
                  width: 280, // Fixed width for small display
                  p: 2,
                  borderRadius: "12px",
                  boxShadow: "0px 5px 15px rgba(0, 0, 0, 0.1)",
                  backgroundColor: "#f9f9f9",
                  transition: "transform 0.2s ease-in-out, box-shadow 0.3s ease-in-out",
                  "&:hover": {
                    transform: "scale(1.05)",
                    boxShadow: "0px 8px 20px rgba(0, 0, 0, 0.15)",
                  },
                }}
              >
                <CardContent>
                  <Typography variant="h6" fontWeight="bold" sx={{ display: "flex", alignItems: "center" }}>
                    <MdShoppingCart size={20} style={{ marginRight: 8 }} />
                    #{order.orderId}
                  </Typography>

                  <Typography variant="body2" sx={{ mt: 1 }}>
                    <strong>Status:</strong> {order.orderStatus}
                  </Typography>
                  <Typography variant="body2">
                    <strong>Total:</strong> ${order.orderTotalAmount}
                  </Typography>

                  <Divider sx={{ my: 1 }} />

                  <Typography variant="subtitle2" fontWeight="bold">
                    📚 {order.course?.courseName || "N/A"}
                  </Typography>
                  <Typography variant="caption">
                    {order.course?.courseDescription || "No description available"}
                  </Typography>

                  <Divider sx={{ my: 1 }} />

                  <Typography variant="body2" sx={{ display: "flex", alignItems: "center" }}>
                    <MdOutlinePayment size={18} style={{ marginRight: 6, color: "#4caf50" }} />
                    <strong>Payment:</strong> {order.payment ? order.payment.payStatus : "Pending"}
                  </Typography>
                </CardContent>
              </Card>
            ))
          ) : (
            <Typography variant="h6" align="center" color="textSecondary">
              No orders found.
            </Typography>
          )}
        </Box>
      )}

      <Box textAlign="center" mt={4}>
        <Button
          variant="contained"
          color="secondary"
          onClick={() => navigate("/UserDashboard")}
          startIcon={<MdArrowBack />}
        >
          Back to Dashboard
        </Button>
      </Box>
    </Container>
  );
};

export default UserOrderList;
