import React, { useState, useEffect, useContext } from "react";
import { Container, Box, Card, CardContent, Typography, Stack } from "@mui/material";
import { MdOutlineLibraryBooks } from "react-icons/md";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { toast } from "react-toastify";

const UserCourseList = () => {
  const [courses, setCourses] = useState([]);
  const { authState } = useContext(AuthContext); // Get the authenticated user from AuthContext

  useEffect(() => {
    if (authState.token) {
      axios
        .get("http://localhost:8082/auth/user/api/courses", {
          headers: {
            Authorization: `Bearer ${authState.token}`,
          },
        })
        .then((response) => {
          setCourses(response.data); // Set courses for the user
        })
        .catch((error) => {
          toast.error("Error fetching courses");
          console.error("Error fetching courses:", error);
        });
    } else {
      toast.error("No token found. Please log in.");
    }
  }, [authState.token]);

  // Uniform card style for all courses
  const cardStyle = {
    background: "linear-gradient(to right, #4A90E2, #50E3C2)", // Blue to Green Gradient
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
      <Typography
        variant="h4"
        align="center"
        gutterBottom
        sx={{ fontWeight: "bold", color: "#333" }}
      >
        📚 Available Courses
      </Typography>

      {/* Course List in a Responsive Layout */}
      <Stack 
        direction="row" 
        flexWrap="wrap" 
        justifyContent="center" 
        gap={3} 
        sx={{ mt: 3 }}
      >
        {courses.length > 0 ? (
          courses.map((course) => (
            <Box key={course.courseId} sx={{ flex: "1 1 300px", maxWidth: "400px" }}>
              <Card sx={cardStyle}>
                <CardContent>
                  <Typography
                    variant="h6"
                    fontWeight="bold"
                    sx={{ display: "flex", alignItems: "center" }}
                  >
                    <MdOutlineLibraryBooks
                      size={28}
                      style={{ marginRight: 8 }}
                    />
                    {course.courseName}
                  </Typography>
                  <Typography
                    variant="body2"
                    sx={{ mt: 1, fontSize: "14px", opacity: 0.9 }}
                  >
                    {course.courseDescription}
                  </Typography>
                  <Typography
                    variant="body2"
                    sx={{ mt: 1, fontWeight: "bold" }}
                  >
                    Course ID:{" "}
                    <span style={{ opacity: 0.9 }}>{course.courseId}</span>
                  </Typography>
                  <Typography
                    variant="body2"
                    sx={{ mt: 1, fontWeight: "bold" }}
                  >
                    Price:{" "}
                    <span style={{ opacity: 0.9 }}>${course.coursePrice}</span>
                  </Typography>
                </CardContent>
              </Card>
            </Box>
          ))
        ) : (
          <Typography variant="h6" align="center" color="textSecondary">
            No courses available
          </Typography>
        )}
      </Stack>
    </Container>
  );
};

export default UserCourseList;

