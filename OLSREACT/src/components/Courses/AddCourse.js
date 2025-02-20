import React, { useState, useEffect, useContext } from "react";
import { Container, Row, Col, Form, FormGroup, Label, Input, Button, Spinner } from "reactstrap";
import axios from "axios";
import { AuthContext } from "../../context/AuthContext";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const AddCourse = () => {
  const [courseName, setCourseName] = useState("");
  const [courseDescription, setCourseDescription] = useState("");
  const [coursePrice, setCoursePrice] = useState("");
  const [category, setCategory] = useState("");  // For the selected category
  const [categories, setCategories] = useState([]);  // For the list of categories
  const [loading, setLoading] = useState(false); // Loading state for categories and form submission
  const { authState } = useContext(AuthContext); // Access token from context
  const navigate = useNavigate(); // Navigate for redirection

  // Fetch categories when the component mounts
  useEffect(() => {
    if (authState.token) {
      setLoading(true);
      axios
        .get("http://localhost:8082/auth/admin/api/category", {  // Updated API endpoint
          headers: {
            Authorization: `Bearer ${authState.token}`,
          },
        })
        .then((response) => {
          setCategories(response.data);
          setLoading(false);
        })
        .catch((error) => {
          setLoading(false);
          toast.error("Error fetching categories");
          console.error("Error fetching categories!", error);
        });
    } else {
      toast.error("You are not authenticated!");
    }
  }, [authState.token]);

  // Handle form submission to add a course
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!category) {
      toast.error("Please select a category");
      return;
    }

    const newCourse = {
      courseName,
      courseDescription,
      coursePrice: parseFloat(coursePrice),
      category: { categoryId: parseInt(category) },  // Include the category in the course data
    };

    if (authState.token) {
      setLoading(true);
      axios
        .post("http://localhost:8082/auth/admin/api/courses", newCourse, {  // Updated API endpoint
          headers: {
            Authorization: `Bearer ${authState.token}`,
          },
        })
        .then(() => {
          setLoading(false);
          toast.success("Course added successfully!");
          navigate("/admin/courses"); // Redirect to course list after adding
        })
        .catch((error) => {
          setLoading(false);
          if (error.response && error.response.status === 403) {
            toast.error("You do not have permission to add a course.");
          } else {
            toast.error("Error adding course");
          }
          console.error("Error adding course!", error);
        });
    } else {
      toast.error("You are not authenticated!");
    }
  };

  return (
    <div>
      <Container className="mt-5">
        <Row>
          <Col md={12} className="text-center">
            <h2>Add New Course</h2>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col md={12}>
            <Form onSubmit={handleSubmit}>
              <FormGroup>
                <Label for="courseName">Course Name</Label>
                <Input
                  type="text"
                  name="courseName"
                  id="courseName"
                  value={courseName}
                  onChange={(e) => setCourseName(e.target.value)}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="courseDescription">Course Description</Label>
                <Input
                  type="text"
                  name="courseDescription"
                  id="courseDescription"
                  value={courseDescription}
                  onChange={(e) => setCourseDescription(e.target.value)}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="coursePrice">Course Price</Label>
                <Input
                  type="number"
                  name="coursePrice"
                  id="coursePrice"
                  value={coursePrice}
                  onChange={(e) => setCoursePrice(e.target.value)}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="category">Category</Label>
                <Input
                  type="select"
                  name="category"
                  id="category"
                  value={category}
                  onChange={(e) => setCategory(e.target.value)} // Update category on change
                  required
                >
                  <option value="">Select a Category</option>
                  {categories.map((category) => (
                    <option key={category.categoryId} value={category.categoryId}>
                      {category.categoryName}
                    </option>
                  ))}
                </Input>
              </FormGroup>
              <Button type="submit" color="primary" disabled={loading}>
                {loading ? <Spinner size="sm" /> : "Add Course"}
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default AddCourse;
