import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { Form, Button } from "react-bootstrap";

const UpdateAuthor = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [author, setAuthor] = useState({ authorName: "" });

  useEffect(() => {
    loadAuthor();
  }, []);

  const loadAuthor = async () => {
    try {
      const result = await axios.get(`http://localhost:2222/authors/${id}`);
      setAuthor(result.data);
    } catch (error) {
      toast.error("Error loading author details");
    }
  };

  const handleChange = (e) => {
    setAuthor({ ...author, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:2222/authors/${id}`, author);
      toast.success("Author updated successfully");
      navigate("/authors");
    } catch (error) {
      toast.error("Error updating author");
    }
  };

  return (
    <div>
      <h2>Update Author</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Author Name</Form.Label>
          <Form.Control
            type="text"
            name="authorName"
            value={author.authorName}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Button type="submit" variant="warning">
          Update Author
        </Button>
      </Form>
    </div>
  );
};

export default UpdateAuthor;
