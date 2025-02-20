import React, { useState, useEffect, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import {
  Container,
  Typography,
  TextField,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Button,
  Alert,
  Box,
} from "@mui/material";
import { MdAddShoppingCart, MdErrorOutline, MdCheckCircle } from "react-icons/md";

const AddOrder = () => {
  const { authState } = useContext(AuthContext);
  const [courses, setCourses] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState("");
  const [orderStatus, setOrderStatus] = useState("");
  const [orderTotalAmount, setOrderTotalAmount] = useState("");
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const token = localStorage.getItem("jwtToken");
        const response = await axios.get("http://localhost:8082/auth/user/api/courses", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setCourses(response.data);
      } catch (error) {
        console.error("Error fetching courses!", error);
        setError("Failed to load courses.");
      }
    };
    fetchCourses();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!orderStatus || !orderTotalAmount || !selectedCourse) {
      setError("All fields are required!");
      return;
    }

    try {
      const token = localStorage.getItem("jwtToken");

      const responseForUserId = await axios.get(
        `http://localhost:8082/auth/user/api/users/username/${authState.username}`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      const fetchedUser = responseForUserId.data;
      if (!fetchedUser.userId) throw new Error("User ID not found.");

      const orderData = {
        user: { userId: fetchedUser.userId },
        course: { courseId: selectedCourse },
        orderStatus: orderStatus || "pending",
        orderTotalAmount: orderTotalAmount || 0,
      };

      await axios.post("http://localhost:8082/auth/user/api/orders", orderData, {
        headers: { Authorization: `Bearer ${token}`, "Content-Type": "application/json" },
      });

      setSuccessMessage("Order placed successfully!");
      setTimeout(() => navigate("/user/orders"), 2000);
    } catch (error) {
      console.error("Error creating order!", error);
      setError("Failed to create order.");
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        backgroundColor: "#f5f5f5",
      }}
    >
      <Container
        maxWidth="sm"
        sx={{
          p: 4,
          bgcolor: "#FFFFFF",
          borderRadius: 2,
          boxShadow: 3,
        }}
      >
        <Typography variant="h4" align="center" fontWeight="bold" sx={{ color: "#800000", mb: 3 }}>
          <MdAddShoppingCart size={32} style={{ marginRight: 8 }} />
          Add Order
        </Typography>

        {error && (
          <Alert severity="error" sx={{ mb: 2, display: "flex", alignItems: "center" }}>
            <MdErrorOutline size={20} style={{ marginRight: 8 }} />
            {error}
          </Alert>
        )}
        {successMessage && (
          <Alert severity="success" sx={{ mb: 2, display: "flex", alignItems: "center" }}>
            <MdCheckCircle size={20} style={{ marginRight: 8 }} />
            {successMessage}
          </Alert>
        )}

        <Box component="form" onSubmit={handleSubmit} sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
          <FormControl fullWidth>
            <InputLabel>Select Course</InputLabel>
            <Select value={selectedCourse} onChange={(e) => setSelectedCourse(e.target.value)}>
              <MenuItem value="">-- Select Course --</MenuItem>
              {courses.map((course) => (
                <MenuItem key={course.courseId} value={course.courseId}>
                  {course.courseName}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <TextField
            label="Order Status"
            value={orderStatus}
            onChange={(e) => setOrderStatus(e.target.value)}
            fullWidth
            required
          />

          <TextField
            label="Order Total Amount"
            type="number"
            value={orderTotalAmount}
            onChange={(e) => setOrderTotalAmount(e.target.value)}
            fullWidth
            required
          />

          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{
              bgcolor: "#800000",
              color: "#FFF",
              "&:hover": { bgcolor: "#5C0000" },
            }}
          >
            <MdAddShoppingCart size={20} style={{ marginRight: 8 }} />
            Create Order
          </Button>
        </Box>
      </Container>
    </Box>
  );
};

export default AddOrder;
