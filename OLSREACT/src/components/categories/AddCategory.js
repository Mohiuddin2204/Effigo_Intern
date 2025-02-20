import React, { useState, useContext } from "react";
import { Container, TextField, Button, Card, CardContent, Typography, Box } from "@mui/material";
import { toast } from "react-toastify";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import { MdAddCircle, MdCategory } from "react-icons/md";

const AddCategory = () => {
  const [categoryName, setCategoryName] = useState("");
  const navigate = useNavigate();
  const { authState } = useContext(AuthContext);

  const handleAddCategory = async () => {
    if (!categoryName.trim()) {
      toast.warning("Category name cannot be empty!");
      return;
    }

    try {
      await axios.post(
        "http://localhost:8082/auth/admin/api/category",
        { categoryName },
        {
          headers: {
            Authorization: `Bearer ${authState.token}`,
          },
        }
      );
      toast.success("Category added successfully!");
      navigate("/admin/categories");
    } catch (error) {
      toast.error("Error adding category");
      console.error("Error adding category:", error);
    }
  };

  return (
    <Container
      maxWidth="sm"
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh", // Ensures vertical centering
      }}
    >
      <Card sx={{ width: "100%", boxShadow: 3, borderLeft: "5px solid #1976d2", p: 2 }}>
        <CardContent>
          <Box display="flex" alignItems="center" justifyContent="center" mb={2}>
            <MdCategory size={28} color="#1976d2" />
            <Typography variant="h5" sx={{ ml: 1 }}>
              Add Category
            </Typography>
          </Box>

          <TextField
            fullWidth
            label="Category Name"
            variant="outlined"
            value={categoryName}
            onChange={(e) => setCategoryName(e.target.value)}
            placeholder="Enter category name"
            sx={{ mb: 3 }}
          />

          <Button
            variant="contained"
            color="primary"
            fullWidth
            startIcon={<MdAddCircle />}
            onClick={handleAddCategory}
          >
            Add Category
          </Button>
        </CardContent>
      </Card>
    </Container>
  );
};

export default AddCategory;
