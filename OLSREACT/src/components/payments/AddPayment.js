import React, { useState, useContext } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import {
  Container,
  TextField,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Button,
  Alert,
  Box,
  Typography,
} from "@mui/material";
import { MdPayment, MdErrorOutline, MdCheckCircle } from "react-icons/md";
import { toast } from "react-toastify";

const AddPayment = () => {
  const { authState } = useContext(AuthContext);
  const navigate = useNavigate();

  const [orderId, setOrderId] = useState("");
  const [payMethod, setPayMethod] = useState("CASH ON DELIVERY");
  const [payStatus, setPayStatus] = useState("PAID");
  const [error, setError] = useState(null);

  const handlePayment = async (e) => {
    e.preventDefault();
    setError(null);

    try {
      const token = localStorage.getItem("jwtToken");
      if (!token) {
        throw new Error("Authentication token is missing. Please log in again.");
      }

      await axios.post(
        `http://localhost:8082/auth/user/api/payments/process`,
        null,
        {
          params: { orderId, payMethod, payStatus },
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      toast.success("Payment processed successfully!");
      navigate("/user/payments");
    } catch (error) {
      console.error("Payment processing failed:", error);
      setError(error.response?.data || "Failed to process payment.");
      toast.error("Failed to process payment.");
    }
  };

  return (
    <Container maxWidth="sm">
      <Box
        sx={{
          mt: 8,
          p: 4,
          bgcolor: "#f9f9f9",
          borderRadius: 2,
          boxShadow: 3,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <Typography variant="h4" fontWeight="bold" sx={{ color: "#1976D2", mb: 3 }}>
          <MdPayment size={32} style={{ marginRight: 8 }} />
          Process Payment
        </Typography>

        {error && (
          <Alert severity="error" sx={{ mb: 2, display: "flex", alignItems: "center" }}>
            <MdErrorOutline size={20} style={{ marginRight: 8 }} />
            {error}
          </Alert>
        )}

        <Box component="form" onSubmit={handlePayment} sx={{ width: "100%", display: "flex", flexDirection: "column", gap: 2 }}>
          <TextField
            label="Order ID"
            type="number"
            value={orderId}
            onChange={(e) => setOrderId(e.target.value)}
            fullWidth
            required
          />

         {/* Payment Method Dropdown */}
<FormControl fullWidth variant="outlined" sx={{ mt: 2 }}>
  <InputLabel id="pay-method-label">Payment Method</InputLabel>
  <Select
    labelId="pay-method-label"
    value={payMethod}
    onChange={(e) => setPayMethod(e.target.value)}
    label="Payment Method"
  >
    <MenuItem value="CASH ON DELIVERY">Cash on Delivery</MenuItem>
    <MenuItem value="CREDIT CARD">Credit Card</MenuItem>
    <MenuItem value="UPI">UPI</MenuItem>
  </Select>
</FormControl>

{/* Payment Status Dropdown */}
<FormControl fullWidth variant="outlined" sx={{ mt: 2 }}>
  <InputLabel id="pay-status-label">Payment Status</InputLabel>
  <Select
    labelId="pay-status-label"
    value={payStatus}
    onChange={(e) => setPayStatus(e.target.value)}
    label="Payment Status"
  >
    <MenuItem value="PAID">Paid</MenuItem>
    <MenuItem value="PENDING">Pending</MenuItem>
  </Select>
</FormControl>

          {/* Process Payment Button */}
          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{
              bgcolor: "#1976D2",
              color: "#FFF",
              mt: 2,
              "&:hover": { bgcolor: "#1258A5" },
            }}
          >
            <MdCheckCircle size={20} style={{ marginRight: 8 }} />
            Process Payment
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default AddPayment;
