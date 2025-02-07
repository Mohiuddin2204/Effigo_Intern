import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';
import { Form, Button } from 'react-bootstrap';

const UpdatePublisher = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [publisher, setPublisher] = useState({ publisherName: "" });

  useEffect(() => {
    loadPublisher();
  }, []);

  const loadPublisher = async () => {
    try {
      const result = await axios.get(`http://localhost:2222/publishers/${id}`);
      setPublisher(result.data);
    } catch (error) {
      toast.error("Error loading publisher details");
    }
  };

  const handleChange = (e) => {
    setPublisher({ ...publisher, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:2222/publishers/${id}`, publisher);
      toast.success("Publisher updated successfully");
      navigate("/publishers");
    } catch (error) {
      toast.error("Error updating publisher");
    }
  };

  return (
    <div>
      <h2>Update Publisher</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Publisher Name</Form.Label>
          <Form.Control 
            type="text" 
            name="publisherName" 
            value={publisher.publisherName} 
            onChange={handleChange} 
            required 
          />
        </Form.Group>
        <Button type="submit" variant="warning">Update Publisher</Button>
      </Form>
    </div>
  );
};

export default UpdatePublisher;
