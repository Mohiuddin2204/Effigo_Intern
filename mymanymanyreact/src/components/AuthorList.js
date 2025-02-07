import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { Table, Button } from "react-bootstrap";

const AuthorList = () => {
  const [authors, setAuthors] = useState([]);

  useEffect(() => {
    loadAuthors();
  }, []);

  const loadAuthors = async () => {
    try {
      const result = await axios.get("http://localhost:2222/authors");
      setAuthors(result.data);
    } catch (error) {
      toast.error("Error loading authors");
    }
  };

  const deleteAuthor = async (id) => {
    if (window.confirm("Are you sure you want to delete this author?")) {
      try {
        await axios.delete(`http://localhost:2222/authors/${id}`);
        toast.success("Author deleted successfully");
        loadAuthors();
      } catch (error) {
        toast.error("Error deleting author");
      }
    }
  };

  return (
    <div>
      <h2>Authors List</h2>
      <Link to="/authors/add" className="btn btn-success mb-3">
        Add Author
      </Link>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {authors.map((author) => (
            <tr key={author.authorId}>
              <td>{author.authorId}</td>
              <td>{author.authorName}</td>
              <td>
                <Link to={`/authors/update/${author.authorId}`} className="btn btn-warning mx-2">
                  Edit
                </Link>
                <Button variant="danger" onClick={() => deleteAuthor(author.authorId)}>
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default AuthorList;

