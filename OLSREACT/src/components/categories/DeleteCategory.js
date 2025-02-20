import React, { useEffect, useState, useContext } from "react";
import {
  Container,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  Typography,
  Box,
} from "@mui/material";
import { toast } from "react-toastify";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import { MdDelete, MdArrowBack, MdCategory } from "react-icons/md";

const DeleteCategory = () => {
  const [categories, setCategories] = useState([]);
  const { authState } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const response = await axios.get("http://localhost:8082/auth/admin/api/category", {
        headers: {
          Authorization: `Bearer ${authState.token}`,
        },
      });
      setCategories(response.data);
    } catch (error) {
      toast.error("Error fetching categories");
      console.error("Error fetching categories:", error);
    }
  };

  const handleDeleteCategory = async (categoryId) => {
    try {
      await axios.delete(`http://localhost:8082/auth/admin/api/category/${categoryId}`, {
        headers: {
          Authorization: `Bearer ${authState.token}`,
        },
      });
      toast.success("Category deleted successfully!");
      fetchCategories();
    } catch (error) {
      toast.error("Error deleting category");
      console.error("Error deleting category:", error);
    }
  };

  const handleBackToAdminDashboard = () => {
    navigate("/AdminDashboard");
  };

  return (
    <Container
      maxWidth="md"
      sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh", // Centers vertically
      }}
    >
      <Paper sx={{ p: 3, boxShadow: 3, width: "100%", borderLeft: "5px solid #d32f2f" }}>
        <Box display="flex" alignItems="center" justifyContent="center" mb={2}>
          <MdCategory size={28} color="#d32f2f" />
          <Typography variant="h5" sx={{ ml: 1 }}>
            Delete Category
          </Typography>
        </Box>

        <TableContainer component={Paper} sx={{ mt: 2 }}>
          <Table>
            <TableHead sx={{ backgroundColor: "#f5f5f5" }}>
              <TableRow>
                <TableCell>#</TableCell>
                <TableCell>Category ID</TableCell>
                <TableCell>Category Name</TableCell>
                <TableCell>Action</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {categories.length > 0 ? (
                categories.map((category, index) => (
                  <TableRow key={category.categoryId}>
                    <TableCell>{index + 1}</TableCell>
                    <TableCell>{category.categoryId}</TableCell>
                    <TableCell>{category.categoryName}</TableCell>
                    <TableCell>
                      <Button
                        variant="contained"
                        color="error"
                        startIcon={<MdDelete />}
                        onClick={() => handleDeleteCategory(category.categoryId)}
                      >
                        Delete
                      </Button>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={4} align="center">
                    No categories found
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>

        <Button
          variant="contained"
          color="secondary"
          fullWidth
          startIcon={<MdArrowBack />}
          sx={{ mt: 2 }}
          onClick={handleBackToAdminDashboard}
        >
          Back to Admin Dashboard
        </Button>
      </Paper>
    </Container>
  );
};

export default DeleteCategory;
