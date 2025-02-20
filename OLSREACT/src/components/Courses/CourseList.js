import React, { useState, useEffect, useContext } from "react";
import { Container, Grid, Card, CardContent, Typography, Button } from "@mui/material";
import { MdDelete, MdAddCircleOutline } from "react-icons/md";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";

const CourseList = () => {
  const [courses, setCourses] = useState([]);
  const { authState } = useContext(AuthContext);

  useEffect(() => {
    if (authState.token) {
      axios
        .get("http://localhost:8082/auth/admin/api/courses", {
          headers: {
            Authorization: `Bearer ${authState.token}`,
          },
        })
        .then((response) => {
          setCourses(response.data);
        })
        .catch((error) => {
          toast.error("Error fetching courses");
          console.error("Error fetching courses!", error);
        });
    } else {
      toast.error("No token found. Please log in.");
    }
  }, [authState.token]);

  const handleDelete = (courseId) => {
    if (window.confirm("Are you sure you want to delete this course?")) {
      axios
        .delete(`http://localhost:8082/auth/admin/api/courses/${courseId}`, {
          headers: {
            Authorization: `Bearer ${authState.token}`,
          },
        })
        .then(() => {
          toast.success("Course deleted successfully!");
          setCourses(courses.filter((course) => course.courseId !== courseId));
        })
        .catch((error) => {
          toast.error("Error deleting course");
          console.error("Error deleting course!", error);
        });
    }
  };

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" align="center" gutterBottom>
        Manage Courses
      </Typography>

      <Grid container spacing={3}>
        {courses.map((course) => (
          <Grid item xs={12} sm={6} md={4} key={course.courseId}>
            <Card sx={{ boxShadow: 3, p: 2 }}>
              <CardContent>
                <Typography variant="h6" fontWeight="bold">
                  {course.courseName}
                </Typography>
                <Typography variant="body2" color="textSecondary" sx={{ mt: 1 }}>
                  {course.courseDescription}
                </Typography>
                <Typography variant="body2" sx={{ mt: 1 }}>
                  <strong>Price:</strong> ${course.coursePrice}
                </Typography>
                <Typography variant="body2" sx={{ mt: 1 }}>
                  <strong>Category:</strong> {course.category ? course.category.categoryName : "N/A"}
                </Typography>

                <Button
                  variant="contained"
                  color="error"
                  startIcon={<MdDelete />}
                  sx={{ mt: 2 }}
                  onClick={() => handleDelete(course.courseId)}
                >
                  Delete
                </Button>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>

      {/* Add Course Button */}
      <Grid container justifyContent="center" sx={{ mt: 4 }}>
        <Link to="/admin/courses/add" style={{ textDecoration: "none" }}>
          <Button variant="contained" color="primary" startIcon={<MdAddCircleOutline />}>
            Add Course
          </Button>
        </Link>
      </Grid>
    </Container>
  );
};

export default CourseList;
