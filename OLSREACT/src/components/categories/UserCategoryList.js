import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import { Container, Box, Card, CardContent, Typography, Stack, Divider } from "@mui/material";
import { MdCategory } from "react-icons/md";
import { AuthContext } from "../../context/AuthContext";
import { toast } from "react-toastify";

const UserCategoryList = () => {
  const [categories, setCategories] = useState([]);
  const { authState } = useContext(AuthContext);

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const token = localStorage.getItem("jwtToken");
      if (!token) {
        throw new Error("Token missing");
      }

      const response = await axios.get("http://localhost:8082/auth/user/api/category", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setCategories(response.data);
    } catch (error) {
      toast.error("Error fetching categories");
      console.error("Error fetching categories:", error);
    }
  };

  // Card Styling
  const cardStyle = {
    background: "linear-gradient(to right, #ff7e5f, #feb47b)", // Warm Gradient
    color: "white",
    boxShadow: "0px 5px 15px rgba(0, 0, 0, 0.3)",
    borderRadius: "12px",
    transition: "transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out",
    "&:hover": {
      transform: "scale(1.05)",
      boxShadow: "0px 8px 20px rgba(0, 0, 0, 0.4)",
    },
    padding: "16px",
    minWidth: "280px",
  };

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" align="center" gutterBottom sx={{ fontWeight: "bold", color: "#333" }}>
        📂 Available Categories
      </Typography>

      {/* Category List in a Responsive Layout */}
      <Stack direction="row" flexWrap="wrap" justifyContent="center" gap={3} sx={{ mt: 3 }}>
        {categories.length > 0 ? (
          categories.map((category) => (
            <Box key={category.categoryId} sx={{ flex: "1 1 300px", maxWidth: "400px" }}>
              <Card sx={cardStyle}>
                <CardContent>
                  <Typography variant="h6" fontWeight="bold" sx={{ display: "flex", alignItems: "center" }}>
                    <MdCategory size={28} style={{ marginRight: 8 }} />
                    {category.categoryName}
                  </Typography>

                  <Typography variant="body2" sx={{ mt: 1, fontSize: "14px", opacity: 0.9 }}>
                    Category ID: {category.categoryId}
                  </Typography>

                  <Divider sx={{ my: 1, bgcolor: "rgba(255, 255, 255, 0.6)" }} />

                  <Typography variant="body2" fontWeight="bold">
                    Courses:
                  </Typography>
                  {category.courses.length > 0 ? (
                    category.courses.map((course) => (
                      <Typography key={course.courseId} variant="body2" sx={{ mt: 1, opacity: 0.9 }}>
                        • <strong>{course.courseName}</strong> - {course.courseDescription} (${course.coursePrice})
                      </Typography>
                    ))
                  ) : (
                    <Typography variant="body2" sx={{ opacity: 0.7, mt: 1 }}>
                      No courses available
                    </Typography>
                  )}
                </CardContent>
              </Card>
            </Box>
          ))
        ) : (
          <Typography variant="h6" align="center" color="textSecondary">
            No categories found
          </Typography>
        )}
      </Stack>
    </Container>
  );
};

export default UserCategoryList;
