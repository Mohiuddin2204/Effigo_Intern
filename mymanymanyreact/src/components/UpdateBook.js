import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify"; 
import { Form, Button, Spinner } from "react-bootstrap";

const UpdateBook = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState({ name: "", authorIds: [], publisherId: "" });
  const [authors, setAuthors] = useState([]);
  const [publishers, setPublishers] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadBook();
    loadAuthorsAndPublishers();
  }, []);

  const loadBook = async () => {
    setLoading(true);
    try {
      const result = await axios.get(`http://localhost:2222/books/${id}`);
      setBook({ ...result.data, authorIds: result.data.authorIds || [] });
    } catch (error) {
      toast.error("Error loading book details");
    } finally {
      setLoading(false);
    }
  };

  const loadAuthorsAndPublishers = async () => {
    setLoading(true);
    try {
      const [authorsRes, publishersRes] = await Promise.all([
        axios.get("http://localhost:2222/authors"),
        axios.get("http://localhost:2222/publishers"),
      ]);
      setAuthors(authorsRes.data);
      setPublishers(publishersRes.data);
    } catch (error) {
      toast.error("Error loading authors or publishers");
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const handleAuthorChange = (e) => {
    const { value, checked } = e.target;

    setBook((prevBook) => {
      // Ensure authorIds is always an array
      const updatedAuthors = checked
        ? [...prevBook.authorIds, value]
        : prevBook.authorIds.filter((authorId) => authorId !== value);

      return { ...prevBook, authorIds: updatedAuthors };
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const updatedBook = {
      ...book,
      authors: book.authorIds.map((id) => ({ authorId: id })),
      publisher: { id: book.publisherId },
    };
    try {
      await axios.put(`http://localhost:2222/books/${id}`, updatedBook);
      toast.success("Book updated successfully");
      navigate("/books");
    } catch (error) {
      toast.error("Error updating book");
    }
  };

  return (
    <div>
      <h2>Update Book</h2>
      {loading ? (
        <Spinner animation="border" />
      ) : (
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Book Name</Form.Label>
            <Form.Control
              type="text"
              name="name"
              value={book.name}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Authors</Form.Label>
            {authors.map((author) => (
              <Form.Check
                key={author.authorId}
                type="checkbox"
                label={author.authorName}
                value={author.authorId}
                checked={book.authorIds.includes(author.authorId)} // Ensure checkbox is checked if authorId is in authorIds
                onChange={handleAuthorChange}
              />
            ))}
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Publisher</Form.Label>
            <Form.Select
              name="publisherId"
              value={book.publisherId}
              onChange={handleChange}
              required
            >
              <option value="">Select Publisher</option>
              {publishers.map((publisher) => (
                <option key={publisher.id} value={publisher.id}>
                  {publisher.publisherName}
                </option>
              ))}
            </Form.Select>
          </Form.Group>

          <Button type="submit" variant="warning">
            Update Book
          </Button>
        </Form>
      )}
    </div>
  );
};

export default UpdateBook;
