import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { Form, Button } from "react-bootstrap";

const AddBook = () => {
  const navigate = useNavigate();
  const [book, setBook] = useState({ name: "", authorIds: [], publisherId: "" });
  const [authors, setAuthors] = useState([]);
  const [publishers, setPublishers] = useState([]);

  useEffect(() => {
    loadAuthorsAndPublishers();
  }, []);

  const loadAuthorsAndPublishers = async () => {
    try {
      const [authorsRes, publishersRes] = await Promise.all([
        axios.get("http://localhost:2222/authors"),
        axios.get("http://localhost:2222/publishers"),
      ]);
      setAuthors(authorsRes.data);
      setPublishers(publishersRes.data);
    } catch (error) {
      toast.error("Error loading authors or publishers");
    }
  };

  const handleChange = (e) => {
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const handleAuthorChange = (e) => {
    const { value, checked } = e.target;
    setBook((prevBook) => {
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
      publisher: { id: book.publisherId }, // Add publisher ID to the book object
    };
    try {
      await axios.post("http://localhost:2222/books", updatedBook);
      toast.success("Book added successfully");
      navigate("/books");
    } catch (error) {
      toast.error("Error adding book");
    }
  };

  return (
    <div>
      <h2>Add Book</h2>
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

        <Button type="submit" variant="success">
          Add Book
        </Button>
      </Form>
    </div>
  );
};

export default AddBook;
