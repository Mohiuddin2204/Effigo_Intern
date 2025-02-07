import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { Form, Button } from "react-bootstrap";

const AddAuthor = () => {
  const navigate = useNavigate();
  const [author, setAuthor] = useState({ authorName: "" });

  const handleChange = (e) => {
    setAuthor({ ...author, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:2222/authors", author);
      toast.success("Author added successfully");
      navigate("/authors");
    } catch (error) {
      toast.error("Error adding author");
    }
  };

  return (
    <div>
      <h2>Add Author</h2>
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
        <Button type="submit" variant="success">
          Add Author
        </Button>
      </Form>
    </div>
  );
};

export default AddAuthor;
