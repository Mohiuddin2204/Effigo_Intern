import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { toast } from "react-toastify";
import { useNavigate, Link } from "react-router-dom";
import { 
  Container, Grid, Card, CardContent, Typography, Button, Box 
} from "@mui/material";
import { MdCategory, MdRefresh, MdDashboard } from "react-icons/md";

const CategoryList = () => {
  const [categories, setCategories] = useState([]);
  const { authState } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const response = await axios.get("http://localhost:8082/auth/admin/api/category", {
        headers: { Authorization: `Bearer ${authState.token}` },
      });
      setCategories(response.data);
    } catch (error) {
      toast.error("Error fetching categories");
      console.error("Error fetching categories:", error);
    }
  };

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" align="center" gutterBottom>
        All Categories
      </Typography>

      <Grid container spacing={3}>
        {categories.length > 0 ? (
          categories.map((category) => (
            <Grid item xs={12} sm={6} md={4} key={category.categoryId}>
              <Card sx={{ borderLeft: "5px solid #1976d2", boxShadow: 3 }}>
                <CardContent>
                  <Typography variant="h6" gutterBottom>
                    <MdCategory size={22} /> {category.categoryName}
                  </Typography>
                  <Typography>
                    <strong>ID:</strong> {category.categoryId}
                  </Typography>
                  <Typography>
                    <strong>Courses:</strong>
                  </Typography>
                  {category.courses.length > 0 ? (
                    category.courses.map((course) => (
                      <Typography key={course.courseId} sx={{ ml: 2 }}>
                        - {course.courseName} (${course.coursePrice})
                      </Typography>
                    ))
                  ) : (
                    <Typography sx={{ color: "gray", ml: 2 }}>No courses available</Typography>
                  )}
                </CardContent>
              </Card>
            </Grid>
          ))
        ) : (
          <Typography variant="body1" align="center" sx={{ width: "100%", color: "gray", mt: 2 }}>
            No categories found.
          </Typography>
        )}
      </Grid>

      {/* Refresh & Dashboard Buttons */}
      <Box display="flex" justifyContent="center" mt={3} gap={2}>
        <Button variant="contained" color="primary" startIcon={<MdRefresh />} onClick={fetchCategories}>
          Refresh Categories
        </Button>
        <Button variant="contained" color="secondary" component={Link} to="/AdminDashboard" startIcon={<MdDashboard />}>
          Return to Admin Dashboard
        </Button>
      </Box>
    </Container>
  );
};

export default CategoryList;
