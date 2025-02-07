import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { Table, Button } from "react-bootstrap";

const PublisherList = () => {
  const [publishers, setPublishers] = useState([]);

  useEffect(() => {
    loadPublishers();
  }, []);

  const loadPublishers = async () => {
    const result = await axios.get("http://localhost:2222/publishers");
    // Filter out the "No publisher assigned" publisher from the list
    const filteredPublishers = result.data.filter(publisher => publisher.publisherName !== "No publisher assigned");
    setPublishers(filteredPublishers);
  };

  const deletePublisher = async (id) => {
    if (window.confirm("Are you sure you want to delete this publisher?")) {
      try {
        await axios.delete(`http://localhost:2222/publishers/${id}`);
        toast.success("Publisher deleted successfully");
        loadPublishers();
      } catch (error) {
        toast.error("Error deleting publisher");
      }
    }
  };

  return (
    <div>
      <h2>Publishers List</h2>
      
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {publishers.map((publisher) => (
            <tr key={publisher.id}>
              <td>{publisher.id}</td>
              <td>{publisher.publisherName}</td>
              <td>
                <Link to={`/publishers/update/${publisher.id}`} className="btn btn-warning mx-2">Edit</Link>
                <Button variant="danger" onClick={() => deletePublisher(publisher.id)}>Delete</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      <Link to="/publishers/add" className="btn btn-success mb-3">Add Publisher</Link>
    </div>
  );
};

export default PublisherList;
