import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import { Table, Button } from "react-bootstrap";

const BookList = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    loadBooks();
  }, []);

  const loadBooks = async () => {
    try {
      const result = await axios.get("http://localhost:2222/books");
      setBooks(result.data);
    } catch (error) {
      toast.error("Error loading books");
    }
  };

  const deleteBook = async (id) => {
    if (window.confirm("Are you sure you want to delete this book?")) {
      try {
        await axios.delete(`http://localhost:2222/books/${id}`);
        toast.success("Book deleted successfully");
        loadBooks();
      } catch (error) {
        toast.error("Error deleting book");
      }
    }
  };

  const downloadBookInfo = (book) => {
    const authors = book.authors && book.authors.length > 0
      ? book.authors.map((author) => author.authorName).join(", ")
      : "No authors available";
      
    const publisher = book.publisher ? book.publisher.publisherName : "No publisher assigned";

    const content = `Author(s): ${authors}\nPublisher: ${publisher}`;
    const blob = new Blob([content], { type: "text/plain" });
    const url = window.URL.createObjectURL(blob);

    const link = document.createElement("a");
    link.href = url;
    link.download = `${book.name}_details.txt`;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
  };

  return (
    <div>
      <h2>Books List</h2>
      <Link to="/books/add" className="btn btn-success mb-3">
        Add Book
      </Link>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Authors</th>
            <th>Publisher</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.id}</td>
              <td>{book.name}</td>
              <td>
                {book.authors && book.authors.length > 0
                  ? book.authors.map((author) => author.authorName).join(", ")
                  : "No authors available"}
              </td>
              <td>
                {book.publisher ? book.publisher.publisherName : "No publisher assigned"}
              </td>
              <td>
                <Link to={`/books/update/${book.id}`} className="btn btn-warning mx-2">
                  Edit
                </Link>
                <Button variant="danger" onClick={() => deleteBook(book.id)}>
                  Delete
                </Button>
                <Button variant="info" className="mx-2" onClick={() => downloadBookInfo(book)}>
                  Download
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default BookList;
