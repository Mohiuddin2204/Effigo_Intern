import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';
import { Form, Button } from 'react-bootstrap';

const AddPublisher = () => {
  const navigate = useNavigate();
  const [publisher, setPublisher] = useState({ publisherName: "" });
  const [loading, setLoading] = useState(false); // Add loading state to handle async actions

  const handleChange = (e) => {
    setPublisher({ ...publisher, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (loading) return; // Prevent multiple submissions if it's already in progress
    setLoading(true); // Set loading state to true when starting request

    try {
      await axios.post("http://localhost:2222/publishers", publisher);
      toast.success("Publisher added successfully");
      navigate("/publishers");
    } catch (error) {
      toast.error("Error adding publisher");
      console.error("Error adding publisher:", error); // Log the error for debugging
    } finally {
      setLoading(false); // Reset loading state after request completes
    }
  };

  return (
    <div>
      <h2>Add Publisher</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Publisher Name</Form.Label>
          <Form.Control
            type="text"
            name="publisherName"
            value={publisher.publisherName}
            onChange={handleChange}
            required
            placeholder="Enter publisher name" // Placeholder for better UX
          />
        </Form.Group>
        <Button
          type="submit"
          variant="success"
          disabled={loading} // Disable button when loading
        >
          {loading ? "Adding..." : "Add Publisher"}
        </Button>
      </Form>
    </div>
  );
};

export default AddPublisher;

